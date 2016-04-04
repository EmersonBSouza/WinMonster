package br.uefs.WinMonster.util;

import java.io.Serializable;

public class NoArvore implements Comparable<NoArvore> ,Serializable{

	private char letra;
	private int frequencia;
	private NoArvore filhoEsq;
	private NoArvore filhoDir;
	
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
	public NoArvore getFilhoEsq() {
		return filhoEsq;
	}
	public void setFilhoEsq(Object filhoEsq) {
		this.filhoEsq = (NoArvore) filhoEsq;
	}
	public NoArvore getFilhoDir() {
		return filhoDir;
	}
	public void setFilhoDir(Object object) {
		this.filhoDir = (NoArvore) object;
	}
	
	@Override
	public int compareTo(NoArvore o) {
		if( this.frequencia < o.frequencia)
			return -1;
		else if( this.frequencia > o.frequencia)
			return 1;
		return 0;
	}
}
