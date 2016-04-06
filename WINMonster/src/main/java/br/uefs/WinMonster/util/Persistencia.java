package br.uefs.WinMonster.util;

import java.io.File;
import java.util.BitSet;

public interface Persistencia {

	public void salvarBytes(BitSet conjuntoBits,int hashCode,Object objeto,File arquivo);
	public void salvarTexto(String texto,File arquivo);
	public String lerTexto(String caminho,File arquivo);
	public Object lerBytes(Object objeto,File arquivo);
}
