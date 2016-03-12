package br.uefs.WinMonster.util;

public class NoArvore {

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
	public void setFilhoEsq(NoArvore filhoEsq) {
		this.filhoEsq = filhoEsq;
	}
	public NoArvore getFilhoDir() {
		return filhoDir;
	}
	public void setFilhoDir(NoArvore filhoDir) {
		this.filhoDir = filhoDir;
	}
}
