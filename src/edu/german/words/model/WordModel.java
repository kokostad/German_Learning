package edu.german.words.model;

public interface WordModel {
	
	int getWoid();

	int getOid();

	String getMainWord();
	
	String getWord();

	String getMeaning();

	String[] getMeanings();

	void setWoid(int woid);
	
	void setOid(int oid);

	void setMainWord(String word);

	void setMeaning(String meaning);

	void setMeanings(String[] meanings);

	void setWord(String word);
}
