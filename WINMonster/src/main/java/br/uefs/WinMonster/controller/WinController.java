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
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer());
		
		String[] textoDividido;
		StringBuilder textoCodificado = new StringBuilder();
		String[][] dicionario = arvoreFinal.getDicionario();
				
		for(int i=0;i < texto.length();i+=80000){
			if(i+80000<texto.length()){
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, i+80000)));
				arquivo.salvarBytes(textoCodificado.toString(), null, arquivoOriginal);
				textoCodificado.delete(0, textoCodificado.length());
			}
			else{
				textoCodificado.append(arvoreFinal.codificarMensagem(texto.substring(i, texto.length())));
				arquivo.salvarBytes(textoCodificado.toString(), arvoreFinal, arquivoOriginal);
			}
		}	
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
		
		conjuntoBits = (BitSet) arquivo.lerBytes(conjuntoBits,arquivoOriginal);
		arvore = (ArvoreHuffman)arquivo.lerBytes(arvore, arquivoOriginal);
		
		String decodificada = new String();
		
		for(int i=0; i<conjuntoBits.length();i++){
			if(conjuntoBits.get(i)== true)
				decodificada += '1';
			else
				decodificada +='0';
			
		}
		StringBuffer textoOriginal = new StringBuffer();
		
		for(int i=0;i<decodificada.length();i++){
			textoOriginal.append(arvore.decodificarMensagem(arvore.getRaiz(), decodificada));
			
		}
		
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
