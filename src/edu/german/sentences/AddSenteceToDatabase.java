package edu.german.sentences;

import java.util.HashMap;
import java.util.List;

import edu.german.sql.SqlQuery;
import edu.german.sql.QueryContractor;


public class AddSenteceToDatabase {
	private SqlQuery query;

	public AddSenteceToDatabase() {
		this.query = new SqlQuery();
	}

	public boolean checkIfSentenceExist(String sentence, String category) {
		if (!sentence.isBlank() && category != null) {
			String sql = query.getSql("check_sentence");
			QueryContractor qc = new QueryContractor();
			int id = qc.getId(sql, sentence, category);
			if (id > -1)
				return true;
		}

		return false;
	}

	public void addNewSentence(String sql, String sentence, String meaning, String genus) {
		if (!sentence.isBlank() && !meaning.isBlank() && !genus.isBlank())
			new QueryContractor().executeQuery(sql, sentence, meaning, genus);
	}

	public void addList(List<HashMap<String, String>> mapList) {
		for (HashMap<String, String> map : mapList) {
			String sentence = null;
			String meaning = null;
			String type = null;
			String category = null;
			String tens = null;
			String word = null;

			if (map.containsKey("SENTENCE"))
				sentence = map.get("SENTENCE");
			if (map.containsKey("MEANING"))
				meaning = map.get("MEANING");
			if (map.containsKey("TYPE"))
				type = map.get("TYPE");
			if (map.containsKey("CATEGORY"))
				category = map.get("CATEGORY");
			if (map.containsKey("TENS"))
				tens = map.get("TENS");
			if (map.containsKey("WORD"))
				word = map.get("WORD");

			if (!checkIfSentenceExist(sentence, category)) {
				String sql = query.getSql("add_sentence_with_mode");
				new QueryContractor().addSentenceToDatabase(sql, sentence, meaning, type, category, tens, word);
			}
		}

	}

}
