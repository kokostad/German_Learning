package edu.german.words;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class AddNewWordIntoDatabase {

	public AddNewWordIntoDatabase() {
	}

	public void addWordsList(List<HashMap<String, String>> list) {
		for (HashMap<String, String> map : list) {
			String word = null;
			String meaning = null;
			String genus = null;

			for (Entry<String, String> entry : map.entrySet()) {
				if ((entry.getKey()).contains("WORD"))
					word = map.get("WORD");
				if ((entry.getKey()).contains("MEANING"))
					meaning = map.get("MEANING");
				if ((entry.getKey()).contains("GENUS"))
					genus = map.get("GENUS");
			}

			if (!checkIfExistInMainTable(word, genus)) {
				addNewWord(new SqlQuery().getSql("add_new_word"), word, meaning, genus);
			}
		}
	}

	public void addNewWord(String sql, String word, String meaning, String genus) {
		new QueryContractor().executeQuery(sql, word, meaning, genus);
	}

	public boolean checkIfExistInMainTable(String word, String genus) {
		if (new QueryContractor().getId(new SqlQuery().getSql("check_word"), word, genus) > -1)
			return true;

		return false;
	}
}
