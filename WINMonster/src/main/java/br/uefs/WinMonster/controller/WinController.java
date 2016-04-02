package br.uefs.WinMonster.controller;

import java.io.File;

import br.uefs.WinMonster.util.*;

public class WinController {
	
	public void compactarArquivo(String caminho,File arquivoOriginal){
		Arquivo arquivo = new Arquivo();
		String texto = arquivo.lerTexto(caminho,arquivoOriginal);
		
		Fila fila = new Fila();
		boolean existeLetra = false;
		ArvoreHuffman no = new ArvoreHuffman();
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		
		/*for(int i=0; i < texto.length();i++){
			existeLetra = false;
			Iterador iterador = caracteres.iterador();
			if(!caracteres.estaVazia()){
				
				while(iterador.temProximo()){
					no = (ArvoreHuffman)iterador.obterProximo();
					if(no.getRaiz().getLetra() == texto.charAt(i)){
						no.getRaiz().setFrequencia(no.getRaiz().getFrequencia()+1);
						existeLetra = true;
						break;
					}
				}
			}
			if(!existeLetra){
				ArvoreHuffman novoNo = new ArvoreHuffman();
				NoArvore noRaiz = new NoArvore();
				noRaiz.setLetra(texto.charAt(i));
				noRaiz.setFrequencia(1);
				novoNo.setRaiz(noRaiz);
				caracteres.inserirFinal(novoNo);
			}
		}*/
		
		  char[] caracteres = texto.toCharArray();
		  int[] frequencias = new int[256];
		  
		  for(int i=0;i<caracteres.length;i++) {
		   frequencias[caracteres[i]]++;
		  }
		  
		  for(int i=0; i<texto.length();i++){
			  if(frequencias[i]!=0){
			  	  no.getRaiz().setLetra(caracteres[i]);//Aqui tem NULLPOINTER pq a raiz é igual a Null neste ponto
			  	  no.getRaiz().setFrequencia(frequencias[i]);//Aqui tem NULLPOINTER pq a raiz é igual a Null neste ponto
			  	  fila.inserirComPrioridade(no);
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
