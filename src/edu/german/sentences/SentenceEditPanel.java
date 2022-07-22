package edu.german.sentences;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyFont;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditableField;
import edu.german.tools.Titles;

public class SentenceEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "sentence.properties";
	private OneEditableField sentence;
	private OneEditableField meaning;
	private OneEditableField word;
	private String boxList1;
	private String boxList2;
	private MyComboBox kindBox;
	private MyComboBox timeBox;
	private int mapSize;

	public SentenceEditPanel(int mapSize, String boxList1, String boxList2) {
		this.mapSize = mapSize;
		this.boxList1 = boxList1;
		this.boxList2 = boxList2;

		String labelInfo1 = "Wpisz zdanie niemieckie";
		String labelInfo2 = "Wpisz polskie znaczenie";

		sentence = new OneEditableField(labelInfo1, null, 16, 50);
		meaning = new OneEditableField(labelInfo2, null, 16, 50);
		word = new OneEditableField("Słowo kluczowe", "słowo klucz, według którego nastąpi wyszukiwanie",
				new MyFont().fontSize(), 17);

		GridLayout gl = new GridLayout(3, 1);
		JPanel editFieldsPan = new JPanel();
		editFieldsPan.setLayout(gl);
		editFieldsPan.add(sentence);
		editFieldsPan.add(meaning);

		JPanel boxPanel = new JPanel();

		if (boxList1 != null) {
			String[] selectionList = new MyProperties(CFG_FILE).getValuesArray(boxList1);
			kindBox = new MyComboBox(Titles.setTitel("CHOOSE_SENTENCE_MODE"), selectionList);
			boxPanel.add(kindBox);
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

	public Map<Object, Object> getValuesAsMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("SENTENCE", sentence.getValue());
		map.put("MEANING", meaning.getValue());
		if (boxList1 != null)
			map.put("MODE", kindBox.getValue());
		if (boxList2 != null)
			map.put("TIME", timeBox.getValue());
		if (word.getValue() != null)
			map.put("WORD", word.getValue());
		return map;
	}

	String[] getValues() {
		String[] array = new String[mapSize];
		int i = 0;
		if (sentence.getValue() != null && meaning.getValue() != null) {
			array[i++] = sentence.getValue();
			array[i++] = meaning.getValue();
			if (boxList1 != null)
				array[i++] = kindBox.getValue();
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
		kindBox.clearField();
		timeBox.clearField();
		word.clearField();
	}

	public void showData(String newSentence, String newMeaning, String mode, String time, String var) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		kindBox.setValue(mode);
		timeBox.setValue(time);
		word.setValue(var);
	}

	public void showData(String newSentence, String newMeaning, String mode, String time) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		kindBox.setValue(mode);
		timeBox.setValue(time);
	}

	public void showData(String newSentence, String newMeaning, String mode) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		kindBox.setValue(mode);
	}

	public void showData(String newSentence, String newMeaning) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
	}

	public void showData(String[] array) {
		if (array.length == 2)
			showData(array[0], array[1]);

		if (array.length == 3)
			showData(array[0], array[1], array[2]);

		if (array.length == 4)
			showData(array[0], array[1], array[2], array[3]);

		if (array.length == 5)
			showData(array[0], array[1], array[2], array[3], array[4]);

	}

	public void showData(List<HashMap<String, String>> list) {
		for (Map<String, String> map : list) {
			showData(map);
		}
	}

	public void showData(Map<String, String> map) {
		map.entrySet().forEach(entry -> {
			if ((entry.getKey()).equals("SENTENCE"))
				sentence.setValue(entry.getValue());
			if ((entry.getKey()).equals("MEANING"))
				meaning.setValue(entry.getValue());
			if ((entry.getKey()).equals("MODE"))
				kindBox.setValue(entry.getValue());
			if ((entry.getKey()).equals("TIME"))
				timeBox.setValue(entry.getValue());
			if ((entry.getKey()).equals("WORD"))
				word.setValue(entry.getValue());
		});
	}
}
