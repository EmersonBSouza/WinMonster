package br.uefs.WinMonster.model;

import java.util.BitSet;

public class Descompactador {

	public String decodificaBits(BitSet conjuntoBits){
		long tempoInicial = System.currentTimeMillis();
		
		
		StringBuilder decodificada = new StringBuilder();
		for(int i=0; i<conjuntoBits.length()-1;i++){
			if(conjuntoBits.get(i)== true)
				decodificada.append('1');
			else
				decodificada.append('0');
		}
		long tempoFinal = System.currentTimeMillis();
		System.out.printf("Decodifica Bits em :%.3f s%n", (tempoFinal- tempoInicial) / 1000d);
		return decodificada.toString();
	}
	
	public boolean verificarIntegridade(int hashSalvo,String texto){
		long tempoInicial = System.currentTimeMillis();
		
		int hashAtual = 0;
		char[] caracteres = texto.toCharArray();
		for(int i=0;i<texto.length();i++)
			hashAtual += (caracteres[i]*i)/(i+1);
		
		long tempoFinal = System.currentTimeMillis();
		System.out.printf("Verificação integridade em :%.3f s%n", (tempoFinal- tempoInicial) / 1000d);
		if(hashSalvo == hashAtual)
			return true;
		else
			return false;
		
	}
}
