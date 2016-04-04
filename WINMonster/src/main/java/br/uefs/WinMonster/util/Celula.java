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
		 * @param objeto Object - Objeto pertencente à célula
		 * */
		
		public void setObjeto(Object objeto){
			this.objeto = objeto; 
		}
		
		/**
		 * Recupera a referência para a próxima célula
		 * @return proximo*/
		
		public Celula getProximo() {
			return proximo;
		}
		
		/**
		 * Atribui uma referência para a próxima célula
		 * @param proximo Celula - Referência da próxima célula*/
		public void setProximo(Celula proximo) {
			this.proximo = proximo;
		}
	
}
