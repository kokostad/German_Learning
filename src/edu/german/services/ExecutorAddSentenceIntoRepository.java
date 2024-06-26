package edu.german.services;

import java.util.List;

import edu.german.sentences.Sentence;
import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.tools.MyFrameProgressBar;
import edu.german.tools.TextHandler;

public class ExecutorAddSentenceIntoRepository implements Runnable {
	private List<String[]> list;
	private MyFrameProgressBar bar;
	private int sum;
	private boolean order;
	private int i = 0;

	public ExecutorAddSentenceIntoRepository(List<String[]> list, boolean order, MyFrameProgressBar bar) {
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
				execute(a);
				fillBar(i);
			}
		}
	}

	private void execute(String[] array) {
		String sql = new QueryBuilder().addNewSentenceToRepository(cleaning(array));
		System.out.println(sql);
		boolean state = new QueryContractor().executeQuery(sql);
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.fill(result);
		bar.showProgress(result);
		if (result == 100 || i == sum)
			bar.done();
	}

	private String[] cleaning(String[] array) {
		String[] arr = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			arr[i] = new TextHandler().removeWhitespace(array[i].toString());
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
