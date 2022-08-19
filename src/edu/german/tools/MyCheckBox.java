package edu.german.tools;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MyCheckBox extends JPanel {
	private JCheckBox jb;

	public MyCheckBox(String information, String toChoose, String title) {
		JLabel l = new JLabel(information);
		jb = new JCheckBox(toChoose);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(title);

		this.setLayout(new GridLayout(1, 2, 5, 5));
		this.setBorder(titleBorder);
		this.add(l);
		this.add(jb);
	}

	public boolean result() {
		return jb.isSelected();
	}

}
