package br.uefs.WinMonster.util;

import java.io.File;
import java.util.BitSet;

public interface Persistencia {

	public void salvarBytes(String texto,File arquivo);
	public void salvarTexto(String texto,File arquivo);
	public String lerTexto(String caminho,File arquivo);
	public BitSet lerBytes(File arquivo);
	public BitSet converterBits(String texto);
}
