package br.com.alura.java.io.teste.leitura;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TesteLeituraComplexo {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Fluxo de entrada com um arquivo - Padr�o de Projeto - Decorator

		InputStream fis = new FileInputStream("lorem.txt"); // Padr�o de Projeto - Template
		Reader isr = new InputStreamReader(fis, "UTF-8"); // Padr�o de Projeto - Template
		BufferedReader br = new BufferedReader(isr);
		
		while (br.ready()) {
			System.out.println(br.readLine());
		}
		
		br.close();
	}

}
