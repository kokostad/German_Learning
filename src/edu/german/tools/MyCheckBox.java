package edu.german.tools;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MyCheckBox extends JPanel {
	private static final long serialVersionUID = 1L;
	private JCheckBox jb;
	private String toChoose;
	private String tip;

	public MyCheckBox(String information, String toChoose, String title) {
		this.toChoose = toChoose;
		JLabel l = new JLabel(information);
		jb = new JCheckBox(toChoose);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(title);

		this.setLayout(new GridLayout(1, 2, 5, 5));
		this.setBorder(titleBorder);
		this.add(l);
		this.add(jb);
	}

	public MyCheckBox(String information, String toChoose, String title, String tip) {
		this.toChoose = toChoose;
		this.tip = tip;
		JLabel l = new JLabel(information);
		jb = new JCheckBox(toChoose);
		jb.setToolTipText(tip);

		TitledBorder titleBorder = BorderFactory.createTitledBorder(title);

		this.setLayout(new GridLayout(1, 2, 5, 5));
		this.setBorder(titleBorder);
		this.add(l);
		this.add(jb);
	}

	public boolean result() {
		return jb.isSelected();
	}

	// NOTICE maybe it's not needed
	public void clear() {
		jb = new JCheckBox(toChoose);
		jb.setToolTipText(tip);
	}

}
