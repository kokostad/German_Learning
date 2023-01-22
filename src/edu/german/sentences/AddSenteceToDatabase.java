package edu.german.sentences;

import java.util.HashMap;
import java.util.List;

import edu.german.sql.SqlQuery;
import edu.german.words.model.Word;
import edu.german.sql.QueryContractor;

public class AddSenteceToDatabase {

	public AddSenteceToDatabase() {
	}

	public boolean checkIfSentenceExist(String sentence, String category) {
		if (!sentence.isBlank() && category != null) {
			String sql = new SqlQuery().getSql("check_sentence");
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
			String genus = null;
			String mode = null;
			String tens = null;
			String word = null;
			String wordMeaning = null;
			String wordGenus = null;

			if (map.containsKey("SENTENCE"))
				sentence = map.get("SENTENCE");
			if (map.containsKey("MEANING"))
				meaning = map.get("MEANING");
			if (map.containsKey("GENUS"))
				genus = map.get("GENUS");
			if (map.containsKey("MODE"))
				mode = map.get("MODE");
			if (map.containsKey("TENS"))
				tens = map.get("TENS");
			if (map.containsKey("WORD"))
				word = map.get("WORD");
			if (map.containsKey("WORD_MEANING"))
				wordMeaning = map.get("WORD_MEANING");
			if (map.containsKey("WORD_GENUS"))
				wordGenus = map.get("WORD_GENUS");

			if (!checkIfSentenceExist(sentence, wordGenus)) {
				String sql = new SqlQuery().getSql("add_sentence_with_mode");
				Word newWord = new Word();
				if (!newWord.isExist(word, wordGenus))
					newWord.putIntoRepository(word, wordMeaning, wordGenus);
				new QueryContractor().addSentenceToDatabase(sql, sentence, meaning, genus, mode, tens, word);
			}
		}

	}

}
