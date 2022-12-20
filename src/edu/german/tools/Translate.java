package edu.german.tools;

public class Translate {
	private static String CONFIG_FILE = "translation.properties";

	public static String setText(String pattern) {
		return new MyProperties(CONFIG_FILE).getValue(pattern);
	}

}
