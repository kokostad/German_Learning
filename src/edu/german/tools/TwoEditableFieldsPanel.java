package edu.german.tools;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TwoEditableFieldsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditableField adjective;
	private OneEditableField meaning;

	// NOTICE need to use other layout to line up the lines
	public TwoEditableFieldsPanel(String titel, String fieldOneTitel, String fieldTwoTitel) {
		Border border = BorderFactory.createTitledBorder(titel);
		this.setBorder(border);

		adjective = new OneEditableField(fieldOneTitel, null, 16, 19);
		adjective.setAlignmentY(Component.RIGHT_ALIGNMENT);
		meaning = new OneEditableField(fieldTwoTitel, null, 16, 20);
		meaning.setAlignmentY(Component.RIGHT_ALIGNMENT);

//		GridBagLayout g = new GridBagLayout();
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.insets = new Insets(2, 2, 2, 2);
//
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		g.setConstraints(adjective, c);
//		this.add(adjective, c);
//
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		g.setConstraints(meaning, c);
//		this.add(meaning, c);

//		FlowLayout layout = new FlowLayout();
//		this.setLayout(layout);
//		this.add(adjective);
//		this.add(meaning);

		this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
//		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
		this.add(Box.createVerticalGlue());
		this.add(adjective);
		this.add(Box.createRigidArea(new Dimension(10, 10)));
		this.add(meaning);

	}

	public String getAdjective() {
		return adjective.getValue();
	}

	public String getMeaning() {
		return meaning.getValue();
	}

	public void setAdjective(String var) {
		adjective.setValue(var);
	}

	public void setMeaning(String var) {
		meaning.setValue(var);
	}

	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear() {
		setAdjective(null);
		setMeaning(null);
	}

}
