package edu.german.words;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.OneEditField;
import edu.german.tools.ScreenSetup;
import edu.german.tools.TextHandler;
import edu.german.tools.Titel;

public class KeyWord extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditField word;
	private OneEditField meaning;
	private MyComboBox box;
	private String labelInfo1;
	private String labelInfo2;

	public KeyWord(String labelInfo1, String labelInfo2, String[] paramList) {
		this.labelInfo1 = labelInfo1;
		this.labelInfo2 = labelInfo2;
		ScreenSetup ss = new ScreenSetup();

		word = new OneEditField.Builder()
				.withTitle(Titel.setTitel("KEY_WORD"))
				.withHint(Titel.setTitel("KEY_WORD"))
				.withFont(ss.DEFAULT_FONT)
				.withWidth(ss.WORD_FIELD_WIDTH)
				.withHeight(ss.EDIT_FIELD_HEIGHT)
				.build();
		this.add(word);

		if (labelInfo2 != null) {
			meaning = new OneEditField.Builder()
					.withTitle(Titel.setTitel("MEANING"))
					.withHint(Titel.setTitel("WRITE_MEANING"))
					.withFont(ss.DEFAULT_FONT)
					.withWidth(ss.WORD_FIELD_WIDTH)
					.withHeight(ss.EDIT_FIELD_HEIGHT)
					.build();
			this.add(meaning);
		}

		box = new MyComboBox(Titel.setTitel("GENUS") + ": ", paramList);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
		this.add(box);
	}

	public void setWordEditField(String wordVal) {
		word.setValue(wordVal);
	}

	public void setMeaningEditField(String meaningVal) {
		meaning.setValue(meaningVal);
	}

	private void setBox(String str) {
		box.setValue(str);
	}

	public String[] getValuAsArray() {
		String[] array = new String[3];
		if (labelInfo1 != null)
			array[0] = new TextHandler(word.getValue()).getWord();
		if (labelInfo2 != null)
			array[1] = new TextHandler(meaning.getValue()).getWord();

		array[2] = box.getValue();

		return array;
	}

	public List<Object> getValuAsList() {
		List<Object> list = new ArrayList<>();
		if (labelInfo1 != null)
			list.add(new TextHandler(word.getValue()).getWord());
		if (labelInfo2 != null)
			list.add(new TextHandler(meaning.getValue()).getWord());

		list.add(box.getValue());

		return list;
	}

	public Map<String, String> getValueAsHashMap(String[] header) {
		Map<String, String> map = new HashMap<String, String>();
		String w = new TextHandler(word.getValue()).getWord();
		String m = new TextHandler(meaning.getValue()).getWord();
		String g = new TextHandler(box.getValue()).getWord();

		if (w != null)
			map.put("WORD", w);
		if (m != null)
			map.put("WORD_MEANING", w);
		if (g != null)
			map.put("WORD_KIND", g);

		return map;
	}

	public boolean checkCorrectness() {
		if (!word.getValue().isBlank() && !meaning.getValue().isBlank())
			return true;

		return false;
	}

	public void showData(String w, String m, String g) {
		word.setValue(w);
		meaning.setValue(m);
		box.setValue(g);
	}

	public Map<Object, Object> getWord() {
		if (labelInfo1 != null && word.getValue() != null) {
			Map<Object, Object> map = new HashMap<>();
			map.put("WORD", word.getValue());
			return map;
		}
		return null;
	}

	public Map<Object, Object> getMeaning() {
		if (labelInfo1 != null) {
			Map<Object, Object> map = new HashMap<>();
			map.put("WORD_MEANING", new TextHandler(meaning.getValue()).getWord());
			return map;
		}
		return null;
	}

	public Map<Object, Object> getBoxValue() {
		if (labelInfo1 != null && box.getValue() != null) {
			Map<Object, Object> map = new HashMap<>();
			map.put("WORD_KIND", box.getValue());
			return map;
		}
		return null;
	}

	public void showData(Map<String, String> selectedRowAsMap) {
		if (selectedRowAsMap.containsKey("WORD"))
			setWordEditField(selectedRowAsMap.get("WORD"));
		if (selectedRowAsMap.containsKey("WORD_MEANING"))
			setMeaningEditField(selectedRowAsMap.get("WORD_MEANING"));
		if (selectedRowAsMap.containsKey("WORD_KIND"))
			setBox(selectedRowAsMap.get("WORD_KIND"));
	}

	public void clearEditFields() {
		word.setValue(null);
		meaning.setValue(null);
	}
}
