package edu.german.words.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class Word implements WordModel {
	private Map<Object, Object> wordMap;

	public Word() {
		wordMap = new HashMap<>();
	}

	@Override
	public int getOid() {
		if (wordMap.containsKey("OID"))
			return Integer.parseInt(wordMap.get("OID").toString());

		return -1;
	}

	@Override
	public String getMainWord() {
		if (wordMap.containsKey("WORD"))
			return wordMap.get("WORD").toString();

		return null;
	}

	@Override
	public String getMeaning() {
		if (wordMap.containsKey("MEANING"))
			return wordMap.get("MEANING").toString();

		return null;
	}

	@Override
	public String[] getMeanings() {
		if (wordMap.containsKey("MEANINGS"))
			return (String[]) wordMap.get("MEANINGS");

		return null;
	}

	@Override
	public void setOid(int oid) {
		wordMap.put("OID", oid);
	}

	@Override
	public void setMainWord(String word) {
		wordMap.put("WORD", word);
	}

	@Override
	public void setMeaning(String meaning) {
		wordMap.put("MEANING", meaning);
	}

	@Override
	public void setMeanings(String[] meanings) {
		if (meanings != null)
			wordMap.put("MEANINGS", meanings);
	}

	public String getGenus() {
		if (wordMap.containsKey("GENUS"))
			return wordMap.get("GENUS").toString();

		return null;
	}

	public void setGenus(String genus) {
		wordMap.put("GENUS", genus);
	}

	public Map<Object, Object> getWordMap() {
		return wordMap;
	}

	public void setWordMap(Map<Object, Object> wordMap) {
		this.wordMap = wordMap;
	}

	@Override
	public int getWoid() {
		if (wordMap.containsKey("WOID"))
			return Integer.parseInt(wordMap.get("WOID").toString());

		return -1;
	}

	@Override
	public void setWoid(int woid) {
		wordMap.put("WOID", woid);
	}

	@Override
	public String getWord() {
		if (wordMap.containsKey("WORD"))
			return wordMap.get("WORD").toString();

		return null;
	}

	@Override
	public void setWord(String word) {
		wordMap.put("WORD", word);
	}

	@Override
	public boolean isExist(String word, String genus) {
		String query = new SqlQuery().getSql("check_word");
		if (new QueryContractor().getId(query, word, genus) > 0)
			return true;

		return false;
	}

	@Override
	public void putIntoRepository(String word, String meaning, String genus) {
		String query = new SqlQuery().getSql("add_new_word");
		new QueryContractor().addNewWord(query, word, meaning, genus);
	}

	@Override
	public List<Word> getAllWords() {
		String query = new SqlQuery().getSql("get_all_words");
		List<Word> list = new QueryContractor().getAllWordList(query);
		return list;
	}

}
