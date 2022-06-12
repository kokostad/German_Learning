package edu.german.words;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.german.sql.QueryContractor;
import edu.german.tools.GetQuery;

public class AddWordsToDatabase {
	private GetQuery query;

	public AddWordsToDatabase() {
		this.query = new GetQuery();
	}

	public void addWordsList(List<HashMap<String, String>> list) {

		for (HashMap<String, String> hm : list) {
			String word = "";
			String meaning = "";
			String genus = "";

			for (Entry<String, String> entry : hm.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();

				if (key.contains("WORD"))
					word = value;
				if (key.contains("MEANING"))
					meaning = value;
				if (key.contains("GENUS"))
					genus = value;
			}

			if (!checkIfExistInMainTable(word, genus))
				addNewWord(query.getSql("add_new_word"), word, meaning, genus);
		}
	}

	public void addNewWord(String sql, String word, String meaning, String genus) {
		new QueryContractor().executeQuery(sql, word, meaning, genus);
	}

	public boolean checkIfExistInMainTable(String word, String genus) {
		String sql = query.getSql("check_word");
		QueryContractor qc = new QueryContractor();
		int id = qc.getId(sql, word, genus);
		if (id > -1)
			return true;

		return false;
	}
	
	public boolean checkIfExistInSpecificTable() {
		
		return true;
	}

}
