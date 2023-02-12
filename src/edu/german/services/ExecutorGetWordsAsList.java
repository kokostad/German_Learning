package edu.german.services;

import java.util.List;
import java.util.concurrent.Callable;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class ExecutorGetWordsAsList implements Callable<List<String>> {
	private List<String> list;
	
	public ExecutorGetWordsAsList() {
			list = getWordList();
	}

	public List<String> getWordList() {
		String query = new SqlQuery().getSql("get_all_words");
		return new QueryContractor().getWordsAsStringList(query);
	}

	@Override
	public List<String> call() {
		return list;
	}

}
