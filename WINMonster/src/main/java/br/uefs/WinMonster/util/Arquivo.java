package br.uefs.WinMonster.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;

public class Arquivo implements Persistencia{

	@Override
	public void salvarBytes(String texto, File arquivoOriginal) {
		
		BitSet gravar = converterBits(texto);

		File arquivo = new File("arquivo.txt");//Rever isso
		try{
			FileOutputStream escrever = new FileOutputStream(arquivo);
			
			BufferedOutputStream bufferEscrever = new BufferedOutputStream(escrever);
			bufferEscrever.write(gravar.toByteArray());
			
			bufferEscrever.close();
			escrever.close();
		}catch(IOException e){
			
		}
		
	}

	@Override
	public void salvarTexto(String texto, File arquivoOriginal) {
		
		File arquivo = new File("compactado.txt");//Rever isso aqui ainda
		File dir = arquivoOriginal.getParentFile();
		dir.mkdirs();
		
		try {
			arquivo.createNewFile();
			FileWriter escrita = new FileWriter(arquivo);
			BufferedWriter escritaBuffer = new BufferedWriter(escrita);
			
			escritaBuffer.write(texto.toString());
			escritaBuffer.close();
			escrita.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public String lerTexto(String caminho, File arquivo) {
				
		try {
			
			FileReader leitura = new FileReader(arquivo);
			BufferedReader leituraBuffer = new BufferedReader(leitura);
			StringBuffer buffer = new StringBuffer();
			int content = leituraBuffer.read();
			while(content!=-1){
				buffer.append((char)content);
				content = leituraBuffer.read();
			}
			leituraBuffer.close();
			leitura.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public String lerBytes(String caminho,File arquivo) {
				
		try {
			FileInputStream ler = new FileInputStream(arquivo);
			BufferedInputStream bufferLer = new BufferedInputStream(ler);
		    StringBuffer string = new StringBuffer(Integer.toBinaryString((int)bufferLer.read()));
		    bufferLer.close();
		    ler.close();
		    return string.reverse().toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public BitSet converterBits(String texto) {
	
		BitSet bits = new BitSet(texto.length());
		
		for(char c : texto.toCharArray()){
			if(c == '0')
				bits.set(c,false);//Transforma em bit '0'
			else
				bits.set(c,true);//Transforma em bit '1'
		}

		return bits;
	}

}
