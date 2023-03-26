package edu.german.tools;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TwoEditFields extends JPanel {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "screen.properties";
	private MyProperties prop = new MyProperties(CFG_FILE);
	private int editFieldLong = prop.getIntValue("DEFAULT_EDIT_FIELD_WIDTH");
	private int editFieldHight = prop.getIntValue("EDIT_FIELD_HEIGHT");
	private int FONT_SIZE = prop.getIntValue("DEFAULT_FONT_SIZE");
	public int defaultLabelWidth = prop.getIntValue("DEFAULT_LABEL_WIDTH");
	private Font font = new MyFont().myFont(FONT_SIZE);
	private JTextField one;
	private JTextField two;
	private String s1;
	private String s2;

	public TwoEditFields(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
		JLabel l1 = new JLabel(s1 + ": ", SwingConstants.RIGHT);
		l1.setFont(font);
		l1.setBounds(0, 0, defaultLabelWidth, editFieldHight);
		one = new JTextField();
		one.setFont(font);
		one.setBounds(defaultLabelWidth, 0, editFieldLong, editFieldHight);

		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.add(l1);
		p1.add(one);

		JLabel l2 = new JLabel(s2 + ": ", SwingConstants.RIGHT);
		l2.setFont(font);
		l2.setBounds(0, 0, defaultLabelWidth, editFieldHight);
		two = new JTextField();
		two.setFont(font);
		two.setBounds(defaultLabelWidth, 0, editFieldLong, editFieldHight);

		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.add(l2);
		p2.add(two);

		GridLayout gl = new GridLayout(1, 2, 5, 5);
		setLayout(gl);
		add(p1);
		add(p2);
	}

	public void clear() {
		one.setText(null);
		two.setText(null);
	}

	public String getOne() {
		return one.getText();
	}

	public String getTwo() {
		return two.getText();
	}

	public String getFirst() {
		return getOne().toString();
	}

	public String getSecond() {
		return getTwo().toString();
	}

	public List<Map<String, String>> getList() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(s1, getOne());
		map.put(s2, getTwo());

		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		list.add(map);

		return list;
	}

	public TwoEditFields(String s1, int size1, String s2, int size2) {
		this.s1 = s1;
		this.s2 = s2;

		if (size1 > defaultLabelWidth)
			setSize(size1);
		if (size2 > defaultLabelWidth)
			setSize(size2);

		Font font = new MyFont().myFont(FONT_SIZE);

		JLabel l1 = new JLabel(s1 + ": ", SwingConstants.RIGHT);
		l1.setFont(font);
		l1.setBounds(0, 0, defaultLabelWidth, editFieldHight);
		one = new JTextField();
		one.setFont(font);
		one.setBounds(defaultLabelWidth, 0, editFieldLong, editFieldHight);

		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.add(l1);
		p1.add(one);

		JLabel l2 = new JLabel(s2 + ": ", SwingConstants.RIGHT);
		l2.setFont(font);
		l2.setBounds(0, 0, defaultLabelWidth, editFieldHight);
		two = new JTextField();
		two.setFont(font);
		two.setBounds(defaultLabelWidth, 0, editFieldLong, editFieldHight);

		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.add(l2);
		p2.add(two);

		GridLayout gl = new GridLayout(1, 2, 5, 5);
		this.setLayout(gl);
		this.add(p1);
		this.add(p2);
		this.repaint();
	}

	public void setSize(Integer size) {
		this.defaultLabelWidth = size;
	}

	public void setValue(String s1, String s2) {
		one.setText(s1);
		two.setText(s2);
	}

}
