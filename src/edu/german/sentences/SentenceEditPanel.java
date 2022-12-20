package edu.german.sentences;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditableField;
import edu.german.tools.Titles;
import edu.german.tools.Translate;

public class SentenceEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "sentence.properties";
	private OneEditableField sentence;
	private OneEditableField meaning;
	private OneEditableField word;
	private String boxList1;
	private String boxList2;
	private MyComboBox modeBox;
	private MyComboBox timeBox;
	private int mapSize;

	public SentenceEditPanel(int mapSize, String boxList1, String boxList2) {
		this.mapSize = mapSize;
		this.boxList1 = boxList1;
		this.boxList2 = boxList2;

		sentence = new OneEditableField(Translate.setText("write_german_sentence"),
				Translate.setText("write_in_german"), 17, 50);
		meaning = new OneEditableField(Translate.setText("write_polish_meaning"), Translate.setText("write_in_polish"),
				17, 50);

		word = new OneEditableField(Translate.setText("key_word"), Translate.setText("search_word"), 17, 15);

		GridLayout gl = new GridLayout(3, 1);
		JPanel editFieldsPan = new JPanel();
		editFieldsPan.setLayout(gl);
		editFieldsPan.add(sentence);
		editFieldsPan.add(meaning);

		JPanel boxPanel = new JPanel();

		if (boxList1 != null) {
			String[] selectionList = new MyProperties(CFG_FILE).getValuesArray(boxList1);
			modeBox = new MyComboBox(Titles.setTitel("CHOOSE_SENTENCE_MODE"), selectionList);
			boxPanel.add(modeBox);
		}

		if (boxList2 != null) {
			String[] times = new MyProperties(CFG_FILE).getValuesArray(boxList2);
			timeBox = new MyComboBox(Titles.setTitel("CHOOSE_TIME"), times);
			boxPanel.add(timeBox);
		}

		boxPanel.add(word);

		this.add(editFieldsPan);
		this.add(boxPanel);
	}

	Map<Object, Object> getValuesAsMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("SENTENCE", sentence.getValue());
		map.put("MEANING", meaning.getValue());
		if (boxList1 != null)
			map.put("MODE", modeBox.getValue());
		if (boxList2 != null)
			map.put("TIME", timeBox.getValue());
		if (word.getValue() != null)
			map.put("WORD", word.getValue());
		return map;
	}

	String[] getValuesAsArray() {
		String[] array = new String[mapSize];
		int i = 0;
		if (sentence.getValue() != null && meaning.getValue() != null) {
			array[i++] = sentence.getValue();
			array[i++] = meaning.getValue();
			if (boxList1 != null)
				array[i++] = modeBox.getValue();
			if (boxList2 != null)
				array[i++] = timeBox.getValue();
			if (word.getValue() != null)
				array[i] = word.getValue();

			return array;
		}

		return null;
	}

	void clearEditFields() {
		sentence.clearField();
		meaning.clearField();
		modeBox.clearField();
		timeBox.clearField();
		word.clearField();
	}

	public void showData(String newSentence, String newMeaning, String mode, String time, String var) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		modeBox.setValue(mode);
		timeBox.setValue(time);
		word.setValue(var);
	}

	public void showData(String newSentence, String newMeaning, String mode, String var) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		modeBox.setValue(mode);
		word.setValue(var);
	}

	public void showData(String newSentence, String newMeaning, String var) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		word.setValue(var);
	}

	public void showData(String[] array) {
		if (array.length == 3)
			showData(array[0], array[2], array[2]);

		if (array.length == 4)
			showData(array[0], array[1], array[2], array[3]);

		if (array.length == 5)
			showData(array[0], array[1], array[2], array[3], array[4]);

	}

//	sentence.setValue(newSentence);
//	meaning.setValue(newMeaning);
//	box.setValue(mode);
//	timeBox.setValue(time);
//	word.setValue(var);
	public void showData(Map<String, String> map) {
		map.entrySet().forEach(entry -> {
//			System.out.println(entry.getKey() + " " + entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("SENTENCE"))
				sentence.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("MENING"))
				meaning.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("MODE"))
				modeBox.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("TIME"))
				timeBox.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("WORD"))
				word.setValue(entry.getValue());
		});

	}
}
