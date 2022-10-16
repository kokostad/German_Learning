package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.TextCleaner;
import edu.german.tools.TwoEditFields;

public class VerbImperative extends JPanel {
	private static final long serialVersionUID = 1L;
	private TwoEditFields youS;
	private TwoEditFields youP;
	private TwoEditFields we;
	private TwoEditFields they;
	private static final String tens = "IMPERATIV";

	public VerbImperative() {
		youS = new TwoEditFields("du", "ty");
		youP = new TwoEditFields("ihr", "wy");
		we = new TwoEditFields("wir", "my");
		they = new TwoEditFields("Sie, sie", "Pan(i)/oni");

		GridLayout g = new GridLayout(4, 2);
		this.setLayout(g);
		this.add(youS);
		this.add(youP);
		this.add(we);
		this.add(they);
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		if (!youS.getFirst().isBlank()) {
			map.put("DU", new TextCleaner(youS.getFirst()).getWord());
			map.put("TY", new TextCleaner(youS.getSecond()).getWord());
			map.put("WIR", new TextCleaner(we.getFirst()).getWord());
			map.put("MY", new TextCleaner(we.getSecond()).getWord());
			map.put("IHR", new TextCleaner(youP.getFirst()).getWord());
			map.put("WY", new TextCleaner(youP.getSecond()).getWord());
			map.put("SIE_SIE", new TextCleaner(they.getFirst()).getWord());
			map.put("ONI_PANSTWO", new TextCleaner(they.getSecond()).getWord());
			map.put("TENS", getTens());
			return map;
		}
		return null;
	}

	public String getTens() {
		return tens;
	}

	public void clearEditFields() {
		youS.clear();
		youP.clear();
		we.clear();
		they.clear();
	}
}
