package br.uefs.WinMonster.util;

public class Fila implements IFila{

	//Celula primeiro;
	private Lista lista = new Lista();

	@Override
	public int obterTamanho(){
		return lista.obterTamanho();
	}

	public void inserirComPrioridade(Object novoObjeto) {
		Celula atual;
		Celula anterior;

		if(lista.estaVazia()) {
			Celula nova = new Celula();
			nova.setObjeto(novoObjeto);
			lista.primeiro = nova;
		}
		else {
			atual = lista.primeiro;
			anterior = lista.primeiro;
			while(atual != null && ((Comparable) novoObjeto).compareTo(atual.getObjeto()) >= 0) {
				anterior = atual;
				atual = atual.getProximo();
			}
			if(atual==lista.primeiro)
				lista.inserirInicio(novoObjeto);
			else {
				Celula nova = new Celula();
				nova.setObjeto(novoObjeto);
				nova.setProximo(anterior.getProximo());
				anterior.setProximo(nova);
			}
		}
	}	


@Override
public void inserirFinal(Object o) {
	this.lista.inserirFinal(o);
}

@Override
public Object removerInicio() {	
	return (Celula)lista.removerInicio();
}

@Override
public Celula recuperarInicio() {
	return lista.primeiro;

}

@Override
public Iterador iterador() {
	MIterador iterador= new MIterador(recuperarInicio());
	return iterador;
}

@Override
public boolean estaVazia() {
	return this.lista.estaVazia();
}
}
