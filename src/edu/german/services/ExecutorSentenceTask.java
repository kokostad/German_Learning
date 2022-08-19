package edu.german.services;

import java.util.List;

import edu.german.sentences.AddSenteceToDatabase;
import edu.german.tools.GetQuery;
import edu.german.tools.MyProgressBar;

public class ExecutorSentenceTask implements Runnable {
	private List<String[]> list;
	private String type;
	private int sum;
	private MyProgressBar bar;

	public ExecutorSentenceTask(List<String[]> list, String type, MyProgressBar bar, boolean order) {
		this.bar = bar;
		this.list = list;
		this.type = type;
		sum = list.size();
	}

	@Override
	public void run() {
		addListToRepo(list);
	}

	private void addListToRepo(List<String[]> list) {
		if (!list.isEmpty()) {
			int i = 0;
			for (String[] array : list) {
				addSentenceToMainTab(array);
				i += 1;
				int result = ((i * 100) / sum);
				bar.fill(result);
				bar.showProgress(result);
				if (result == 100 || i == sum)
					bar.done();
			}
		}
	}

	private void addSentenceToMainTab(String[] array) {
		AddSenteceToDatabase astdb = new AddSenteceToDatabase();
		if (!astdb.checkIfExist(array[0], type, null, null, null))
			astdb.addNewSentence(new GetQuery().getSql("add_new_sentence"), array[0], array[1], type);
	}
}
