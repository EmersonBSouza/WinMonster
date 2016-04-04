package br.uefs.WinMonster.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListaEncadeadaTest {
	
	@Test
	public void testEstaVaziaSucesso() {
		Lista lista = new Lista();
		assertEquals(lista.estaVazia(), true);		
	}
	
	@Test
	public void testObterTamanhoSucesso() {
		
		Lista lista = new Lista();
		lista.inserirFinal("nome1");
		lista.inserirFinal("nome2");
		lista.inserirFinal("nome3");
		lista.inserirFinal("nome4");
		
		assertEquals(lista.obterTamanho(), 4);
		
		lista.inserirFinal("nome5");
		lista.inserirFinal("nome6");
		lista.inserirFinal("nome7");
		
		assertEquals(lista.obterTamanho(), 7);
	}
	@Test
	public void testInserirNoInicioSucesso() {
		
		Lista lista = CriarObjetos.criarLista();
		
		lista.inserirInicio("Novo nome");
		
		
		assertEquals(lista.recuperarInicio().getObjeto(), "Novo nome");
		
		lista.inserirInicio("Novo nome 2");
		
		assertEquals(lista.recuperarInicio().getObjeto(), "Novo nome 2");
		
		
	}
	
	@Test
	public void testInserirNoFimSucesso() {
		
		Lista lista = CriarObjetos.criarLista();
		
		lista.inserirFinal("Novo nome");
		
		String ultimoNome = null;
		
		Iterador iterador = lista.iterador();
		while(iterador.temProximo())
			ultimoNome = (String) iterador.obterProximo();
		
		assertEquals(ultimoNome, "Novo nome");
		
		lista.inserirFinal("Novo nome2");
		
		iterador = lista.iterador();
		while(iterador.temProximo())
			ultimoNome = (String) iterador.obterProximo();
		
		assertEquals(ultimoNome, "Novo nome2");
	}
	
	@Test
	public void testRemoverNoInicioSucesso() {
		
		Lista lista = CriarObjetos.criarLista();
		lista.inserirInicio("Novo nome");
		
		assertEquals(lista.recuperarInicio().getObjeto(), "Novo nome");
		
		lista.removerInicio();
		
		assertNotEquals(lista.recuperarInicio().getObjeto(), "Novo nome");
		
		
	}
	
	@Test
	public void testRemoverNoFimSucesso() {
		
		Lista lista = CriarObjetos.criarLista();
		
		lista.inserirFinal("Novo nome");
		
		String ultimoNome = null;
		Iterador iterador = lista.iterador();
		while(iterador.temProximo())
			ultimoNome = (String) iterador.obterProximo();
		
		assertEquals(ultimoNome, "Novo nome");
		
		lista.removerFinal();
		
		ultimoNome = null;
		iterador = lista.iterador();
		while(iterador.temProximo())
			ultimoNome = (String) iterador.obterProximo();
		
		assertNotEquals(ultimoNome, "Novo nome");
		
	}
	
	@Test
	public void testRemoverPeloIndexSucesso() {
		
		Lista lista = new Lista();
		lista.inserirFinal("nome1");
		lista.inserirFinal("nome2");
		lista.inserirFinal("nome3");
		lista.inserirFinal("nome4");
		lista.inserirFinal("nome5");
		
		lista.remover(3);
		
		boolean removido = true;
		
		String nomeAtual = null;
		
		Iterador iterador = lista.iterador();
		
		while(iterador.temProximo()) {
			nomeAtual = (String) iterador.obterProximo();
			if(nomeAtual.equals("nome3"))
				removido = false;
		}
		
		assertEquals(removido, true);
	}
	
	@Test
	public void testRecuperarSucesso() {
		Lista lista = new Lista();
		lista.inserirFinal("nome1");
		lista.inserirFinal("nome2");
		lista.inserirFinal("nome3");
		lista.inserirFinal("nome4");
		lista.inserirFinal("nome5");
		lista.inserirFinal("nome6");
		
		String recuperado = (String) lista.recuperar(4);
		assertEquals(recuperado, "nome4");
		
		boolean removido = true;
		
		String nomeAtual = null;
		
		Iterador iterador = lista.iterador();
		
		while(iterador.temProximo()) {
			nomeAtual = (String) iterador.obterProximo();
			if(nomeAtual.equals("nome4"))
				removido = false;
		}
				
		assertEquals(removido, false);
	}
	
	@Test
	public void testIterador() {
		
		Lista lista = CriarObjetos.criarLista();
		String[] nomes = new String[8];
		
		int i;
		for(i = 0; i<8; i++){
			nomes[i] = (String) lista.recuperarInicio().getObjeto();
			lista.removerInicio();
		}
		
		String nomeAtual = null;
		
		Iterador iterador = lista.iterador();
		lista = CriarObjetos.criarLista();
		i=0;
		while(iterador.temProximo()) {
			nomeAtual = (String) iterador.obterProximo();
			System.out.println(nomes[i]);
			assertEquals(nomeAtual, nomes[i]);
			i++;
		}
	}
}
