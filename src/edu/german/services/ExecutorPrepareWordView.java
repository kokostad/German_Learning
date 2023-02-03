package edu.german.services;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class ExecutorPrepareWordView implements Runnable {

	@Override
	public void run() {
		prepareViewForGame();
	}

	private void prepareViewForGame() {
		new QueryContractor().executeQuery(new SqlQuery().getSql("prepare_view_words_for_game"));
	}
}
