package br.uefs.WinMonster.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal {
			
	public void criarTela() {
		TelaWinMonster telaPrincipal = new TelaWinMonster();
		telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL url = this.getClass().getResource("logo.png"); 
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		telaPrincipal.setIconImage(iconeTitulo);
		telaPrincipal.setSize(new Dimension(500, 400));        
		telaPrincipal.setLocation(265, 115);
		SplashScreen splash = new SplashScreen(5000);
		splash.mostrarSplashESair();
		telaPrincipal.setVisible(true);	
		String nome;
        nome = JOptionPane.showInputDialog("Informe seu nome: ");
        JOptionPane.showMessageDialog(null,"Seja bem vindo(a), " + nome + "!");
	}

	public static void main(String[] args) {
		new Principal().criarTela();
	}
}