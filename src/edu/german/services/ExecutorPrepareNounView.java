package edu.german.services;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class ExecutorPrepareNounView implements Runnable {

	@Override
	public void run() {
		prepareViewForGame();
	}

	private void prepareViewForGame() {
		new QueryContractor().executeQuery(new SqlQuery().getSql("prepare_view_nouns_for_game"));
	}
}
