package edu.german.io;

import java.util.List;

import edu.german.sql.SqlQueryBuilder;
import edu.german.sql.QueryContractor;

public class GetListOfSentences {

	public GetListOfSentences() {
	}

	public List<String> getList(String category) {
		System.out.println("I wat to get the list of sentences as string");
		String sql = new SqlQueryBuilder().getAllSentencesFromSpecyficTable(category);
		System.out.println(sql);

		QueryContractor qc = new QueryContractor();
		return qc.getSentencesAsList(sql);
	}

}
