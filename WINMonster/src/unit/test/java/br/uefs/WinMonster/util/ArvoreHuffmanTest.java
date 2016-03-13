package br.uefs.WinMonster.util;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArvoreHuffmanTest {

	@Test
	public void testCriarArvoreSucesso() {

		// Simulação de entrada da String "SUSIE SAYS ITS EASY\n"

		Fila fila = new Fila();

		/* Criamos um vetor e nós com os caracteres da frase e suas frequências.
		 * Os nós criados são inseridos no vetor em ordem crescente de frequência*/
		NoArvore[] vetorAux = new NoArvore[9];

		NoArvore no1 = new NoArvore();
		no1.setLetra('\n');
		no1.setFrequencia(1);
		vetorAux[0] = no1;

		NoArvore no2 = new NoArvore();
		no2.setLetra('U');
		no2.setFrequencia(1);
		vetorAux[1] = no2;

		NoArvore no3 = new NoArvore();
		no3.setLetra('T');
		no3.setFrequencia(1);
		vetorAux[2] = no3;

		NoArvore no4 = new NoArvore();
		no4.setLetra('Y');
		no4.setFrequencia(2);
		vetorAux[3] = no4;

		NoArvore no5 = new NoArvore();
		no5.setLetra('E');
		no5.setFrequencia(2);
		vetorAux[4] = no5;

		NoArvore no6 = new NoArvore();
		no6.setLetra('A');
		no6.setFrequencia(2);
		vetorAux[5] = no6;

		NoArvore no7 = new NoArvore();
		no7.setLetra('I');
		no7.setFrequencia(3);
		vetorAux[6] = no7;

		NoArvore no8 = new NoArvore();
		no8.setLetra(' ');
		no8.setFrequencia(4);
		vetorAux[7] = no8;

		NoArvore no9 = new NoArvore();
		no9.setLetra('S');
		no9.setFrequencia(6);
		vetorAux[8] = no9;

		/*Cria um objeto ArvoreHuffman para cada um dos nós, tendo eles como raiz, e os insere 
		 *em uma fila. Os caracteres de menor frequência possuem preferência. */
		for(int i=0;i<vetorAux.length;i++) {
			ArvoreHuffman arvore = new ArvoreHuffman();
			arvore.setRaiz(vetorAux[i]);
			fila.inserirFinal(arvore);;
		}

		//Cria-se uma árvore de Huffman com a fila gerada
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		arvoreFinal = arvoreFinal.criaArvore(fila);

		/* Código esperado que seja gerado por uma árvore de HuffMan para a String em questão
		 * em ordem crescente de frequência */
		String codificacao = "01110" //\n 
				+ "01111" //U
				+ "0110" //T
				+ "1110" //Y
				+ "1111" //E
				+ "010" //A
				+ "110" //I
				+ "00" //Barra de espaço
				+ "10"; //S				

		char[] vetorCodificacao = codificacao.toCharArray(); //Cria um vetor com essa String

		//Vetor que armazenará os caracteres obtidos da árvore criada
		char[] vetorObtido = new char[9];

		/* Percorre a árvore lendo a codificacao, onde 0 significa esquerda e 1 direita*/
		char direcao;
		int i = 0; //variável que controlará o andamento na String de codificação
		int posicaoNoVetorObtido = 0; //variável que controlará o salvamento de letras encontradas no vetor
		NoArvore atual;
		atual = arvoreFinal.getRaiz();
		do {
			/* Caso o nó atual não possua letra, avança-se um nó na árvore, para a esquerda
			 * ou para direita, dependendo do caractere lido na String de código*/
			if(atual.getLetra() == '\0') {
				direcao = vetorCodificacao[i];
				if(direcao == '0')
					atual = atual.filhoEsq;
				else if (direcao == '1')
					atual = atual.filhoDir;
				i++;
			}
			/* Caso seja encontrada uma letra(folha), ela é salva em um vetor e voltamos ao 
			 * início da árvore, até que este esteja completamente preenchido*/
			else {
				vetorObtido[posicaoNoVetorObtido] = atual.getLetra();
				posicaoNoVetorObtido++;
				atual = arvoreFinal.getRaiz();				
			} 
		} while( posicaoNoVetorObtido < vetorObtido.length);
		
		/* Depois de percorrer a árvore com o código e salvar as letras encontradas na ordem
		 * em um vetor, verificamos se temos a sequência esperada*/
		for(i = 0; i < vetorAux.length; i++)
			assertEquals(vetorAux[i].getLetra(), vetorObtido[i]);

	}
}
