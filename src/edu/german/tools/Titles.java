package edu.german.tools;

public class Titles {

	private static String CONFIG_FILE = "titles.properties";

	public static String setTitel(String pattern) {
		return new MyProperties(CONFIG_FILE).getText(pattern);
	}

}
