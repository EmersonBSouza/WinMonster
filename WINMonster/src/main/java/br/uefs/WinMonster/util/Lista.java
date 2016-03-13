package br.uefs.WinMonster.util;



public class Lista implements ILista{

	Celula primeiro;
	
	/**
	 * Este m�todo verifica se a lista tem algum elemento
	 * @return primeiro==null boolean - Retorna true se o primeiro elemento da lista for null
	 * */
	@Override
	public boolean estaVazia() {
		return primeiro == null;	
	}
	
	/**
	 * Este m�todo calcula o tamanho da lista e retorna
	 * @return tamanho int - Retorna tamanho quando chegar ao fim da lista
	 * */
	@Override
	public int obterTamanho() {
		int tamanho = 0;
		Celula atual;
		atual = primeiro;
		while(atual!=null){//Enquanto n�o chegar ao fim da lista
			tamanho++;
			atual= atual.getProximo();
		}		
		return tamanho;//Retorna tamanho da lista
	}
	
	
	/**
	 * Recupera a primeira c�lula da lista
	 * @return primeiro Celula - Retorna a primeira c�lula
	 * */
	public Celula getPrimeiro(){
		return primeiro;
	}
	
	/**
	 * Este m�todo insere um objeto no �nicio da lista
	 * @param objeto Object - Recebe um objeto gen�rico
	 */
	@Override
	public void inserirInicio(Object objeto) {
		
		Celula nova = new Celula();//O objeto do tipo "Celula" � instanciado
		nova.setObjeto(objeto); //O objeto gen�rico � atribu�do � c�lula
		nova.setProximo(primeiro);// A refer�ncia para a pr�xima c�lula aponta para o primeiro
		primeiro = nova; // A primeira c�lula "aponta" para o novo elemento
	}
	
	/**
	 * Este m�todo insere um objeto no �nicio da lista
	 * @param objeto Object - Recebe um objeto gen�rico
	 */
	@Override
	public void inserirFinal(Object objeto) {
		
		Celula ultimaCelula;// Uma refer�ncia para a �ltima c�lula � criada
		Celula nova = new Celula();// Um objeto do tipo Celula � instanciado
		ultimaCelula = primeiro;
		nova.setObjeto(objeto);
		
		if(primeiro == null){//Se a lista estiver vazia a a��o � executada
			primeiro=nova;
		}
		else{//Se houver algum elemento na lista, o fim da mesma ser� buscado
			while(ultimaCelula.getProximo()!=null){
				ultimaCelula = ultimaCelula.getProximo();
			}
			ultimaCelula.setProximo(nova);//Quando a �ltima c�lula � encontrada
		}								 //a refer�ncia para o pr�ximo passa a "apontar" para a nova celula
	}

	/**
	 * Este m�todo remove o primeiro elemento da lista
	 * @return primeiro Celula - Retorna o novo primeiro elemento
	 * */
	@Override
	public Object removerInicio() {
		Celula aux = primeiro;
		primeiro = primeiro.getProximo();
		return aux;
	}
	/**
	 *Este m�todo guarda o �ltimo elemento removido do inicio
	 *@return aux Celula - Retorna o �ltimo elemento removido 
	 */
	 
	public Celula recuperaRemovido(){
		Celula aux = primeiro;
		primeiro = primeiro.getProximo();
		return aux;
	}
	/**
	 * Este m�todo remove o �ltimo elemento da lista
	 * @return penultimaCelula Celula - penultimaCelula passa a ser a �ltima quando o m�todo termina
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

	public Object recuperarCelula(int index) {
		
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
		objetoProcurado = atual;
		return objetoProcurado;
	}
	public int buscaIndex(Object objeto){
		
		int index = 1;
		
		Celula celulaAtual = primeiro;
		
		if(objeto == null){
			return -1;
		}
		while(!objeto.equals(celulaAtual.getObjeto())){
			celulaAtual = celulaAtual.getProximo();
			index++;
			if(celulaAtual == null){
				index = -1;
				return index;
			}			
		}
		return index;
	}
	/**
	 * Este m�todo divide a lista
	 * @param lista Lista - Recebe uma lista para ser dividida
	 * @return auxiliar Lista - Quando a lista for ordenada ser� retornada 
	 * */
	
	public Lista ordena(Lista lista){
		
		Lista primeira = new Lista();
		Lista segunda = new Lista();
		
		if(lista.obterTamanho()> 1){
			int inicio = 1;
			int meio = lista.obterTamanho()/2;
			int fim = lista.obterTamanho();
			
			for(int i=inicio; i <= meio-inicio+1;i++){//Cria a lista da esquerda
				primeira.inserirFinal(lista.recuperaRemovido().getObjeto());
			}
		
			for(int i=0; i < fim-meio;i++){//Cria a lista da direita
				segunda.inserirFinal(lista.recuperaRemovido().getObjeto());
			}
			ordena(primeira);//Chamada Recursiva
			ordena(segunda);//Chamada Recursiva
			mergeSort(lista, primeira,segunda);//Chamada Recursiva
		}

		return lista;
	}
	/**
	 * Este m�todo compara os elementos de duas listas e faz a troca das posi��es se for necess�rio
	 * @param lista Lista
	 * @param primeira Lista
	 * @param segunda Lista
	 * */
	public void mergeSort(Lista lista,Lista primeira,Lista segunda){
		
		
		 while(primeira.getPrimeiro()!= null && segunda.getPrimeiro()!=null){//Enquanto houver objetos nas duas listas
			 Celula celula1 = (Celula)primeira.getPrimeiro();
			 Celula celula2 = (Celula)segunda.getPrimeiro();
			 Comparavel objeto1 = (Comparavel)celula1.getObjeto();
			 Comparavel objeto2 = (Comparavel)celula2.getObjeto();
			 if(objeto1.comparar(objeto2)){//Compara os objetos
				 lista.inserirFinal(primeira.recuperaRemovido().getObjeto());
			 }else{
				 lista.inserirFinal(segunda.recuperaRemovido().getObjeto());
			 }
		 }
		 
		 while(primeira.getPrimeiro()!=null){//Enquanto houver objetos na lista da esquerda
			 lista.inserirFinal(primeira.recuperaRemovido().getObjeto());
		 }
		 
		 while(segunda.getPrimeiro()!=null){//Enquanto houver objetos na lista da direita
			 lista.inserirFinal(segunda.recuperaRemovido().getObjeto());
		 }
		
	}
	
	/**
	 * Este m�todo instancia um objeto iterador
	 * @return iterador Iterador - Retorna um iterador do tipo da interface
	 * */
	@Override
	public Iterador iterador() {
		MIterador iterador = new MIterador(primeiro);
		return iterador;
	}

}
