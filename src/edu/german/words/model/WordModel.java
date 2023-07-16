package edu.german.words.model;

import java.util.List;
import java.util.Properties;

public interface WordModel {

	String getGenus();

	int getWoid(String word, String genus);

	int getOid();

	String getMainWord();

	String getWord();

	String getMeaning();

	String[] getMeanings();

	Properties getProperites();

	List<Properties> getPropertiesList();
	
	List<Properties> getPropertiesList(int oid);

	List<Word> getAllWords();

	void setGenus(String genus);

	void setWoid(int woid);

	void setOid(int oid);

	void setMainWord(String word);

	void setMeaning(String meaning);

	void setMeanings(String[] meanings);

	void setWord(String word);

	void setProperites(Properties properties);

	void setPropertiesList(List<Properties> propertiesList);

	boolean isExist(String word, String genus);

	void putIntoRepository(String word, String meaning, String genus);

}
