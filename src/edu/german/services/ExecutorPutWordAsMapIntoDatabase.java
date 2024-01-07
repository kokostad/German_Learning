package edu.german.services;

import java.util.List;
import java.util.Map;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.tools.MyFrameProgressBar;

public class ExecutorPutWordAsMapIntoDatabase implements Runnable {
	private List<Map> lm;
	private MyFrameProgressBar mbar;
	private boolean order;
	private String[] headers;
	private int sum;
	private int i = 0;

	public ExecutorPutWordAsMapIntoDatabase(List<Map> lm, boolean order) {
		mbar = new MyFrameProgressBar();
		this.lm = lm;
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

	private void execute(Map<String, String> m) {
		String sql = new QueryBuilder().wordMapToSQL(m);
		if (sql != null)
			new QueryContractor().executeQuery(sql);
	}

	private boolean checkWord(Map<String, String> map) {
		String sql = new QueryBuilder().getWordId(map.get("WORD"), map.get("GENUS"));

		if (sql != null) {
			int id = new QueryContractor().getId(sql);
			if (id > -1)
				return true;
		}
		return false;
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		mbar.fill(result);
		mbar.showProgress(result);

		if (result == 100 || i == sum)
			mbar.done();
	}
}
