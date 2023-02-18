package edu.german.words;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyFont;
import edu.german.tools.OneEditField;
import edu.german.tools.ScreenSetup;
import edu.german.tools.TextCleaner;
import edu.german.tools.Titles;

public class WordEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditField word;
	private OneEditField meaning;
	private MyComboBox box;
	private String labelInfo1;
	private String labelInfo2;

	public WordEditPanel(String labelInfo1, String labelInfo2, String[] paramList) {
		this.labelInfo1 = labelInfo1;
		this.labelInfo2 = labelInfo2;
		ScreenSetup ss = new ScreenSetup();

		word = new OneEditField.Builder()
				.withTitle(Titles.setTitel("KEY_WORD"))
				.withHint(Titles.setTitel("KEY_WORD"))
				.withFont(ss.DEFAULT_FONT)
				.withWidth(ss.WORD_FIELD_WIDTH)
				.withHeight(ss.EDIT_FIELD_HEIGHT)
				.build();
		this.add(word);

		if (labelInfo2 != null) {
			meaning = new OneEditField.Builder()
					.withTitle(Titles.setTitel("MEANING"))
					.withHint(Titles.setTitel("WRITE_MEANING"))
					.withFont(ss.DEFAULT_FONT)
					.withWidth(ss.WORD_FIELD_WIDTH)
					.withHeight(ss.EDIT_FIELD_HEIGHT)
					.build();
			this.add(meaning);
		}

		box = new MyComboBox(Titles.setTitel("GENUS"), paramList);

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
			array[0] = new TextCleaner(word.getValue()).getWord();
		if (labelInfo2 != null)
			array[1] = new TextCleaner(meaning.getValue()).getWord();

		array[2] = box.getValue();

		return array;
	}

	public List<Object> getValuAsList() {
		List<Object> list = new ArrayList<>();
		if (labelInfo1 != null)
			list.add(new TextCleaner(word.getValue()).getWord());
		if (labelInfo2 != null)
			list.add(new TextCleaner(meaning.getValue()).getWord());

		list.add(box.getValue());

		return list;
	}

	public Map<String, String> getValueAsHashMap(String[] header) {
		Map<String, String> map = new HashMap<String, String>();
		String wordTmp = new TextCleaner(word.getValue()).getWord();
		if (wordTmp != null)
			map.put(header[0], wordTmp);

		wordTmp = new TextCleaner(meaning.getValue()).getWord();
		if (labelInfo2 != null)
			map.put(header[1], wordTmp);

		wordTmp = box.getValue();

		map.put(header[2], wordTmp);

		return map;
	}

	public boolean checkCorrectness() {
		if (!word.getValue().isBlank() && !meaning.getValue().isBlank())
			return true;

		return false;
	}

	public void showData(String wordTmp, String meaningTmp, String genusTmp) {
		word.setValue(wordTmp);
		meaning.setValue(meaningTmp);
		box.setValue(genusTmp);
	}

	public Map<Object, Object> getWord() {
		Map<Object, Object> map = new HashMap<>();
		if (labelInfo1 != null) {
			String str = word.getValue();
			map.put("WORD", str);
		}

		return map;
	}

	// TODO need to improve text cleaner, check if exist some value or null
	public Map<Object, Object> getMeaning() {
		Map<Object, Object> map = new HashMap<>();
		if (labelInfo1 != null) {
			String str = new TextCleaner(meaning.getValue()).getWord();
			map.put("WORD_MEANING", str);
		}

		return map;
	}

	public Map<Object, Object> getBoxValue() {
		Map<Object, Object> map = new HashMap<>();
		map.put("WORD_KIND", box.getValue());
		return map;
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
