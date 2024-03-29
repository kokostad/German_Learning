package edu.german.words.verbs;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VerbIndikativ extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String modus = "INDIKATIV";
	private JTabbedPane tb;
	private VerbIndikativI vi1;
	private VerbIndikativII vi2;

	public VerbIndikativ(String titel) {
		vi1 = new VerbIndikativI(modus);
		vi2 = new VerbIndikativII(modus);

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tb.add("I", vi1);
		tb.add("II", vi2);

		this.setLayout(new BorderLayout());
		this.add(tb, BorderLayout.CENTER);
	}

	public void clearEditFields() {
		vi1.clearEditFields();
		vi2.clearEditFields();
	}

	public String getMainWord() {
		return null;
	}

	public void fieldsFilling(Properties properties) {
		if (properties.contains("PRESENT"))
			vi1.setPresent(properties);

		if (properties.contains("PLUSQUAMPERFEKT"))
			vi2.setPresent(properties);
	}

	public List<Properties> getPropertiesList() {
		List<Properties> list = new LinkedList<>();
		list.addAll(vi1.getProperitesList());
		list.addAll(vi2.getProperitesList());

		return list;
	}

}
