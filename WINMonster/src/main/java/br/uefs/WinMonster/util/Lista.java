package br.uefs.WinMonster.util;



public class Lista implements ILista{

	Celula primeiro;
	
	/**
	 * Este método verifica se a lista tem algum elemento
	 * @return primeiro==null boolean - Retorna true se o primeiro elemento da lista for null
	 * */
	@Override
	public boolean estaVazia() {
		return primeiro == null;	
	}
	
	/**
	 * Este método calcula o tamanho da lista e retorna
	 * @return tamanho int - Retorna tamanho quando chegar ao fim da lista
	 * */
	@Override
	public int obterTamanho() {
		int tamanho = 0;
		Celula atual;
		atual = primeiro;
		while(atual!=null){//Enquanto não chegar ao fim da lista
			tamanho++;
			atual= atual.getProximo();
		}		
		return tamanho;//Retorna tamanho da lista
	}
	
	
	/**
	 * Recupera a primeira célula da lista
	 * @return primeiro Celula - Retorna a primeira célula
	 * */
	public Celula recuperarInicio(){
		return primeiro;
	}
	
	/**
	 * Este método insere um objeto no ínicio da lista
	 * @param objeto Object - Recebe um objeto genérico
	 */
	@Override
	public void inserirInicio(Object objeto) {
		
		Celula nova = new Celula();//O objeto do tipo "Celula" é instanciado
		nova.setObjeto(objeto); //O objeto genérico é atribuído à célula
		nova.setProximo(primeiro);// A referência para a próxima célula aponta para o primeiro
		primeiro = nova; // A primeira célula "aponta" para o novo elemento
	}
	
	/**
	 * Este método insere um objeto no ínicio da lista
	 * @param objeto Object - Recebe um objeto genérico
	 */
	@Override
	public void inserirFinal(Object objeto) {
		
		Celula ultimaCelula;// Uma referência para a última célula é criada
		Celula nova = new Celula();// Um objeto do tipo Celula é instanciado
		ultimaCelula = primeiro;
		nova.setObjeto(objeto);
		
		if(primeiro == null){//Se a lista estiver vazia a ação é executada
			primeiro=nova;
		}
		else{//Se houver algum elemento na lista, o fim da mesma será buscado
			while(ultimaCelula.getProximo()!=null){
				ultimaCelula = ultimaCelula.getProximo();
			}
			ultimaCelula.setProximo(nova);//Quando a última célula é encontrada
		}								 //a referência para o próximo passa a "apontar" para a nova celula
	}

	/**
	 * Este método remove o primeiro elemento da lista
	 * @return primeiro Celula - Retorna o novo primeiro elemento
	 * */
	@Override
	public Object removerInicio() {
		Celula aux = primeiro;
		primeiro = primeiro.getProximo();
		return aux;
	}
		
	/**
	 * Este método remove o último elemento da lista
	 * @return penultimaCelula Celula - penultimaCelula passa a ser a última quando o método termina
	 * */
	@Override
	public Object removerFinal() {
		
		Celula ultimaCelula = primeiro;
		Celula penultimaCelula = ultimaCelula;
		
		while(ultimaCelula.getProximo()!= null){
			penultimaCelula = ultimaCelula;
			ultimaCelula = ultimaCelula.getProximo();
		}
		penultimaCelula.setProximo(null);
		
		
		return penultimaCelula;
	}

	@Override
	public Object remover(int index) {

		Object objetoAtual;
		Celula atual;
		Celula anterior;

		if(index == 1){
			objetoAtual = removerInicio();
		}
		else{
			atual = primeiro;
			anterior = primeiro;

			int contador=1;

			while(contador != index){
				anterior = atual;
				atual = atual.getProximo();
				contador++;
			}
			anterior.setProximo(atual.getProximo());
			objetoAtual = atual.getObjeto();
		}	
		return objetoAtual;
	}

	@Override
	public Object recuperar(int index) {
		
		Object objetoProcurado;
		Celula atual = primeiro;
		int contador = 1;
		
		while(contador != index){
			atual = atual.getProximo();
			if(atual.getProximo()==null){
				return null;
			}
			contador++;
		}
		objetoProcurado = atual.getObjeto();
		return objetoProcurado;
	}

	/**
	 * Este método instancia um objeto iterador
	 * @return iterador Iterador - Retorna um iterador do tipo da interface
	 * */
	@Override
	public Iterador iterador() {
		MIterador iterador = new MIterador(primeiro);
		return iterador;
	}

}
