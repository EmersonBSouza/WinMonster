package br.uefs.WinMonster.util;

import java.nio.IntBuffer;

public class ArvoreHuffman implements Comparable<ArvoreHuffman>{

	private NoArvore raiz;
	private String [][] dicionario;

	public ArvoreHuffman criaArvore(Fila fila){

		if(fila.obterTamanho() > 1) {

			NoArvore novoNo = new NoArvore();
			ArvoreHuffman arvore1 = (ArvoreHuffman) fila.removerInicio().getObjeto();
			ArvoreHuffman arvore2 = (ArvoreHuffman) fila.removerInicio().getObjeto();
			NoArvore filhoEsq = arvore1.raiz;
			NoArvore filhoDir = arvore2.raiz;
			novoNo.setFilhoEsq(filhoEsq);
			novoNo.setFilhoDir(filhoDir);

			ArvoreHuffman novaArvore = new ArvoreHuffman();

			novoNo.setFrequencia(filhoDir.getFrequencia()+ filhoEsq.getFrequencia());

			novaArvore.setRaiz(novoNo);

			fila.inserirComPrioridade(novaArvore);
			criaArvore(fila);	
		}

		return (ArvoreHuffman)fila.recuperarInicio().getObjeto();
	}

	public void criarDicionario(NoArvore noAtual, StringBuffer construtorCodigo) {
		if(noAtual != null) {
			construtorCodigo.append('0');
			criarDicionario(noAtual.filhoEsq,construtorCodigo);
			if(noAtual.getFilhoDir()== null && noAtual.getFilhoEsq()== null) {
				adicionarLetraAoDicionario(noAtual.getLetra(), construtorCodigo.toString());
				construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
				return;
			}
			construtorCodigo.append('1');
			criarDicionario(noAtual.filhoDir,construtorCodigo);
		}
		if(construtorCodigo.length() > 0)
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);
	}

	public void adicionarLetraAoDicionario(char letra, String codigo) {
		int posicaoAtual = 0;
		while(dicionario[posicaoAtual][0] != null && posicaoAtual < dicionario.length)
			posicaoAtual++;
		dicionario[posicaoAtual][0] = Character.toString(letra);
		dicionario[posicaoAtual][1] = codigo;
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
	
	public String decodificarCaractere(NoArvore No,String codigo){
		String decodificacao = "";
		NoArvore raiz = No;

		for(char index :codigo.toCharArray()){

			if(index=='0'){//Se o caractere atual da String for 0, o deslocamento na árvore é feito para a esquerda
				if(No.getFilhoEsq().getFilhoEsq()== null){//Verifica se é um nó do tipo folha
					decodificacao += No.getFilhoEsq().getLetra();//Se for uma folha, o valor da letra é obtido e guardado na String de decodificação
					No = raiz;//Retorna para a raiz da árvore
				}

				else{ 
					No = No.getFilhoEsq();//Movimenta para a esquerda da árvore
				}
			}
			else if(index == '1'){//Se o caractere atual da String for 0, o deslocamento na árvore é feito para a direita
				if(No.getFilhoDir().getFilhoDir()==null){//Verifica se é um nó do tipo folha
					decodificacao += No.getFilhoDir().getLetra();//Se for uma folha, o valor da letra é obtido e guardado na String de decodificação
					No = raiz;//Retorna para a raiz da árvore
				}
				else{
					No=No.getFilhoDir();//Movimenta para a direita da árvore
				}		
			}			
		}

		return decodificacao;//Retorna a String concluída
	}
	public NoArvore getRaiz() {
		return this.raiz;
	}

	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
	}

	public String[][] getDicionario() {
		return this.dicionario;
	}

	public void setDicionario(String [][] dicionario) {
		this.dicionario = dicionario;
	}

	@Override
	public int compareTo(ArvoreHuffman arvore2) {
		if( this.getRaiz().frequencia < arvore2.getRaiz().frequencia)
			return -1;
		else if( this.getRaiz().frequencia > arvore2.getRaiz().frequencia)
			return 1;
		return 0;
	}
}
