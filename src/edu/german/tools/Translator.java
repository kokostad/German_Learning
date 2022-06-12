package edu.german.tools;

public class Translator {
	private static String CFG_FILE = "translation_table.properties";
	private MyProperties prop;
	private String name;

	public Translator() {
		this.prop = new MyProperties(CFG_FILE);
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

	public String translate(String name) {
		String var = prop.getValue(name.toString());
		return var;
	}

	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
}
