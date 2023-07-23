package edu.german.words.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class Word implements WordModel {
	private int woid;
	private int oid;
	private String word;
	private String meaning;
	private String[] meanings;
	private String genus;
	private Properties properties;
	private List<Properties> propertiesList;

	public Word() {
		this.properties = new Properties();
		this.propertiesList = new LinkedList<>();
	}

	public Word(String word, String meaning, String genus) {
		properties = new Properties();
		propertiesList = new LinkedList<>();
		this.word = word;
		this.meaning = meaning;
		this.genus = genus;
		setWoid(new QueryContractor().getId(new SqlQuery().getSql("get_word_woid"), word, genus));
	}

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

	@Override
	public void setGenus(String genus) {
		this.genus = genus;
	}

	@Override
	public int getWoid(String word, String genus) {
		if (woid < 0)
			return new QueryContractor().getId(new SqlQuery().getSql("get_word_woid"), word, genus);

		return -1;
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

	@Override
	public boolean isExist(String word, String genus) {
		String query = new SqlQuery().getSql("check_word");
		if (new QueryContractor().getId(query, word, genus) > 0)
			return true;

		return false;
	}

	@Override
	public void putIntoRepository(String word, String meaning, String genus) {
		if (!isExist(word, genus)) {
			String query = new SqlQuery().getSql("add_new_word");
			new QueryContractor().addNewWord(query, word, meaning, genus);
		}
	}

	@Override
	public List<Word> getAllWords() {
		String query = new SqlQuery().getSql("get_all_words");
		List<Word> list = new QueryContractor().getAllWordList(query);
		return list;
	}

	public void addToPropertiesList(Properties properties) {
		propertiesList.add(properties);
	}

	@Override
	public List<Properties> getPropertiesList() {
		return propertiesList;
	}

	@Override
	public void setPropertiesList(List<Properties> propertiesList) {
		this.propertiesList = propertiesList;
	}

	@Override
	public Properties getProperites() {
		return properties;
	}

	@Override
	public void setProperites(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String getGenus() {
		return genus;
	}

	@Override
	public List<Properties> getPropertiesList(int oid) {
		return propertiesList;
	}

}
