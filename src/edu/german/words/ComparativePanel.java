package edu.german.words;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ComparativePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ComparativePanel(String setTitel) {
		Border border = BorderFactory.createTitledBorder(setTitel);
		this.setBorder(border);
	}

}
