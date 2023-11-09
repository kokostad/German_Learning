package edu.german.services;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class ExecutorPrepareNounView implements Runnable {
	private String pattern = "prepare_view_all_words";
	
	public ExecutorPrepareNounView(String pattern){
		this.pattern = pattern;
	}

	@Override
	public void run() {
		prepareViewForGame();
	}

	private void prepareViewForGame() {
		new QueryContractor().executeQuery(new SqlQuery().getSql(pattern));
	}
}
