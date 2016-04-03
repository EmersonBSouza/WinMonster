package br.uefs.WinMonster.model;

import java.util.BitSet;

public class Compactador {

	public BitSet transformarEmBits(String texto){
		BitSet conjuntoBits = new BitSet();
		
		for(int i = 0; i < texto.length();i++){
			if(texto.charAt(i) == '1'){
				conjuntoBits.set(i,true);
			}
			else{
				conjuntoBits.set(i,false);
			}
		}
		
		return conjuntoBits;
	}
}
