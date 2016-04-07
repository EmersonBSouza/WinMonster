package br.uefs.WinMonster.util;


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

import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;

public class Arquivo implements Persistencia{

	/**
	 * Este m�todo � respons�vel por salvar os objetos usando o fluxo de bytes
	 * @param BitSet conjuntoBits
	 * @param int hashCode
	 * @param Object objeto
	 * @File arquivo*/
	@Override
	public void salvarBytes(BitSet conjuntoBits,int hashCode,Object objeto, File arquivoOriginal) throws IOException {
		
		String nomeAnterior = arquivoOriginal.getPath();
		String extensao = nomeAnterior.substring(nomeAnterior.lastIndexOf('.'));
		File arquivo = new File(nomeAnterior+".wmn");
		File arquivo2 = new File("arquivo2");

		try{
			FileOutputStream escrever = new FileOutputStream(arquivo);
						
			ObjectOutputStream bufferEscrever = new ObjectOutputStream(escrever);
			
			bufferEscrever.writeObject(conjuntoBits);//Escreve o conjunto de bits no arquivo
			if(objeto!=null){
				bufferEscrever.writeInt(hashCode);//Escreve o c�digo hash no arquivo
				bufferEscrever.writeObject(objeto);//Escreve a �rvore de Huffman no arquivo;
			}
			bufferEscrever.close();
			escrever.close();
		}catch(IOException e){
			throw new IOException();//Lan�a a exce��o caso algum erro ocorra
		}
		
	}

	/**
	 * Este m�todo � respons�vel por salvar o texto usando o fluxo de texto
	 * @param String texto
	 * @param File arquivoOriginal
	 * */
	@Override
	public void salvarTexto(String texto, File arquivoOriginal) throws IOException {
		
		String nomeAnterior = arquivoOriginal.getPath();
		String extensao = nomeAnterior.substring(nomeAnterior.lastIndexOf(".wmn"));
		nomeAnterior = nomeAnterior.replace(extensao,"");
		extensao = nomeAnterior.substring(nomeAnterior.lastIndexOf("."),nomeAnterior.length());
		String nomeArquivoNovo = nomeAnterior.replace(extensao,"(1)"+extensao);
		
		File arquivo = new File(nomeArquivoNovo);//Cria um novo arquivo
		try {
			
			FileWriter escrita = new FileWriter(arquivo);
			BufferedWriter escritaBuffer = new BufferedWriter(escrita);
			
			escritaBuffer.write(texto.toString());//Escreve o texto no arquivo
			escritaBuffer.close();
			escrita.close();
		} catch (IOException e1) {
			throw new IOException();
		}
		
	}
	
	/**
	 * Este m�todo � respons�vel ler o texto salvo no arquivo usando o fluxo de texto
	 * @param File arquivo
	 * @return String buffer.toString()*/
	@Override
	public String lerTexto(File arquivo) throws IOException {
				
		try {
			
			FileReader leitura = new FileReader(arquivo);
			BufferedReader leituraBuffer = new BufferedReader(leitura);
			StringBuffer buffer = new StringBuffer();
			int content = leituraBuffer.read();
			while(content!=-1){//Enquanto n�o for o fim do arquivo, a leitura continua
				buffer.append((char)content);//Concatena a string
				content = leituraBuffer.read();//L� o texto
			}
			leituraBuffer.close();
			leitura.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();//Lan�a a exce��o caso seja necess�rio
		} catch (IOException e) {
			throw new IOException();//Lan�a a exce��o caso seja necess�rio
		}
	}

	/**
	 * Este m�todo � respons�vel pela leitura do arquivo por meio do fluxo de bytes
	 * @param Object objeto
	 * @param File arquivoOriginal
	 * @return BitSet objeto*/
	@Override
	public Object lerBytes(Object objeto,File arquivoOriginal) throws IOException {
		
		try {
			objeto = (BitSet) new ObjectInputStream(new FileInputStream(arquivoOriginal)).readObject();	//L� o objeto do arquivo
		} catch (ClassNotFoundException | IOException e) {
			throw new IOException();//Lan�a a exce��o caso seja necess�rio
		}
		return objeto;
		
	}
	/**
	 * Este m�todo � repons�vel por obter a ArvoreHuffman guardada no arquivo
	 * @param Object arvore
	 * @param File arquivoOriginal
	 * @return ArvoreHuffman arvore*/
	@Override
	public ArvoreHuffman lerArvore(Object arvore,File arquivoOriginal) throws IOException, ArquivoCorrompidoException{
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoOriginal));
			in.readObject();//L� um objeto do arquivo
			in.readInt();//L� um inteiro do arquivo
			arvore = in.readObject();//L� um objeto do arquivo
		} catch (IOException e) {
			throw new IOException();//Lan�a a exce��o caso seja necess�rio
		}
		catch (ClassNotFoundException e) {
			throw new ArquivoCorrompidoException();//Lan�a a exce��o caso seja necess�rio
		}
		return (ArvoreHuffman) arvore;
	}
	
	/**
	 * Este m�todo � responsavel por obter o c�digo hash armazenado no arquivo
	 * @param File arquivoOriginal
	 * @return int hash*/
	@Override
	public int obterHash(File arquivoOriginal) throws IOException, ArquivoCorrompidoException{
		
		int hash =0;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoOriginal));
			in.readObject();
			hash = in.readInt();	
			return hash;
		} catch (IOException e) {
			throw new IOException();//Lan�a a exce��o caso seja necess�rio
		} catch (ClassNotFoundException e) {
			throw new ArquivoCorrompidoException();//Lan�a a exce��o caso seja necess�rio
		}
		
	}

}
