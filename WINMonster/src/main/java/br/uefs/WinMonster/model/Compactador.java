package br.uefs.WinMonster.model;

import java.util.BitSet;

public class Compactador {

	/**
	 * Este m�todo transforma o texto codificado em um conjunto de bits
	 * @param String texto 
	 * @return BitSet conjuntoBits*/
	public BitSet transformarEmBits(String texto){
		BitSet conjuntoBits = new BitSet();
		char[] caracteres = texto.toCharArray();//Converte o texto para um vetor de caracteres
		int i=0;
		for(i = 0; i < caracteres.length;i++){//Este la�o converte o texto em bits
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
	 * Este m�todo � respons�vel por gerar o c�digo hash do texto
	 * @param String texto
	 * @return int hash*/
	public int gerarHash(String texto){
		int hash = 0;
		char[] caracteres = texto.toCharArray();//Converte o texto para um vetor de caracteres
		for(int i=0;i<texto.length();i++)//Gera o c�digo hash
			hash += (caracteres[i]*i)/(i+1);
		return hash;
	}
}
