package br.uefs.WinMonster.util;

public class ArvoreHuffman implements Comparable<ArvoreHuffman>{

	//Eclipse � bugado
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
		
		if(raiz.getFilhoDir()== null && raiz.getFilhoEsq()== null){//Se o n� for uma folha, este cont�m a letra a ser codificada
			if(raiz.getLetra()== original){//Se a letra for igual � letra desejada, a string final � retornada 
				return construtorCodigo.toString();//Isso s� ir� acontecer ao final das chamadas recursivas
			}
		}else{
			
			//Percorre a esquerda da �rvore
			construtorCodigo.append('0');//Concatena "0" � string que est� sendo constru�da, representando que o elemento est� � esquerda
			String esquerda = codificarMensagem(raiz.getFilhoEsq(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verifica��o sobre qual tipo de n� est� sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a cria��o da string
			
			//Percorre a direita da �rvore
			construtorCodigo.append('1');//Concatena "1" � string que est� sendo constru�da, representando que o elemento est� � direita
			String direita = codificarMensagem(raiz.getFilhoDir(),construtorCodigo,original);//Chama o metodo recursivamente, para realizar a verifica��o sobre qual tipo de n� est� sendo tratado folha/pai
			construtorCodigo.deleteCharAt(construtorCodigo.length()-1);//Remove o elemento indesejado que foi concatenado durante a cria��o da string
			
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
