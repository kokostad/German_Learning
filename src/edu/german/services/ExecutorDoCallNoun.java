package edu.german.services;

import java.util.List;
import java.util.concurrent.Callable;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.words.model.Noun;

public class ExecutorDoCallNoun implements Callable<List<Noun>> {
	private List<Noun> list;

	public ExecutorDoCallNoun() {
		list = getNounList();
	}

	public List<Noun> getNounList() {
		return list = new QueryContractor()
				.getAllNounsFromView(new SqlQuery().getSql("get_nouns_view_for_game"));
	}

	@Override
	public List<Noun> call() throws Exception {
		return list;
	}

}
