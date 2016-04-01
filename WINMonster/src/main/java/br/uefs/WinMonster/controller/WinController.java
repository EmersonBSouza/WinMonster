package br.uefs.WinMonster.controller;

import br.uefs.WinMonster.util.*;

public class WinController {
	
	public void compactarArquivo(String caminho){
		Arquivo arquivo = new Arquivo();
		String texto = arquivo.lerTexto(caminho);
		Lista caracteres = new Lista();
		Fila fila = new Fila();
		boolean existeLetra = false;
		NoArvore no;
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		
		for(int i=0; i < texto.length();i++){
			while(caracteres.iterador().temProximo()){
				no = (NoArvore)caracteres.iterador().obterProximo();
				if(no.getLetra() == texto.charAt(i)){
					no.setFrequencia(no.getFrequencia()+1);
					existeLetra = true;
					break;
				}
			}
			if(!existeLetra){
				NoArvore novoNo = new NoArvore();
				novoNo.setLetra(texto.charAt(i));
				novoNo.setFrequencia(1);
				caracteres.inserirFinal(novoNo);
			}
		}
		
		while(caracteres.iterador().temProximo()){
			no = (NoArvore)caracteres.iterador().obterProximo();
			fila.inserirComPrioridade(no);
		}
		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer());
		
	}
	
	public void descompactarArquivo(Object objeto){
		
	}
	
	public void verificarIntegridade(){
		
	}
	
	
}
