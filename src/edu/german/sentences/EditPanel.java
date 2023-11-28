package edu.german.sentences;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.OneEditField;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.Translate;

public class EditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditField sentence;
	private OneEditField meaning;
	private String[] header;

	public EditPanel() {
		ScreenSetup sc = new ScreenSetup();

		sentence = new OneEditField.Builder()
				.withTitle(Translate.setText("write_german_sentence"))
				.withHint(Translate.setText("write_in_german"))
				.withFont(sc.DEFAULT_FONT)
				.withWidth(sc.EDIT_FIELD_WIDTH)
				.withHeight(sc.EDIT_FIELD_FACTOR)
				.build();

		meaning = new OneEditField.Builder()
				.withTitle(Translate.setText("write_polish_meaning"))
				.withHint(Translate.setText("write_in_polish"))
				.withFont(sc.DEFAULT_FONT)
				.withWidth(sc.EDIT_FIELD_WIDTH)
				.withHeight(sc.EDIT_FIELD_FACTOR)
				.build();

		GridLayout gl = new GridLayout(2, 1, 2, 2);
		JPanel editFieldsPan = new JPanel();
		editFieldsPan.setLayout(gl);
		editFieldsPan.add(sentence);
		editFieldsPan.add(meaning);

		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
		this.add(editFieldsPan);
	}

	public Map<Object, Object> getSentence() {
		Map<Object, Object> map = new HashMap<>();
		if (sentence != null || !sentence.getValue().isBlank())
			map.put("SENTENCE", sentence.getValue());

		return map;
	}

	public Map<Object, Object> getMeaning() {
		Map<Object, Object> map = new HashMap<>();
		if (!meaning.getValue().isBlank() || meaning.getValue() != null)
			map.put("SENTENCE_MEANING", meaning.getValue());

		return map;
	}

	public Map<Object, Object> getMap() {
		Map<Object, Object> map = new HashMap<>();
		if (!sentence.getValue().isBlank())
			map.put("SENTENCE", sentence.getValue());
		else
			new ShowMessage("Empty field!");

		if (!meaning.getValue().isEmpty())
			map.put("SENTENCE_MEANING", meaning.getValue());
		else
			new ShowMessage("Empty field!");

		return map;
	}

	String[] getArray() {
		String[] array = new String[header.length];
		int i = 0;
		if (sentence.getValue() != null || meaning.getValue() != null) {
			array[i++] = sentence.getValue();
			array[i++] = meaning.getValue();

			return array;
		}
		return null;
	}

	void clearFields() {
		sentence.clearField();
		meaning.clearField();
	}

	public void showData(Map<String, String> map) {
		map.entrySet().forEach(entry -> {
			if ((entry.getKey().toUpperCase()).equals("SENTENCE"))
				sentence.setValue(entry.getValue());
			if ((entry.getKey().toUpperCase()).equals("SENTENCE_MEANING"))
				meaning.setValue(entry.getValue());
		});
	}
}
