package br.uefs.WinMonster.util;

public class MIterador implements Iterador{
	
	Celula atual;//Referência para a célula atual da iteração
	Celula inicio;//Referência para a célula primeiro da lista
	
	
	public MIterador(Object primeiro){
		atual = (Celula)primeiro;
		inicio = (Celula)primeiro;
	}
	
	/**
	 * Este método faz o iterador retornar para a primeira posição da lista iterada
	 * */
	public void reset() {
		  atual = inicio;
	}
	
	/**
	 * Este método verifica se existe próximo elemento na lista
	 * @return atual!=null Boolean - Retorna true se houver próximo
	 * */
	public boolean temProximo() {
		return atual != null;
	}
	
	/**
	 * Este método avança para a próxima posição da lista e retorna o objeto atual
	 * @return objeto Object - Retorna o objeto atual que o iterador "aponta" */
	public Object obterProximo() {
		if(temProximo()){//Se não for o último elemento
			Object objeto = (Object) atual.getObjeto();
			atual = atual.getProximo();
			return objeto;
		}
		return null;
	}

}
