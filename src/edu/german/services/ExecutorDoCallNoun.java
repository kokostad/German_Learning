package edu.german.services;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import edu.german.sql.GetSqlFromConfigFile;
import edu.german.sql.QueryContractor;
import edu.german.words.model.Noun;
import edu.german.words.model.Word;

public class ExecutorDoCallNoun implements Callable<List<Noun>> {

	private List<Noun> list;

	public ExecutorDoCallNoun() {
		list = getNounList();
	}

	public List<Noun> getNounList() {
		String query = new GetSqlFromConfigFile("get_all_" + "nouns").getSql();
		return list = new QueryContractor().getAllNounsList(query);
	}

	@Override
	public List<Noun> call() throws Exception {
		return list;
	}

}
