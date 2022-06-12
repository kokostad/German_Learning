package edu.german.tools;

import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class OneEditableField extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPasswordField password;
	private JTextField textField;
	private JLabel label;
	private static String sign = ": ";
	private String labelInfo;
	private int fontSize = 14;
	private int fieldSize = 10;

	public OneEditableField(String labelInfo, String tipInfo) {
		this.labelInfo = labelInfo;

		label = new JLabel(labelInfo + sign);
		label.setFont(new MyFont().myFont(fontSize));
		textField = new JTextField(fieldSize);
		textField.setText(null);
		textField.setFont(new MyFont().myFont(fontSize));
		if (tipInfo != null)
			textField.setToolTipText(tipInfo);

		var gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(2).addComponent(textField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE);

	}

	public OneEditableField(String labelInfo, String tipInfo, int fontSize) {
		this.labelInfo = labelInfo;
		this.fontSize = fontSize;

		label = new JLabel(labelInfo + sign);
		label.setFont(new MyFont().myFont(fontSize));
		textField = new JTextField(fieldSize);
		textField.setFont(new MyFont().myFont(fontSize));
		if (tipInfo != null)
			textField.setToolTipText(tipInfo);

		var gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(2).addComponent(textField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE);
	}

	public OneEditableField(String labelInfo, String tipInfo, int fontSize, int fieldSize) {
		this.labelInfo = labelInfo;
		this.fontSize = fontSize;
		this.fieldSize = fieldSize;

		label = new JLabel(labelInfo + sign);
		label.setFont(new MyFont().myFont(fontSize));
		textField = new JTextField(fieldSize);
		textField.setFont(new MyFont().myFont(fontSize));
		if (tipInfo != null)
			textField.setToolTipText(tipInfo);

		var gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(2).addComponent(textField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE);
	}

	public String getValue() {
		if (textField.getText() != null && !(textField.getText()).equals(""))
			return textField.getText();

		return null;
	}

	public void setValue(String str) {
		textField.setText(str);
	}

	public void clearField() {
		textField.setText(null);
	}

	public String getPassword() {
		return new String(password.getPassword());
	}

	public void setPassword(String str) {
		password.setText(str);
	}

	public Map<String, String> getMap() {
		String lInf = labelInfo.toUpperCase();
		String txt = getValue();

		Map<String, String> map = new HashMap<String, String>();
		map.put(lInf, txt);

		return map;
	}

	public char getSign() {
		char sign = (char) textField.getText().charAt(0);
		return sign;
	}

}
