package edu.german.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.german.sql.SqlQuery;
import edu.german.tools.MyProgressBar;
import edu.german.words.AddNewWordIntoDatabase;

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
				putWordIntoMainTab(formatString(line));
				i += 1;
				int result = ((i * 100) / sum);
				bar.fill(result);
				bar.showProgress(result);
				if (result == 100 || i == sum)
					bar.done();
			}
		}
	}

	private void putWordIntoMainTab(String[] array) {
//		AddNewWordIntoDatabase awtdb = new AddNewWordIntoDatabase();
		if (order) {
//			if (!(array[1].toString()).isBlank())
				/*
				 * TODO check if word exist in main table in database check if word exist in
				 * specific table if not exist write down to the appropriate tables
				 */
//				if (!awtdb.checkIfExistInSpecificTable(array[1].toString(), genus))
//					awtdb.addNewWord(new SqlQuery().getSql("add_new_word"), array[1], array[0], genus);
		} else {
//			if (!(array[0].toString()).isBlank())
//				if (!awtdb.checkIfExistInSpecificTable(array[0].toString(), genus))
//					awtdb.addNewWord(new SqlQuery().getSql("add_new_word"), array[0], array[1], genus);
		}
	}

	// TODO check and improve this method if needed
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
