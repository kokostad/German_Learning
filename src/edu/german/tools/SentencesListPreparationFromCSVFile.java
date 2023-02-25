package edu.german.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SentencesListPreparationFromCSVFile {
	private BufferedReader br;
	private String sign;
	private List<String[]> list;
	private List<String[]> wrongList;

	public SentencesListPreparationFromCSVFile(BufferedReader br, String sign) {
		this.br = br;
		this.sign = sign;
		list = new LinkedList<String[]>();
		wrongList = new LinkedList<String[]>();
		prepareListOfSentences();
	}

	private void prepareListOfSentences() {
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

	public List<String[]> getWrongList() {
		if (!wrongList.isEmpty())
			return wrongList;

		return null;
	}

}
