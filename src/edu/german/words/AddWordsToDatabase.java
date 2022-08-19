package edu.german.words;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.german.sql.QueryContractor;
import edu.german.tools.GetQuery;
import edu.german.tools.MyProperties;
import edu.german.tools.ReplaceSpaces;

public class AddWordsToDatabase {
	private String genus;
	private String tableName;

	public AddWordsToDatabase() {
	}

	public AddWordsToDatabase(String genus) {
		this.genus = genus;
		tableName = getTableName(genus);
	}

	private String getTableName(String genus) {
		genus = new ReplaceSpaces().replaceSpaceWithUnderscore(genus);
		return new MyProperties("table_name.properties").getValue(genus);
	}

	public void addWordsList(List<HashMap<String, String>> list) {
		AddAdjectiveGradationIntoRepository addAdj = new AddAdjectiveGradationIntoRepository();
		for (HashMap<String, String> hm : list) {
			String word = "";
			String meaning = "";

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

			if (!checkIfExistInMainTable(word)) {
				String sql = new GetQuery().getSql("add_new_word");
				addNewWord(sql, word, meaning);
			}

			if (!checkIfExistInSpecificTable(word, genus))
				addAdj.putIntoRepo(word, hm);
		}
	}

	public void addNewWord(String sql, String word, String meaning) {
		new QueryContractor().executeQuery(sql, word, meaning, genus);
	}

	public void addNewWord(String sql, String word, String meaning, String genus) {
		new QueryContractor().executeQuery(sql, word, meaning, genus);
	}

	public boolean checkIfExistInMainTable(String word) {
		if (new QueryContractor().getId(new GetQuery().getSql("check_word"), word, genus) > -1)
			return true;

		return false;
	}

	public boolean checkIfExistInSpecificTable(String word, String genus) {
		if (new QueryContractor().getId(new GetQuery().getSql("check_" + tableName), word) > -1)
			return true;

		return false;
	}

}
