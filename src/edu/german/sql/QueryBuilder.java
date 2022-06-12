package edu.german.sql;

import edu.german.tools.MyProperties;

public class QueryBuilder {

	public QueryBuilder() {
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
		sb.append("SELECT * FROM ge.sentences ");
		if (category.length() > 0)
			sb.append("WHERE category = '" + category + "' ");

		sb.append(";");

		return sb.toString();
	}

}
