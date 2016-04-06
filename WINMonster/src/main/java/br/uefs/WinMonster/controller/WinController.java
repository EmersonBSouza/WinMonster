package br.uefs.WinMonster.controller;

import java.io.File;
import java.util.BitSet;
import br.uefs.WinMonster.util.*;

public class WinController {

	public void compactarArquivo(String caminho,File arquivoOriginal){
		Arquivo arquivo = new Arquivo();
		String texto = arquivo.lerTexto(caminho,arquivoOriginal);

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
		
		iterador = listaDeFrequencias.iterador();
		while(iterador.temProximo()) {
			atual = (NoArvore) iterador.obterProximo();
			ArvoreHuffman arvore = new ArvoreHuffman();
			arvore.setRaiz(atual);
			fila.inserirComPrioridade(arvore);
		}
			

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

		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer());
		Lista lista = new Lista();
		String[] textoDividido;
		StringBuilder textoCodificado = new StringBuilder();

		for(int i=0;i < texto.length();i+=30000){
			if(i+30000>texto.length()){
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, texto.length())));
				lista.inserirFinal(textoCodificado.toString());
				//arquivo.salvarBytes(textoCodificado.toString(), arvoreFinal, arquivoOriginal);
				textoCodificado.delete(0, textoCodificado.length());
			}
			else{
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, i+30000)));
				lista.inserirFinal(textoCodificado.toString());
				//arquivo.salvarBytes(textoCodificado.toString(), null, arquivoOriginal);
				textoCodificado.delete(0, textoCodificado.length());
			}
		}
		iterador = lista.iterador();
		StringBuilder textoFinal = new StringBuilder();
		String auxiliar;
		while(iterador.temProximo()){
			textoFinal.append(iterador.obterProximo());
		}
		arquivo.salvarBytes(textoFinal.toString(), arvoreFinal, arquivoOriginal);
		/*textoCodificado.append(dicionario[(int)texto.charAt(i)][1]);

			if(textoCodificado.length() > 30000){
				arquivo.salvarBytes(textoCodificado.toString(),null, arquivoOriginal);
				textoCodificado.delete(0, textoCodificado.length());
			}*/


		//arquivo.salvarBytes(textoCodificado.toString(),arvoreFinal ,arquivoOriginal);

	}

	public void descompactarArquivo(File arquivoOriginal){
		Arquivo arquivo = new Arquivo();
		BitSet conjuntoBits = new BitSet();
		ArvoreHuffman arvore = new ArvoreHuffman();


		conjuntoBits= (BitSet)arquivo.lerBytes(conjuntoBits,arquivoOriginal);
		arvore = (ArvoreHuffman)arquivo.lerArvore(arvore, arquivoOriginal);

		StringBuilder decodificada = new StringBuilder();
		for(int i=0; i<conjuntoBits.length()-1;i++){
			if(conjuntoBits.get(i)== true)
				decodificada.append('1');
			else
				decodificada.append('0');
		}
		StringBuffer textoOriginal = new StringBuffer();

		//for(int i=0;i<decodificada.length();i++){
		textoOriginal.append(arvore.decodificarMensagem(arvore.getRaiz(), decodificada.toString()));	
		arquivo.salvarTexto(textoOriginal.toString(), arquivoOriginal);

	}

	public boolean verificarIntegridade(String x, String y) {

		char[] texto1 = x.toCharArray();
		char[] texto2 = y.toCharArray();

		String hash1 = null;
		String hash2 = null;

		Integer hashParcial = 0;

		int tamanhoDoTexto1 = texto1.length;
		int tamanhoDoTexto2 = texto2.length;

		if(tamanhoDoTexto1 != tamanhoDoTexto2)
			return false;

		for(int i=0;i<tamanhoDoTexto1;i++)
			hashParcial += (texto1[i]*i)/(i+1);
		hash1 = Integer.toString(hashParcial);

		hashParcial = 0;

		for(int i=0;i<tamanhoDoTexto2;i++)
			hashParcial += (texto2[i]*i)/(i+1);
		hash2 = Integer.toString(hashParcial);

		return hash1.equals(hash2);
	}
}
