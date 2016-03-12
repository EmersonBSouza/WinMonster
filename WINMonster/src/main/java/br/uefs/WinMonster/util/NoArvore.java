package br.uefs.WinMonster.util;

public class NoArvore {
//s
	char letra;
	int frequencia;
	NoArvore filhoEsq;
	NoArvore filhoDir;
	
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
}
