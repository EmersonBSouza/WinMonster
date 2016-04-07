package br.uefs.WinMonster.model;

import java.util.BitSet;

public class Compactador {

	/**
	 * Este método transforma o texto codificado em um conjunto de bits
	 * @param String texto 
	 * @return BitSet conjuntoBits*/
	public BitSet transformarEmBits(String texto){
		BitSet conjuntoBits = new BitSet();
		char[] caracteres = texto.toCharArray();//Converte o texto para um vetor de caracteres
		int i=0;
		for(i = 0; i < caracteres.length;i++){//Este laço converte o texto em bits
			if(caracteres[i]=='1'){	
				conjuntoBits.set(i,true);
			}
			else{
				conjuntoBits.set(i,false);
			}
		}
		conjuntoBits.set(i,true);
		return conjuntoBits;//Retorna o conjunto de bits
	}
	/**
	 * Este método é responsável por gerar o código hash do texto
	 * @param String texto
	 * @return int hash*/
	public int gerarHash(String texto){
		int hash = 0;
		char[] caracteres = texto.toCharArray();//Converte o texto para um vetor de caracteres
		for(int i=0;i<texto.length();i++)//Gera o código hash
			hash += (caracteres[i]*i)/(i+1);
		return hash;
	}
}
