package edu.german.io;

import java.util.List;

import edu.german.sql.SqlQueryBuilder;
import edu.german.sql.QueryContractor;

public class GetListOfWords {

	private List<String[]> getList(String genus) {
		System.out.println("I wat to get the list of words");
		String sql = new SqlQueryBuilder().getAllWordFromSpecyficTable(genus);
		System.out.println(sql);

		QueryContractor qc = new QueryContractor();
		List<String[]> var = qc.getWordsList(sql);

		for (String string[] : var) {
			for (int i = 0; i < string.length; i++)
				System.out.print(string[i] + "; ");

			System.out.println();
		}
		return var;
	}

	public List<String[]> getList(String filePath, String separationSign, String genus) {
		System.out.println(filePath + " " + separationSign + " " + genus);
		return getList(genus);

	}

}
