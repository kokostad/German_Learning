package edu.german.io;

import java.util.List;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class GetListOfSentences {

	public GetListOfSentences() {
	}

	public List<String> getList(String order) {
		String sql = new SqlQuery().getSql("get_all_sentences_to_export");
		if (order.equals("pl"))
			sql = new SqlQuery().getSql("get_all_sentences_to_export_pl");

		return new QueryContractor().getSentencesAsList(sql);
	}

}
