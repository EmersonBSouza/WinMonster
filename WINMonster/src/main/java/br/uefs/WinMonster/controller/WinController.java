package br.uefs.WinMonster.controller;

import java.io.File;
import java.util.BitSet;

import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;
import br.uefs.WinMonster.model.Compactador;
import br.uefs.WinMonster.model.Descompactador;
import br.uefs.WinMonster.util.*;

public class WinController {

	public void compactarArquivo(String caminho,File arquivoOriginal){
		long tempoInicial = System.currentTimeMillis();
		Arquivo arquivo = new Arquivo();
		String texto = arquivo.lerTexto(caminho,arquivoOriginal);
		Compactador compactador = new Compactador();
		Fila fila = new Fila();
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();

		char[] caracteres = texto.toCharArray();

		Lista listaDeFrequencias = new Lista();
		NoArvore atual = null;
		Iterador iterador;
		boolean estaNaLista;
		for(int i=0;i<caracteres.length;i++) {
			estaNaLista = false;
			iterador = listaDeFrequencias.iterador();
			while(iterador.temProximo()) {
				atual = (NoArvore) iterador.obterProximo();
				if(atual.getLetra() == caracteres[i]) {
					atual.setFrequencia(atual.getFrequencia()+1);
					estaNaLista = true;
					break;
				}
			}
			if(!estaNaLista) {
				atual = new NoArvore();
				atual.setLetra(caracteres[i]);
				atual.setFrequencia(1);
				listaDeFrequencias.inserirInicio(atual);
			}
		}
		long tempoLista = System.currentTimeMillis();
		System.out.printf("Lista em :%.3f s%n", (tempoLista - tempoInicial) / 1000d);
		
		long tempoInicioArvore = System.currentTimeMillis();
		iterador = listaDeFrequencias.iterador();
		while(iterador.temProximo()) {
			atual = (NoArvore) iterador.obterProximo();
			ArvoreHuffman arvore = new ArvoreHuffman();
			arvore.setRaiz(atual);
			fila.inserirComPrioridade(arvore);
		}
		long tempoFimArvore = System.currentTimeMillis();
		System.out.printf("Arvore em :%.3f s%n", (tempoFimArvore - tempoInicioArvore) / 1000d);		

		/*int[] frequencias = new int[256];

		for(int i=0;i<caracteres.length;i++) {
			frequencias[caracteres[i]]++;
		}

		
		for(int i=0; i<256;i++){
			if(frequencias[i]!=0){
				NoArvore no = new NoArvore();
				no.setLetra((char) i);
				no.setFrequencia(frequencias[i]);
				ArvoreHuffman arvore = new ArvoreHuffman();
				arvore.setRaiz(no);
				fila.inserirComPrioridade(arvore);
			}
		}*/
		int quantidadeDeCaracteres = fila.obterTamanho();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.setDicionario(arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer(), new String[quantidadeDeCaracteres][2]));
		Lista lista = new Lista();
		StringBuilder textoCodificado = new StringBuilder();

		long tempoStrings = System.currentTimeMillis();
		for(int i=0;i < texto.length();i+=30000){
			if(i+30000>texto.length()){
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, texto.length())));
				lista.inserirFinal(textoCodificado.toString());
				textoCodificado.delete(0, textoCodificado.length());
			}
			else{
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, i+30000)));
				lista.inserirFinal(textoCodificado.toString());
				textoCodificado.delete(0, textoCodificado.length());
			}
		}
		long tempoFimString = System.currentTimeMillis();
		System.out.printf("Tempo Strings em :%.3f s%n", (tempoFimString - tempoStrings) / 1000d);
		iterador = lista.iterador();
		StringBuilder textoFinal = new StringBuilder();
		String auxiliar;
		while(iterador.temProximo()){
			textoFinal.append(iterador.obterProximo());
		}
		BitSet conjuntoBits = new BitSet();
		conjuntoBits = compactador.transformarEmBits(textoFinal.toString());
		int hash = compactador.gerarHash(texto);
		arquivo.salvarBytes(conjuntoBits,hash ,arvoreFinal, arquivoOriginal);
		long tempoTotal = System.currentTimeMillis();
		System.out.printf("Total Compactação em :%.3f s%n", (tempoTotal - tempoInicial) / 1000d);
		//arquivo.salvarBytes(textoFinal.toString(), arvoreFinal, arquivoOriginal);
	}

	public void descompactarArquivo(File arquivoOriginal) throws ArquivoCorrompidoException{
		long tempoInicial = System.currentTimeMillis();
		Arquivo arquivo = new Arquivo();
		BitSet conjuntoBits = new BitSet();
		ArvoreHuffman arvore = new ArvoreHuffman();
		Descompactador descompactador = new Descompactador();
		int hash;

		conjuntoBits= (BitSet)arquivo.lerBytes(conjuntoBits,arquivoOriginal);
		hash = arquivo.obterHash(arquivoOriginal);
		arvore = (ArvoreHuffman)arquivo.lerArvore(arvore, arquivoOriginal);
		
		
		String decodificada = new String();
		decodificada = descompactador.decodificaBits(conjuntoBits);
//		for(int i=0; i<conjuntoBits.length()-1;i++){
//			if(conjuntoBits.get(i)== true)
//				decodificada.append('1');
//			else
//				decodificada.append('0');
//		}
		StringBuffer textoOriginal = new StringBuffer();
			long tempoDecodifInicial = System.currentTimeMillis();
		textoOriginal.append(arvore.decodificarMensagem(arvore.getRaiz(), decodificada.toString()));
			long tempoTotalDecod = System.currentTimeMillis();
			System.out.printf("Decodificação texto em :%.3f s%n", (tempoTotalDecod - tempoDecodifInicial) / 1000d);
		
		boolean estaIntegro = descompactador.verificarIntegridade(hash,textoOriginal.toString());
		if(estaIntegro){
			arquivo.salvarTexto(textoOriginal.toString(), arquivoOriginal);
		}
		else{
			throw new ArquivoCorrompidoException();
		}
			
		long tempoTotal = System.currentTimeMillis();
		System.out.printf("Total Descompactação em :%.3f s%n", (tempoTotal - tempoInicial) / 1000d);

	}

//	public boolean verificarIntegridade(String x, String y) {
//
//		char[] texto1 = x.toCharArray();
//		char[] texto2 = y.toCharArray();
//
//		String hash1 = null;
//		String hash2 = null;
//
//		Integer hashParcial = 0;
//
//		int tamanhoDoTexto1 = texto1.length;
//		int tamanhoDoTexto2 = texto2.length;
//
//		if(tamanhoDoTexto1 != tamanhoDoTexto2)
//			return false;
//
//		for(int i=0;i<tamanhoDoTexto1;i++)
//			hashParcial += (texto1[i]*i)/(i+1);
//		hash1 = Integer.toString(hashParcial);
//
//		hashParcial = 0;
//
//		for(int i=0;i<tamanhoDoTexto2;i++)
//			hashParcial += (texto2[i]*i)/(i+1);
//		hash2 = Integer.toString(hashParcial);
//
//		return hash1.equals(hash2);
//	}
}
