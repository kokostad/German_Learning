package edu.german.words.verbs;

import java.awt.Font;

import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditField;
import edu.german.tools.PrepareString;
import edu.german.tools.ScreenSetup;

public class MainVerbPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String PATH = "src/edu/german/words/cfg/";
	private String CFG_FILE = "word.properties";
	private OneEditField word;
	private OneEditField meaning;
	private Font font = new ScreenSetup().DEFAULT_FONT;
	private MyComboBox separatable;
	private MyComboBox regular;
	private PrepareString prs;

	public MainVerbPanel() {
		prs = new PrepareString();
		word = new OneEditField.Builder()
				.withTitle("Wpisz czasownik: ")
				.withHint("czasownik w wersji podstawowej")
				.withFont(font)
				.withHeight(30)
				.withWidth(160)
				.build();

		meaning = new OneEditField.Builder()
				.withTitle("znaczenie: ")
				.withHint("znaczenie czasownika")
				.withFont(font)
				.withHeight(30)
				.withWidth(160)
				.build();

		separatable = new MyComboBox("Nierozłączny/rozłączny: ",
				new MyProperties(PATH, CFG_FILE).getValuesArray("VERB_SEPARABLE"));
		regular = new MyComboBox("Regularny/nieregularny: ",
				new MyProperties(PATH, CFG_FILE).getValuesArray("VERB_REGULAR"));

		this.add(word);
		this.add(meaning);
		this.add(regular);
		this.add(separatable);
	}

	public String getWord() {
		return prs.cutSpaces(word.getValue());
	}

	public String getMeaing() {
		return prs.cutSpaces(meaning.getValue());
	}

	public String getSeparatable() {
		return separatable.getValue();
	}

	public String getRegular() {
		return regular.getValue();
	}

	public void clearEditFields() {
		word.clearField();
		meaning.clearField();
	}

}
