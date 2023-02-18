package edu.german.tools;

import java.awt.Dimension;
import java.awt.FlowLayout;

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
		boxPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		if (array != null) {
			box = new JComboBox<Object>(array);
			box.setFont(ss.DEFAULT_FONT);
			box.setAlignmentX(CENTER_ALIGNMENT);
			boxPanel.add(box);
		}

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
