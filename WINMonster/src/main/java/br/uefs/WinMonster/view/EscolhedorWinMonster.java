package br.uefs.WinMonster.view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.uefs.WinMonster.exceptions.FormatoArquivoInvalidoException;

@SuppressWarnings("serial")
public class EscolhedorWinMonster extends JFileChooser {

	public EscolhedorWinMonster() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de texto", "txt","cpp","html","c");
		this.setFileFilter(filtro);
	}
	protected boolean leituraPossivel(String extensao) throws FormatoArquivoInvalidoException{
		if(extensao.equals(".cpp")||extensao.equals(".txt")||extensao.equals(".html")||extensao.equals(".c")){
			return true;
		}
		else{
			throw new FormatoArquivoInvalidoException();
		}
	}
	protected JDialog createDialog(Component parent) {
		JDialog dlg = super.createDialog(parent);
		dlg.setLocation(220, 115);
		return dlg;
	}
}

