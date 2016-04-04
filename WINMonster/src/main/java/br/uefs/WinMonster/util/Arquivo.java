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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;

public class Arquivo implements Persistencia{

	@Override
	public void salvarBytes(String texto, Object objeto, File arquivoOriginal) {
		
		String nomeAnterior = arquivoOriginal.getPath();
		String extensao = nomeAnterior.substring(nomeAnterior.lastIndexOf('.'));
		File arquivo = new File(nomeAnterior.replace(extensao, ".wmn"));
		File arquivoTree = new File(nomeAnterior.replace(extensao, ".wmnt"));
		File arquivo2 = new File("arquivo2");
		BitSet conjuntoBits = new BitSet();
		int i=0;
		for(i = 0; i < texto.length();i++){
			if(texto.charAt(i) == '1'){
				conjuntoBits.set(i,true);
			}
			else{
				conjuntoBits.set(i,false);
			}
		}
		conjuntoBits.set(i,true);
		try{
			FileOutputStream escrever = new FileOutputStream(arquivo,true);
			
			FileWriter escreverTexto = new FileWriter(arquivo2,true);
			
			ObjectOutputStream bufferEscrever = new ObjectOutputStream(escrever);
			
			BufferedWriter bufferEscrevertexto = new BufferedWriter(escreverTexto);

			bufferEscrever.writeObject(conjuntoBits);
			bufferEscrevertexto.write(texto.toString());
			if(objeto!=null){
				FileOutputStream arvore = new FileOutputStream(arquivoTree,true);
				ObjectOutputStream bufferArvore = new ObjectOutputStream(arvore);
				//bufferEscrever.writeChars("/%%|%%/");
				bufferArvore.writeObject(objeto);
				bufferArvore.close();
			}
			bufferEscrevertexto.close();
			bufferEscrever.close();
			escrever.close();
		}catch(IOException e){
			
		}
		
	}

	@Override
	public void salvarTexto(String texto, File arquivoOriginal) {
		
		String nomeAnterior = arquivoOriginal.getName();
		File arquivo = new File(nomeAnterior+" Compactado.txt");
		
		try {
			
			FileWriter escrita = new FileWriter(arquivo,true);
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
	public Object lerBytes(Object objeto,File arquivoOriginal) {
		
		String nomeArvore = arquivoOriginal.getPath();
		String extensao = nomeArvore.substring(nomeArvore.lastIndexOf('.'));
		
		File arquivoArvore = new File(nomeArvore.replace(extensao, ".wmnt"));	
		try {
			if(objeto instanceof BitSet)
				objeto = (BitSet) new ObjectInputStream(new FileInputStream(arquivoOriginal)).readObject();
			else if(objeto instanceof ArvoreHuffman){
				
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoArvore));
				//objeto = (ArvoreHuffman)new ObjectInputStream(new FileInputStream(arquivo)).readObject();
				objeto = (ArvoreHuffman)in.readObject();
				in.close();
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return objeto;
		
	}

}
