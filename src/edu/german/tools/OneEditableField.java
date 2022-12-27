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

		label = new JLabel();
		label.setText(labelInfo + sign);
		label.setFont(new MyFont().myFont(fontSize));
		textField = new JTextField();
		textField.setText(null);
		textField.setFont(new MyFont().myFont(fontSize));
		if (tipInfo != null)
			textField.setToolTipText(tipInfo);

		var gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(2).addComponent(getTextField(), GroupLayout.DEFAULT_SIZE,
				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
	}

	public OneEditableField(String labelInfo, String tipInfo, int fontSize) {
		this.labelInfo = labelInfo;
		this.fontSize = fontSize;

		label = new JLabel(labelInfo + sign);
		label.setFont(new MyFont().myFont(fontSize));
		setTextField(new JTextField(fieldSize));
		getTextField().setFont(new MyFont().myFont(fontSize));
		if (tipInfo != null)
			getTextField().setToolTipText(tipInfo);

		var gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(2).addComponent(getTextField(), GroupLayout.DEFAULT_SIZE,
				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
	}

	public OneEditableField(String labelInfo, String tipInfo, int fontSize, int fieldSize) {
		this.labelInfo = labelInfo;
		this.fontSize = fontSize;
		this.fieldSize = fieldSize;

		label = new JLabel(labelInfo + sign);
		label.setFont(new MyFont().myFont(fontSize));
		setTextField(new JTextField(fieldSize));
		getTextField().setFont(new MyFont().myFont(fontSize));
		if (tipInfo != null)
			getTextField().setToolTipText(tipInfo);

		var gl = new GroupLayout(this);
		GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
		sg.addComponent(label).addGap(2).addComponent(getTextField(), GroupLayout.DEFAULT_SIZE,
				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
	}

	public void setLabeInfo(String str) {
		label.setText(labelInfo + sign + str);
	}

	public String getValue() {
		if (getTextField().getText() != null && !(getTextField().getText()).equals(""))
			return getTextField().getText();

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
		Map<String, String> map = new HashMap<String, String>();
		map.put(labelInfo.toUpperCase(), getValue());

		return map;
	}

	public char getSign() {
		return (char) getTextField().getText().charAt(0);
	}

	public JTextField getTextField() {
		return textField;
	}

	private void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
