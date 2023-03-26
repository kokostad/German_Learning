package edu.german.words.verbs;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	public Map<String, List<Map<String, String>>> getMapImperativ() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		if (imperative.getMap() != null)
			list.add(imperative.getMap());

		if (!list.isEmpty()) {
			Map<String, List<Map<String, String>>> modusMap = new HashMap<String, List<Map<String, String>>>();
			modusMap.put(imperative.getTens(), list);
			return modusMap;
		}
		return null;
	}

	public Map<String, List<Map<String, String>>> getMapImpersonal() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		if (impersonal.getMap() != null)
			list.add(impersonal.getMap());

		if (!list.isEmpty()) {
			Map<String, List<Map<String, String>>> modusMap = new HashMap<String, List<Map<String, String>>>();
			modusMap.put(impersonal.getTens(), list);
			return modusMap;
		}
		return null;
	}

	public void clearEditFields() {
		imperative.clearEditFields();
		impersonal.clearEditFields();
	}

}
