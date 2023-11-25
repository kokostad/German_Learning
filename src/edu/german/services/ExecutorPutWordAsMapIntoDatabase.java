package edu.german.services;

import java.util.List;
import java.util.Map;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.tools.MyProgressBar;

public class ExecutorPutWordAsMapIntoDatabase implements Runnable {
	private List<Map> lm;
	private MyProgressBar bar;
	private boolean order;
	private String[] headers;
	private int sum;
	private int i = 0;

	public ExecutorPutWordAsMapIntoDatabase(List<Map> lm, MyProgressBar bar, boolean order) {
		this.lm = lm;
		this.bar = bar;
		this.order = order;
		sum = lm.size();
	}

	@Override
	public void run() {
		putWordIntoDb();
	}

	private void putWordIntoDb() {
		lm.forEach(m -> {
			i += 1;
			if (!checkWord(m)) {
				execute(m);
			}
			fillBar(i);
		});
	}

	private void execute(Map m) {
		String sql = new QueryBuilder().wordMapToSQL(m);
		if (sql != null)
			new QueryContractor().executeQuery(sql);
	}

	private boolean checkWord(Map<String, String> map) {
		String sql = new QueryBuilder().getWordId(map.get("WORD"), map.get("GENUS"));

		if (sql != null) {
			System.out.println(sql);
			int id = new QueryContractor().getId(sql);
			if (id > -1)
				return true;
		}
		return false;
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.showProgress(result);

		if (result == 100 || i == sum)
			bar.done();
	}
}
