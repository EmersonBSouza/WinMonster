package br.uefs.WinMonster.controller;

import java.io.File;

import br.uefs.WinMonster.util.*;

public class WinController {

	public void compactarArquivo(String caminho,File arquivoOriginal){
		Arquivo arquivo = new Arquivo();
		String texto = arquivo.lerTexto(caminho,arquivoOriginal);

		Fila fila = new Fila();
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();

		char[] caracteres = texto.toCharArray();
		int[] frequencias = new int[256];

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
		}

		int tamanho = fila.obterTamanho();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.setDicionario(new String[tamanho][2]);
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer());

		String textoCodificado = new String();
		String[][] dicionario = arvoreFinal.getDicionario();

		
		for(int i=0;i < texto.length();i++){
			int posicaoAtual = 0;
			while(texto.charAt(i) != dicionario[posicaoAtual][0].charAt(0)){
				posicaoAtual++;
			}
			textoCodificado += dicionario[posicaoAtual][1];
		}

		arquivo.salvarTexto(textoCodificado.toString(), arquivoOriginal);
	}

	public void descompactarArquivo(Object objeto){

	}

	public void verificarIntegridade(){
		/*String hash = null;
		Integer hashParcial = 0;
		int tamanhoDoTexto = caracteres.length;
		for(int i=0;i<tamanhoDoTexto;i++)
			hashParcial += (caracteres[i]*i)/(i+1);
		hash = Integer.toString(hashParcial);
		System.out.println(hash);*/
	}


}
