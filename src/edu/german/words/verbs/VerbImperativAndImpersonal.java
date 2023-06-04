package edu.german.words.verbs;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import edu.german.tools.Titel;

public class VerbImperativAndImpersonal extends JPanel {
	private static final long serialVersionUID = 1L;
	private VerbImperative imperative;
	private ImpersonalForm impersonal;

	public VerbImperativAndImpersonal(String string) {
		TitledBorder titleImperative = BorderFactory.createTitledBorder(Titel.setTitel("IMPERATIVE"));
		imperative = new VerbImperative();
		imperative.setBorder(titleImperative);

		Component up = new JScrollPane(imperative);

		TitledBorder titleImpersonal = BorderFactory.createTitledBorder(Titel.setTitel("IMPERSONAL_FORMS"));
		impersonal = new ImpersonalForm();
		impersonal.setBorder(titleImpersonal);

		Component down = new JScrollPane(impersonal);

		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, up, down);
		split.setDividerLocation(300);

		this.setLayout(new GridLayout(1, 1, 5, 5));
		this.add(split);
	}

	public void clearEditFields() {
		imperative.clearEditFields();
		impersonal.clearEditFields();
	}

	public Collection<? extends Properties> getPropertiesList() {
		List<Properties> list = new LinkedList<>();
		list.add(imperative.getProperties());
		list.add(impersonal.getProperties());
		return list;
	}

	public void fieldsFilling(Properties prop) {
		if (prop.contains("IMPERATIV"))
			imperative.setData(prop);

		if (prop.contains("UNPERSÖNLICHE FORMEN"))
			impersonal.setData(prop);
	}

}
