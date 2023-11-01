package edu.german.services;

import java.util.List;

import edu.german.sentences.Sentence;
import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.tools.MyProgressBar;
import edu.german.tools.TextCleaner;

public class ExecutorStringSentenceIntoDB implements Runnable {
	private List<String[]> list;
	private MyProgressBar bar;
	private int sum;
	private boolean order;
	private int i = 0;

	public ExecutorStringSentenceIntoDB(List<String[]> list, MyProgressBar bar, boolean order) {
		this.bar = bar;
		this.list = list;
		this.order = order;
		sum = list.size();
	}

	@Override
	public void run() {
		addToRepo(list);
	}

	private void addToRepo(List<String[]> list) {
		if (!order) {
			for (String[] a : list) {
				i += 1;
				a = checkIfSentenceExist(a);
				if (a != null)
					execute(a);

				fillBar(i);
			}
		}
	}

	private void execute(String[] array) {
		String sql = new QueryBuilder().addNewSentenceToRepository(cleaning(array));
		new QueryContractor().executeQuery(sql);
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.showProgress(result);
		if (result == 100 || i == sum)
			bar.done();
	}

	private String[] cleaning(String[] array) {
		String[] arr = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			arr[i] = new TextCleaner().removeWhitespace(array[i].toString());
		}

		return arr;
	}

	private String[] checkIfSentenceExist(String[] arr) {
		if (!new Sentence().checkOid(arr[0])) {
			return arr;
		}
		return null;
	}

}
