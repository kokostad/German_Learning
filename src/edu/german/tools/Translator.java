package edu.german.tools;

public class Translator {
	private static String PATH = "config/";
	private static String CFG_FILE = "translation_table.cfg";
	private MyProperties prop;
	private String name;

	public Translator() {
		this.prop = new MyProperties(PATH, CFG_FILE);
	}

	public Translator(String genus) {
		setName(translate(genus));
	}

	public String[] changeArrayContents(String[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = translate(array[i]).toString();
		}
		return array;
	}

	public String[] changeArrayContents(Object[] array) {
		String[] newArray = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = translate(array[i].toString());
		}
		return newArray;
	}
	public String translate(String name) {
		return prop.getText(name.toString());
	}

	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
}
