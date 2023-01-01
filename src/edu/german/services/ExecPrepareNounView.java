package edu.german.services;

import edu.german.sql.QueryContractor;

public class ExecPrepareNounView implements Runnable {

	@Override
	public void run() {
		prepareViewForGame();
	}

	private void prepareViewForGame() {
		String sql = "create or replace recursive view ge.wordgames(word, meaning) "
				+ "	as (select awo.word, awo.meaning from ge.all_words_objects awo "
				+ " where awo.genus = 'das Substantiv') ;";
		new QueryContractor().executeQuery(sql);
	}
}
