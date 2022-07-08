package edu.german.sentences;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.OneEditableField;
import edu.german.tools.Titles;

public class SentenceEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditableField sentence;
	private OneEditableField meaning;
	private OneEditableField word;
	private MyComboBox box;
	private String[] selectionList;
	private int mapSize;

	public SentenceEditPanel(String labelInfo1, String labelInfo2, String[] selectionList, int mapSize) {
		this.selectionList = selectionList;
		this.mapSize = mapSize;

		sentence = new OneEditableField(labelInfo1, null, 16, 50);
		meaning = new OneEditableField(labelInfo2, null, 16, 50);

		word = new OneEditableField("Słowo kluczowe", "słowo klucz, według którego nastąpi wyszukiwanie", 16, 20);

		GridLayout gl = new GridLayout(2, 1);
		JPanel editFieldsPan = new JPanel();
		editFieldsPan.setLayout(gl);
		editFieldsPan.add(sentence);
		editFieldsPan.add(meaning);

		box = new MyComboBox(Titles.setTitel("CHOOSE_SENTENCE_MODE"), selectionList);
		JPanel boxPanel = new JPanel();
		boxPanel.add(box);
		boxPanel.add(word);

		this.add(editFieldsPan);
		this.add(boxPanel);
	}

	Map<Object, Object> getValuesAsMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("SENTENCE", sentence.getValue());
		map.put("MEANING", meaning.getValue());
		map.put("MODE", box.getValue());
		map.put("WORD", word.getValue());
		return map;
	}

	String[] getValues() {
		String[] array = new String[mapSize];
		String newSentence = sentence.getValue();
		String newMeaning = meaning.getValue();
		String boxVar = box.getValue();
		String wordField = word.getValue();

		if (newSentence != null && newMeaning != null && boxVar != null) {
			array[0] = newSentence;
			array[1] = newMeaning;
			array[2] = boxVar;
			array[3] = wordField;

			return array;
		}

		return null;
	}

	void setValues(String newSentence, String newMeaning, String mode, String var) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		box.setValue(mode);
		word.setValue(var);
	}

	void clearEditFields() {
		sentence.clearField();
		meaning.clearField();
		box.clearField();
		word.clearField();
	}

	public void showData(String newSentence, String newMeaning, String mode, String var) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		box.setValue(mode);
		word.setValue(var);
	}
}
