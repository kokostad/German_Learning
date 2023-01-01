package edu.german.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class clean text from spaces
 * @author Tadeusz.Kokotowski
 */
public class TextCleaner {
	private String word;

	public TextCleaner() {
	}

	public TextCleaner(String word) {
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
	 * @param str is a word or part of the word
	 * as result return cleaned string
	 */
	public String removeWhitespace(String word) {
		Stream<String> stream = Stream.of(word);
		ArrayList<String> filtered = new ArrayList<String>(stream.map(s -> s.replaceAll("[0-9]", ""))
				.map(s -> s.replace(" ", "")).collect(Collectors.toList()));

		return filtered.toString();
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

}