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

	public boolean checkIfExist(String sentence, String type, String category, String tens, String mode) {
		if (!sentence.isBlank() && type != null) {
			String sql = query.getSql("check_sentence");
			QueryContractor qc = new QueryContractor();
			int id = qc.getId(sql, sentence, type);
			if (id > -1)
				return true;
		}

		if (!sentence.isBlank() && mode != null) {
			String sql = query.getSql("check_sentence_mode");
			QueryContractor qc = new QueryContractor();
			int id = qc.getId(sql, sentence, type);
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
			String mode = null;

			if (map.containsKey("SENTENCE"))
				sentence = map.get("SENTENCE");
			if (map.containsKey("MEANING"))
				meaning = map.get("MEANING");
			if (map.containsKey("MODE"))
				mode = map.get("MODE");

			if (sentence != null && mode != null && meaning != null) {
				if (!checkIfExist(sentence, null, null, null, mode)) {
					String sql = query.getSql("add_sentence_with_mode");
					new QueryContractor().executeQuery(sql, sentence, meaning, mode);
				}
			}
		}

	}

}
