package edu.german.services;

import java.util.List;

import edu.german.sentences.Sentence;
import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.tools.MyProgressBar;

public class ExecutorSentenceIntoRepository implements Runnable {
	private List<String[]> list;
	private int sum;
	private boolean order;
	private MyProgressBar bar;

	public ExecutorSentenceIntoRepository(List<String[]> list, MyProgressBar bar, boolean order) {
		this.list = list;
		this.bar = bar;
		this.bar = bar;
		sum = list.size();
	}

	@Override
	public void run() {
		addListToRepo(list);
	}

	private void addListToRepo(List<String[]> list) {
		int i = 0;
		if (!order) {
			for (String[] array : list) {
				i += 1;
				if (!checkSentence(array[0].toString(), array[1].toString()))
					putSentenceToRepository(array);

				fillBar(i);
			}
		}

		else {
			for (String[] array : list) {
				i += 1;
				if (!checkSentence(array[1].toString(), array[0].toString()))
					putSentenceToRepository(array);

				fillBar(i);
			}
		}
	}

	private void putSentenceToRepository(String[] sentence) {
		String sql = new QueryBuilder().addNewSentenceToRepository(sentence);
		new QueryContractor().executeQuery(sql);
	}

	private boolean checkSentence(String one, String two) {
		return new Sentence().checkOid(one, two);
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.showProgress(result);
		if (result == 100 || i == sum)
			bar.done();
	}
}
