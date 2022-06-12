package edu.german.words;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.Titles;

public class WordSelectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MyComboBox box;
	private MyComboBox boxNumber;
	private static final String FILE_NAME = "words_genus.properties";

	public WordSelectionPanel() {
		String[] selectionList = new MyProperties(FILE_NAME).getValuesArray("GENUS_LIST");
		box = new MyComboBox(Titles.setTitel("CHOOSE_GENUS"), selectionList);

		String[] numbers = { "10", "15", "20", "50" };
		boxNumber = new MyComboBox(Titles.setTitel("WORDS_NUMBER"), numbers);

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		this.add(boxNumber);
	}

	public String getGenus() {
		return box.getValue();
	}

	public Integer getNumber() {
		return Integer.parseInt(boxNumber.getValue());
	}
}
