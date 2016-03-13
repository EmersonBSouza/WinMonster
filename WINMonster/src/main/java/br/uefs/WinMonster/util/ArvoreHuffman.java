package br.uefs.WinMonster.util;

public class ArvoreHuffman implements Comparable<ArvoreHuffman>{

	
	private NoArvore raiz;
	
	public ArvoreHuffman criaArvore(Fila fila){
		//Se houver apenas um elemento na fila, não entrará na condição e será retornada a árvore com o objeto
		ArvoreHuffman arvoreFinal = (ArvoreHuffman)fila.recuperarInicio().getObjeto();
		
		if(fila.obterTamanho()> 1) {
			
			NoArvore novoNo = new NoArvore();
			NoArvore filhoEsq =((ArvoreHuffman) fila.removerInicio()).getRaiz();
			NoArvore filhoDir =((ArvoreHuffman) fila.removerInicio()).getRaiz();
			novoNo.setFilhoEsq(filhoDir);
			novoNo.setFilhoDir(filhoEsq);
			novoNo.setFrequencia(novoNo.getFilhoDir().getFrequencia()+ novoNo.getFilhoEsq().getFrequencia());
			
			fila.inserirComPrioridade(novoNo);
			criaArvore(fila);
			raiz = (NoArvore)fila.recuperarInicio().getObjeto();
			
		}
		
		return arvoreFinal;
	}
	
	public void codificarMensagem(NoArvore No){
		if(raiz!=null){
			codificarMensagem(raiz.getFilhoEsq());
			//Manipulação
			codificarMensagem(raiz.getFilhoDir());
		}
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
