package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

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
		map.put("ICH", i.getFirst());
		map.put("JA", i.getSecond());
		map.put("DU", youS.getFirst());
		map.put("TY", youS.getSecond());
		map.put("ER_SIE_ES", heSheIt.getFirst());
		map.put("ON_ONA_ONO", heSheIt.getSecond());
		map.put("WIR", we.getFirst());
		map.put("MY", we.getSecond());
		map.put("IHR", youP.getFirst());
		map.put("WY", youP.getSecond());
		map.put("SIE_SIE", they.getFirst());
		map.put("ONI_PANSTWO", they.getSecond());
		map.put("TENS", tens);
		return map;
	}

}
