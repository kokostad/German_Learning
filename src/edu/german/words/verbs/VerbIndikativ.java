package edu.german.words.verbs;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VerbIndikativ extends JPanel {
	private static final long serialVersionUID = 1L;
	private String modus;
	private JTabbedPane tb;
	private VerbIndikativI vi1;
	private VerbIndikativII vi2;

	public VerbIndikativ(String modus) {
		this.modus = modus;
		vi1 = new VerbIndikativI();
		vi2 = new VerbIndikativII();

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tb.add("I", vi1);
		tb.add("II", vi2);

		this.setLayout(new BorderLayout());
		this.add(tb, BorderLayout.CENTER);
	}

	public void clearEditFields() {
	}

	public Map<String, List<Map<String, String>>> getMap() {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();

		Map<String, List<Map<String, String>>> modusMap = new HashMap<String, List<Map<String, String>>>();
		List<Map<String, String>> list1 = vi1.getList();
		List<Map<String, String>> list2 = vi2.getList();

		list.addAll(list1);
		list.addAll(list2);

		modusMap.put(modus, list);

		return modusMap;
	}

	public String getMainWord() {
		return null;
	}

	public void fieldsFilling(Properties properties) {
		System.out.println(properties.toString());
		if(properties.contains("PRESENT"))
			vi1.setPresent();
		
	}

}
