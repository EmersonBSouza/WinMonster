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
	public String[][] criarDicionario(NoArvore noAtual, StringBuffer construtorCodigo, String[][] dicionario) {
		if(noAtual != null) {
			/*Anda para a direita da �rvore, adicionando 0 ao c�digo*/
			construtorCodigo.append('0');
			criarDicionario(noAtual.getFilhoEsq(),construtorCodigo, dicionario);
			/*Caso seja encontrado uma folha, que possui um caractere, ela � adicionada a �rvore*/
			if(noAtual.getFilhoDir()== null && noAtual.getFilhoEsq()== null) {
				int posicaoAtual = 0;
				/*Percorre o dicion�rio procurando um lugar vazio para inserir a nova letra e seu c�digo*/
				while(dicionario[posicaoAtual][0] != null && posicaoAtual < dicionario.length)
					posicaoAtual++;
				dicionario[posicaoAtual][0] = Character.toString(noAtual.getLetra());
				dicionario[posicaoAtual][1] = construtorCodigo.toString();
				//adicionarLetraAoDicionario(noAtual.getLetra(), construtorCodigo.toString());
				if(construtorCodigo.length() > 0)
					construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
				return dicionario;
			}
			/*Anda para a esquerda da �rvore, adicionando 1 ao c�digo*/
			construtorCodigo.append('1');
			criarDicionario(noAtual.getFilhoDir(),construtorCodigo, dicionario);
		}
		/*Caso seja encontrado um No nulo, remove-se um caractere do c�digo*/
		if(construtorCodigo.length() > 0)
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
		return dicionario;
	}
	
	/**
	 * Adiciona um caractere com seu respectivo c�digo ao dicion�rio da �rvore
	 * @param letra
	 * @param codigo
	 */
	public void adicionarLetraAoDicionario(char letra, String codigo) {
		int posicaoAtual = 0;
		/*Percorre o dicion�rio procurando um lugar vazio para inserir a nova letra e seu c�digo*/
		while(dicionario[posicaoAtual][0] != null && posicaoAtual < dicionario.length)
			posicaoAtual++;
		dicionario[posicaoAtual][0] = Character.toString(letra);
		dicionario[posicaoAtual][1] = codigo;
	}
	/**
	 * Este m�todo codifica o texto
	 * @param String texto
	 * @return String textoCodificado.toString()*/
	public String codificarMensagem(String texto){
		StringBuilder textoCodificado = new StringBuilder();
		char[] caracteres = texto.toCharArray();
		//Percorre o texto
		for(int i=0;i<texto.length();i++){
			int posicaoAtual = 0;
			String caractereAtual = Character.toString(caracteres[i]);
			/*Para cada letra percorre o dicion�rio procurando a letra e seu c�digo*/
			while(!dicionario[posicaoAtual][0].equals(caractereAtual)) {
				posicaoAtual++;
			}
			textoCodificado.append(dicionario[posicaoAtual][1]);
		}
		return textoCodificado.toString();
	}
	/**
	 * Este m�todo � respons�vel por decodificar a mensagem 
	 * @param NoArvore noRaiz
	 * @param String texto
	 * @throws NullPointerException - Acontece se o texto estiver codificado em Unicode
	 * @return String decodificacao.toString()*/
	public String decodificarMensagem(NoArvore noRaiz,String texto)throws NullPointerException{
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
