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
	private MyComboBox box;

	public SentenceEditPanel(String labelInfo1, String labelInfo2, String[] selectionList) {
		sentence = new OneEditableField(labelInfo1, null, 16, 70);
		meaning = new OneEditableField(labelInfo2, null, 16, 70);

		GridLayout gl = new GridLayout(2, 1);
		JPanel editFieldsPan = new JPanel();
		editFieldsPan.setLayout(gl);
		editFieldsPan.add(sentence);
		editFieldsPan.add(meaning);

		box = new MyComboBox(Titles.setTitel("CHOOSE_SENTENCE_MODE"), selectionList);
		JPanel boxPanel = new JPanel();
		boxPanel.add(box);

		this.add(editFieldsPan);
		this.add(boxPanel);
	}

	Map getValuesAsMap() {
		Map map = new HashMap<>();
		map.put("SENTENCE", sentence.getValue());
		map.put("MEANING", meaning.getValue());
		map.put("MODE", box.getValue());
		return map;
	}

	String[] getValues() {
		String[] array = new String[3];
		String newSentence = sentence.getValue();
		String newMeaning = meaning.getValue();
		String var = box.getValue();

		if (newSentence != null && newMeaning != null && var != null) {
			array[0] = newSentence;
			array[1] = newMeaning;
			array[2] = var;

			return array;
		}

		return null;
	}

	void setValues(String newSentence, String newMeaning, String mode) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		box.setValue(mode);
	}

	void clearEditFields() {
		sentence.setValue(null);
		meaning.setValue(null);
		box.setValue(null);
	}

	public void showData(String newSentence, String newMeaning, String mode) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		box.setValue(mode);
	}
}
