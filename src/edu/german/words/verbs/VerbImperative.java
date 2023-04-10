package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JPanel;

import edu.german.tools.LabelSize;
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
		int labelSize = new LabelSize("du").getSize();
		if (labelSize < new LabelSize("ty").getSize())
			labelSize = new LabelSize("ty").getSize();
		if (labelSize < new LabelSize("Ihr").getSize())
			labelSize = new LabelSize("Ihr").getSize();
		if (labelSize < new LabelSize("wy").getSize())
			labelSize = new LabelSize("wy").getSize();
		if (labelSize < new LabelSize("wir").getSize())
			labelSize = new LabelSize("wir").getSize();
		if (labelSize < new LabelSize("my").getSize())
			labelSize = new LabelSize("my").getSize();
		if (labelSize < new LabelSize("Sie, sie").getSize())
			labelSize = new LabelSize("Sie, sie").getSize();
		if (labelSize < new LabelSize("Pan(i)/oni").getSize())
			labelSize = new LabelSize("Pan(i)/oni").getSize();

		youS = new TwoEditFields("du", labelSize, "ty", labelSize);
		youP = new TwoEditFields("ihr", labelSize, "wy", labelSize);
		we = new TwoEditFields("wir", labelSize, "my", labelSize);
		they = new TwoEditFields("Sie, sie", labelSize, "Pan(i)/oni", labelSize);

		GridLayout g = new GridLayout(6, 2, 5, 5);
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

	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("DU", new TextCleaner(youS.getFirst()).getWord());
		properties.put("TY", new TextCleaner(youS.getSecond()).getWord());
		properties.put("WIR", new TextCleaner(we.getFirst()).getWord());
		properties.put("MY", new TextCleaner(we.getSecond()).getWord());
		properties.put("IHR", new TextCleaner(youP.getFirst()).getWord());
		properties.put("WY", new TextCleaner(youP.getSecond()).getWord());
		properties.put("SIE_SIE", new TextCleaner(they.getFirst()).getWord());
		properties.put("ONI_PANSTWO", new TextCleaner(they.getSecond()).getWord());
		properties.put("TENS", getTens());
		properties.put("MODUS", "IMPERATIV");

		return properties;
	}
}
