package br.uefs.WinMonster.controller;

import java.io.File;
import java.util.BitSet;

import br.uefs.WinMonster.model.Compactador;
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
		arvoreFinal.setDicionario(new String[256][2]);
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer());

		StringBuilder textoCodificado = new StringBuilder();
		String[][] dicionario = arvoreFinal.getDicionario();
				
		for(int i=0;i < texto.length();i++){
			textoCodificado.append(dicionario[(int)texto.charAt(i)][1]);
			
			if(textoCodificado.length() > 30000){
				arquivo.salvarBytes(textoCodificado.toString(), arquivoOriginal);
				textoCodificado.delete(0, textoCodificado.length());
			}	
		}
		
		arquivo.salvarBytes(textoCodificado.toString(), arquivoOriginal);
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
