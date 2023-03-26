package edu.german.words.verbs;

import java.awt.Font;

import javax.swing.JPanel;

import edu.german.tools.ArrayFromEnum;
import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditField;
import edu.german.tools.ScreenSetup;

public class MainVerbPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE ="word.properties";
	private OneEditField mainVerbField;
	private Font font = new ScreenSetup().DEFAULT_FONT;
	private MyComboBox separatable;
	private MyComboBox regular;

	public MainVerbPanel() {
		mainVerbField = new OneEditField.Builder()
				.withTitle("Wpisz czasownik: ")
				.withHint("czasownik w wersji podstawowej")
				.withFont(font)
				.withHeight(30)
				.withWidth(220)
				.build();

		separatable = new MyComboBox("Rozłączny/nierozłączny: ",
				new MyProperties(CFG_FILE).getValuesArray("VERB_SEPARABLE"));
		regular = new MyComboBox("Regularny/nieregularny: ",
				new MyProperties(CFG_FILE).getValuesArray("VERB_REGULAR"));

		this.add(mainVerbField);
		this.add(regular);
		this.add(separatable);
	}

	public String getWord() {
		return mainVerbField.getValue();
	}

	public String getSeparatable() {
		return separatable.getValue();
	}

	public String getRegular() {
		return regular.getValue();
	}

}
