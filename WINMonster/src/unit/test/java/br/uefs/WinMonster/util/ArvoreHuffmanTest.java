package br.uefs.WinMonster.util;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArvoreHuffmanTest {

	@Test
	public void testCriarArvoreSucesso() {

		Fila fila = CriarObjetos.criarFila();
		NoArvore[] vetorAux = CriarObjetos.criarVetor();
		//Cria-se uma �rvore de Huffman com a fila gerada
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		arvoreFinal = arvoreFinal.criaArvore(fila);

		/* C�digo esperado que seja gerado por uma �rvore de HuffMan para a String em quest�o
		 * em ordem crescente de frequ�ncia */
		String codificacao = "01110" //\n 
				+ "01111" //U
				+ "0110" //T
				+ "1110" //Y
				+ "1111" //E
				+ "010" //A
				+ "110" //I
				+ "00" //Barra de espa�o
				+ "10"; //S				

		char[] vetorCodificacao = codificacao.toCharArray(); //Cria um vetor com essa String

		//Vetor que armazenar� os caracteres obtidos da �rvore criada
		char[] vetorObtido = new char[9];

		/* Percorre a �rvore lendo a codificacao, onde 0 significa esquerda e 1 direita*/
		char direcao;
		int i = 0; //vari�vel que controlar� o andamento na String de codifica��o
		int posicaoNoVetorObtido = 0; //vari�vel que controlar� o salvamento de letras encontradas no vetor
		NoArvore atual;
		atual = arvoreFinal.getRaiz();
		do {
			/* Caso o n� atual n�o possua letra, avan�a-se um n� na �rvore, para a esquerda
			 * ou para direita, dependendo do caractere lido na String de c�digo*/
			if(atual.getLetra() == '\0') {
				direcao = vetorCodificacao[i];
				if(direcao == '0')
					atual = atual.filhoEsq;
				else if (direcao == '1')
					atual = atual.filhoDir;
				i++;
			}
			/* Caso seja encontrada uma letra(folha), ela � salva em um vetor e voltamos ao 
			 * in�cio da �rvore, at� que este esteja completamente preenchido*/
			else {
				vetorObtido[posicaoNoVetorObtido] = atual.getLetra();
				posicaoNoVetorObtido++;
				atual = arvoreFinal.getRaiz();				
			} 
		} while( posicaoNoVetorObtido < vetorObtido.length);
		
		/* Depois de percorrer a �rvore com o c�digo e salvar as letras encontradas na ordem
		 * em um vetor, verificamos se temos a sequ�ncia esperada*/
		for(i = 0; i < vetorAux.length; i++)
			assertEquals(vetorAux[i].getLetra(), vetorObtido[i]);

	}
	
	/*@Test
	public void testCodificarMensagem(){
		
		Fila fila = CriarObjetos.criarFila();
		NoArvore[] vetorAux = CriarObjetos.criarVetor();
		
		//Cria-se uma �rvore de Huffman com a fila gerada
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		arvoreFinal = arvoreFinal.criaArvore(fila);

		 C�digo esperado que seja gerado por uma �rvore de HuffMan para a String em quest�o
		 * em ordem crescente de frequ�ncia 
		String codificacao = "00" //Barra de espa�o
				+ "010" //A
				+ "0110" //T
				+"01110" //\n 
				+ "01111" //U
				+ "10" //S	
				+ "110" //I
				+ "1110" //Y
				+ "1111"; //E
				
		char[] vetorCodificacao = codificacao.toCharArray(); //Cria um vetor com essa String
		String teste = " AT\nUSIYE";//A string para o teste � criada na ordem em que a travessia � feita na �rvore
		
		String textoCodificado ="";//Uma String vazia � criada
		for(char original:teste.toCharArray()){//A string � percorrida
			textoCodificado += arvoreFinal.codificarCaractere(arvoreFinal.getRaiz(), new StringBuffer(), original);//Chama o m�todo de codifica��o
		}
		
		assertEquals(textoCodificado,codificacao);
	}*/
	
	@Test
	public void testDecodificarTexto(){
		
		Fila fila = CriarObjetos.criarFila();
		NoArvore[] vetorAux = CriarObjetos.criarVetor();
		
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		
		String codificacao = "00" //Barra de espa�o
				+ "010" //A
				+ "0110" //T
				+"01110" //\n 
				+ "01111" //U
				+ "10" //S	
				+ "110" //I
				+ "1110" //Y
				+ "1111"; //E
		
		String obtida;
		
		obtida = arvoreFinal.decodificarCaractere(arvoreFinal.getRaiz(), codificacao);
		
		String gabarito = " AT\nUSIYE";
		
		assertEquals(gabarito,obtida);
		
	}
	
	@Test
	public void criarDicionarioSucesso() {
		Fila fila = CriarObjetos.criarFila();
		//Cria-se uma �rvore de Huffman com a fila gerada
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		int quantidadeDeCaracteres = fila.obterTamanho();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.setDicionario(new String[quantidadeDeCaracteres][2]);
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer());
		
		String codificacao = "00" //Barra de espa�o
				+ "010" //A
				+ "0110" //T
				+"01110" //\n 
				+ "01111" //U
				+ "10" //S	
				+ "110" //I
				+ "1110" //Y
				+ "1111"; //E
		String [][] teste = arvoreFinal.getDicionario();//Recebe o dicion�rio
		String textoObtido = "";//Cria uma string para receber a sequ�ncia contida no dicion�rio;
		
		for(int i = 0;i < teste.length;i++){//Percorre o array do dicion�rio
			textoObtido += teste[i][1];//Concatena os c�digos recebidos
		}
		
		assertEquals(codificacao,textoObtido);
		
		String gabarito = " AT\nUSIYE";
		String caracteresObtidos = "";
		
		for(int i = 0;i < teste.length;i++){//Percorre o array do dicion�rio
			caracteresObtidos += teste[i][0];//Concatena os caracteres recebidos
		}
		
		assertEquals(gabarito,caracteresObtidos);
	}
	
}
