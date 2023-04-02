package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JPanel;

import edu.german.tools.LabelSize;
import edu.german.tools.TextCleaner;
import edu.german.tools.TwoEditFields;

public class ImpersonalForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private TwoEditFields presentInfinitive;
	private TwoEditFields InfinitivePerfect;
	private TwoEditFields participleOne;
	private TwoEditFields participleTwo;
	private static final String tens = "UNPERSÖNLICHE FORMEN";

	public ImpersonalForm() {
		int labelSize = new LabelSize("Infinitiv Präsens").getSize();
		if (labelSize < new LabelSize("Czas teraźniejszy").getSize())
			labelSize = new LabelSize("Czas teraźniejszy").getSize();
		if (labelSize < new LabelSize("Infinitiv Perfekt").getSize())
			labelSize = new LabelSize("Infinitiv Perfekt").getSize();
		if (labelSize < new LabelSize("Czas dokonany").getSize())
			labelSize = new LabelSize("Czas dokonany").getSize();
		if (labelSize < new LabelSize("Partizip I").getSize())
			labelSize = new LabelSize("Partizip I").getSize();
		if (labelSize < new LabelSize("Imiesłów I").getSize())
			labelSize = new LabelSize("Imiesłów I").getSize();
		if (labelSize < new LabelSize("Partizip II").getSize())
			labelSize = new LabelSize("Partizip II").getSize();
		if (labelSize < new LabelSize("Imiesłów II").getSize())
			labelSize = new LabelSize("Imiesłów II").getSize();

		presentInfinitive = new TwoEditFields("Infinitiv Präsens", labelSize, "Czas teraźniejszy", labelSize);
		InfinitivePerfect = new TwoEditFields("Infinitiv Perfekt", labelSize, "Czas dokonany", labelSize);
		participleOne = new TwoEditFields("Partizip I", labelSize, "Imiesłów I", labelSize);
		participleTwo = new TwoEditFields("Partizip II", labelSize, "Imiesłów II", labelSize);

		GridLayout g = new GridLayout(6, 1, 5, 5);
		this.setLayout(g);
		this.add(presentInfinitive);
		this.add(InfinitivePerfect);
		this.add(participleOne);
		this.add(participleTwo);
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		if (!presentInfinitive.getFirst().isBlank()) {
			map.put("PRESENT_INFINITIVE", new TextCleaner(presentInfinitive.getFirst()).getWord());
			map.put("CZAS_TERAZNIEJSZ", new TextCleaner(presentInfinitive.getSecond()).getWord());
			map.put("INFINITIVE_PERFECT", new TextCleaner(InfinitivePerfect.getFirst()).getWord());
			map.put("CZAS_DOKONANY", new TextCleaner(InfinitivePerfect.getSecond()).getWord());
			map.put("PARTICIPLE_I", new TextCleaner(participleOne.getFirst()).getWord());
			map.put("IMIESLOW_I", new TextCleaner(participleOne.getSecond()).getWord());
			map.put("PARTICIPLE_II", new TextCleaner(participleTwo.getFirst()).getWord());
			map.put("IMIESLOW_II", new TextCleaner(participleTwo.getSecond()).getWord());
			map.put("TENS", getTens());
			return map;
		}
		return null;
	}

	public String getTens() {
		return tens;
	}

	public void clearEditFields() {
		presentInfinitive.clear();
		InfinitivePerfect.clear();
		participleOne.clear();
		participleTwo.clear();
	}

	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("PRESENT_INFINITIVE", new TextCleaner(presentInfinitive.getFirst()).getWord());
		properties.put("CZAS_TERAZNIEJSZ", new TextCleaner(presentInfinitive.getSecond()).getWord());
		properties.put("INFINITIVE_PERFECT", new TextCleaner(InfinitivePerfect.getFirst()).getWord());
		properties.put("CZAS_DOKONANY", new TextCleaner(InfinitivePerfect.getSecond()).getWord());
		properties.put("PARTICIPLE_I", new TextCleaner(participleOne.getFirst()).getWord());
		properties.put("IMIESLOW_I", new TextCleaner(participleOne.getSecond()).getWord());
		properties.put("PARTICIPLE_II", new TextCleaner(participleTwo.getFirst()).getWord());
		properties.put("IMIESLOW_II", new TextCleaner(participleTwo.getSecond()).getWord());
		properties.put("TENS", getTens());
		properties.put("MODUS", "IMPERSONAL");

		return properties;
	}

}
