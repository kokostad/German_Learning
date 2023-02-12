package edu.german.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class ScreenSetup {

	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public int SCR_WIDTH = dim.width;
	public int SCR_HEIGHT = dim.height;
	public int SCR_START = 0;

	public int DECREASE_WIDTH = SCR_WIDTH / 5;
	public int DECREASE_HEIGHT = SCR_HEIGHT / 7;

	public int SCR_DEFAULT_WIDTH = SCR_WIDTH - DECREASE_WIDTH;
	public int SCR_DEFAULT_HEIGHT = SCR_HEIGHT - DECREASE_HEIGHT;

	public final int BOTTOM_BORDER = 31;
	public final int ICON_WIDTH = 20;
	public final int ICON_HEIGHT = 20;

	public double SPLIT_PANE_FACTOR = 0.15;
	public double STANDARD_SPLIT_PANE_FACTOR = 0.25;
	public double BIGGER_SPLIT_PANE_FACTOR = 0.35;
	public double MOST_BIG_SPLIT_PANE_FACTOR = 0.45;

	public int WORD_FIELD_WIDTH = SCR_DEFAULT_WIDTH / 6;
	public int EDIT_FIELD_WIDTH = SCR_DEFAULT_WIDTH / 2;
	public int EDIT_FIELD_HEIGHT = 30;

	public int DEFAULT_FONT_SIZE = 16;
	public int GAME_FONT_SIZE = 18;
	public int GAME_BIG_FONT_SIZE = 24;

	public int EDIT_FIELD_FACTOR = 30;

	public String STANDART_FONT_ART = "Serif";
	public String GAME_FONT_ART = "MV Boli";

	public Font DEFAULT_FONT = new Font(STANDART_FONT_ART, Font.ITALIC, DEFAULT_FONT_SIZE);
	public Font NORMAL_FONT = new Font(STANDART_FONT_ART, Font.PLAIN, DEFAULT_FONT_SIZE);
	public Font GAME_FONT = new Font(GAME_FONT_ART, Font.BOLD | Font.ITALIC, GAME_FONT_SIZE);

//	Color parameters
	public Color LABEL_COLOR = new Color(139, 69, 19);
	public Color LABEL_COLOR_2 = new Color(51, 51, 255);

}
