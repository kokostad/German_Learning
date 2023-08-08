package edu.german.services;

import java.util.List;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.words.Noun;

public class ExecutorPutNounIntoDatabase implements Runnable {
	private List<Noun> list;

	public ExecutorPutNounIntoDatabase(List<Noun> list) {
		this.list = list;
	}

	@Override
	public void run() {
		putNounIntoDatabase();
	}

	private void putNounIntoDatabase() {
		String sql;
		int id = -1;
		for (Noun noun : list) {
			id = noun.getOid();
			if (id <= 0) {
				sql = new SqlQuery().getSql("get_noun_oid");
				id = new QueryContractor().getId(sql, noun.getWord(), noun.getMeaning());
			}

			if (id < 0) {
				sql = new QueryBuilder().putNewNounIntoDb(noun.getWord(), noun.getMeaning(), noun.getWordPlural(),
						noun.getMeaningPlural());
				new QueryContractor().executeQuery(sql);
			} else {
				sql = new QueryBuilder().updateNoun(id, noun.getWord(), noun.getMeaning(), noun.getWordPlural(),
						noun.getMeaningPlural());
				new QueryContractor().executeQuery(sql);
			}
		}
	}
}
