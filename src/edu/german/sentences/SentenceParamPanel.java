package edu.german.sentences;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.Titles;

public class SentenceParamPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "sentence.properties";
	private MyComboBox typeBox;
	private MyComboBox tensBox;
	private MyComboBox modeBox;

	public SentenceParamPanel() {
		String[] selectionList = new MyProperties(CFG_FILE).getValuesArray("MODE_LIST");
		typeBox = new MyComboBox(Titles.setTitel("GENUS"), selectionList);

		String[] mode = new MyProperties(CFG_FILE).getValuesArray("GENUS_LIST");
		modeBox = new MyComboBox(Titles.setTitel("MODE"), mode);

		String[] tenses = new MyProperties(CFG_FILE).getValuesArray("TENS_LIST");
		tensBox = new MyComboBox(Titles.setTitel("TENS"), tenses);

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.add(typeBox);
		this.add(modeBox);
		this.add(tensBox);
	}

	public String getType() {
		return typeBox.getValue();
	}

	public void setType(String type) {
		typeBox.setValue(type);
	}

	public String getTens() {
		return tensBox.getValue();
	}

	public void setTens(String tens) {
		tensBox.setValue(tens);
	}

	public String getMode() {
		return modeBox.getValue();
	}

	public void setMode(String category) {
		modeBox.setValue(category);
	}

	public Map<Object, Object> getModeMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("MODE", getMode());
		return map;
	}

	public Map<Object, Object> getGenusMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("GENUS", getType());
		return map;
	}

	public Map<Object, Object> getTensMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("TENS", getTens());
		return map;
	}

	public void showData(Map<String, String> selectedRowAsMap) {
			if (selectedRowAsMap.containsKey("MODE"))
				setMode(selectedRowAsMap.get("MODE"));
			if (selectedRowAsMap.containsKey("TENS"))
				setTens(selectedRowAsMap.get("TENS"));
			if (selectedRowAsMap.containsKey("GENUS"))
				setType(selectedRowAsMap.get("GENUS"));
	}


}
