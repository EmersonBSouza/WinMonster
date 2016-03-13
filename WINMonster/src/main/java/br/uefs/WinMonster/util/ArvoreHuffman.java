package br.uefs.WinMonster.util;

public class ArvoreHuffman implements Comparable<ArvoreHuffman>{

	//Eclipse é bugado
	private NoArvore raiz;
	
	public ArvoreHuffman criaArvore(Fila fila){
		
		if(fila.obterTamanho() > 1) {
			
			NoArvore novoNo = new NoArvore();
			NoArvore filhoEsq =((ArvoreHuffman) fila.removerInicio()).getRaiz();
			NoArvore filhoDir =((ArvoreHuffman) fila.removerInicio()).getRaiz();
			ArvoreHuffman novaArvore = new ArvoreHuffman();
			
			novoNo.setFilhoEsq(filhoEsq);
			novoNo.setFilhoDir(filhoDir);
			novoNo.setFrequencia(filhoDir.getFrequencia()+ filhoEsq.getFrequencia());
			
			novaArvore.setRaiz(novoNo);
			
			fila.inserirComPrioridade(novaArvore);
			criaArvore(fila);	
		}
		
		return (ArvoreHuffman)fila.recuperarInicio().getObjeto();
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
