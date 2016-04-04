package br.uefs.WinMonster.util;

import java.io.Serializable;

public class ArvoreHuffman implements Comparable<ArvoreHuffman>,Serializable{

	private NoArvore raiz;
	private String [][] dicionario;

	/**
	 * Cria uma �rvore de Huffman
	 * @param fila
	 * @return novaArvoreDeHuffman
	 */
	public ArvoreHuffman criaArvore(Fila fila){

		/*Enquanto houver mais de um elemento na fila */
		if(fila.obterTamanho() > 1) {

			/* remove as duas primeiras �rvores da fila e salva suas ra�zes em n�s */
			ArvoreHuffman arvore1 = (ArvoreHuffman) fila.removerInicio().getObjeto();
			ArvoreHuffman arvore2 = (ArvoreHuffman) fila.removerInicio().getObjeto();
			NoArvore filhoEsq = arvore1.raiz;
			NoArvore filhoDir = arvore2.raiz;
			
			/* cria-se um n� pai que possui os dois n�s removidos como filhos */
			NoArvore novoNo = new NoArvore();
			novoNo.setFilhoEsq(filhoEsq);
			novoNo.setFilhoDir(filhoDir);
			
			/* O n� pai possui frequ�ncia igual a soma das frequ�ncias dos n�s filhos */
			novoNo.setFrequencia(filhoDir.getFrequencia()+ filhoEsq.getFrequencia());
			
			/* Cria-se uma �rvore com esse novo n�*/
			ArvoreHuffman novaArvore = new ArvoreHuffman();
			novaArvore.setRaiz(novoNo);
			
			/*Adiciona a �rvore a fila e chama o m�todo novamente, que dessa vez possui um
			 * elemento a menos na fila*/
			fila.inserirComPrioridade(novaArvore);
			criaArvore(fila);	
		}
		/*Quando houver apenas uma �rvore na fila, ela � a �rvore de Huffman completa*/
		return (ArvoreHuffman)fila.recuperarInicio().getObjeto();
	}
	
	/**
	 * -Recursivo- Encontra os caracteres e seus respectivos c�digos e cria o dicion�rio da �rvore
	 * @param noAtual
	 * @param construtorCodigo
	 */
	public void criarDicionario(NoArvore noAtual, StringBuffer construtorCodigo) {
		if(noAtual != null) {
			/*Anda para a direita da �rvore, adicionando 0 ao c�digo*/
			construtorCodigo.append('0');
			criarDicionario(noAtual.getFilhoEsq(),construtorCodigo);
			/*Caso seja encontrado uma folha, que possui um caractere, ela � adicionada a �rvore*/
			if(noAtual.getFilhoDir()== null && noAtual.getFilhoEsq()== null) {
				adicionarLetraAoDicionario(noAtual.getLetra(), construtorCodigo.toString());
				construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
				return;
			}
			/*Anda para a esquerda da �rvore, adicionando 1 ao c�digo*/
			construtorCodigo.append('1');
			criarDicionario(noAtual.getFilhoDir(),construtorCodigo);
		}
		/*Caso seja encontrado um No nulo, remove-se um caractere do c�digo*/
		if(construtorCodigo.length() > 0)
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
	}
	
	/**
	 * Adiciona um caractere com seu respectivo c�digo ao dicion�rio da �rvore
	 * @param letra
	 * @param codigo
	 */
	public void adicionarLetraAoDicionario(char letra, String codigo) {
		int posicaoAtual = 0;
		/*Percorre o dicion�rio procurando um lugar vazio para inserir a nova letra e seu c�digo*/
		while(dicionario[(int)letra][0] != null && posicaoAtual < dicionario.length)
			posicaoAtual++;
		dicionario[(int)letra][0] = Character.toString(letra);
		dicionario[(int)letra][1] = codigo;
	}
	
	/*public String codificarCaractere(NoArvore No,StringBuffer construtorCodigo,char original){

		if(No.getFilhoDir()== null && No.getFilhoEsq()== null){//Se o n� for uma folha, este cont�m a letra a ser codificada
			if(No.getLetra()== original){//Se a letra for igual � letra desejada, a string final � retornada 
				return construtorCodigo.toString();//Isso s� ir� acontecer ao final das chamadas recursivas
			}
		}else{

			//Percorre a esquerda da �rvore
			construtorCodigo.append('0');//Concatena "0" � string que est� sendo constru�da, representando que o elemento est� � esquerda
			String esquerda = codificarCaractere(No.getFilhoEsq(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verifica��o sobre qual tipo de n� est� sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a cria��o da string

			//Percorre a direita da �rvore
			construtorCodigo.append('1');//Concatena "1" � string que est� sendo constru�da, representando que o elemento est� � direita
			String direita = codificarCaractere(No.getFilhoDir(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verifica��o sobre qual tipo de n� est� sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a cria��o da string

			if(esquerda == null)
				return direita;
			else
				return esquerda;
		}
		return null;
	}*/
	
	public String decodificarCaractere(NoArvore noRaiz,String texto){
		StringBuilder decodificacao = new StringBuilder();
		NoArvore noAtual = noRaiz;

		for(int index=0;index<texto.length();index++){

			if(texto.charAt(index) == '0'){//Se o caractere atual da String for 0, o deslocamento na �rvore � feito para a esquerda
				noAtual = noAtual.getFilhoEsq();
			}
			else if(texto.charAt(index) == '1'){//Se o caractere atual da String for 0, o deslocamento na �rvore � feito para a direita
				noAtual = noAtual.getFilhoDir();
			}	
			if(noAtual.getLetra()!= '\0'){
				decodificacao.append(noAtual.getLetra());
				noAtual = noRaiz;
			}		
		}

		return decodificacao.toString();//Retorna a String conclu�da
	}
	
	/**
	 * Retorna a raiz da �rvore
	 * @return
	 */
	public NoArvore getRaiz() {
		return this.raiz;
	}
	
	/**
	 * Atribui uma nova raiz a �rvore
	 * @param raiz
	 */
	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
	}
	
	/**
	 * Retorna o dicion�rio da �rvore
	 * @return
	 */
	public String[][] getDicionario() {
		return this.dicionario;
	}

	/**
	 * Atribui um novo dicion�rio a �rvore
	 * @param raiz
	 */
	public void setDicionario(String [][] dicionario) {
		this.dicionario = dicionario;
	}
	
	@Override
	public int compareTo(ArvoreHuffman arvore2) {
		if( this.getRaiz().getFrequencia() < arvore2.getRaiz().getFrequencia())
			return -1;
		else if( this.getRaiz().getFrequencia() > arvore2.getRaiz().getFrequencia())
			return 1;
		return 0;
	}
}
