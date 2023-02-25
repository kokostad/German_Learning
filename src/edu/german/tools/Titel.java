package edu.german.tools;

public class Titel {
	private static String CONFIG_FILE = "titles.properties";

	/**
	 * @param pattern - string to search
	 * @return - search effect
	 */
	public static String setTitel(String pattern) {
		return new MyProperties(CONFIG_FILE).getText(pattern);
	}

}
