package edu.german.sentences;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.Titel;

public class SentenceParamPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String PATH = "src/edu/german/sentences/cfg/";
	private String CFG_FILE = "sentence.properties";
	private MyComboBox kindBox;
	private MyComboBox tribeBox;
	private MyComboBox tensBox;

	public SentenceParamPanel() {
		// NOTICE kind of sentence
		String[] selectionList = new MyProperties(PATH, CFG_FILE).getValuesArray("SENTENCE_KIND_LIST");
		kindBox = new MyComboBox(Titel.setTitel("KIND"), selectionList);

		// NOTICE mode = tribe
		String[] mode = new MyProperties(PATH, CFG_FILE).getValuesArray("SENTENCE_TRIBE_LIST");
		tribeBox = new MyComboBox(Titel.setTitel("TRIBE"), mode);

		// NOTICE tens = time
		String[] tenses = new MyProperties(PATH, CFG_FILE).getValuesArray("SENTENCE_TENS_LIST");
		tensBox = new MyComboBox(Titel.setTitel("TENS"), tenses);

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.add(kindBox);
		this.add(tribeBox);
		this.add(tensBox);
	}

	public String getKind() {
		return kindBox.getValue();
	}

	public void setKind(String kind) {
		kindBox.setValue(kind);
	}

	public String getTens() {
		return tensBox.getValue();
	}

	public void setTens(String tens) {
		tensBox.setValue(tens);
	}

	public void setTribe(String tribe) {
		tribeBox.setValue(tribe);
	}

	public String getTribe() {
		return tribeBox.getValue();
	}

	public Map<Object, Object> getKindAsMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("SENTENCE_KIND", getKind());
		return map;
	}

	public Map<Object, Object> getTribeAsMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("SENTENCE_TRIBE", getTribe());
		return map;
	}

	public Map<Object, Object> getTensAsMap() {
		Map<Object, Object> map = new HashMap<>();
		map.put("TENS", getTens());
		return map;
	}

	public void showData(Map<String, String> selectedRowAsMap) {
		if (selectedRowAsMap.containsKey("SENTENCE_KIND"))
			setKind(selectedRowAsMap.get("SENTENCE_KIND"));
		if (selectedRowAsMap.containsKey("SENTENCE_TRIBE"))
			setTribe(selectedRowAsMap.get("SENTENCE_TRIBE"));
		if (selectedRowAsMap.containsKey("TENS"))
			setTens(selectedRowAsMap.get("TENS"));
	}

}
