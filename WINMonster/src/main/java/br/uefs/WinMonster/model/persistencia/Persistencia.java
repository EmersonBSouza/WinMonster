package br.uefs.WinMonster.model.persistencia;

import java.util.BitSet;

public interface Persistencia {

	public void salvarBytes(String texto,String path);
	public void salvarTexto(String texto,String path);
	public String lerTexto(String path);
	public String lerBytes(String path);
	public BitSet converterBits(String texto);
}
