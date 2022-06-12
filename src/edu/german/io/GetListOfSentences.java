package edu.german.io;

import java.util.List;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;

public class GetListOfSentences {

	private List<String[]> getList(String category) {
		System.out.println("I wat to get the list of sentences");
		String sql = new QueryBuilder().getAllSentencesFromSpecyficTable(category);
		System.out.println(sql);

		QueryContractor qc = new QueryContractor();
		List<String[]> var = qc.getSentencesList(sql);

		for (String string[] : var) {
			for (int i = 0; i < string.length; i++)
				System.out.print(string[i] + "; ");

			System.out.println();
		}
		return var;

	}

	public List<String[]> getList(String filePath, String separationSign, String category) {
		System.out.println(filePath + " " + separationSign + " " + category);
		return getList(category);
	}

}
