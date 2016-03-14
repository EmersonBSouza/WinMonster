package br.uefs.WinMonster.util;

public class ArvoreHuffman implements Comparable<ArvoreHuffman>{

	//Eclipse é bugado
	private NoArvore raiz;
	
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
	
	public String codificarMensagem(NoArvore No,StringBuffer construtorCodigo,char original){
		
		if(raiz.getFilhoDir()== null && raiz.getFilhoEsq()== null){//Se o nó for uma folha, este contém a letra a ser codificada
			if(raiz.getLetra()== original){//Se a letra for igual à letra desejada, a string final é retornada 
				return construtorCodigo.toString();//Isso só irá acontecer ao final das chamadas recursivas
			}
		}else{
			
			//Percorre a esquerda da árvore
			construtorCodigo.append('0');//Concatena "0" à string que está sendo construída, representando que o elemento está à esquerda
			String esquerda = codificarMensagem(raiz.getFilhoEsq(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verificação sobre qual tipo de nó está sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a criação da string
			
			//Percorre a direita da árvore
			construtorCodigo.append('1');//Concatena "1" à string que está sendo construída, representando que o elemento está à direita
			String direita = codificarMensagem(raiz.getFilhoDir(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verificação sobre qual tipo de nó está sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a criação da string
			
			if(esquerda == null)
				return direita;
			else
				return esquerda;
		}
		return null;
	}

	public NoArvore getRaiz() {
		return this.raiz;
	}
	
	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
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
