package br.uefs.WinMonster.util;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class FilaTest {
	
	@Test
	public void testInserirComPrioridadeSucesso() {
		String nome1 = "João";
		String nome2 = "Maria";
		String nome3 = "Zuleica";
		String nome4 = "Lokisley";
		String nome5 = "Ana";
		Fila fila = new Fila();
		fila.inserirComPrioridade(nome1);
		fila.inserirComPrioridade(nome2);
		fila.inserirComPrioridade(nome3);
		fila.inserirComPrioridade(nome4);
		fila.inserirComPrioridade(nome5);
		Iterador iterador = fila.iterador();
		assertEquals("Ana",iterador.obterProximo());
		assertEquals("João",iterador.obterProximo());
		assertEquals("Lokisley",iterador.obterProximo());
		assertEquals("Maria",iterador.obterProximo());
		assertEquals("Zuleica",iterador.obterProximo());
	}
}
