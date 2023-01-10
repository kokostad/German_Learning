package edu.german.services;

import java.util.HashMap;
import java.util.List;

import edu.german.words.AddNewWordIntoDatabase;

public class ExecutorPutWordIntoDatabase implements Runnable {
	private List<HashMap<String, String>> list;

	public ExecutorPutWordIntoDatabase(List<HashMap<String, String>> list) {
		this.list = list;
	}

	@Override
	public void run() {
		new AddNewWordIntoDatabase().addWordsList(list);
	}

}
