package br.uefs.WinMonster.model;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.junit.Test;

public class CompactadorTest {

	Compactador compactadorTest = new Compactador();
	String texto = "101010"
			+ "001001"
			+ "100101"
			+ "011001";
	
	@Test
	public void testTransformarEmBits() {
		
		BitSet bitsetTest = compactadorTest.transformarEmBits(texto);
		
		
		boolean[] bitsEsperados = new boolean[texto.length()];
		
		char[] caracteresDoTexto = texto.toCharArray();
		
		int i;
		
		for(i = 0; i < texto.length(); i++)  {
			if(caracteresDoTexto[i] == '1')
				bitsEsperados[i] = true;
			else
				bitsEsperados[i] = false;
		}
		
		for(i = 0; i < texto.length(); i++)
			assertEquals(bitsEsperados[i], bitsetTest.get(i));
	}
	
	@Test
	public void testGerarHash() {
		
		char[] caracteres = texto.toCharArray();
		
		int hashEsperado = 0;
		
		for(int i=0;i<texto.length();i++)
			hashEsperado += (caracteres[i]*i)/(i+1);
		
		int hashObtido = compactadorTest.gerarHash(texto);
		
		assertEquals(hashEsperado, hashObtido);
	}
}

