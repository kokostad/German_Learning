package edu.german.tools;

import edu.german.words.verbs.VerbRegular;
import edu.german.words.verbs.VerbSeparable;

public class ArrayFromEnum {
	private String[] array;

	public ArrayFromEnum(VerbSeparable[] values) {
		int enough = values.length;
		array = new String[enough];

		int i = 0;
		for (VerbSeparable var : VerbSeparable.values()) {
			array[i] = var.toString();
			i += 1;
		}
	}

	public ArrayFromEnum(VerbRegular[] values) {
		int enough = values.length;
		array = new String[enough];

		int i = 0;
		for (VerbRegular var : VerbRegular.values()) {
			array[i] = var.toString();
			i += 1;
		}
	}

	public String[] getArray() {
		return array;
	}
}
