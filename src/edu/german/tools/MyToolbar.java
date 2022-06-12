package edu.german.tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import edu.german.tools.buttons.ExitButton;
import edu.german.tools.buttons.ImportButton;

public class MyToolbar extends JToolBar {
	private static final long serialVersionUID = 1L;
//	JButton exitBtn;
//	JButton importBtn;

	public MyToolbar() {
//		exitBtn = new ExitButton();
//		importBtn = new ImportButton();

		this.setBackground(new Color(220, 220, 220)); // gainsboro
		this.setOpaque(true);
		this.setRollover(true);
//		this.add(exitBtn);
		this.addSeparator();
//		this.add(importBtn);
	}

//	public ExitButton getExitBtn() {
//		return (ExitButton) exitBtn;
//	}
//
//	public ImportButton getImportBtn() {
//		return (ImportButton) importBtn;
//	}

}
