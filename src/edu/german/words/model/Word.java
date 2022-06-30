package edu.german.words.model;

import java.util.HashMap;
import java.util.Map;

public class Word implements WordModel {
	private int oid = -1;
	private int woid = -1;
	private String word;
	private String meaning;
	private String[] meanings;
	private String genus;
	private Map<Object, Object> wordAsMap;

	@Override
	public int getOid() {
		return oid;
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
	public void setOid(int oid) {
		this.oid = oid;
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
		return wordAsMap;
	}

	public void setWordAsMap(Map<Object, Object> wordAsMap) {
		this.wordAsMap = wordAsMap;
	}

	@Override
	public int getWoid() {
		return woid;
	}

	@Override
	public void setWoid(int woid) {
		this.woid = woid;
	}

	@Override
	public String getWord() {
		return word;
	}

	@Override
	public void setWord(String word) {
		this.word = word;
	}

}
