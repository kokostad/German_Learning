package edu.german.tools;

import java.awt.Dimension;

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
	private int fontSize;
	private int width;
	private int height;

	public OneEditField(String title, String hint, int fontSize, int width, int height) {
		this.height = height;
		this.width = width;
		this.fontSize = fontSize;
		this.title = title;
		this.hint = hint;

		label = new JLabel(title + ": ");
		label.setFont(new MyFont().myFont(fontSize));

		editField = new JTextField();
		editField.setPreferredSize(new Dimension(width, height));
		editField.setFont(new MyFont().myFont(fontSize));
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

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getValue() {
		return editField.getText();
	}

	public void setValue(String value) {
		editField.setText(value);
	}

	public static class Builder {
		private String title;
		private String value;
		private String hint;
		private int fontSize;
		private int width;
		private int height;

		public Builder setValue(String value) {
			this.value = value;
			return this;
		}

		public Builder setHeight(int height) {
			this.height = height;
			return this;
		}

		public Builder setWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder setFontSize(int fontSize) {
			this.fontSize = fontSize;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setHint(String hint) {
			this.hint = hint;
			return this;
		}

		public Builder() {
		}

		public OneEditField build() {
			return new OneEditField(title, hint, fontSize, width, height);
		}

	}

}
