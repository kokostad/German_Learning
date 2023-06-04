package edu.german.words.verbs;

import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import edu.german.tools.MyProperties;
import edu.german.tools.Titel;

public class VerbKonjunktiv1 extends JPanel {
	private static final long serialVersionUID = 1L;
	private VerbTens vPresent;
	private VerbTens vPerfekt;
	private VerbTens vFutur1;
	private VerbTens vFutur2;
	private static final String modus = "KONJUNKTIV I";

	public VerbKonjunktiv1() {
		int row = new MyProperties("screen.properties").getIntValue("VERB_ROW");
		TitledBorder titlePresent = BorderFactory.createTitledBorder(Titel.setTitel("PRESENT"));
		vPresent = new VerbTens("PRESENT", modus);
		vPresent.setBorder(titlePresent);

		TitledBorder titlePerfekt = BorderFactory.createTitledBorder(Titel.setTitel("PERFEKT"));
		vPerfekt = new VerbTens("PERFEKT", modus);
		vPerfekt.setBorder(titlePerfekt);

		TitledBorder titleFutur1 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_I"));
		vFutur1 = new VerbTens("FUTUR_I", modus);
		vFutur1.setBorder(titleFutur1);

		TitledBorder titlevFutur2 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_II"));
		vFutur2 = new VerbTens("FUTUR_II", modus);
		vFutur2.setBorder(titlevFutur2);

		GridLayout gl = new GridLayout(row, 1);
		setLayout(gl);
		add(vPresent);
		add(vPerfekt);
		add(vFutur1);
		add(vFutur2);
	}

	public void clearEditFields() {
		vPresent.clearEditFields();
		vPerfekt.clearEditFields();
		vFutur1.clearEditFields();
		vFutur2.clearEditFields();
	}

	public List<Properties> getProperiesList() {
		List<Properties> list = new LinkedList<>();
		list.add(vPresent.getProperties());
		list.add(vPerfekt.getProperties());
		list.add(vFutur1.getProperties());
		list.add(vFutur2.getProperties());

		return list;
	}

	public void setData(Properties properties) {
		vPresent.setValues(properties);
		vPerfekt.setValues(properties);
		vFutur1.setValues(properties);
		vFutur2.setValues(properties);
	}

}
