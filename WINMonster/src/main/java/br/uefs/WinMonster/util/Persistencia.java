package br.uefs.WinMonster.util;

import java.io.File;
import java.util.BitSet;

public interface Persistencia {

	public void salvarBytes(String texto,String path);
	public void salvarTexto(String texto,String path);
	public String lerTexto(String caminho,File arquivo);
	public String lerBytes(String caminho,File arquivo);
	public BitSet converterBits(String texto);
}
