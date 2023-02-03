package edu.german.services;

import java.util.HashMap;
import java.util.List;

import edu.german.sentences.AddSenteceToDatabase;

public class ExecutorPutSentenceIntoDatabase implements Runnable {
	private List<HashMap<String, String>> list;

	public ExecutorPutSentenceIntoDatabase(List<HashMap<String, String>> list) {
		this.list = list;
	}

	@Override
	public void run() {
		new AddSenteceToDatabase().addList(list);
	}

}
