package edu.german.tools;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class clean text from spaces
 * @author Tadeusz.Kokotowski
 */
public class TextHandler {
	private String word;

	public TextHandler() {
	}

	// TODO need to improve this method, check if exist some value or null
	public TextHandler(String word) {
		StringBuilder sb = new StringBuilder();
		String[] wordParts = word.split(" ");
		ArrayList<String> ar = new ArrayList<String>();

		for (String s : wordParts) {
			if (!s.equals(" ") && s != "" && s != null)
				ar.add(s);
		}

		int i = 1;
		for (String s : ar) {
			sb.append(removeSpace(s));
			if (wordParts.length > 1 && i < wordParts.length)
				sb.append(" ");
			i += 1;
		}
		setWord(sb.toString());
	}

	/**
	 * Method change string into stream and clean from spaces and digit
	 * @param word -> it's string to clean
	 * as result return cleaned string
	 */
	public String removeWhitespace(String word) {
		String result = word.replaceAll("\\s+", " ");
		return result.trim();
	}

	/**
	 * Method change string into stream and clean from spaces and digit
	 * @param str is a word or part of the word
	 * as result return cleaned string
	 */
	public String removeWhitespaceAndDigit(String string) {
		String value = string.chars()
				.filter(c -> !Character.isDigit(c))
				.filter(c -> !Character.isSpaceChar(c))
				.mapToObj(c -> String.valueOf((char) c))
				.collect(Collectors.joining());

		return value;
	}

	/**
	 * Method clean string from spaces
	 * @param str - is word or part of the word
	 * as result return cleaned string
	 */
	private String removeSpace(String str) {
		StringBuilder sb = new StringBuilder();
		char[] array = str.toCharArray();

		for (char c : array)
			if (!Character.isSpaceChar(c) && !Character.isWhitespace(c))
				sb.append(c);

		return sb.toString();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String removeTab(String string) {
		return string.replace("\t", "");
	}
	
	public String addUnderscore(String str) {
		String result = str.replaceAll("\\s", "_");
		return result;
	}
}
