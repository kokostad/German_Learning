package edu.german.tools;

import java.util.regex.Pattern;

public class ReplaceSpaces {
	private String regex = "_";

	public String replaceSpaceWithUnderscore(String str) {
		return str.replace(' ', '_');
	}

	public String replaceUnderscoreWithSpace(String str) {
		if (Pattern.matches(regex, str))
			return str.replace('_', ' ');

		return str;
	}
}
