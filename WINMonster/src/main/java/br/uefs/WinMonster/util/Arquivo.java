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
	 * Este método é responsável por salvar os objetos usando o fluxo de bytes
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
				bufferEscrever.writeInt(hashCode);//Escreve o código hash no arquivo
				bufferEscrever.writeObject(objeto);//Escreve a árvore de Huffman no arquivo;
			}
			bufferEscrever.close();
			escrever.close();
		}catch(IOException e){
			throw new IOException();//Lança a exceção caso algum erro ocorra
		}
		
	}

	/**
	 * Este método é responsável por salvar o texto usando o fluxo de texto
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
	 * Este método é responsável ler o texto salvo no arquivo usando o fluxo de texto
	 * @param File arquivo
	 * @return String buffer.toString()*/
	@Override
	public String lerTexto(File arquivo) throws IOException {
				
		try {
			
			FileReader leitura = new FileReader(arquivo);
			BufferedReader leituraBuffer = new BufferedReader(leitura);
			StringBuffer buffer = new StringBuffer();
			int content = leituraBuffer.read();
			while(content!=-1){//Enquanto não for o fim do arquivo, a leitura continua
				buffer.append((char)content);//Concatena a string
				content = leituraBuffer.read();//Lê o texto
			}
			leituraBuffer.close();
			leitura.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();//Lança a exceção caso seja necessário
		} catch (IOException e) {
			throw new IOException();//Lança a exceção caso seja necessário
		}
	}

	/**
	 * Este método é responsável pela leitura do arquivo por meio do fluxo de bytes
	 * @param Object objeto
	 * @param File arquivoOriginal
	 * @return BitSet objeto*/
	@Override
	public Object lerBytes(Object objeto,File arquivoOriginal) throws IOException {
		
		try {
			objeto = (BitSet) new ObjectInputStream(new FileInputStream(arquivoOriginal)).readObject();	//Lê o objeto do arquivo
		} catch (ClassNotFoundException | IOException e) {
			throw new IOException();//Lança a exceção caso seja necessário
		}
		return objeto;
		
	}
	/**
	 * Este método é reponsável por obter a ArvoreHuffman guardada no arquivo
	 * @param Object arvore
	 * @param File arquivoOriginal
	 * @return ArvoreHuffman arvore*/
	@Override
	public ArvoreHuffman lerArvore(Object arvore,File arquivoOriginal) throws IOException, ArquivoCorrompidoException{
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoOriginal));
			in.readObject();//Lê um objeto do arquivo
			in.readInt();//Lê um inteiro do arquivo
			arvore = in.readObject();//Lê um objeto do arquivo
		} catch (IOException e) {
			throw new IOException();//Lança a exceção caso seja necessário
		}
		catch (ClassNotFoundException e) {
			throw new ArquivoCorrompidoException();//Lança a exceção caso seja necessário
		}
		return (ArvoreHuffman) arvore;
	}
	
	/**
	 * Este método é responsavel por obter o código hash armazenado no arquivo
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
			throw new IOException();//Lança a exceção caso seja necessário
		} catch (ClassNotFoundException e) {
			throw new ArquivoCorrompidoException();//Lança a exceção caso seja necessário
		}
		
	}

}
