package br.uefs.WinMonster.util;

public class Fila implements IFila{

	//Celula primeiro;
	private Lista objeto = new Lista();
	
	@Override
	public int obterTamanho(){
		return objeto.obterTamanho();
	}

	@Override
	public void inserirFinal(Object o) {
		this.objeto.inserirFinal(o);
	}

	@Override
	public Object removerInicio() {	
		return (Celula)objeto.removerInicio();
	}

	@Override
	public Celula recuperarInicio() {
		return objeto.primeiro;
		
	}

	@Override
	public Iterador iterador() {
		MIterador iterador= new MIterador(recuperarInicio());
		return iterador;
	}

	@Override
	public boolean estaVazia() {
		return this.objeto.estaVazia();
	}
}
