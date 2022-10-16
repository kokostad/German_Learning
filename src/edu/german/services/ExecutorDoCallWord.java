package edu.german.services;

import java.util.List;
import java.util.concurrent.Callable;

import edu.german.sql.SqlQuery;
import edu.german.sql.QueryContractor;
import edu.german.words.model.Word;

public class ExecutorDoCallWord implements Callable<List<Word>> {
	private List<Word> list;

	public ExecutorDoCallWord() {
		list = getWordList();
	}

	public List<Word> getWordList() {
		String query = new SqlQuery().getSql("get_all_words");
		return list = new QueryContractor().getAllWordList(query);
	}

	@Override
	public List<Word> call() {
		return list;
	}

}
