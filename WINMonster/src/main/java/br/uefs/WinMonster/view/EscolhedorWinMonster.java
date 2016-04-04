package br.uefs.WinMonster.view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class EscolhedorWinMonster extends JFileChooser {

	public EscolhedorWinMonster() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de texto", "txt","cpp","java");
		this.setFileFilter(filtro);
	}
	protected JDialog createDialog(Component parent) {
		JDialog dlg = super.createDialog(parent);
		dlg.setLocation(220, 115);
		return dlg;
	}
}

