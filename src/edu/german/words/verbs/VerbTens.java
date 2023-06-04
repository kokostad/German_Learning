package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JPanel;

import edu.german.tools.TextCleaner;
import edu.german.tools.TwoEditFields;

public class VerbTens extends JPanel {
	private static final long serialVersionUID = 1L;
	private String tens;
	private String modus;
	private TwoEditFields i;
	private TwoEditFields youS;
	private TwoEditFields heSheIt;
	private TwoEditFields we;
	private TwoEditFields youP;
	private TwoEditFields they;

	public VerbTens(String tens, String modus) {
		this.tens = tens;
		this.modus = modus;
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

	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("MODUS", modus);
		properties.put("TENS", tens);
		properties.put("ICH", new TextCleaner(i.getFirst()).getWord());
		properties.put("JA", new TextCleaner(i.getSecond()).getWord());
		properties.put("DU", new TextCleaner(youS.getFirst()).getWord());
		properties.put("TY", new TextCleaner(youS.getSecond()).getWord());
		properties.put("ER_SIE_ES", new TextCleaner(heSheIt.getFirst()).getWord());
		properties.put("ON_ONA_ONO", new TextCleaner(heSheIt.getSecond()).getWord());
		properties.put("WIR", new TextCleaner(we.getFirst()).getWord());
		properties.put("MY", new TextCleaner(we.getSecond()).getWord());
		properties.put("IHR", new TextCleaner(youP.getFirst()).getWord());
		properties.put("WY", new TextCleaner(youP.getSecond()).getWord());
		properties.put("SIE_SIE", new TextCleaner(they.getFirst()).getWord());
		properties.put("ONI_PANSTWO", new TextCleaner(they.getSecond()).getWord());

		return properties;
	}

	public void setValues(Properties properties) {
		i.setValue(properties.getProperty("ICH"), properties.getProperty("JA"));
		youS.setValue(properties.getProperty("DU"), properties.getProperty("TY"));
		heSheIt.setValue(properties.getProperty("ER_SIE_ES"), properties.getProperty("ON_ONA_ONO"));
		we.setValue(properties.getProperty("WIR"), properties.getProperty("MY"));
		youP.setValue(properties.getProperty("IHR"), properties.getProperty("WY"));
		they.setValue(properties.getProperty("SIE_SIE"), properties.getProperty("ONI_PANSTWO"));
	}

}
