package br.uefs.WinMonster.model;

import java.util.BitSet;

import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;

public class Descompactador {

	/**
	 * Este método é responsável por transformar um BitSet em uma String com o texto original
	 * @param BitSet conjuntoBits
	 * @return String decodificada.toString()*/
	public String decodificaBits(BitSet conjuntoBits){		
		StringBuilder decodificada = new StringBuilder();
		for(int i=0; i<conjuntoBits.length()-1;i++){//Este laço transforma o conjunto de bits em texto
			if(conjuntoBits.get(i)== true)
				decodificada.append('1');
			else
				decodificada.append('0');
		}
		return decodificada.toString();//Retorna a String decodificada
	}
	
	/**
	 * Este método verifica se o texto está integro
	 * @param int hashSalvo
	 * @param String texto
	 * @throws ArquivoCorrompidoException - Caso o arquivo esteja corrompido
	 * @return boolean true - retorna true, se o texto estiver integro*/
	public boolean verificarIntegridade(int hashSalvo,String texto) throws ArquivoCorrompidoException{
		
		int hashAtual = 0;
		char[] caracteres = texto.toCharArray();
		for(int i=0;i<texto.length();i++)
			hashAtual += (caracteres[i]*i)/(i+1);
		
		if(hashSalvo == hashAtual)//Verifica se os códigos são iguais
			return true;
		else
			throw new ArquivoCorrompidoException();
		
	}
}
