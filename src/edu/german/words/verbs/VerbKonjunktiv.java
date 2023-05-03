package edu.german.words.verbs;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VerbKonjunktiv extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tb;
	private VerbKonjunktiv1 konjunktiv1;
	private VerbKonjunktiv2 konjunktiv2;

	public VerbKonjunktiv() {
		konjunktiv1 = new VerbKonjunktiv1();
		konjunktiv2 = new VerbKonjunktiv2();

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tb.add("KONJUNKTIV I", konjunktiv1);
		tb.add("KONJUNKTIV II", konjunktiv2);

		setLayout(new BorderLayout());
		add(tb, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	public Map<String, List<Map<String, String>>> getMapOne() {
		return konjunktiv1.getMap();
	}

	public Map<String, List<Map<String, String>>> getMapTwo() {
		return konjunktiv2.getMap();
	}

	public void clearEditFields() {
		konjunktiv1.clearEditFields();
		konjunktiv2.clearEditFields();
	}

	public void fieldsFilling(Properties properties) {
		if (properties.contains("KONJUNKTIV I")) {
			konjunktiv1.setData(properties);
		}

		if (properties.contains("KONJUNKTIV II")) {
			konjunktiv2.setData(properties);
		}
	}

	public List<Properties> getPropertiesList() {
		List<Properties> list = new LinkedList<>();
		list.addAll(konjunktiv1.getProperiesList());
		list.addAll(konjunktiv2.getProperiesList());

		return list;
	}

}
