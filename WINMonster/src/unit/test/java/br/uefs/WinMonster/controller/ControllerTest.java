package br.uefs.WinMonster.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import br.uefs.WinMonster.util.CriarObjetos;

public class ControllerTest {

	@Test
	public void testeCompactacao(){
		
		WinController controller = new WinController();
		File arquivoOriginal = CriarObjetos.criarArquivo();
		
		controller.compactarArquivo(arquivoOriginal);
		
		if(arquivoOriginal.length() > tamanhoDooutroArquivo){
			assertTrue(true);
		}
		
	}
	
	
}
