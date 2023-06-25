package edu.german.tools;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShowMessage extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String FILE_CFG = "../config/messages.properties";

	public ShowMessage() {
	}

	public ShowMessage(String pattern) {
		JOptionPane.showMessageDialog(this, new MyProperties(FILE_CFG).getValuesArray(pattern));
	}

	public ShowMessage(String pattern, String hint) {
		String information = new MyProperties(FILE_CFG).getValue(pattern, false);
		JOptionPane.showMessageDialog(this, information + " (" + hint + ")");
	}

	public void directMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
