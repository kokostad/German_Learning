package edu.german.words.model;

import java.util.Arrays;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class Noun extends Word {
	private static final String genus = "das Substantiv";
	private int oid;
	private String[] noun;
	private String article;
	private String mainPart;
	private String wordPlural;
	private String meanigPlural;

	public Noun() {
	}

	public Noun(String wordSingular, String meanigSingular, String wordPlural, String meanigPlural) {
		super(wordSingular, meanigSingular, genus);
		this.wordPlural = wordPlural;
		this.meanigPlural = meanigPlural;
		setOid(wordSingular, meanigSingular);
		setNoun(wordSingular);
		setArticle(wordSingular);
		setMainPart(wordSingular);
	}

	private void setOid(String word, String meanig) {
		String sql = new SqlQuery().getSql("get_noun_oid");
		oid = new QueryContractor().getId(sql, word, meanig);
	}

	public String getArticle() {
		return article;
	}

	public void setNoun(String word) {
		this.noun = word.split(" ");
	}

	public String[] getNoun() {
		return noun;
	}

	public void setArticle(String word) {
		String[] noun = word.split(" ");
		this.article = noun[0];
	}

	public String getWordPlural() {
		return wordPlural;
	}

	public void setWordPlural(String wordPlural) {
		this.wordPlural = wordPlural;
	}

	public String getMeanigPlural() {
		return meanigPlural;
	}

	public void setMeanigPlural(String meanigPlural) {
		this.meanigPlural = meanigPlural;
	}

	public int getOid() {
		return oid;
	}

	public String getMainPart() {
		return mainPart;
	}

	public void setMainPart(String mainPart) {
		String[] noun = word.split(" ");
		this.mainPart = noun[1];
	}

	@Override
	public String toString() {
		return "Noun [ genus: " + genus +  ", oid=" + oid + ", noun=" + word + ", article=" + article + ", word plural="
				+ wordPlural + ", meaning plural=" + meanigPlural + ", noun tema: " + mainPart + "]";
	}
}