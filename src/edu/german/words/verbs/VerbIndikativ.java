package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import edu.german.tools.Titles;

public class VerbIndikativ extends JPanel {
	private static final long serialVersionUID = 1L;
	private AddVerbTens vPresent;
	private AddVerbTens vPreterite;
	private AddVerbTens vPerfekt;
	private AddVerbTens vPlusquamperfekt;
	private AddVerbTens vFutur1;
	private AddVerbTens vFutur2;
	private String modus;

	public VerbIndikativ(String modus) {
		this.modus = modus;
		TitledBorder titlePresent = BorderFactory.createTitledBorder(Titles.setTitel("PRESENT"));
		vPresent = new AddVerbTens("PRESENT");
		vPresent.setBorder(titlePresent);

		TitledBorder titlePreterite = BorderFactory.createTitledBorder(Titles.setTitel("PRETERITE"));
		vPreterite = new AddVerbTens("PRETERITE");
		vPreterite.setBorder(titlePreterite);

		TitledBorder titlePerfekt = BorderFactory.createTitledBorder(Titles.setTitel("PERFEKT"));
		vPerfekt = new AddVerbTens("PERFEKT");
		vPerfekt.setBorder(titlePerfekt);

		TitledBorder titlePlusquamperfekt = BorderFactory.createTitledBorder(Titles.setTitel("PLUSQUAMPERFEKT"));
		vPlusquamperfekt = new AddVerbTens("PLUSQUAMPERFEKT");
		vPlusquamperfekt.setBorder(titlePlusquamperfekt);

		TitledBorder titleFutur1 = BorderFactory.createTitledBorder(Titles.setTitel("FUTUR_I"));
		vFutur1 = new AddVerbTens("FUTUR_I");
		vFutur1.setBorder(titleFutur1);

		TitledBorder titlevFutur2 = BorderFactory.createTitledBorder(Titles.setTitel("FUTUR_II"));
		vFutur2 = new AddVerbTens("FUTUR_II");
		vFutur2.setBorder(titlevFutur2);

		GridLayout gl = new GridLayout(6, 1);
		setLayout(gl);
		add(vPresent);
		add(vPreterite);
		add(vPerfekt);
		add(vPlusquamperfekt);
		add(vFutur1);
		add(vFutur2);

	}

	public void clearEditFields() {
		vPresent.clearEditFields();
		vPreterite.clearEditFields();
		vPerfekt.clearEditFields();
		vPlusquamperfekt.clearEditFields();
		vFutur1.clearEditFields();
		vFutur2.clearEditFields();
	}

	public Map<String, List<Map<String, String>>> getMap() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		list.add(vPresent.getMap());
		list.add(vPreterite.getMap());
		list.add(vPerfekt.getMap());
		list.add(vPlusquamperfekt.getMap());
		list.add(vFutur1.getMap());
		list.add(vFutur2.getMap());

		Map<String, List<Map<String, String>>> modusMap = new HashMap<String, List<Map<String, String>>>();
		modusMap.put(modus, list);

		return modusMap;
	}

}
