package edu.german.tools;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyComboBox extends JPanel {
	private static final long serialVersionUID = 1L;
	private int fontSize = 16;

	@SuppressWarnings("rawtypes")
	private JComboBox box;

	/**
	 * Class to create a JCombobox
	 * 
	 * @param labelInfo - label title
	 * @param array     - value list for box
	 * @param b
	 * @param translate - parameter showing whether to translate the values
	 */
	public MyComboBox(String labelInfo, Object[] array) {
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));
		boxPanel.add(Box.createHorizontalStrut(16));

		JLabel label = new JLabel(labelInfo);
		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setFont(new MyFont().myFont(fontSize));
		boxPanel.add(label);
		boxPanel.add(Box.createRigidArea(new Dimension(10, 5)));
		if (array != null) {
			box = new JComboBox<Object>(array);
			box.setFont(new MyFont().myFont(fontSize));
			box.setAlignmentX(CENTER_ALIGNMENT);
			boxPanel.add(box);
		}

		setLayout(new GridLayout(1, 1, 5, 5));
		add(boxPanel);
	}

	public void setValue(String str) {
		box.setSelectedItem(str);
	}

	public String getValue() {
		return (box.getSelectedItem().toString());
	}

	public void clearField() {
		box.setSelectedItem(null);
	}
}
