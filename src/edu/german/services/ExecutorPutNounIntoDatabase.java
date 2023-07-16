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
		for (Noun word : list) {
			sql = new SqlQuery().getSql("get_noun_oid");
//			System.out.println(sql);
			int id = new QueryContractor().getId(sql, word.getWord(), word.getMeaning());
//			System.out.println("id: " + id);

			if (id < 0) {
				sql = new QueryBuilder().putNewNounIntoDbQuery(word.getWord(), word.getMeaning(), word.getWordPlural(),
						word.getMeanigPlural());
//				System.out.println(sql);
				new QueryContractor().executeQuery(sql);
			}
		}
	}
}
