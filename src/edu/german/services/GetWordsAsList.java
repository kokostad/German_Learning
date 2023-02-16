package edu.german.services;

import java.util.List;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.sql.SqlQueryBuilder;

/**
 * @author Tadeusz Kokotowski email: t.kokotowski@gail.com 
 * Class return list of words
 */
public class GetWordsAsList {

	public List<String> getAllWordList() {
		String query = new SqlQuery().getSql("get_all_words");
		return new QueryContractor().getWordsAsStringList(query);
	}

	/**
	 * @param order - pl or ge
	 * @return all words from main word table without OID
	 */
	public List<String> getAllWordListToExport(String order) {
		String query = new SqlQuery().getSql("get_all_words_to_export");

		if (order.equals("pl"))
			query = new SqlQuery().getSql("get_all_words_to_export_pl");

		return new QueryContractor().getWordsAsStringList(query);
	}

	/**
	 * @param wordGenus - the genus of word
	 * @param order - what is exported as first field (pl or ge)
	 * @return only words by specific kind (genus) from main word table without OID
	 */
	public List<String> getGenusWordListToExport(String wordGenus, String order) {
		String query = new SqlQueryBuilder().getWordListByGenus(wordGenus, order);
		return new QueryContractor().getWordsAsStringList(query);
	}

}
