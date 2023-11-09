package edu.german.words.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.tools.JSONHandler;

public class Word implements IWord {
	private int woid;
	private int oid;
	private String word;
	private String meaning;
	private String[] meanings;
	private String genus;
	private Properties properties;
	private List<Properties> propertyList;
	private Optional<String> optData;

	public Word() {
		this.setProperties(new Properties());
		this.propertyList = new LinkedList<>();
	}

	public Word(String word, String meaning, String genus) {
		setProperties(new Properties());
		propertyList = new LinkedList<>();
		this.word = word;
		this.meaning = meaning;
		this.genus = genus;

		if (!isExist(word, genus) && (meaning != null))
			new QueryContractor().addNewWord(new SqlQuery().getSql("add_new_word"), word, meaning, genus);

		setWoid(new QueryContractor().getId(new SqlQuery().getSql("get_word_woid"), word, genus));
	}

	public Word(Optional<String> optData) {
		this.optData = optData;

	}

	public boolean checkData() {
		return optData.isEmpty();
	}

	public List<String> getListFromData() {
		List<String> list = new LinkedList<>();
		for (String line : optData.get().split("\\n")) {
			list.add(line);
		}
		return list;
	}

	public List<String[]> arrayList(List<String> listToExecute) {
		List<String[]> list = new LinkedList<>();
		listToExecute.forEach(s -> {
			String[] arr = s.split(";");
//			int id = findId(arr[0], arr[1], arr[2]);
//			if (id < 0) {
			list.add(arr);
//			}
		});
		return list;
	}

	public int getOid(String w, String m) {
		return findId(w, m);
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

	public boolean isExist(String word, String meaninig, String genus) {
		// TODO Improve this method
//		String query = new SqlQuery().getSql("check_word");
		String sql = new QueryBuilder().getWordId(word, meaninig, genus);

		System.out.println(sql);

//		int var = new QueryContractor().getId(sql, word, genus);

		if ((new QueryContractor().getId(sql)) > -1)
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

	public void addToPropertyList(Properties properties) {
		propertyList.add(properties);
	}

	@Override
	public List<Properties> getPropertyList() {
		return propertyList;
	}

	@Override
	public void setPropertyList(List<Properties> propertiesList) {
		this.propertyList = propertiesList;
	}

	@Override
	public Properties getProperites() {
		return getProperties();
	}

	@Override
	public void setProperites(Properties properties) {
		this.setProperties(properties);
	}

	@Override
	public String getGenus() {
		return genus;
	}

	@Override
	public List<Properties> getPropertyList(int oid) {
		return propertyList;
	}

	@Override
	public int hashCode(int oid, String word) {
		return 0;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public int findId(String word, String genus) {
		String sql = new QueryBuilder().getWordId(word, meaning, genus);
		int oid = new QueryContractor().getId(sql);
		return oid;
	}

	public int findId(String word, String meaninig, String genus) {
		String sql = new QueryBuilder().getWordId(word, meaning, genus);
		int oid = new QueryContractor().getId(sql);
		return oid;
	}

	public List<String[]> arrayListFromJSON() {
		return new JSONHandler(optData).arrayListFromJSON();
	}

	public boolean checkOid(String string) {
		String sql = new QueryBuilder().getOid(string);
		int oid = new QueryContractor().getId(sql);
		if (oid > -1)
			return true;

		return false;
	}

	public List<Map> getMapList() {
		return new JSONHandler(optData).mapListFromJSON();
	}

	public List<Map> getMapListFromJSON() {
		List<Map> lm = new LinkedList<>();
//		String[] headers = new MyProperties("src/edu/german/words/cfg/", "headers.cfg").getValuesArray("ALL_HEADERS");

		optData.stream().forEach(var -> {
			String[] arr = var.split("\\n");
			for (int i = 0; i < arr.length; i++) {
				String jsonString = arr[i].toString();
				jsonString.lines().forEach(line -> {
					if (!line.contains("WORDS") && !line.contains("]}")) {
						JSONObject jsonObject = new JSONObject(line);
						lm.add(toMap(jsonObject));
					}
				});
			}
		});

		return lm;
	}

	public static Map<String, String> toMap(JSONObject jsonobj) throws JSONException {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> keys = jsonobj.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = (String) jsonobj.get(key);
			map.put(key, value);
		}
		return map;
	}
}
