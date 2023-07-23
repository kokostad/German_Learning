package edu.german.services;

import java.util.List;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.words.model.Noun;

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
		for (Noun word : list) {
			id = word.getOid();
			if (id <= 0) {
				sql = new SqlQuery().getSql("get_noun_oid");
//				System.out.println(sql);
				id = new QueryContractor().getId(sql, word.getWord(), word.getMeaning());
//				System.out.println("id: " + id);
			}

			if (id < 0) {
				sql = new QueryBuilder().putNewNounIntoDb(word.getWord(), word.getMeaning(), word.getWordPlural(),
						word.getMeaningPlural());
//				System.out.println(sql);
				new QueryContractor().executeQuery(sql);
			} else {
				sql = new QueryBuilder().updateNoun(id, word.getWord(), word.getMeaning(), word.getWordPlural(),
						word.getMeaningPlural());
//				System.out.println(sql);
				new QueryContractor().executeQuery(sql);
			}
		}
	}
}
