package br.uefs.WinMonster.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.uefs.WinMonster.controller.WinController;
import br.uefs.WinMonster.exceptions.ArquivoCorrompidoException;

public class TelaPrincipal {
	private JScrollPane barraRolagem;
	private JTextArea saidas;
	private WinController controller = new WinController();

	
	private JMenuBar criarBarraMenu() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JMenu menu = new JMenu("Arquivo");
		menu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent me) {
				saidas.setText(saidas.getText());
			}

			@Override
			public void menuDeselected(MenuEvent me) {
				saidas.setText(saidas.getText());
			}

			@Override
			public void menuCanceled(MenuEvent me) {
				saidas.setText(saidas.getText());
			}
		});

		saidas.setEditable(false);
		JMenuBar barraMenu = new JMenuBar();
		JMenuItem itemMenu;


		//adiciona menu na barra
		barraMenu.add(menu);

		//cria novo item pro menu
		itemMenu = new JMenuItem("Compactar", new ImageIcon("compactar.jpg"));
		itemMenu.getAccessibleContext().setAccessibleDescription(
				"Compacta um arquivo de texto");
		itemMenu.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				EscolhedorWinMonster escolhedor = new EscolhedorWinMonster();
				int retorno = escolhedor.showOpenDialog(saidas);
				if(retorno == escolhedor.APPROVE_OPTION){
					controller.compactarArquivo(escolhedor.getSelectedFile().getPath(),escolhedor.getSelectedFile());
					//saidas.setText(saidas.getText() + "Arquivo compactado com sucesso! -ou não\n");
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Arquivo compactado com sucesso!");					
				}else{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Compactação cancelada");
				}
			}

		});
		itemMenu.setMnemonic(KeyEvent.VK_A);

		//adiciona item ao menu
		menu.add(itemMenu);
		
		menu.addSeparator();

		//cria outro item para o menu
		itemMenu = new JMenuItem("Descompactar", new ImageIcon("descompactar.png"));
		itemMenu.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser escolhedor = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("WinMonster File", "wmn");
				escolhedor.setFileFilter(filtro);
				int retorno = escolhedor.showOpenDialog(saidas);
				if(retorno == escolhedor.APPROVE_OPTION){
					try {
						controller.descompactarArquivo(escolhedor.getSelectedFile());
					} catch (ArquivoCorrompidoException e) {
						JOptionPane.showMessageDialog(null, "Arquivo Corrompido");
					}
					//saidas.setText(saidas.getText() + "Arquivo compactado com sucesso! -ou não\n");
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Descompactação concluída!");
				}else{
					JOptionPane.showMessageDialog(null, "Descompactação cancelada");
				}
			}

		});
		itemMenu.setMnemonic(KeyEvent.VK_B);


		//adiciona item ao menu
		menu.add(itemMenu);


		menu = new JMenu("Aparência");
		//cria outro item para o menu
		itemMenu = new JMenuItem();
		//itemMenu.setIcon(new ImageIcon(getClass().getResource("descompactar.png")));
		itemMenu.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				saidas.setText(saidas.getText());
			}

		});

		barraMenu.add(menu);
		JMenu submenu = new JMenu("Selecionar cor de fundo");
		
		ButtonGroup group = new ButtonGroup();
		JMenuItem botaoRadio = new JRadioButtonMenuItem("Branco");
		botaoRadio.setSelected(true);
		botaoRadio.setMnemonic(KeyEvent.VK_R);
		group.add(botaoRadio);
		botaoRadio.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               	saidas.setBackground(Color.white);
            }
            
        });
		submenu.add(botaoRadio);

		botaoRadio = new JRadioButtonMenuItem("Verde");
		botaoRadio.setMnemonic(KeyEvent.VK_O);
		group.add(botaoRadio);
		botaoRadio.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               	saidas.setBackground(Color.green);
            }
            
        });
		submenu.add(botaoRadio);
		
		botaoRadio = new JRadioButtonMenuItem("Vermelho");
		botaoRadio.setMnemonic(KeyEvent.VK_O);
		group.add(botaoRadio);
		botaoRadio.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               	saidas.setBackground(Color.red);
            }
            
        });
		submenu.add(botaoRadio);
		
		botaoRadio = new JRadioButtonMenuItem("Azul");
		botaoRadio.setMnemonic(KeyEvent.VK_O);
		group.add(botaoRadio);
		botaoRadio.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               	saidas.setBackground(Color.blue);
            }
            
        });
		submenu.add(botaoRadio);
		
		botaoRadio = new JRadioButtonMenuItem("Amarelo");
		botaoRadio.setMnemonic(KeyEvent.VK_O);
		group.add(botaoRadio);
		botaoRadio.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               	saidas.setBackground(Color.yellow);
            }
            
        });
		submenu.add(botaoRadio);
		
		botaoRadio = new JRadioButtonMenuItem("Magenta");
		botaoRadio.setMnemonic(KeyEvent.VK_O);
		group.add(botaoRadio);
		botaoRadio.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               	saidas.setBackground(Color.magenta);
            }
            
        });
		submenu.add(botaoRadio);
	
		menu.add(submenu);
				
		return barraMenu;

	}



	public Container criarPainelContainer() {
		JPanel painel = new JPanel(null);
		painel.setOpaque(false);

		saidas = new JTextArea(500, 400);
		saidas.setEditable(true);
		barraRolagem = new JScrollPane(saidas);

		painel.add(barraRolagem, BorderLayout.CENTER);

		return painel;
	}



	public void criarTela() {
		SplashScreen splash = new SplashScreen(5000);
		splash.mostrarSplashESair();
		JFrame frame = new JFrame("Win Monster");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this.criarPainelContainer());
		frame.setJMenuBar(this.criarBarraMenu());
		URL url = this.getClass().getResource("logo.png");  
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);  
		frame.setIconImage(iconeTitulo);
		frame.setSize(new Dimension(500, 400));        
		frame.setLocation(265, 115);
		frame.setVisible(true);
		
		
	}

	public static void main(String[] args) {
		new TelaPrincipal().criarTela();
	}
}