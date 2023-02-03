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
	private String title;
	private String value;
	private String hint;
	private int width;
	private int height;
	private Font font;
	private Color color;

	public OneEditField() {
	}

	public OneEditField(String title, String hint, Font font, Color color, int width, int height) {
		this.height = height;
		this.width = width;
		this.title = title;
		this.hint = hint;
		this.font = font;
		this.color = color;

		label = new JLabel(title);
		label.setFont(font);

		editField = new JTextField();
		editField.setPreferredSize(new Dimension(width, height));
		editField.setFont(font);
		editField.setFont(getFont());
		if (hint != null)
			editField.setToolTipText(hint);

		var gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(2).addComponent(editField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE);
	}

	public void clearField() {
		editField.setText(null);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getValue() {
		String str = editField.getText();
		return str;
	}

	public void setValue(String value) {
		editField.setText(value);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public static class Builder {
		private String title;
		private String value;
		private String hint;
		private int width;
		private int height;
		private Font font;
		private Color color;

		public Builder withValue(String value) {
			this.value = value;
			return this;
		}

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

		public Builder withColor(Color color) {
			this.color = color;
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
