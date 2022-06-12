package edu.german.words.model;

public interface WordModel {

	Integer getId();

	String getMainWord();

	String getMeaning();

	String[] getMeanings();

	void setId(Integer s);

	void setMainWord(String word);

	void setMeaning(String meaning);

	void setMeanings(String[] meanings);
}
