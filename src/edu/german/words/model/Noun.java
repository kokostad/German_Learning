package edu.german.words.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.german.sql.GetSqlFromConfigFile;
import edu.german.sql.QueryContractor;

public class Noun implements WordModel {
	private Integer woid;
	private Integer oid;
	private String article;
	private String word;
	private String meaning;
	private String[] meanings;
	private String[] noun;
	private String genus = "das Substantiv";
	private Map<Object, Object> propertyMap;
	private List<Map<Object, Object>> propertyList;

	public Noun() {
		propertyList = new LinkedList<Map<Object, Object>>();
	}

	@Override
	public Integer getId() {
		if (oid > -1)
			return oid;

		return -1;
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
		meanings = meaning.split(",");
		return meanings;
	}

	@Override
	public void setId(Integer oid) {
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

	public Integer getWoid() {
		if (woid > -1)
			return woid;

		return -1;
	}

	public void setWoid(Integer woid) {
		this.woid = woid;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String[] getNoun() {
		return noun;
	}

	public void setNoun(String[] noun) {
		this.noun = noun;
	}

	public Map<Object, Object> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<Object, Object> propertyMap) {
		this.propertyMap = propertyMap;
	}

	public List<Map<Object, Object>> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Map<Object, Object>> propertyList) {
		this.propertyList = propertyList;
	}

	public void addPropertyToList(Map<Object, Object> propertyMap) {
		propertyList.add(propertyMap);
	}

	public void prepareNoun(String word) {
		String sql = new GetSqlFromConfigFile("get_noun_by_word").getSql();
		Noun nounTmp = new QueryContractor().getNoun(sql, word);

		// NOTICE need to set up all values
		if (!nounTmp.getArticle().isBlank()) {
			this.setArticle(nounTmp.getArticle());
			this.setGenus(nounTmp.getGenus());
			this.setId(nounTmp.getId());
			this.setMainWord(nounTmp.getMainWord());
			this.setMeaning(nounTmp.getMeaning());
		}
//			propertyList.add(wordMap);

//			return this;

	}

	private int checkIfExist(String tablename, String word) {
		String sql = new GetSqlFromConfigFile("check_word_in_table_" + tablename).getSql();
		return new QueryContractor().getWoidByWord(sql, word);
	}

	private int checkIfExist(String word) {
		String sql = new GetSqlFromConfigFile("get_word_from_main_table_by_word").getSql();
		return new QueryContractor().getWoidByWord(sql, word);
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	@Override
	public String toString() {
		return "Noun [woid=" + woid + ", oid=" + oid + ", article=" + article + ", word=" + word + ", meaning="
				+ meaning + ", meanings=" + Arrays.toString(meanings) + ", noun=" + Arrays.toString(noun) + ", genus="
				+ genus + "]";
	}

}
