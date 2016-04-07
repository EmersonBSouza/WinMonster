package br.uefs.WinMonster.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow {

	private int duracao; 

	public SplashScreen(int duracao) {
		this.duracao = duracao;
	}

	/**
	 * Deixa a splashScreen visível pelo tempo do seu atributo duração
	 */
	public void mostrarSplash() {        
		JPanel content = (JPanel)getContentPane();
		// Configura a posição e o tamanho da janela
		int largura = 450;
		int altura =115;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		setBounds(x,y,largura,altura);
		// Constrói o splash screen
		URL url = this.getClass().getResource("splashScreen.png");  
		JLabel label = new JLabel(new ImageIcon(url));
		JLabel copyrt = new JLabel("UEFS - 2016, Marcus Aldrey & Emerson Souza", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(label);
		content.add(copyrt, BorderLayout.SOUTH);
		setVisible(true);

		// Faz a tela ficar um tempo visível
		try { Thread.sleep(duracao); } catch (Exception e) {}        
		setVisible(false);        
	}

	/**
	 * Mostra a splashScreen pelo tempo da sua duração e fecha
	 */
	public void mostrarSplashESair() {        
		mostrarSplash();
		dispose();    
	}
}

