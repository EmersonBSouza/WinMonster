package br.uefs.WinMonster.util;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArvoreHuffmanTest {

	@Test
	public void testCriarArvoreSucesso() {

		// Simula��o de entrada da String "SUSIE SAYS ITS EASY\n"

		Fila fila = new Fila();
		
		/* Os n�s criados s�o inseridos na ordem que se espera que eles estejam
		   localizados em uma �rvore de HuffMan em uma leitura da esquerda para direita	*/
		NoArvore[] vetorAux = new NoArvore[10];

		NoArvore no1 = new NoArvore();
		no1.setLetra(' ');
		no1.setFrequencia(4);
		vetorAux[0] = no1;

		NoArvore no2 = new NoArvore();
		no1.setLetra('A');
		no1.setFrequencia(2);
		vetorAux[1] = no2;

		NoArvore no3 = new NoArvore();
		no1.setLetra('T');
		no1.setFrequencia(1);
		vetorAux[2] = no3;

		NoArvore no4 = new NoArvore();
		no1.setLetra('\n');
		no1.setFrequencia(1);
		vetorAux[3] = no4;

		NoArvore no5 = new NoArvore();
		no1.setLetra('U');
		no1.setFrequencia(1);
		vetorAux[4] = no5;

		NoArvore no6 = new NoArvore();
		no1.setLetra('S');
		no1.setFrequencia(6);
		vetorAux[5] = no6;

		NoArvore no7 = new NoArvore();
		no1.setLetra('I');
		no1.setFrequencia(3);
		vetorAux[6] = no7;

		NoArvore no8 = new NoArvore();
		no1.setLetra('Y');
		no1.setFrequencia(2);
		vetorAux[7] = no8;

		NoArvore no9 = new NoArvore();
		no1.setLetra('E');
		no1.setFrequencia(2);
		vetorAux[8] = no9;
		
		//Cria fila com os caracteres de menor frequ�ncia com prefer�ncia
		for(int i=0;i<vetorAux.length;i++)
			fila.inserirComPrioridade(vetorAux[i]);
		
		//Cria �rvore de Huffmann 
		ArvoreHuffman arvore = new ArvoreHuffman();
		arvore = arvore.criaArvore(fila);

		/*C�digo esperado que seja gerado por uma �rvore de HuffMan para a String em quest�o
		 * lida da esquerda para direita*/
		String codificacao = "00" //Barra de espa�o
				+ "010" //A
				+ "0110" //T
				+ "01110" //\n
				+ "01111" //U
				+ "10" //S
				+ "110" //I
				+ "1110" //Y
				+ "1111"; //E
		char[] vetorCodificacao = codificacao.toCharArray(); //Cria um vetor com essa String
		
		//Vetor que armazenar� os caracteres obtidos da �rvore criada
		char[] vetorObtido = new char[9];

		/* Percorre a �rvore utilizando a codificacao, onde 0 significa esquerda e 1 direita*/
		NoArvore atual = arvore.getRaiz();
		for(int i = 0; atual!= null; i++) {
			
			char direcao;
			int posicaoAtual = 0;
			direcao = vetorCodificacao[i];
			
			if(direcao == '0')
				atual = atual.filhoEsq;
			
			else if(direcao == '1')
				atual = atual.filhoDir;
			
			/* Caso seja encontrada uma letra, ela � salva no vetor e voltamos a raiz
			 * para a verifica��o do pr�ximo c�digo*/
			if (atual.getLetra() != '\0') {
				vetorObtido[posicaoAtual] = atual.getLetra();
				posicaoAtual++;
				atual = arvore.getRaiz();
			}
		}
		
		/* Depois de percorrer a �rvore com o c�digo e salvar as letras encontradas na ordem
		 * em um vetor, verificamos se temos a sequ�ncia esperada*/
		for(int i = 0; i < codificacao.length(); i++)
			assertEquals(vetorAux[i], vetorObtido[i]);

	}
}
