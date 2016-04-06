package br.uefs.WinMonster.model;

import java.util.BitSet;

public class Compactador {

	public BitSet transformarEmBits(String texto){
		long tempoBit = System.currentTimeMillis();
		BitSet conjuntoBits = new BitSet();
		char[] caracteres = texto.toCharArray();
		int i=0;
		for(i = 0; i < caracteres.length;i++){
			//if(texto.charAt(i) == '1'){
			if(caracteres[i]=='1'){	
				conjuntoBits.set(i,true);
			}
			else{
				conjuntoBits.set(i,false);
			}
		}
		conjuntoBits.set(i,true);
		long tempoFimBit = System.currentTimeMillis();
		System.out.printf("TempoBit em :%.3f s%n", (tempoFimBit - tempoBit) / 1000d);
		return conjuntoBits;
	}
	
	public int gerarHash(String texto){
		long tempohash = System.currentTimeMillis();
		int hash = 0;
		char[] caracteres = texto.toCharArray();
		for(int i=0;i<texto.length();i++)
			hash += (caracteres[i]*i)/(i+1);
		long tempofimhash = System.currentTimeMillis();
		System.out.printf("Tempo codificacao do Hash em :%.3f s%n", (tempofimhash - tempohash) / 1000d);
		return hash;
	}
}
