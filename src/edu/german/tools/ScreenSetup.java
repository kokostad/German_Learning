package edu.german.tools;

import java.awt.Dimension;
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

	public static final int BOTTOM_BORDER = 31;
	public static final int ICON_WIDTH = 20;
	public static final int ICON_HEIGHT = 20;

	public double SPLIT_PANE_FACTOR = 0.15;

	public int EDIT_FIELD_WIDTH = 420;
	public int EDIT_FIELD_HEIGHT = 30;

	public int DEFAULT_FONT_SIZE = 16;

}
