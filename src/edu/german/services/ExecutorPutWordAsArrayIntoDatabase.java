package edu.german.services;

import java.util.List;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.tools.MyProgressBar;
import edu.german.tools.TextHandler;
import edu.german.words.model.Word;

public class ExecutorPutWordAsArrayIntoDatabase implements Runnable {
	private List<String[]> list;
	private MyProgressBar bar;
	private int sum;
	private boolean order;
	private int i = 0;

	public ExecutorPutWordAsArrayIntoDatabase(List<String[]> list, MyProgressBar bar, boolean order) {
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
			list.forEach(arr -> {
				i += 1;
				arr = checkIfWordExist(arr);
				if (arr != null) {
					execute(arr);
				}
				fillBar(i);
			});
		}
	}

	private void execute(String[] array) {
		array = cleaning(array);
		String sql = new QueryBuilder().addNewWord(array[0], array[1], array[2]);
		System.out.println(sql);
		boolean state = new QueryContractor().executeQuery(sql);
	}

	private String[] cleaning(String[] array) {
		String[] arr = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			arr[i] = new TextHandler().removeWhitespace(array[i].toString());
		}

		return arr;
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.showProgress(result);
		if (result == 100 || i == sum)
			bar.done();
	}

	private String[] checkIfWordExist(String[] arr) {
		if (!new Word().isExist(arr[0], arr[1], arr[2])) {
			return arr;
		}
		return null;
	}

}
