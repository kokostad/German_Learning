package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import edu.german.tools.MyProperties;
import edu.german.tools.Titel;

public class VerbIndikativI extends JPanel {
	private static final long serialVersionUID = 1L;
	private AddVerbTens vPresent;
	private AddVerbTens vPreterite;
	private AddVerbTens vPerfekt;

	public VerbIndikativI(String modus) {
		int row = new MyProperties("screen.properties").getIntValue("VERB_ROW");
		TitledBorder titlePresent = BorderFactory.createTitledBorder(Titel.setTitel("PRESENT"));
		vPresent = new AddVerbTens("PRESENT", modus);
		vPresent.setBorder(titlePresent);

		TitledBorder titlePreterite = BorderFactory.createTitledBorder(Titel.setTitel("PRETERITE"));
		vPreterite = new AddVerbTens("PRETERITE", modus);
		vPreterite.setBorder(titlePreterite);

		TitledBorder titlePerfekt = BorderFactory.createTitledBorder(Titel.setTitel("PERFEKT"));
		vPerfekt = new AddVerbTens("PERFEKT", modus);
		vPerfekt.setBorder(titlePerfekt);

		GridLayout gl = new GridLayout(row, 1);
		this.setLayout(gl);

		this.add(vPresent);
		this.add(vPreterite);
		this.add(vPerfekt);
	}

	public List<Properties> getProperitesList() {
		List<Properties> list = new LinkedList<>();
		list.add(vPresent.getProperties());
		list.add(vPreterite.getProperties());
		list.add(vPerfekt.getProperties());

		return list;
	}

	public void clearEditFields() {
		vPresent.clearEditFields();
		vPreterite.clearEditFields();
		vPerfekt.clearEditFields();
	}

	public List<Map<String, String>> getList() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();

		if (vPresent.getMap() != null)
			list.add(vPresent.getMap());

		if (vPreterite.getMap() != null)
			list.add(vPreterite.getMap());

		if (vPerfekt.getMap() != null)
			list.add(vPerfekt.getMap());

		return list;
	}

	public void setPresent(Properties properties) {
		vPresent.setValues(properties);
		vPreterite.setValues(properties);
		vPerfekt.setValues(properties);
	}

}
