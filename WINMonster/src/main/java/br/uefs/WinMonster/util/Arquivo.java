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
	public void salvarBytes(BitSet conjuntoBits,int hashCode,Object objeto, File arquivoOriginal) {
		
		String nomeAnterior = arquivoOriginal.getPath();
		String extensao = nomeAnterior.substring(nomeAnterior.lastIndexOf('.'));
//		File arquivo = new File(nomeAnterior.replace(extensao, ".wmn"));
		File arquivo = new File(nomeAnterior+".wmn");
		File arquivo2 = new File("arquivo2");

		try{
			FileOutputStream escrever = new FileOutputStream(arquivo,true);
			
			FileWriter escreverTexto = new FileWriter(arquivo2,true);
			
			ObjectOutputStream bufferEscrever = new ObjectOutputStream(escrever);
			
			BufferedWriter bufferEscrevertexto = new BufferedWriter(escreverTexto);

			bufferEscrever.writeObject(conjuntoBits);
			//bufferEscrevertexto.write(texto.toString());
			if(objeto!=null){
				bufferEscrever.writeInt(hashCode);
				bufferEscrever.writeObject(objeto);
				//bufferArvore.close();
			}
			bufferEscrevertexto.close();
			bufferEscrever.close();
			escrever.close();
		}catch(IOException e){
			
		}
		
	}

	@Override
	public void salvarTexto(String texto, File arquivoOriginal) {
		
		String nomeAnterior = arquivoOriginal.getPath();
		String extensao = nomeAnterior.substring(nomeAnterior.lastIndexOf(".wmn"));
		File arquivo = new File(nomeAnterior.replace(extensao,""));//Rever a saída
		
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
		
		try {
			objeto = (BitSet) new ObjectInputStream(new FileInputStream(arquivoOriginal)).readObject();	
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return objeto;
		
	}
	
	public ArvoreHuffman lerArvore(Object arvore,File arquivoOriginal){
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoOriginal));
			in.readObject();
			in.readInt();
			arvore = in.readObject();
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//in.readObject();
		//objeto = (ArvoreHuffman)new ObjectInputStream(new FileInputStream(arquivoOriginal)).readObject();
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ArvoreHuffman) arvore;
	}
	public int obterHash(File arquivoOriginal){
		
		int hash =0;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoOriginal));
			in.readObject();
			hash = in.readInt();
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//in.readObject();
		//objeto = (ArvoreHuffman)new ObjectInputStream(new FileInputStream(arquivoOriginal)).readObject();
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hash;
	}

}
