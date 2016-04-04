package br.uefs.WinMonster.util;

public class Celula{

		private Object objeto;
		private Celula proximo;
		
		/**
		 * Recupera um objeto
		 * @return objeto
		 */
		public Object getObjeto(){
			return this.objeto;
		}
		
		/**
		 * Atribui um valor ao Objeto
		 * @param objeto Object - Objeto pertencente � c�lula
		 * */
		
		public void setObjeto(Object objeto){
			this.objeto = objeto; 
		}
		
		/**
		 * Recupera a refer�ncia para a pr�xima c�lula
		 * @return proximo*/
		
		public Celula getProximo() {
			return proximo;
		}
		
		/**
		 * Atribui uma refer�ncia para a pr�xima c�lula
		 * @param proximo Celula - Refer�ncia da pr�xima c�lula*/
		public void setProximo(Celula proximo) {
			this.proximo = proximo;
		}
	
}
