package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JPanel;

import edu.german.tools.LabelSize;
import edu.german.tools.TextHandler;
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
		properties.put("TENS", getTens());
		properties.put("MODUS", "IMPERSONAL");
		properties.put("PRESENT_INFINITIVE", new TextHandler(presentInfinitive.getFirst()).getWord());
		properties.put("CZAS_TERAZNIEJSZ", new TextHandler(presentInfinitive.getSecond()).getWord());
		properties.put("INFINITIVE_PERFECT", new TextHandler(InfinitivePerfect.getFirst()).getWord());
		properties.put("CZAS_DOKONANY", new TextHandler(InfinitivePerfect.getSecond()).getWord());
		properties.put("PARTICIPLE_I", new TextHandler(participleOne.getFirst()).getWord());
		properties.put("IMIESLOW_I", new TextHandler(participleOne.getSecond()).getWord());
		properties.put("PARTICIPLE_II", new TextHandler(participleTwo.getFirst()).getWord());
		properties.put("IMIESLOW_II", new TextHandler(participleTwo.getSecond()).getWord());

		return properties;
	}

	public void setData(Properties prop) {
		presentInfinitive.setData(prop.getProperty("PRESENT_INFINITIVE"), prop.getProperty("CZAS_TERAZNIEJSZ"));
		InfinitivePerfect.setData(prop.getProperty("INFINITIVE_PERFECT"), prop.getProperty("CZAS_DOKONANY"));
		participleOne.setData(prop.getProperty("PARTICIPLE_I"), prop.getProperty("IMIESLOW_I"));
		participleTwo.setData(prop.getProperty("PARTICIPLE_II"), prop.getProperty("IMIESLOW_II"));
	}

}
