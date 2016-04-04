package br.uefs.WinMonster.util;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArvoreHuffmanTest {

	@Test
	public void testCriarArvoreSucesso() {

		Fila fila = CriarObjetos.criarFila();
		NoArvore[] vetorAux = CriarObjetos.criarVetor();
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
					atual = atual.getFilhoEsq();
				else if (direcao == '1')
					atual = atual.getFilhoDir();
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
	
	@Test
	public void testCodificarMensagem(){
		
		Fila fila = CriarObjetos.criarFila();
				
		//Cria-se uma árvore de Huffman com a fila gerada
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(),new StringBuffer());

		String textoOriginal = "SUSIE SAYS ITS EASY";
		String codigoEsperado = "10" //S
		+ "01111" //U
		+ "10" //S
		+ "110" //I
		+ "1111" // E
		+ "00" //espaço
		+ "10" //S
		+ "010" //A
		+ "1110" //Y
		+ "10" //S
		+ "00" //espaço
		+ "110" //I
		+ "0110" //T
		+ "10" //S
		+ "00" //espaço
		+ "1111" //E
		+ "010" //A
		+ "10" //S
		+ "1110"; //Y
		
		char[] caracteres = textoOriginal.toCharArray();
		
		String [][] dicionario = arvoreFinal.getDicionario();
		
		StringBuilder textoCodificado = new StringBuilder();
		for(int i=0; i<caracteres.length; i++)
			textoCodificado.append(dicionario[caracteres[i]][1]);	
		
		assertEquals(codigoEsperado,textoCodificado.toString());
	}
	
	@Test
	public void testDecodificarTexto(){
		
		Fila fila = CriarObjetos.criarFila();
				
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		
		String codigo = "10" //S
				+ "01111" //U
				+ "10" //S
				+ "110" //I
				+ "1111" // E
				+ "00" //espaço
				+ "10" //S
				+ "010" //A
				+ "1110" //Y
				+ "10" //S
				+ "00" //espaço
				+ "110" //I
				+ "0110" //T
				+ "10" //S
				+ "00" //espaço
				+ "1111" //E
				+ "010" //A
				+ "10" //S
				+ "1110"; //Y
		String textoEsperado = "SUSIE SAYS ITS EASY";
		
		String textoObtido = arvoreFinal.decodificarMensagem(arvoreFinal.getRaiz(), codigo);	
		
		assertEquals(textoEsperado, textoObtido);
		
	}
	
	@Test
	public void criarDicionarioSucesso() {
		Fila fila = CriarObjetos.criarFila();
		//Cria-se uma árvore de Huffman com a fila gerada
		ArvoreHuffman arvoreFinal = new ArvoreHuffman();
		int quantidadeDeCaracteres = fila.obterTamanho();
		arvoreFinal = arvoreFinal.criaArvore(fila);
		arvoreFinal.criarDicionario(arvoreFinal.getRaiz(), new StringBuffer());
		
		//(EDIT) Agora segue a ordem da tabela Ascii, assim como a String gabarito
		String codificacao =   "01110" //\n
							  +"00" //Barra de espaço 
							  + "010" //A
							  + "1111" //E
							  + "110" //I
							  + "10" //S
							  + "0110" //T	
							  + "01111" //U
							  + "1110" ;//Y
				/*"00" //Barra de espaço
				+ "010" //A
				+ "0110" //T
				+"01110" //\n 
				+ "01111" //U
				+ "10" //S	
				+ "110" //I
				+ "1110" //Y
				+ "1111"; //E*/
		
		String [][] teste = arvoreFinal.getDicionario();//Recebe o dicionário
		String textoObtido = "";//Cria uma string para receber a sequência contida no dicionário;
		
		for(int i = 0;i < teste.length;i++){//Percorre o array do dicionário
			if(teste[i][1]!=null){
				textoObtido += teste[i][1];//Concatena os códigos recebidos
			}
		}
		
		assertEquals(codificacao,textoObtido);
		
		String gabarito = "\n AEISTUY";
		String caracteresObtidos = "";
		
		for(int i = 0;i < teste.length;i++){//Percorre o array do dicionário
			if(teste[i][0]!=null){
				caracteresObtidos += teste[i][0];//Concatena os caracteres recebidos
			}
		}
		
		assertEquals(gabarito,caracteresObtidos);
	}
	
}
