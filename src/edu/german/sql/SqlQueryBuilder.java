package edu.german.sql;

import edu.german.tools.MyProperties;

public class SqlQueryBuilder {

	public SqlQueryBuilder() {
	}

	public String prepareInsertQueryWithTableNames(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ge." + tableName + "s");

		return sb.toString();
	}

	public String prepareUpdateQueryWithTableNames(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE INTO ge." + tableName + "s");

		return sb.toString();
	}

	public String prepareSelectQueryWithTableNames(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ge." + tableName + "s");

		return sb.toString();
	}

	public String getAllWordFromSpecyficTable(String genus) {
		String tableName = new MyProperties("table_name.properties").getValue(genus, true);
		System.out.println(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ge." + tableName);
		sb.append(";");

		return sb.toString();
	}

	public String getAllSentencesFromSpecyficTable(String category) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT sentence, meaning FROM ge.sentences");
		if (category != null)
			sb.append("WHERE category = '" + category + "' ");

		sb.append(";");

		return sb.toString();
	}

	public String getWordListByGenus(String wordGenus, String order) {
		StringBuilder sb = new StringBuilder();
		if (order.equals("ge"))
			sb.append("SELECT word, meaning, genus FROM ge.all_words_objects ");
		else if (order.equals("pl"))
			sb.append("SELECT meaning, word, genus FROM ge.all_words_objects ");

		if (wordGenus != null)
			sb.append("where genus = '" + wordGenus + "' ");

		sb.append(";");

		return sb.toString();
	}

	public String getWordListByGenusReversedOrder(String wordGenus) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT meaning, word, genus FROM ge.all_words_objects ");
		if (wordGenus != null)
			sb.append("where genus = '" + wordGenus + "' ");

		sb.append(";");

		return sb.toString();
	}

}
