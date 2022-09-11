package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import edu.german.tools.TextCleaner;
import edu.german.tools.TwoEditFields;

public class AddVerbTens extends JPanel {
	private static final long serialVersionUID = 1L;
	private TwoEditFields i;
	private TwoEditFields youS;
	private TwoEditFields heSheIt;
	private TwoEditFields we;
	private TwoEditFields youP;
	private TwoEditFields they;
	private String tens;

	public AddVerbTens(String tens) {
		this.tens = tens;
		i = new TwoEditFields("ich", "ja");
		youS = new TwoEditFields("du", "ty");
		heSheIt = new TwoEditFields("er, sie, es", "on, ona, ono");
		we = new TwoEditFields("wir", "my");
		youP = new TwoEditFields("ihr", "wy");
		they = new TwoEditFields("Sie, sie", "Pan(i)/oni");

		GridLayout gLeft = new GridLayout(3, 1);
		JPanel left = new JPanel();
		left.setLayout(gLeft);
		left.add(i);
		left.add(youS);
		left.add(heSheIt);

		GridLayout gRight = new GridLayout(3, 1);
		JPanel right = new JPanel();
		right.setLayout(gRight);
		right.add(we);
		right.add(youP);
		right.add(they);

		GridLayout g = new GridLayout(1, 2);
		setLayout(g);
		add(left);
		add(right);
	}

	public void clearEditFields() {
		i.clear();
		youS.clear();
		heSheIt.clear();
		we.clear();
		youP.clear();
		they.clear();
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ICH", new TextCleaner(i.getFirst()).getWord());
		map.put("JA", new TextCleaner(i.getSecond()).getWord());
		map.put("DU", new TextCleaner(youS.getFirst()).getWord());
		map.put("TY", new TextCleaner(youS.getSecond()).getWord());
		map.put("ER_SIE_ES", new TextCleaner(heSheIt.getFirst()).getWord());
		map.put("ON_ONA_ONO", new TextCleaner(heSheIt.getSecond()).getWord());
		map.put("WIR", new TextCleaner(we.getFirst()).getWord());
		map.put("MY", new TextCleaner(we.getSecond()).getWord());
		map.put("IHR", new TextCleaner(youP.getFirst()).getWord());
		map.put("WY", new TextCleaner(youP.getSecond()).getWord());
		map.put("SIE_SIE", new TextCleaner(they.getFirst()).getWord());
		map.put("ONI_PANSTWO", new TextCleaner(they.getSecond()).getWord());
		map.put("TENS", tens);
		return map;
	}

}
