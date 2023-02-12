package edu.german.words.model;

import java.util.List;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class Verb extends Word {
	private final String genus = "das Verb";
	// TODO make Builder inner class
	private int woid;
	private int oid;
	private String word;
	private String meaning;
	private boolean irregular;

	public Verb() {
	}

	public Verb(int woid, int oid, String word, String meaning, boolean irregular) {
		this.woid = woid;
		this.oid = oid;
		this.word = word;
		this.meaning = meaning;
		this.irregular = irregular;
	}

	public String getGenus() {
		return genus;
	}

	public boolean isIrregular() {
		return irregular;
	}

	public void setIrregular(boolean irregular) {
		this.irregular = irregular;
	}

	@Override
	public int getWoid() {
		if (woid > 0)
			return woid;
		else if (word != null) {
			String sql = new SqlQuery().getSql("check_verb");
			return new QueryContractor().getVerb(sql, word, "woid");
		} else
			return -1;
	}

	@Override
	public int getOid() {
		if (oid > 0)
			return oid;
		else if (word != null) {
			String sql = new SqlQuery().getSql("check_verb");
			return new QueryContractor().getVerb(sql, word, "oid");
		} else
			return -1;
	}

	@Override
	public String getMainWord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMeaning() {
		// TODO Auto-generated method stub
		return meaning;
	}

	@Override
	public String[] getMeanings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWoid(int woid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOid(int oid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMainWord(String word) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMeaning(String meaning) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMeanings(String[] meanings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWord(String word) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isExist(String word, String genus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putIntoRepository(String word, String meaning, String genus) {
		// TODO Auto-generated method stub

	}

	public List<Verb> getAllVerbs() {
		String query = new SqlQuery().getSql("get_all_verbs");
		List<Verb> list = new QueryContractor().getVerbList(query);
		return list;
	}

}
