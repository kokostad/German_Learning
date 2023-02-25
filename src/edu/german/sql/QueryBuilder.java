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

	public String addNewSentenceToRepository(String sentence, String meaning, String genus, String mode, String tens,
			String word, String wordMeaning, String wordGenus) {

		// sentence, meaning, type, category, tens, word, woid
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ge.sentences(sentence, meaning, type, category, tens, word, woid) VALUES(");
		sb.append("'" + sentence + "', '" + meaning + "', '" + genus + "', '" + mode + "', '" + tens + "', '" + word
				+ "' ");
		sb.append("(SELECT meaning, woid FROM ge.all_words_objects WHERE ");

		return null;
	}

	// sentence, meaning, type, category, tens, word, woid
	public String addNewSentenceWithWoid(String sentence, String meaning, String type, String category, String tens,
			String word, int woid) {

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ge.sentences(sentence, meaning, type, category, tens, word, woid) VALUES(");
		sb.append("'" + sentence + "', '" + meaning + "', '" + type + "', ");
		sb.append("'" + category + "', '" + tens + "', '" + word + "', '" + "" + woid + "') ;");

		return sb.toString();
	}

	public String addNewSentenceToRepository(String[] sentence) {
		String[] header = new MyProperties("table_headers.properties").getValuesArray("SENTENCES");
		StringBuilder sb = new StringBuilder();
		int length = sentence.length - 1;

		sb.append("INSERT INTO ge.sentences(");

		for (int i = 0; i <= length; i++) {
			if (i < length)
				sb.append(header[i] + ", ");
			else
				sb.append(header[i]);
		}

		sb.append(") VALUES (");

		for (int i = 0; i <= length; i++) {
			if (i < length)
				sb.append("'" + sentence[i] + "', ");
			else
				sb.append("'" + sentence[i] + "'");
		}

		sb.append(");");

		return sb.toString();
	}

}
