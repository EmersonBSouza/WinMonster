package br.uefs.WinMonster.controller;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;

import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;
import br.uefs.WinMonster.exceptions.ArquivoVazioException;
import br.uefs.WinMonster.model.Compactador;
import br.uefs.WinMonster.model.Descompactador;
import br.uefs.WinMonster.util.*;

public class WinController {

	/**
	 * Este m�todo � respons�vel por gerenciar o processo da compacta��o dos arquivos
	 * @param File arquivoOriginal
	 * */
	public void compactarArquivo(File arquivoOriginal) throws ArquivoVazioException, IOException{
		Arquivo arquivo = new Arquivo();
		String texto = arquivo.lerTexto(arquivoOriginal);
		Compactador compactador = new Compactador();
		Fila fila = new Fila();
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();

		char[] caracteres = texto.toCharArray();//Transforma o texto em um array

		Lista listaDeFrequencias = new Lista();
		NoArvore atual = null;
		Iterador iterador;
		boolean estaNaLista;
		for(int i=0;i<caracteres.length;i++) {//Ser� executado at� o fim do texto
			estaNaLista = false;
			iterador = listaDeFrequencias.iterador();
			while(iterador.temProximo()) {//Este la�o verifica se j� existe uma letra na lista de frequ�ncias
				atual = (NoArvore) iterador.obterProximo();
				if(atual.getLetra() == caracteres[i]) {//Compara os caracteres
					atual.setFrequencia(atual.getFrequencia()+1);//Se forem iguais, a frequ�ncia � incrementada
					estaNaLista = true;
					break;
				}
			}
			if(!estaNaLista) {//Se a letra n�o estiver na lista, uma nova c�lula � criada e adicionada � lista
				atual = new NoArvore();
				atual.setLetra(caracteres[i]);
				atual.setFrequencia(1);
				listaDeFrequencias.inserirInicio(atual);
			}
		}
		
		if(listaDeFrequencias.obterTamanho()==0){//Se a lista estiver vazia, retorna uma exce��o
			throw new ArquivoVazioException();
		}
		
		iterador = listaDeFrequencias.iterador();
		while(iterador.temProximo()) {//Neste la�o os objetos da lista s�o inseridos na fila de prioridade
			atual = (NoArvore) iterador.obterProximo();
			ArvoreHuffman arvore = new ArvoreHuffman();
			arvore.setRaiz(atual);
			fila.inserirComPrioridade(arvore);
		}

		int quantidadeDeCaracteres = fila.obterTamanho();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.setDicionario(arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer(), new String[quantidadeDeCaracteres][2]));
		Lista lista = new Lista();
		StringBuilder textoCodificado = new StringBuilder();

		for(int i=0;i < texto.length();i+=30000){//Neste la�o o texto � processado de forma dividida
			if(i+30000>texto.length()){
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, texto.length())));
				lista.inserirFinal(textoCodificado.toString());//Insere no final da lista
				textoCodificado.delete(0, textoCodificado.length());//Limpa o conte�do da string
			}
			else{
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, i+30000)));
				lista.inserirFinal(textoCodificado.toString());//Insere no final da lista
				textoCodificado.delete(0, textoCodificado.length());//Limpa o conte�do da string
			}
		}
		iterador = lista.iterador();
		StringBuilder textoFinal = new StringBuilder();
		String auxiliar;
		while(iterador.temProximo()){//Neste la�o o texto armazenado na lista � concatenado
			textoFinal.append(iterador.obterProximo());
		}
		BitSet conjuntoBits = new BitSet();
		conjuntoBits = compactador.transformarEmBits(textoFinal.toString());//Recebe um BitSet com o texto convertido em bits
		int hash = compactador.gerarHash(texto);//Chama o m�todo respons�vel por gerar o c�digo Hash
		arquivo.salvarBytes(conjuntoBits,hash ,arvoreFinal, arquivoOriginal);//Salva os dados
	}
	/**
	 * Este m�todo � repons�vel por gerenciar o processo de descompacta��o do arquivo
	 * @param File arquivoOriginal*/
	public void descompactarArquivo(File arquivoOriginal) throws ArquivoCorrompidoException,NullPointerException, IOException{
		Arquivo arquivo = new Arquivo();
		BitSet conjuntoBits = new BitSet();
		ArvoreHuffman arvore = new ArvoreHuffman();
		Descompactador descompactador = new Descompactador();
		int hash;

		conjuntoBits= (BitSet)arquivo.lerBytes(conjuntoBits,arquivoOriginal);//Recebe o conjunto de bits salvos no arquivo
		hash = arquivo.obterHash(arquivoOriginal);//Recebe o c�digo hash salvo no arquivo
		arvore = (ArvoreHuffman)arquivo.lerArvore(arvore, arquivoOriginal);//Recebe a �rvore de Huffman salva no arquivo
		
		
		String decodificada = new String();
		decodificada = descompactador.decodificaBits(conjuntoBits);//Recebe o texto decodificado
		
		StringBuffer textoOriginal = new StringBuffer();
		textoOriginal.append(arvore.decodificarMensagem(arvore.getRaiz(), decodificada.toString()));//Chama o m�todo respons�vel por decodificar a mensagem
		
		boolean estaIntegro = descompactador.verificarIntegridade(hash,textoOriginal.toString());
		if(estaIntegro){
			arquivo.salvarTexto(textoOriginal.toString(), arquivoOriginal);//Chama o m�todo respons�vel por verificar a integridade
		}
		else{
			throw new ArquivoCorrompidoException();//Lan�a a exce��o se o arquivo estiver corrompido
		}
			
	}
}
