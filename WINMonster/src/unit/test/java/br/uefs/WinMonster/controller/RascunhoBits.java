package br.uefs.WinMonster.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RascunhoBits {

public static void main(String[] args) throws IOException {
		
		
		boolean[] b = new boolean[80];
		String n = "10011111"
					+"01101111"
					+"00100101"
					+"11010001"
					+"10011010"
					+"00111101"
					+"01011100"
					+"1110010"
					+"0001000100010001";
		//1011 significa 11
		//1111 significa 15
		//Vem false por padrão
		
//		b.set(0,true);
//		b.set(1,false);
//		b.set(2,false);
//		b.set(3,true);
//		b.set(4,true);
//    	b.set(5,true);
//    	b.set(6,false);
//        b.set(7,true);
//        
//        b.set(8,false);
//		b.set(9,false);
//		b.set(10,false);
//		b.set(11,false);
//		b.set(12, true);
//    	b.set(13,true);
//    	b.set(14,false);
//        b.set(15, true);
//        
//        b.set(16,false);
//		b.set(17,false);
//		b.set(18,false);
//		b.set(19,false);
//		b.set(20, true);
//    	b.set(21,true);
//    	b.set(22,false);
//        b.set(23, true);

		//00001111
		for(int i = 0; i < n.length();i++){
			if(n.charAt(i) == '1'){
				b[i] = true;
			}
			else{
				b[i] = false;
			}
		}
		int x=0;
		
		StringBuffer numero = new StringBuffer();
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		
		while(x<b.length){
			if(b[x]==true) {
				bytes.write(1);
				numero.append(1);
			}
			else {
				bytes.write(0);
				numero.append(0);
			}
			x++;
		}
		
		System.out.println(numero);
		
		File arq = new File("arq.txt");
		
		try {
			FileWriter escrita = new FileWriter(arq);
			BufferedWriter escritaBuffer = new BufferedWriter(escrita);
			
			escritaBuffer.write(numero.toString());
			escritaBuffer.close();
			escrita.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FileReader leitura = new FileReader(arq);
		BufferedReader leituraBuffer = new BufferedReader(leitura);
		
		String buffer;
		buffer = leituraBuffer.readLine();
		leituraBuffer.close();
		leitura.close();
		
		System.out.println("Buffer normal:" +buffer);
		
		File arquivo = new File("arquivo.txt");
		try{
			FileOutputStream escrever = new FileOutputStream(arquivo);
			
			BufferedOutputStream bufferEscrever = new BufferedOutputStream(escrever);
			//String dina = b.toString();
			bytes.writeTo(bufferEscrever);
			bufferEscrever.close();
			escrever.close();
		}catch(IOException e){
			
		}
	 
	  FileInputStream ler = new FileInputStream(arquivo);
	  BufferedInputStream bufferLer = new BufferedInputStream(ler);
	  StringBuffer string = new StringBuffer();
	  StringBuffer definitiva = new StringBuffer();
	  int content;
      while( ( content = bufferLer.read() ) != -1){
          string.append(Integer.toBinaryString(content)).reverse();
       	  definitiva.append(string.toString());
       	  string = new StringBuffer();
      }
	  bufferLer.close();
	  
	  System.out.println("Buffer bit:"+ definitiva);
	  
	}
	
}
