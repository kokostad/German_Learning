package edu.german.services;

import java.util.List;
import java.util.concurrent.Callable;

import edu.german.sql.SqlQuery;
import edu.german.sql.QueryContractor;
import edu.german.words.model.Noun;

public class ExecutorDoCallNoun implements Callable<List<Noun>> {

	private List<Noun> list;

	public ExecutorDoCallNoun() {
		list = getNounList();
	}

	public List<Noun> getNounList() {
//		String sql = new SqlQuery().getSql("select * from ge.wordgames w ;");
		String sql = "select * from ge.wordgames w ;";
		return list = new QueryContractor().getAllNounsFromView(sql);
	}

	@Override
	public List<Noun> call() throws Exception {
		return list;
	}

}
