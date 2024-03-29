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

public class VerbIndikativII extends JPanel {
	private static final long serialVersionUID = 1L;
	private VerbTens vPlusquamperfekt;
	private VerbTens vFutur1;
	private VerbTens vFutur2;

	public VerbIndikativII(String modus) {
		int row = new MyProperties("screen.properties").getIntValue("VERB_ROW");
		TitledBorder titlePlusquamperfekt = BorderFactory.createTitledBorder(Titel.setTitel("PLUSQUAMPERFEKT"));
		vPlusquamperfekt = new VerbTens("PLUSQUAMPERFEKT", modus);
		vPlusquamperfekt.setBorder(titlePlusquamperfekt);

		TitledBorder titleFutur1 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_I"));
		vFutur1 = new VerbTens("FUTUR_I", modus);
		vFutur1.setBorder(titleFutur1);

		TitledBorder titlevFutur2 = BorderFactory.createTitledBorder(Titel.setTitel("FUTUR_II"));
		vFutur2 = new VerbTens("FUTUR_II", modus);
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

	public void setPresent(Properties properties) {
		vPlusquamperfekt.setValues(properties);
		vFutur1.setValues(properties);
		vFutur2.setValues(properties);
	}

}
