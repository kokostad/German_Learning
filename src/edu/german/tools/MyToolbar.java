package edu.german.tools;

import java.awt.Color;

import javax.swing.JToolBar;

public class MyToolbar extends JToolBar {
	private static final long serialVersionUID = 1L;

	public MyToolbar() {
		this.setBackground(new Color(220, 220, 220));
		this.setFloatable(true);
		this.setRollover(true);
	}

}
