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
	private boolean order;

	public ExecutorWordTask(BufferedReader br, String sign, String genus, MyProgressBar bar, boolean order) {
		list = new LinkedList<String>();
		this.order = order;
		this.bar = bar;
		this.sign = sign;
		this.genus = genus;
		sum = countLines(br);
	}

	@Override
	public void run() {
		writeIntoDb();
	}

	private void writeIntoDb() {
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
		}
	}

	private void addWordToMainTab(String[] array) {
		AddWordsToDatabase awtdb = new AddWordsToDatabase();
		if (order) {
			if (!(array[1].toString()).isBlank())
				/*
				 * TODO check if word exist in main table in database check if word exist in
				 * specific table if not exist write down to the appropriate tables
				 */
				if (!awtdb.checkIfExistInMainTable(array[1], genus))
					awtdb.addNewWord(new GetQuery().getSql("add_new_word"), array[1], array[0], genus);
		} else {
			if (!(array[0].toString()).isBlank())
				if (!awtdb.checkIfExistInMainTable(array[0], genus))
					awtdb.addNewWord(new GetQuery().getSql("add_new_word"), array[0], array[1], genus);
		}
	}

	private String[] formatString(String line) {
//		System.out.println(line);
		int idx = line.indexOf(sign);
//		System.out.println(idx + " " + sign);
		// TODO improve this method

//		String[] tmpArray = new String[2];
//		tmpArray[0] = line.substring(0, idx);
//		tmpArray[1] = line.substring(idx, line.lastIndexOf(line) - 1);

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
