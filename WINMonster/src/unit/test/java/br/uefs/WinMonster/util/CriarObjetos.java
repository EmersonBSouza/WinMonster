package br.uefs.WinMonster.util;

public class CriarObjetos {

	public static Fila criarFila(){
		// Simulação de entrada da String "SUSIE SAYS ITS EASY\n"

		Fila fila = new Fila();
		NoArvore[] vetorAux = criarVetor();
		/*Cria um objeto ArvoreHuffman para cada um dos nós, tendo eles como raiz, e os insere 
		 *em uma fila. Os caracteres de menor frequência possuem preferência. */
		for(int i=0;i<vetorAux.length;i++) {
			ArvoreHuffman arvore = new ArvoreHuffman();
			arvore.setRaiz(vetorAux[i]);
			fila.inserirFinal(arvore);;
		}

		return fila;	
	}
	
	public static NoArvore[] criarVetor(){
		
		/* Criamos um vetor e nós com os caracteres da frase e suas frequências.
		 * Os nós criados são inseridos no vetor em ordem crescente de frequência*/
		NoArvore[] vetorAux = new NoArvore[9];

		NoArvore no1 = new NoArvore();
		no1.setLetra('\n');
		no1.setFrequencia(1);
		vetorAux[0] = no1;

		NoArvore no2 = new NoArvore();
		no2.setLetra('U');
		no2.setFrequencia(1);
		vetorAux[1] = no2;

		NoArvore no3 = new NoArvore();
		no3.setLetra('T');
		no3.setFrequencia(1);
		vetorAux[2] = no3;

		NoArvore no4 = new NoArvore();
		no4.setLetra('Y');
		no4.setFrequencia(2);
		vetorAux[3] = no4;

		NoArvore no5 = new NoArvore();
		no5.setLetra('E');
		no5.setFrequencia(2);
		vetorAux[4] = no5;

		NoArvore no6 = new NoArvore();
		no6.setLetra('A');
		no6.setFrequencia(2);
		vetorAux[5] = no6;

		NoArvore no7 = new NoArvore();
		no7.setLetra('I');
		no7.setFrequencia(3);
		vetorAux[6] = no7;

		NoArvore no8 = new NoArvore();
		no8.setLetra(' ');
		no8.setFrequencia(4);
		vetorAux[7] = no8;

		NoArvore no9 = new NoArvore();
		no9.setLetra('S');
		no9.setFrequencia(6);
		vetorAux[8] = no9;
	
		return vetorAux;
	}
}
