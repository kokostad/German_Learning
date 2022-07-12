package edu.german.words.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Noun extends Word {
	private String genus = "das Substantiv";
	private List<Map<Object, Object>> propertyList;

	public Noun() {
		propertyList = new LinkedList<Map<Object, Object>>();
		super.setGenus(genus);
	}

	private Object getObjectFromList(String key) {
		for (Map<Object, Object> m : propertyList)
			if (m.containsKey(key))
				return m.get(key);

		return null;
	}

	private void setObjectToList(Object key, Object value) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(key, value);
		propertyList.add(map);
	}

	public int getOid() {
		if (Integer.parseInt("OID") > -1)
			return Integer.parseInt("OID");
		else
			return -1;
	}

	public String getWord() {
		return getObjectFromList("WORD").toString();
	}

	public String getMeaning() {
		return getObjectFromList("MEANING").toString();
	}

	public String[] getMeanings() {
		return (getObjectFromList("MEANING").toString()).split(" ");
	}

	public void setOid(int oid) {
		if (oid > -1)
			setObjectToList("OID", oid);
	}

	public void setWord(String word) {
		if (!word.isBlank())
			setObjectToList("WORD", word);
	}

	public void setMeaning(String meaning) {
		if (!meaning.isBlank())
			setObjectToList("MEANING", meaning);
	}

	public void setMeanings(String[] meanings) {
		setObjectToList("MEANINGS", meanings);
	}

	public int getWoid() {
		int num = Integer.parseInt(getObjectFromList("WOID").toString());

		if (num > -1)
			return num;

		return -1;
	}

	public void setWoid(Integer woid) {
		if (woid > -1)
			setObjectToList("WOID", woid);
	}

	public String getArticle() {
		String[] arr = getNoun();
		return arr[0];
	}

	public void setArticle(String article) {
		setObjectToList("ARTICLE", article);
	}

	public String[] getNoun() {
		if (!(getObjectFromList("NOUN").toString().isBlank())) {
			return (getObjectFromList("NOUN").toString()).split(" ");
		}
		return null;
	}

	public void setNoun(String[] noun) {
		if (noun.length > 0) {
			setObjectToList("NOUN", noun);
		}
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

	public String getGenus() {
		return genus;
	}

	public void setWoid(int woid) {
		setObjectToList("WOID", woid);
	}

}
