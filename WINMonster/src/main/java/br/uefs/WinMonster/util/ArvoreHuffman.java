package br.uefs.WinMonster.util;

public class ArvoreHuffman {

	
	Fila arvores = new Fila();
	private NoArvore raiz;
	
	public ArvoreHuffman criaArvore(Fila fila){
		//Se houver apenas um elemento na fila, n�o entrar� na condi��o e ser� retornada a �rvore com o objeto
		ArvoreHuffman arvoreFinal = (ArvoreHuffman)fila.recuperarInicio().getObjeto();
		
		if(fila.obterTamanho()> 1){
			
			NoArvore no = new NoArvore();
			no.setFilhoEsq(fila.removerInicio());
			no.setFilhoDir(fila.removerInicio());
			no.setFrequencia(no.getFilhoDir().getFrequencia()+ no.getFilhoEsq().getFrequencia());
			
			arvores.inserirComPrioridade(no);
			arvoreFinal = (ArvoreHuffman)criaArvore(fila);
			
		}
		
		return arvoreFinal;
	}
	
	public void codificarMensagem(ArvoreHuffman arvore){
		if(arvore!=null){
			codificarMensagem(arvore.getFilhoEsq);
			//Manipula��o
			codificarMensagem(arvore.getFilhoDir());
		}
	}
}
