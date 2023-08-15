package edu.german.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import edu.german.sentences.Sentence;

public class SentencesListPreparationFromCSVFile {
	private static String sign;
	private List<String[]> list;
	private List<String[]> wrongList;
	private List<Sentence> sentences;

	public SentencesListPreparationFromCSVFile(BufferedReader br, String sign) {
		this.sign = sign;
		list = new LinkedList<String[]>();
		wrongList = new LinkedList<String[]>();
		prepareListOfSentences(br);
	}

	public SentencesListPreparationFromCSVFile(String data, String sign) {
		this.sign = sign;

		sentences = data
				.lines()
				.map(SentencesListPreparationFromCSVFile::lineToSentnce)
				.toList();
	}

	public static Sentence lineToSentnce(String s) {
		Sentence sentenceTmp = new Sentence();
		String[] arr = s.split(sign);

		if (arr.length > 0)
			sentenceTmp.setSentence(arr[0]);

		if (arr.length > 1)
			sentenceTmp.setMeaning(arr[1]);

		if (arr.length > 2)
			sentenceTmp.setType(arr[2]);

		if (arr.length > 3)
			sentenceTmp.setCategory(arr[3]);

		if (arr.length > 4)
			sentenceTmp.setTens(arr[4]);

		if (arr.length > 5)
			sentenceTmp.setWord(arr[5]);

		return sentenceTmp;
	}

	private void prepareListOfSentences(BufferedReader br) {
		if (br != null) {
			try {
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] array = prepareArray(line);
					if (array != null)
						list.add(prepareArray(line));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * NOTICE what if there is only one sentence without the meaning? this is
	 * important method, need to check if line is OK?
	 */
	private String[] prepareArray(String line) {
		String[] array = line.split(sign);

		if (array.length <= 1)
			wrongList.add(array);
		else
			return array;

		return null;
	}

	public List<String[]> getList() {
		if (!list.isEmpty())
			return list;

		return null;
	}

	public List<Sentence> getSentenceList() {
		return sentences;
	}

	public List<String[]> getWrongList() {
		if (!wrongList.isEmpty())
			return wrongList;

		return null;
	}

}
