package edu.german.words;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.OneEditableField;
import edu.german.tools.Titles;

public class EditWordsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditableField word;
	private OneEditableField meaning;
	private MyComboBox box;
	
	public EditWordsPanel(String labelInfo1, String labelInfo2, String[] selectionList) {
		word = new OneEditableField(labelInfo1, null, 16, 16);
		meaning = new OneEditableField(labelInfo2, null, 16, 16);
		box = new MyComboBox(Titles.setTitel("CHOOSE_WORDS_GENUS"), selectionList);

		this.add(word);
		this.add(meaning);
		this.add(box);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
	}

	public void setEditFields(String str) {
		word.setValue(str);
		meaning.setValue(str);
	}

	public String[] getValuAsArray() {
		String[] array = new String[3];
		array[0] = word.getValue();
		array[1] = meaning.getValue();
		array[2] = box.getValue();

		return array;
	}

	public List<Object> getValuAsList() {
		List<Object> list = new ArrayList<>();
		list.add(word.getValue());
		list.add(meaning.getValue());
		list.add(box.getValue());

		return list;
	}

	public Map<String, String> getValueAsHashMap(String[] tableHeaders) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(tableHeaders[0], word.getValue());
		map.put(tableHeaders[1], meaning.getValue());
		map.put(tableHeaders[2], box.getValue());

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

}
