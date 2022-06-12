package edu.german.words;

import java.util.HashMap;
import java.util.Map;

import edu.german.words.model.WordModel;

public class Word implements WordModel {
	private Integer id = -1;
	private String word;
	private String meaning;
	private String[] meanings;
	private String genus;
	private Map<Object, Object> wordAsMap;

	public Word() {
		wordAsMap = new HashMap<Object, Object>();
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getMainWord() {
		return word;
	}

	@Override
	public String getMeaning() {
		return meaning;
	}

	@Override
	public String[] getMeanings() {
		return meanings;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;

	}

	@Override
	public void setMainWord(String word) {
		this.word = word;
	}

	@Override
	public void setMeaning(String meaning) {
		this.meaning = meaning;

	}

	@Override
	public void setMeanings(String[] meanings) {
		this.meanings = meanings;

	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public Map<Object, Object> getWordAsMap() {
		if (!wordAsMap.isEmpty())
			return wordAsMap;

		return null;
	}

	public void setWordAsMap(Map<Object, Object> wordAsMap) {
		this.wordAsMap = wordAsMap;
	}

}
