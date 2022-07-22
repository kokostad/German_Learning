package edu.german.tools;

import java.awt.Font;

public class MyFont {
	public Font myFont(int size) {
		return new Font("Serif", Font.BOLD, size);
	}

	public Font myFont() {
		int size = Integer.parseInt(new MyProperties("screen.properties").getValue("DEFAULT_FONT_SIZE"));
		return new Font("Serif", Font.BOLD, size);
	}
	
	public int fontSize() {
		return Integer.parseInt(new MyProperties("screen.properties").getValue("DEFAULT_FONT_SIZE"));
	}
}
