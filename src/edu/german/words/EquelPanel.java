package edu.german.words;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.german.tools.OneEditableField;

public class EquelPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditableField adjective;
	private OneEditableField meaning;

	public EquelPanel(String setTitel) {
		Border border = BorderFactory.createTitledBorder(setTitel);
		this.setBorder(border);
		this.setLayout(new GridLayout(2, 1, 10, 10));

		adjective = new OneEditableField(setTitel, setTitel, 16, 14);
		meaning = new OneEditableField(setTitel, setTitel, 16, 14);

		this.add(adjective);
		this.add(meaning);
	}

}
