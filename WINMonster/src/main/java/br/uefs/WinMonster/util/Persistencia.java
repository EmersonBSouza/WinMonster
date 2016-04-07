package br.uefs.WinMonster.util;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;

import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;

public interface Persistencia {

	public void salvarBytes(BitSet conjuntoBits,int hashCode,Object objeto,File arquivo) throws IOException;
	public void salvarTexto(String texto,File arquivo) throws IOException;
	public String lerTexto(File arquivo) throws IOException;
	public Object lerBytes(Object objeto,File arquivo) throws IOException;
	public ArvoreHuffman lerArvore(Object objeto, File arquivoOriginal) throws IOException, ArquivoCorrompidoException;
	public int obterHash(File arquivoOriginal) throws IOException, ArquivoCorrompidoException;
}
