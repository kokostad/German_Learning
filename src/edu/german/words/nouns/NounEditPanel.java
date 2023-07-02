package edu.german.words.nouns;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import edu.german.tools.OneEditField;
import edu.german.tools.PrepareString;
import edu.german.tools.ScreenSetup;
import edu.german.words.model.Word;

public class NounEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditField wordSingular;
	private OneEditField meaningSingular;
	private OneEditField wordPlural;
	private OneEditField meaningPlural;
	private Font font = new ScreenSetup().DEFAULT_FONT;
	private int height = 30;
	private int width = 250;
	
	public NounEditPanel() {
		wordSingular = new OneEditField.Builder()
				.withTitle(" Wpisz rzeczownik  [lp]: ")
				.withHint("rzeczownik w liczbie pojedynczej")
				.withFont(font)
				.withHeight(height)
				.withWidth(width)
				.build();

		meaningSingular = new OneEditField.Builder()
				.withTitle("znaczenie: ")
				.withHint("znaczenie rzeczownika")
				.withFont(font)
				.withHeight(height)
				.withWidth(width)
				.build();

		wordPlural = new OneEditField.Builder()
				.withTitle("Wpisz rzeczownik [lm]: ")
				.withHint("rzeczownik w liczbie mnogiej")
				.withFont(font)
				.withHeight(height)
				.withWidth(width)
				.build();

		meaningPlural = new OneEditField.Builder()
				.withTitle("znaczenie: ")
				.withHint("znaczenie rzeczownika")
				.withFont(font)
				.withHeight(height)
				.withWidth(width)
				.build();

		this.setLayout(new GridLayout(2, 2));
		this.add(wordSingular);
		this.add(meaningSingular);
		this.add(wordPlural);
		this.add(meaningPlural);
	}

	public String getWordSingular() {
		return new PrepareString().cutSpaces(wordSingular.getValue());
	}

	public String getMeanigSingular() {
		return new PrepareString().cutSpaces(meaningSingular.getValue());
	}

	public String getWordPlural() {
		return new PrepareString().cutSpaces(wordPlural.getValue());
	}

	public String getMeanigPlural() {
		return new PrepareString().cutSpaces(meaningPlural.getValue());
	}

	public Word getWord(){
		Word word = new Word();
		return word;
	}

	public void clearEditFields() {
		wordSingular.clearField();
		meaningSingular.clearField();
		wordPlural.clearField();
		meaningPlural.clearField();
	}

}
