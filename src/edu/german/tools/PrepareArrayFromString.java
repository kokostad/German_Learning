package edu.german.tools;

public class PrepareArrayFromString {
	private String[] array = null;

	public PrepareArrayFromString(String str) {
		prepareArray(str);
	}

	private void prepareArray(String str) {
		if (str.contains(", ")) {
			array = str.split(", ");
		} else if (str.contains(",")) {
			array = str.split(",");
		}
	}

	public String[] getArray() {
		return array;
	}

}
