package edu.german.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.german.tools.GetQuery;
import edu.german.tools.MyProgressBar;
import edu.german.words.AddWordsToDatabase;

public class ExecutorWordTask implements Runnable {
	private String sign;
	private String genus;
	private MyProgressBar bar;
	private int sum;
	private List<String> list;

	public ExecutorWordTask(BufferedReader br, String sign, String genus, MyProgressBar bar) {
		list = new LinkedList<String>();
		this.bar = bar;
		this.sign = sign;
		this.genus = genus;
		sum = countLines(br);
	}

	@Override
	public void run() {
		writeBrIntoDb();
	}

	private void writeBrIntoDb() {
		if (!list.isEmpty()) {
			int i = 0;
			for (String line : list) {
				addWordToMainTab(formatString(line));
				i += 1;
				int result = ((i * 100) / sum);
				bar.fill(result);
				bar.showProgress(result);
				if (result == 100 || i == sum)
					bar.done();
			}
//			bar.done();
		}
	}

	private void addWordToMainTab(String[] array) {
		AddWordsToDatabase awtdb = new AddWordsToDatabase();
		if (!awtdb.checkIfExistInMainTable(array[0], genus))
			awtdb.addNewWord(new GetQuery().getSql("add_new_word"), array[0], array[1], genus);
	}

	private String[] formatString(String line) {
		String[] arr = line.split(sign);
		String[] tmpArr = new String[arr.length];

		if (arr.length > 1) {
			int i = 0;
			for (String s : arr) {
				if (i == 0) {
					tmpArr[0] = s;
					i += 1;
				} else if (i == 1) {
					tmpArr[1] = s;
				}
			}
		}

		return tmpArr;
	}

	/**
	 * @param br - BufferedReader
	 * @set list of words
	 * @return sum of lines in BufferedReader
	 */
	private int countLines(BufferedReader br) {
		int lines = 0;
		String line;
		try {
			while ((line = br.readLine()) != null) {
				list.add(line);
				lines++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

}
