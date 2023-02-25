package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import edu.german.tools.Titel;

public class VerbKonjunktiv1 extends JPanel {
	private static final long serialVersionUID = 1L;
	private AddVerbTens vPresent;
	private AddVerbTens vPerfekt;
	private AddVerbTens vFutur1;
	private AddVerbTens vFutur2;
	private static final String modus = "KONJUNKTIV I";

	public VerbKonjunktiv1() {
		TitledBorder titlePresent = BorderFactory.createTitledBorder(Titel.setTitel("PRESENT"));
		vPresent = new AddVerbTens("PRESENT");
		vPresent.setBorder(titlePresent);

		TitledBorder titlePerfekt = BorderFactory.createTitledBorder(Titel.setTitel("PERFEKT"));
		vPerfekt = new AddVerbTens("PERFEKT");
		vPerfekt.setBorder(titlePerfekt);

		TitledBorder titleFutur1 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_I"));
		vFutur1 = new AddVerbTens("FUTUR_I");
		vFutur1.setBorder(titleFutur1);

		TitledBorder titlevFutur2 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_II"));
		vFutur2 = new AddVerbTens("FUTUR_II");
		vFutur2.setBorder(titlevFutur2);

		GridLayout gl = new GridLayout(6, 1);
		setLayout(gl);
		add(vPresent);
		add(vPerfekt);
		add(vFutur1);
		add(vFutur2);
	}

	public Map<String, List<Map<String, String>>> getMap() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();

		if (vPresent.getMap() != null)
			list.add(vPresent.getMap());

		if (vPerfekt.getMap() != null)
			list.add(vPerfekt.getMap());

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
		vPresent.clearEditFields();
		vPerfekt.clearEditFields();
		vFutur1.clearEditFields();
		vFutur2.clearEditFields();
	}

}
