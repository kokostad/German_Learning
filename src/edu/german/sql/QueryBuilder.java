package edu.german.sql;

import java.util.Properties;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.german.tools.MyProperties;
import edu.german.tools.Translator;

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

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ge.sentences(sentence, meaning, type, category, tens, word, woid) VALUES(");
		sb.append("'" + sentence + "', '" + meaning + "', '" + genus + "', '" + mode + "', '" + tens + "', '" + word
				+ "' ");
		sb.append("(SELECT meaning, woid FROM ge.all_words_objects WHERE ");

		return null;
	}

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

	public String getSimpleVerb(String word, String regular, String separable) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ge.verbs WHERE word = '" + word + "' ");

		if (regular != null || separable != null) {
			if (regular != null)
				sb.append(" AND irregular = '" + regular + "' ");

			if (separable != null)
				sb.append(" AND separable = '" + separable + "' ");
		}

		sb.append(";");

		return sb.toString();
	}

	public String addVerbConjugation(int oid, String word, String irregular, String separable, Properties prop) {
		String[] tableHeaders = new MyProperties("table_headers.properties")
				.getValuesArray("VERBS_CONJUGATION_TABLE_HEADER");

		ArrayList<String> headersList = new ArrayList<String>();

		String tens = prop.getProperty("TENS");
		prop.remove("TENS");
		String modus = prop.getProperty("MODUS");
		prop.remove("MODUS");

		for (String s : tableHeaders)
			headersList.add(s.toUpperCase());

		StringBuilder sbK = new StringBuilder();
		StringBuilder sbV = new StringBuilder();

		int enough = headersList.size();

		int i = 0;
		for (String st : headersList) {
			Object key = headersList.get(i);
			Object value = prop.get(headersList.get(i));
			if (value != null && !(value.toString()).isEmpty()) {
				if (i > 0)
					sbK.append(",");
				sbK.append(headersList.get(i));

				if (i > 0)
					sbV.append(",");
				sbV.append("'");
				sbV.append(value);
				sbV.append("'");
			}
			i++;
		}

		if ((sbK.toString()).contains("ICH") && tens != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ge.verbs_conjugation (TENS,MODUS,VOID,");
			sb.append(sbK.toString());
			sb.append(") VALUES(");
			sb.append("'");
			sb.append(tens);
			sb.append("','");
			sb.append(modus);
			sb.append("',");
			sb.append(oid);
			sb.append(",");
			sb.append(sbV.toString());
			sb.append(") ;");

			return sb.toString();
		}

		return null;
	}

	public String existVerbConjugation(int oid, String tens, String modus) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT oid FROM ge.verbs_conjugation WHERE tens = '");
		sb.append(tens);
		sb.append("' AND modus ='");
		sb.append(modus);
		sb.append("' AND void = ");
		sb.append(oid);
		sb.append(" ;");

		System.out.println(sb.toString());
		
		return sb.toString();
	}

}
