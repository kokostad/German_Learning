package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

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
		presentInfinitive = new TwoEditFields("Infinitiv Präsens", "Czas teraźniejszy");
		InfinitivePerfect = new TwoEditFields("Infinitiv Perfekt", "Czas dokonany");
		participleOne = new TwoEditFields("Partizip I", "Imiesłów I");
		participleTwo = new TwoEditFields("Partizip II", "Imiesłów II");

		GridLayout g = new GridLayout(4, 2);
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
}
