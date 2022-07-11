package edu.german.words;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.Titles;

public class WordSelectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "words_genus.properties";
	private MyComboBox kindBox;
	private MyComboBox boxNumber;
	private String[] selectionList;

	public WordSelectionPanel(boolean setWordKinds) {
		String[] numbers = { "10", "15", "20", "50" };
		boxNumber = new MyComboBox(Titles.setTitel("WORDS_NUMBER"), numbers);

		if (setWordKinds) {
			selectionList = new MyProperties(FILE_NAME).getValuesArray("GENUS_LIST_FOR_GAMES");
			kindBox = new MyComboBox(Titles.setTitel("CHOOSE_GENUS"), selectionList);
			this.add(kindBox);
		}

		this.add(boxNumber);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
	}

	public String getGenus() {
		return kindBox.getValue();
	}

	public Integer getNumber() {
		return Integer.parseInt(boxNumber.getValue());
	}

}
