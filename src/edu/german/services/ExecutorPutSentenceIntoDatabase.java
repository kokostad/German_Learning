package edu.german.services;

import java.util.HashMap;
import java.util.List;

import edu.german.sentences.AddToPepository;

public class ExecutorPutSentenceIntoDatabase implements Runnable {
	private List<HashMap<String, String>> list;

	public ExecutorPutSentenceIntoDatabase(List<HashMap<String, String>> list) {
		this.list = list;
	}

	@Override
	public void run() {
		new AddToPepository().addList(list);
	}

}
