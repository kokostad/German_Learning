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

public class VerbImperativAndImpersonal extends JPanel {
	private static final long serialVersionUID = 1L;
	private VerbImperative imperative;
	private ImpersonalForm impersonal;

	public VerbImperativAndImpersonal(String string) {
		TitledBorder titleImperative = BorderFactory.createTitledBorder(Titles.setTitel("IMPERATIVE"));
		imperative = new VerbImperative();
		imperative.setBorder(titleImperative);

		TitledBorder titleImpersonal = BorderFactory.createTitledBorder(Titles.setTitel("IMPERSONAL_FORMS"));
		impersonal = new ImpersonalForm();
		impersonal.setBorder(titleImpersonal);

		GridLayout g = new GridLayout(5, 1);
		this.setLayout(g);
		this.add(imperative);
		this.add(impersonal);
	}

	public Map<String, List<Map<String, String>>> getMapImperativ() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		list.add(imperative.getMap());

		Map<String, List<Map<String, String>>> modusMap = new HashMap<String, List<Map<String, String>>>();
		modusMap.put(imperative.getTens(), list);
		return modusMap;
	}

	public Map<String, List<Map<String, String>>> getMapImpersonal() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		list.add(impersonal.getMap());

		Map<String, List<Map<String, String>>> modusMap = new HashMap<String, List<Map<String, String>>>();
		modusMap.put(impersonal.getTens(), list);
		return modusMap;
	}

	public void clearEditFields() {
		imperative.clearEditFields();
		impersonal.clearEditFields();
	}

}
