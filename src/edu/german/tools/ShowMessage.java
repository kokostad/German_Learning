package edu.german.tools;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShowMessage extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String FILE_CFG = "../cfg/messages.properties";

	public ShowMessage(String pattern) {
		JOptionPane.showMessageDialog(this, new MyProperties(FILE_CFG).getValuesArray(pattern));
	}

}
