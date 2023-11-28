package edu.german.tools;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyComboBox extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> box;

	public MyComboBox(String labelInfo, Object[] array) {
		JPanel boxPanel = new JPanel();
		JLabel label = new JLabel(labelInfo);
		ScreenSetup ss = new ScreenSetup();

		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setFont(ss.DEFAULT_FONT);
		boxPanel.add(label);
		boxPanel.add(Box.createRigidArea(new Dimension(2, 2)));
		if (array != null) {
			box = new JComboBox<Object>(array);
			box.setFont(ss.DEFAULT_FONT);
			box.setAlignmentX(CENTER_ALIGNMENT);
			boxPanel.add(box);
		}

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		this.add(boxPanel);
	}

	public MyComboBox(String labelInfo, Object[] array, String tip) {
		JPanel boxPanel = new JPanel();
		JLabel label = new JLabel(labelInfo);
		ScreenSetup ss = new ScreenSetup();

		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setFont(ss.DEFAULT_FONT);
		boxPanel.add(label);
		boxPanel.add(Box.createRigidArea(new Dimension(2, 2)));
		if (array != null) {
			box = new JComboBox<Object>(array);
			box.setFont(ss.DEFAULT_FONT);
			box.setAlignmentX(CENTER_ALIGNMENT);
			box.setToolTipText(tip);
			boxPanel.add(box);
		}

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		this.add(boxPanel);
	}

	public MyComboBox(String labelInfo, Object[] array, Font font) {
		JPanel boxPanel = new JPanel();
		JLabel label = new JLabel(labelInfo);

		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setFont(font);
		boxPanel.add(label);
		boxPanel.add(Box.createRigidArea(new Dimension(2, 2)));
		if (array != null) {
			box = new JComboBox<Object>(array);
			box.setFont(font);
			box.setAlignmentX(CENTER_ALIGNMENT);
			boxPanel.add(box);
		}

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		this.add(boxPanel);
	}

	public MyComboBox(String labelInfo, Object[] array, Font font, String tip) {
		JPanel boxPanel = new JPanel();
		JLabel label = new JLabel(labelInfo);

		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setFont(font);
		boxPanel.add(label);
		boxPanel.add(Box.createRigidArea(new Dimension(2, 2)));
		if (array != null) {
			box = new JComboBox<Object>(array);
			box.setFont(font);
			box.setAlignmentX(CENTER_ALIGNMENT);
			box.setToolTipText(tip);
			boxPanel.add(box);
		}

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		this.add(boxPanel);
	}

	
	public void setValue(String value) {
		box.setSelectedItem(value);
	}

	public String getValue() {
		if (box.getSelectedItem() != null)
			return (box.getSelectedItem().toString());

		return null;
	}

	public void clearField() {
		box.setSelectedItem(null);
	}
}
