package edu.german.tools.buttons;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.german.tools.MyFont;
import edu.german.tools.MyProperties;

public class ButtonsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String CFG_FILE = "buttons.properties";
	private MyFont myFont;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private Integer defaultFontSize = 16;

	public ButtonsPanel() {
		// NOTICE Nothing to do
	}

	public void setFontSize(Integer size) {
		this.defaultFontSize = size;
	}

	private String setTitel(Object pattern) {
		return new MyProperties(CFG_FILE).getText(pattern.toString());
	}

	public ButtonsPanel(Object t1) {
		myFont = new MyFont();
		JPanel up = new JPanel();
		GridBagLayout g = new GridBagLayout();
		up.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 10, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		b1 = new JButton(setTitel(t1));
		b1.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b1, c);
		up.add(b1);

		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		this.add(up, BorderLayout.NORTH);
	}

	public ButtonsPanel(String t1, String t2) {
		myFont = new MyFont();
		JPanel up = new JPanel();
		GridBagLayout g = new GridBagLayout();
		up.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 10, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		b1 = new JButton(setTitel(t1));
		b1.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b1, c);
		up.add(b1);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		b2 = new JButton(setTitel(t2));
		b2.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b2, c);
		up.add(b2);

		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		this.add(up, BorderLayout.NORTH);
	}

	public ButtonsPanel(String t1, String t2, String t3) {
		myFont = new MyFont();
		JPanel up = new JPanel();
		GridBagLayout g = new GridBagLayout();
		up.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 5, 10, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		b1 = new JButton(setTitel(t1));
		b1.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b1, c);
		up.add(b1);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		b2 = new JButton(setTitel(t2));
		b2.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b2, c);
		up.add(b2);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		b3 = new JButton(setTitel(t3));
		b3.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b3, c);
		up.add(b3);

		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		this.add(up, BorderLayout.NORTH);
	}

	public ButtonsPanel(String t1, String t2, String t3, String t4) {
		myFont = new MyFont();
		JPanel up = new JPanel();
		GridBagLayout g = new GridBagLayout();
		up.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 10, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		b1 = new JButton(setTitel(t1));
		b1.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b1, c);
		up.add(b1);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		b2 = new JButton(setTitel(t2));
		b2.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b2, c);
		up.add(b2);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		b3 = new JButton(setTitel(t3));
		b3.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b3, c);
		up.add(b3);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		b4 = new JButton(setTitel(t4));
		b4.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b4, c);
		up.add(b4);

		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		this.add(up, BorderLayout.NORTH);
	}

	public ButtonsPanel(String t1, String t2, String t3, String t4, String t5) {
		myFont = new MyFont();
		JPanel up = new JPanel();
		GridBagLayout g = new GridBagLayout();
		up.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 10, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		b1 = new JButton(setTitel(t1));
		b1.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b1, c);
		up.add(b1);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		b2 = new JButton(setTitel(t2));
		b2.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b2, c);
		up.add(b2);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		b3 = new JButton(setTitel(t3));
		b3.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b3, c);
		up.add(b3);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		b4 = new JButton(setTitel(t4));
		b4.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b4, c);
		up.add(b4);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		b5 = new JButton(setTitel(t5));
		b5.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b5, c);
		up.add(b5);

		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		this.add(up, BorderLayout.NORTH);
	}

	public ButtonsPanel(String t1, String t2, String t3, String t4, String t5, String t6) {
		myFont = new MyFont();
		JPanel up = new JPanel();
		GridBagLayout g = new GridBagLayout();
		up.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 10, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		b1 = new JButton(setTitel(t1));
		b1.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b1, c);
		up.add(b1);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		b2 = new JButton(setTitel(t2));
		b2.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b2, c);
		up.add(b2);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		b3 = new JButton(setTitel(t3));
		b3.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b3, c);
		up.add(b3);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		b4 = new JButton(setTitel(t4));
		b4.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b4, c);
		up.add(b4);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		b5 = new JButton(setTitel(t5));
		b5.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b5, c);
		up.add(b5);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		b6 = new JButton(setTitel(t6));
		b6.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b6, c);
		up.add(b6);

		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		this.add(up, BorderLayout.NORTH);
	}

	public ButtonsPanel(String t1, String t2, String t3, String t4, String t5, String t6, String t7) {
		myFont = new MyFont();
		JPanel up = new JPanel();
		GridBagLayout g = new GridBagLayout();
		up.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 10, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		b1 = new JButton(setTitel(t1));
		b1.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b1, c);
		up.add(b1);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		b2 = new JButton(setTitel(t2));
		b2.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b2, c);
		up.add(b2);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		b3 = new JButton(setTitel(t3));
		b3.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b3, c);
		up.add(b3);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		b4 = new JButton(setTitel(t4));
		b4.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b4, c);
		up.add(b4);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		b5 = new JButton(setTitel(t5));
		b5.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b5, c);
		up.add(b5);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		b6 = new JButton(setTitel(t6));
		b6.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b6, c);
		up.add(b6);

		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		b7 = new JButton(setTitel(t7));
		b7.setFont(myFont.myFont(defaultFontSize));
		g.setConstraints(b7, c);
		up.add(b7);

		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		this.add(up, BorderLayout.NORTH);
	}

	public JButton getB1() {
		return b1;
	}

	public JButton getB2() {
		return b2;
	}

	public JButton getB3() {
		return b3;
	}

	public JButton getB4() {
		return b4;
	}

	public JButton getB5() {
		return b5;
	}

	public JButton getB6() {
		return b6;
	}

	public JButton getB7() {
		return b7;
	}

}
