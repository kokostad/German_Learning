package edu.german.sentences.model;

import java.util.List;
import java.util.Properties;

public interface ISentence {

	void setOid(Integer oid);

	Integer getOid();

	void setSentence(String sentence);

	String getSentence();

	void setMeaning(String meaning);

	String getMeaning();

	void setCategory(String category);

	String getCategory();

	void setPropertyList(List<Properties> list);

	List<Properties> getPropertyList();

}
