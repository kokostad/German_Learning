package edu.german.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SentencesListPreparation {
	private BufferedReader br;
	private String sign;
	private List<String[]> list;

	public SentencesListPreparation(BufferedReader br, String sign) {
		this.br = br;
		this.sign = sign;
		list = new LinkedList<String[]>();
		getListOfSentences();
	}

	private void getListOfSentences() {
		if (br != null) {
			try {
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] array = prepareArray(line);
					if (array != null && (!array[0].isBlank() || !array[1].isBlank()))
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

	private String[] prepareArray(String line) {
		if (!line.isBlank()) {
			String[] array = line.split(sign);
			String[] newArray = new String[2];
			if (array.length > 1) {
				newArray[0] = array[0];
				String[] arr = array[1].split(",");
				newArray[1] = arr[0];

				return newArray;
			}
			return null;
		}

		return null;
	}

	public List<String[]> getList() {
		return list;
	}

}
