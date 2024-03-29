package edu.german.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OneEditField extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField editField;

	public OneEditField() {
	}

	public OneEditField(String title, String hint, Font font, Color color, int width, int height) {
		label = new JLabel(title + ": ");
		label.setFont(font);

		editField = new JTextField();
		editField.setPreferredSize(new Dimension(width, height));
		editField.setFont(font);
		if (hint != null)
			editField.setToolTipText(hint);

		GroupLayout gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(10).addComponent(editField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE);
	}

	public void clearField() {
		editField.setText(null);
	}

	public String getValue() {
		if (!editField.getText().isBlank() && !editField.getText().equals(""))
			return editField.getText();

		return null;
	}

	public void setValue(Object value) {
		if (value != null)
			editField.setText(value.toString());
	}

	public static class Builder {
		private String title;
		private String hint;
		private Font font;
		private int width;
		private int height;
		private Color color;

		public Builder withHeight(int height) {
			this.height = height;
			return this;
		}

		public Builder withWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder withHint(String hint) {
			this.hint = hint;
			return this;
		}

		public Builder withFont(Font font) {
			this.font = font;
			return this;
		}

		public Builder() {
		}

		public OneEditField build() {
			return new OneEditField(title, hint, font, color, width, height);
		}
	}
}
