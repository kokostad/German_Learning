package edu.german.sentences;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditField;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titles;
import edu.german.tools.Translate;

public class SentenceEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "sentence.properties";
	private OneEditField sentence;
	private OneEditField meaning;
	private OneEditField word;
	private String typeList;
	private String tensList;
	private MyComboBox typeBox;
	private MyComboBox tensBox;
	private MyComboBox categoryBox;
	private String[] header;

	public SentenceEditPanel(String[] header, String typeList, String tensList) {
		this.header = header;
		this.typeList = typeList;
		this.tensList = tensList;
		ScreenSetup ss = new ScreenSetup();

		sentence = new OneEditField.Builder()
				.withTitle(Translate.setText("write_german_sentence"))
				.withHint(Translate.setText("write_in_german"))
				.withFontSize(ss.DEFAULT_FONT_SIZE)
				.withWidth( ss.EDIT_FIELD_WIDTH - 50)
				.withHeight(ss.EDIT_FIELD_FACTOR)
				.build();

		meaning = new OneEditField.Builder()
				.withTitle(Translate.setText("write_polish_meaning"))
				.withHint(Translate.setText("write_in_polish"))
				.withFontSize(ss.DEFAULT_FONT_SIZE)
				.withWidth( ss.EDIT_FIELD_WIDTH - 50)
				.withHeight(ss.EDIT_FIELD_FACTOR)
				.build();

		word = new OneEditField.Builder()
				.withTitle(Translate.setText("key_word"))
				.withHint(Translate.setText("search_word"))
				.withFontSize(ss.DEFAULT_FONT_SIZE)
				.withWidth(ss.EDIT_FIELD_WIDTH / 2)
				.withHeight(ss.EDIT_FIELD_HEIGHT)
				.build();

		GridLayout gl = new GridLayout(2, 1);
		JPanel editFieldsPan = new JPanel();
		editFieldsPan.setLayout(gl);
		editFieldsPan.add(sentence);
		editFieldsPan.add(meaning);

		GridLayout gl2 = new GridLayout(2, 2);
		JPanel boxPanel = new JPanel();

		boxPanel.setLayout(gl2);
		boxPanel.add(word);

		if (typeList != null) {
			String[] selectionList = new MyProperties(CFG_FILE).getValuesArray(typeList);
			typeBox = new MyComboBox(Titles.setTitel("CHOOSE_SENTENCE_MODE"), selectionList);
			boxPanel.add(typeBox);
		}

		if (tensList != null) {
			String[] tenses = new MyProperties(CFG_FILE).getValuesArray(tensList);
			tensBox = new MyComboBox(Titles.setTitel("CHOOSE_TENS"), tenses);
			boxPanel.add(tensBox);
		}

		String[] mode = new MyProperties(CFG_FILE).getValuesArray("CATEGORY");
		categoryBox = new MyComboBox(Titles.setTitel("CHOOSE_SENTENCE_GENUS"), mode);
		boxPanel.add(categoryBox);

		this.add(editFieldsPan);
		this.add(boxPanel);
	}

	Map<Object, Object> getValueAsMap() {
		Map<Object, Object> map = new HashMap<>();
		if (!sentence.getValue().isBlank())
			map.put("SENTENCE", sentence.getValue());
		else
			new ShowMessage("Empty field!");

		if (!meaning.getValue().isEmpty())
			map.put("MEANING", meaning.getValue());
		else
			new ShowMessage("Empty field!");

		if (typeList != null)
			map.put("TYPE", typeBox.getValue());

		if (categoryBox.getValue() != null)
			map.put("CATEGORY", categoryBox.getValue());

		if (tensList != null)
			map.put("TENS", tensBox.getValue());

		if (word.getValue() != null)
			map.put("WORD", word.getValue());

		return map;
	}

	String[] getValuesAsArray() {
		String[] array = new String[header.length];
		int i = 0;
		if (sentence.getValue() != null || meaning.getValue() != null) {
			array[i++] = sentence.getValue();
			array[i++] = meaning.getValue();
			array[i++] = categoryBox.getValue();
			array[i++] = typeBox.getValue();
			array[i++] = tensBox.getValue();
			array[i] = word.getValue();

			return array;
		}

		return null;
	}

	void clearEditFields() {
		sentence.clearField();
		meaning.clearField();
		typeBox.clearField();
		categoryBox.clearField();
		tensBox.clearField();
		word.clearField();
	}

	public void showData(String newSentence, String newMeaning, String type, String category, String tens, String var) {
		if (!newSentence.isBlank())
			sentence.setValue(newSentence);
		if (!newMeaning.isBlank())
			meaning.setValue(newMeaning);
		if (!category.isBlank())
			categoryBox.setValue(category);
		if (type.isBlank())
			typeBox.setValue(type);
		if (!tens.isBlank())
			tensBox.setValue(tens);
		if (!var.isBlank())
			word.setValue(var);
	}

	public void showData(Map<String, String> map) {
		map.entrySet().forEach(entry -> {
			if ((entry.getKey().toUpperCase()).equals("SENTENCE"))
				sentence.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("MEANING"))
				meaning.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("TYPE"))
				typeBox.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("CATEGORY"))
				categoryBox.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("TENS"))
				tensBox.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("WORD"))
				word.setValue(entry.getValue());
		});
	}

}
