package edu.german.words.model;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface IWord {

	String getGenus();
	
	List<Map<String, String>> getType(String type);

	int getWoid(String word, String genus);

	int getOid();

	int hashCode(int oid, String word);

	String getMainWord();

	String getWord();

	String getMeaning();

	String[] getMeanings();

	Properties getProperites();

	List<Properties> getPropertyList();

	List<Properties> getPropertyList(int oid);

	List<Word> getAllWords();

	void setGenus(String genus);

	void setWoid(int woid);

	void setOid(int oid);

	void setMainWord(String word);

	void setMeaning(String meaning);

	void setMeanings(String[] meanings);

	void setWord(String word);

	void setProperites(Properties properties);

	void setPropertyList(List<Properties> propertiesList);

	boolean isExist(String word, String genus);
	
	int findId(String word, String genus);

	void putIntoRepository(String word, String meaning, String genus);
	
//	void putIlistation(int oid, String word, String meaning, String genus) {};
	
//	getIlustation(){int oid, String word, String genus};

}
