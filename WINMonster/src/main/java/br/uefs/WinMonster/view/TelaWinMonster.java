package br.uefs.WinMonster.view;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.uefs.WinMonster.controller.WinController;
import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;
import br.uefs.WinMonster.exceptions.ArquivoVazioException;
import br.uefs.WinMonster.exceptions.FormatoArquivoInvalidoException;

@SuppressWarnings("serial")

public class TelaWinMonster extends JFrame{
	
	private WinController controller = new WinController();
	
	public TelaWinMonster() {

		super("WinMonster");
		criarMenu();
		criarpainelBotoes();
	}

	public void criarMenu() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JMenu menuArquivo = new JMenu("Arquivo");

		//Cria item de compactar
		URL url = this.getClass().getResource("iconeCompactar.png");
		ImageIcon icone = new ImageIcon(url);
		JMenuItem menuItemCompactar = new JMenuItem("Compactar", icone);
		menuItemCompactar.addActionListener(new CompactarAction());
		menuArquivo.add(menuItemCompactar);

		//Cria item de descompactar
		url = this.getClass().getResource("iconeDescompactar.png");
		icone = new ImageIcon(url);
		JMenuItem menuItemDescompactar = new JMenuItem("Descompactar", icone);
		menuItemDescompactar.addActionListener(new DescompactarAction());
		menuArquivo.add(menuItemDescompactar);
		
		menuArquivo.addSeparator();
		
		//Cria item de sair
		url = this.getClass().getResource("iconeSair.png");
		icone = new ImageIcon(url);
		JMenuItem menuItemSair = new JMenuItem("Sair", icone);
		menuItemSair.addActionListener(new SairAction());
		menuArquivo.add(menuItemSair);

		//Cria barra de menu
		JMenuBar barraDeMenu = new JMenuBar();
		this.setJMenuBar(barraDeMenu);

		barraDeMenu.add(menuArquivo);
		
	}

	public void criarpainelBotoes() {

		this.setLayout(new GridLayout(1, 1));

		//JPanel painelBotoes = new JPanel();
		
		//Cria botão de compactar
		URL url = this.getClass().getResource("botaoCompactar.png");
		ImageIcon icone = new ImageIcon(url);
		JButton botaoCompactar = new JButton("Compactar", icone);
		botaoCompactar.addActionListener(new CompactarAction());
		//painelBotoes.add(botaoCompactar);

		//Cria botão de descompactar
		url = this.getClass().getResource("botaoDescompactar.png");
		icone = new ImageIcon(url);
		JButton botaoDescompactar = new JButton("Descompactar", icone);
		botaoDescompactar.addActionListener(new DescompactarAction());
		//painelBotoes.add(botaoDescompactar);

		this.add(botaoCompactar);
		this.add(botaoDescompactar);
	}

	private class CompactarAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			EscolhedorWinMonster escolhedor = new EscolhedorWinMonster();
			int retorno = escolhedor.showOpenDialog(null);
			if(retorno == JFileChooser.APPROVE_OPTION){
				String nomeArquivo = escolhedor.getSelectedFile().getName();
				String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length());

				try{
					escolhedor.leituraPossivel(extensao);
					controller.compactarArquivo(escolhedor.getSelectedFile());
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Arquivo compactado com sucesso!");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo");
				} catch (ArquivoVazioException e) {
					JOptionPane.showMessageDialog(null, "O arquivo está vazio");
				} catch(FormatoArquivoInvalidoException e){
					JOptionPane.showMessageDialog(null, "Não é possível ler esse tipo de arquivo");
				} 					
			}else{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Compactação cancelada");
			}
		}

	}

	private class DescompactarAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			JFileChooser escolhedor = new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("WinMonster File", "wmn");
			escolhedor.setFileFilter(filtro);
			int retorno = escolhedor.showOpenDialog(null);
			
			if(retorno == JFileChooser.APPROVE_OPTION){
				try {
					String nomeArquivo = escolhedor.getSelectedFile().getName();
					String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length());
					if(extensao.equals(".wmn")){
						controller.descompactarArquivo(escolhedor.getSelectedFile());
						JOptionPane.showMessageDialog(null, "Descompactação concluída!");
						Toolkit.getDefaultToolkit().beep();
					}
					else{
						JOptionPane.showMessageDialog(null, "Não é possível ler esse tipo de arquivo");
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo");
				} catch (ArquivoCorrompidoException e) {
					JOptionPane.showMessageDialog(null, "Arquivo Corrompido");
				} catch (NullPointerException e){
					JOptionPane.showMessageDialog(null, "Erro: arquivos com codificação Unicode não são aceitos");
				} 
			}else{
				JOptionPane.showMessageDialog(null, "Descompactação cancelada");
			}
		}
	}

	private class SairAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			System.exit(0);

		}
	}

}
