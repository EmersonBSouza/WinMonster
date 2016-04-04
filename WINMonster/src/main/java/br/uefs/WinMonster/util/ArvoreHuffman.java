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
	public void criarDicionario(NoArvore noAtual, StringBuffer construtorCodigo) {
		if(noAtual != null) {
			/*Anda para a direita da árvore, adicionando 0 ao código*/
			construtorCodigo.append('0');
			criarDicionario(noAtual.getFilhoEsq(),construtorCodigo);
			/*Caso seja encontrado uma folha, que possui um caractere, ela é adicionada a árvore*/
			if(noAtual.getFilhoDir()== null && noAtual.getFilhoEsq()== null) {
				adicionarLetraAoDicionario(noAtual.getLetra(), construtorCodigo.toString());
				construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
				return;
			}
			/*Anda para a esquerda da árvore, adicionando 1 ao código*/
			construtorCodigo.append('1');
			criarDicionario(noAtual.getFilhoDir(),construtorCodigo);
		}
		/*Caso seja encontrado um No nulo, remove-se um caractere do código*/
		if(construtorCodigo.length() > 0)
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
	}
	
	/**
	 * Adiciona um caractere com seu respectivo código ao dicionário da árvore
	 * @param letra
	 * @param codigo
	 */
	public void adicionarLetraAoDicionario(char letra, String codigo) {
		int posicaoAtual = 0;
		/*Percorre o dicionário procurando um lugar vazio para inserir a nova letra e seu código*/
		while(dicionario[(int)letra][0] != null && posicaoAtual < dicionario.length)
			posicaoAtual++;
		dicionario[(int)letra][0] = Character.toString(letra);
		dicionario[(int)letra][1] = codigo;
	}
	
	/*public String codificarCaractere(NoArvore No,StringBuffer construtorCodigo,char original){

		if(No.getFilhoDir()== null && No.getFilhoEsq()== null){//Se o nó for uma folha, este contém a letra a ser codificada
			if(No.getLetra()== original){//Se a letra for igual à letra desejada, a string final é retornada 
				return construtorCodigo.toString();//Isso só irá acontecer ao final das chamadas recursivas
			}
		}else{

			//Percorre a esquerda da árvore
			construtorCodigo.append('0');//Concatena "0" à string que está sendo construída, representando que o elemento está à esquerda
			String esquerda = codificarCaractere(No.getFilhoEsq(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verificação sobre qual tipo de nó está sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a criação da string

			//Percorre a direita da árvore
			construtorCodigo.append('1');//Concatena "1" à string que está sendo construída, representando que o elemento está à direita
			String direita = codificarCaractere(No.getFilhoDir(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verificação sobre qual tipo de nó está sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a criação da string

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
