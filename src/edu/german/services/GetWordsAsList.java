package edu.german.services;

import java.util.List;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class GetWordsAsList {

	public List<String> getAllWordList() {
		String query = new SqlQuery().getSql("get_all_words");
		return new QueryContractor().getWordsAsStringList(query);
	}

	public List<String> getAllWordListToExport() {
		String query = new SqlQuery().getSql("get_all_words_to_export");
		return new QueryContractor().getWordsAsStringList(query);
	}

}
