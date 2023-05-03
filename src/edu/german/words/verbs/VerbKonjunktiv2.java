package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.util.Properties;

import edu.german.tools.MyProperties;
import edu.german.tools.Titel;

public class VerbKonjunktiv2 extends JPanel {
	private static final long serialVersionUID = 1L;
	private AddVerbTens vPreterite;
	private AddVerbTens vPlusquamperfekt;
	private AddVerbTens vFutur1;
	private AddVerbTens vFutur2;
	private static final String modus = "KONJUNKTIV II";

	public VerbKonjunktiv2() {
		int row = new MyProperties("screen.properties").getIntValue("VERB_ROW");
		TitledBorder titlePreterite = BorderFactory.createTitledBorder(Titel.setTitel("PRETERITE"));
		vPreterite = new AddVerbTens("PRETERITE", modus);
		vPreterite.setBorder(titlePreterite);

		TitledBorder titlePlusquamperfekt = BorderFactory.createTitledBorder(Titel.setTitel("PLUSQUAMPERFEKT"));
		vPlusquamperfekt = new AddVerbTens("PLUSQUAMPERFEKT", modus);
		vPlusquamperfekt.setBorder(titlePlusquamperfekt);

		TitledBorder titleFutur1 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_I"));
		vFutur1 = new AddVerbTens("FUTUR_I", modus);
		vFutur1.setBorder(titleFutur1);

		TitledBorder titlevFutur2 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_II"));
		vFutur2 = new AddVerbTens("FUTUR_II", modus);
		vFutur2.setBorder(titlevFutur2);

		GridLayout gl = new GridLayout(row, 1);
		setLayout(gl);
		add(vPreterite);
		add(vPlusquamperfekt);
		add(vFutur1);
		add(vFutur2);
	}

	public Map<String, List<Map<String, String>>> getMap() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();

		if (vPreterite.getMap() != null)
			list.add(vPreterite.getMap());

		if (vPlusquamperfekt.getMap() != null)
			list.add(vPlusquamperfekt.getMap());

		if (vFutur1.getMap() != null)
			list.add(vFutur1.getMap());

		if (vFutur2.getMap() != null)
			list.add(vFutur2.getMap());

		System.out.println("List size: " + list.size());

		if (list.size() > 0) {
			Map<String, List<Map<String, String>>> modusMap = new HashMap<String, List<Map<String, String>>>();
			modusMap.put(modus, list);
			return modusMap;
		}

		return null;
	}

	public void clearEditFields() {
		vPreterite.clearEditFields();
		vPlusquamperfekt.clearEditFields();
		vFutur1.clearEditFields();
		vFutur2.clearEditFields();
	}

	public List<Properties> getProperiesList() {
		List<Properties> list = new LinkedList<>();
		list.add(vPreterite.getProperties());
		list.add(vPlusquamperfekt.getProperties());
		list.add(vFutur1.getProperties());
		list.add(vFutur2.getProperties());

		return list;
	}

	public void setData(Properties properties) {
		vPreterite.setValues(properties);
		vPlusquamperfekt.setValues(properties);
		vFutur1.setValues(properties);
		vFutur2.setValues(properties);
	}

}
