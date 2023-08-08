package edu.german.services;

import java.util.List;
import java.util.concurrent.Callable;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.words.Noun;

public class GetNounList implements Callable<List<Noun>> {
	List<Noun> list;

	public List<Noun> getList() {
		return new QueryContractor()
				.getAllNounsList(new QueryBuilder()
						.getAllWordFromSpecyficTable("das Substantiv"));
	}

	@Override
	public List<Noun> call() throws Exception {
		return getList();
	}
}
