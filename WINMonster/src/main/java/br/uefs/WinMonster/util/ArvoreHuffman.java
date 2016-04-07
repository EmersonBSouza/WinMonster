package br.uefs.WinMonster.util;

import java.io.Serializable;

public class ArvoreHuffman implements Comparable<ArvoreHuffman>,Serializable{

	private NoArvore raiz;
	private String [][] dicionario;

	/**
	 * Cria uma árvore de Huffman
	 * @param fila
	 * @return novaArvoreDeHuffman
	 */
	public ArvoreHuffman criaArvore(Fila fila){

		/*Enquanto houver mais de um elemento na fila */
		if(fila.obterTamanho() > 1) {

			/* remove as duas primeiras árvores da fila e salva suas raízes em nós */
			ArvoreHuffman arvore1 = (ArvoreHuffman) fila.removerInicio().getObjeto();
			ArvoreHuffman arvore2 = (ArvoreHuffman) fila.removerInicio().getObjeto();
			NoArvore filhoEsq = arvore1.raiz;
			NoArvore filhoDir = arvore2.raiz;
			
			/* cria-se um nó pai que possui os dois nós removidos como filhos */
			NoArvore novoNo = new NoArvore();
			novoNo.setFilhoEsq(filhoEsq);
			novoNo.setFilhoDir(filhoDir);
			
			/* O nó pai possui frequência igual a soma das frequências dos nós filhos */
			novoNo.setFrequencia(filhoDir.getFrequencia()+ filhoEsq.getFrequencia());
			
			/* Cria-se uma árvore com esse novo nó*/
			ArvoreHuffman novaArvore = new ArvoreHuffman();
			novaArvore.setRaiz(novoNo);
			
			/*Adiciona a árvore a fila e chama o método novamente, que dessa vez possui um
			 * elemento a menos na fila*/
			fila.inserirComPrioridade(novaArvore);
			criaArvore(fila);	
		}
		/*Quando houver apenas uma árvore na fila, ela é a árvore de Huffman completa*/
		return (ArvoreHuffman)fila.recuperarInicio().getObjeto();
	}
	
	/**
	 * -Recursivo- Encontra os caracteres e seus respectivos códigos e cria o dicionário da árvore
	 * @param noAtual
	 * @param construtorCodigo
	 */
	public String[][] criarDicionario(NoArvore noAtual, StringBuffer construtorCodigo, String[][] dicionario) {
		if(noAtual != null) {
			/*Anda para a direita da árvore, adicionando 0 ao código*/
			construtorCodigo.append('0');
			criarDicionario(noAtual.getFilhoEsq(),construtorCodigo, dicionario);
			/*Caso seja encontrado uma folha, que possui um caractere, ela é adicionada a árvore*/
			if(noAtual.getFilhoDir()== null && noAtual.getFilhoEsq()== null) {
				int posicaoAtual = 0;
				/*Percorre o dicionário procurando um lugar vazio para inserir a nova letra e seu código*/
				while(dicionario[posicaoAtual][0] != null && posicaoAtual < dicionario.length)
					posicaoAtual++;
				dicionario[posicaoAtual][0] = Character.toString(noAtual.getLetra());
				dicionario[posicaoAtual][1] = construtorCodigo.toString();
				//adicionarLetraAoDicionario(noAtual.getLetra(), construtorCodigo.toString());
				if(construtorCodigo.length() > 0)
					construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
				return dicionario;
			}
			/*Anda para a esquerda da árvore, adicionando 1 ao código*/
			construtorCodigo.append('1');
			criarDicionario(noAtual.getFilhoDir(),construtorCodigo, dicionario);
		}
		/*Caso seja encontrado um No nulo, remove-se um caractere do código*/
		if(construtorCodigo.length() > 0)
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
		return dicionario;
	}
	
	/**
	 * Adiciona um caractere com seu respectivo código ao dicionário da árvore
	 * @param letra
	 * @param codigo
	 */
	public void adicionarLetraAoDicionario(char letra, String codigo) {
		int posicaoAtual = 0;
		/*Percorre o dicionário procurando um lugar vazio para inserir a nova letra e seu código*/
		while(dicionario[posicaoAtual][0] != null && posicaoAtual < dicionario.length)
			posicaoAtual++;
		dicionario[posicaoAtual][0] = Character.toString(letra);
		dicionario[posicaoAtual][1] = codigo;
	}
	/**
	 * Este método codifica o texto
	 * @param String texto
	 * @return String textoCodificado.toString()*/
	public String codificarMensagem(String texto){
		StringBuilder textoCodificado = new StringBuilder();
		char[] caracteres = texto.toCharArray();
		//Percorre o texto
		for(int i=0;i<texto.length();i++){
			int posicaoAtual = 0;
			String caractereAtual = Character.toString(caracteres[i]);
			/*Para cada letra percorre o dicionário procurando a letra e seu código*/
			while(!dicionario[posicaoAtual][0].equals(caractereAtual)) {
				posicaoAtual++;
			}
			textoCodificado.append(dicionario[posicaoAtual][1]);
		}
		return textoCodificado.toString();
	}
	/**
	 * Este método é responsável por decodificar a mensagem 
	 * @param NoArvore noRaiz
	 * @param String texto
	 * @throws NullPointerException - Acontece se o texto estiver codificado em Unicode
	 * @return String decodificacao.toString()*/
	public String decodificarMensagem(NoArvore noRaiz,String texto)throws NullPointerException{
		StringBuilder decodificacao = new StringBuilder();
		NoArvore noAtual = noRaiz;

		for(int index=0;index<texto.length();index++){

			if(texto.charAt(index) == '0'){//Se o caractere atual da String for 0, o deslocamento na árvore é feito para a esquerda
				noAtual = noAtual.getFilhoEsq();
			}
			else if(texto.charAt(index) == '1'){//Se o caractere atual da String for 0, o deslocamento na árvore é feito para a direita
				noAtual = noAtual.getFilhoDir();
			}	
			if(noAtual.getLetra()!= '\0'){
				decodificacao.append(noAtual.getLetra());
				noAtual = noRaiz;
			}		
		}

		return decodificacao.toString();//Retorna a String concluída
	}
	
	/**
	 * Retorna a raiz da árvore
	 * @return
	 */
	public NoArvore getRaiz() {
		return this.raiz;
	}
	
	/**
	 * Atribui uma nova raiz a árvore
	 * @param raiz
	 */
	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
	}
	
	/**
	 * Retorna o dicionário da árvore
	 * @return
	 */
	public String[][] getDicionario() {
		return this.dicionario;
	}

	/**
	 * Atribui um novo dicionário a árvore
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
