package br.uefs.WinMonster.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.BitSet;

import org.junit.Test;

import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;

public class DescompactadorTest {

	Descompactador descompactadorTest = new Descompactador();
	String texto = "1000101010100001110011011";

	@Test
	public void testDecodificaBits() {

		BitSet bitsetTest = new BitSet();
		char[] caracteresDoTexto = texto.toCharArray();

		int i;
		for(i = 0; i < texto.length(); i++)  {
			if(caracteresDoTexto[i] == '1')
				bitsetTest.set(i, true);
			else
				bitsetTest.set(i, false);
		}

		String textoEsperado = "100010101010000111001101";
		String textoObtido = descompactadorTest.decodificaBits(bitsetTest);

		assertEquals(textoEsperado, textoObtido);
	}

	@Test
	public void testVerificarIntegridadeStringsDiferentes() {

		texto = "Aquele pão, quentinho, hmmm";
		int hashTexto1 = 0;
		char[] caracteresDoTexto = texto.toCharArray();
		for(int i=0;i<texto.length();i++)
			hashTexto1 += (caracteresDoTexto[i]*i)/(i+1);

		String texto2 = "chega a manteiga derrete";

		try {
			descompactadorTest.verificarIntegridade(hashTexto1, texto2);
		} catch(ArquivoCorrompidoException cause) {
			assertTrue(true);
		}

	}

	@Test
	public void testVerificarIntegridadeSucesso() {
		
		texto = "Aquele pão, quentinho, hmmm";
		int hashTexto = 0;
		char[] caracteresDoTexto = texto.toCharArray();
		for(int i=0;i<texto.length();i++)
			hashTexto += (caracteresDoTexto[i]*i)/(i+1);
		boolean estaIntegro = false;

		try {
			estaIntegro = descompactadorTest.verificarIntegridade(hashTexto, texto);
		} catch(ArquivoCorrompidoException cause) {
			fail();
		}

		assertEquals(true, estaIntegro);
	}



}
