package edu.german.words;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.Titel;

public class WordSelectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String PATH = "src/edu/german/words/cfg/";
	private static final String FILE_NAME = "word.cfg";
	private MyComboBox kindBox;
	private MyComboBox boxNumber;
	private String[] selectionList;

	public WordSelectionPanel(boolean setWordKinds) {
		String[] numbers = { "10", "15", "20", "30", "50" };
		boxNumber = new MyComboBox(Titel.setTitel("WORDS_NUMBER"), numbers);

		if (setWordKinds) {
			selectionList = new MyProperties(PATH, FILE_NAME).getValuesArray("GENUS_LIST_FOR_GAMES");
			kindBox = new MyComboBox(Titel.setTitel("CHOOSE_GENUS"), selectionList);
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
