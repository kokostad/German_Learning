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
import edu.german.tools.ScreenSetup;
import edu.german.tools.Titel;
import edu.german.words.verbs.AddVerbTens;

public class VerbIndikativII extends JPanel {
	private static final long serialVersionUID = 1L;
	private AddVerbTens vPlusquamperfekt;
	private AddVerbTens vFutur1;
	private AddVerbTens vFutur2;

	public VerbIndikativII(String modus) {
		int row = new MyProperties("screen.properties").getIntValue("VERB_ROW");
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
		this.setLayout(gl);

		this.add(vPlusquamperfekt);
		this.add(vFutur1);
		this.add(vFutur2);
	}

	public List<Properties> getProperitesList() {
		List<Properties> list = new LinkedList<>();
		list.add(vPlusquamperfekt.getProperties());
		list.add(vFutur1.getProperties());
		list.add(vFutur2.getProperties());

		return list;
	}

	public void clearEditFields() {
		vPlusquamperfekt.clearEditFields();
		vFutur1.clearEditFields();
		vFutur2.clearEditFields();
	}

	public List<Map<String, String>> getList() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();

		if (vPlusquamperfekt.getMap() != null)
			list.add(vPlusquamperfekt.getMap());

		if (vFutur1.getMap() != null)
			list.add(vFutur1.getMap());

		if (vFutur2.getMap() != null)
			list.add(vFutur2.getMap());

		return list;
	}

	public void setPresent(Properties properties) {
		System.out.println("VerbIndikativII -> " + properties.toString());

		vPlusquamperfekt.setValues(properties);
		vFutur1.setValues(properties);
		vFutur2.setValues(properties);
	}

}
