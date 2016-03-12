package br.uefs.WinMonster.util;

public class MIterador implements Iterador{
	
	Celula atual;//Refer�ncia para a c�lula atual da itera��o
	Celula inicio;//Refer�ncia para a c�lula primeiro da lista
	
	
	public MIterador(Object primeiro){
		atual = (Celula)primeiro;
		inicio = (Celula)primeiro;
	}
	
	/**
	 * Este m�todo faz o iterador retornar para a primeira posi��o da lista iterada
	 * */
	public void reset() {
		  atual = inicio;
	}
	
	/**
	 * Este m�todo verifica se existe pr�ximo elemento na lista
	 * @return atual!=null Boolean - Retorna true se houver pr�ximo
	 * */
	public boolean temProximo() {
		return atual != null;
	}
	
	/**
	 * Este m�todo avan�a para a pr�xima posi��o da lista e retorna o objeto atual
	 * @return objeto Object - Retorna o objeto atual que o iterador "aponta" */
	public Object obterProximo() {
		if(temProximo()){//Se n�o for o �ltimo elemento
			Object objeto = (Object) atual.getObjeto();
			atual = atual.getProximo();
			return objeto;
		}
		return null;
	}

}
