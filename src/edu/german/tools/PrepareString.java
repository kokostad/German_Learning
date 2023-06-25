package edu.german.tools;

import java.util.regex.Pattern;

/**
 * PrepareString.java
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 * Class for manipulating strings
 */
public class PrepareString {
	private String string;
	private String regex = "_";

	public PrepareString() {
	}

	public PrepareString(String string) {
		this.string = string;
	}

	public String firstUpper() {
		return (string.substring(0, 1)).toUpperCase() + (string.substring(1)).toLowerCase();
	}

	public String firstUpper(String str) {
		return (str.substring(0, 1)).toUpperCase() + (str.substring(1)).toLowerCase();
	}

	public String cutSpaces() {
		return string.strip();
	}

	public String cutSpaces(String str) {
		return str.strip();
	}

	public boolean emptyString() {
		return string.isBlank();
	}

	public boolean emptyString(String str) {
		return str.isBlank();
	}

	public String removeInternalSpaces() {
		String[] array = string.split(" ");
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (String val : array) {
			sb.append(cutSpaces());
			if (i < array.length)
				sb.append(" ");

			i++;
		}

		return sb.toString();
	}

	public String removeInternalSpaces(String str) {
		String[] array = str.split(" ");
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (String val : array) {
			sb.append(cutSpaces());
			if (i < array.length)
				sb.append(" ");

			i++;
		}

		return sb.toString();
	}

	public String addDot() {
		StringBuilder sb = new StringBuilder();
		sb.append(string);
		sb.append(".");
		return sb.toString();
	}

	public String addDot(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		sb.append(".");
		return sb.toString();
	}

	public String replaceSpaceWithUnderscore() {
		return string.replace(' ', '_');
	}

	public String replaceSpaceWithUnderscore(String str) {
		return str.replace(' ', '_');
	}

	public String replaceUnderscoreWithSpace() {
		if (Pattern.matches(regex, string))
			return string.replace('_', ' ');

		return string;
	}

	public String replaceUnderscoreWithSpace(String str) {
		if (Pattern.matches(regex, str))
			return str.replace('_', ' ');

		return str;
	}
}
