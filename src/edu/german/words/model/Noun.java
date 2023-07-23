package edu.german.words.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.german.sql.NounQueryContractor;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.tools.MyProperties;

public class Noun extends Word implements WordModel {
	private static final String genus = "das Substantiv";
	private int oid;
	private String[] noun;
	private String word;
	private String meaning;
	private String article;
	private String mainPart;
	private String wordPlural;
	private String meanigPlural;
	private List<Properties> propertiesList = new LinkedList<>();

	public Noun() {
	}

	public Noun(int oid, String wordSingular, String meanigSingular, String wordPlural, String meanigPlural,
			List<Properties> propertiesList) {
		super(wordSingular, meanigSingular, genus);

		if (oid <= 0)
			setOid(wordSingular, meanigSingular);
		else
			this.oid = oid;

		if (wordSingular != null)
			this.word = wordSingular;
		
		if (wordPlural != null)
			this.wordPlural = wordPlural;
		else {
			// TODO check if it exist in the database if exist get from database
		}

		if (meanigPlural != null)
			this.meanigPlural = meanigPlural;
		else {
			// TODO check if it exist in the database if exist get from database
		}

		if (propertiesList != null)
			this.propertiesList = propertiesList;
		else {
			// TODO check if it exist in the database if exist get from database
			// NOTICE if properties list is empty check it exist in database
		}

		setNoun(wordSingular);
		setMeaning(meanigSingular);
		setArticle(wordSingular);
		setMainPart(wordSingular);
		setWordPlural(wordPlural);
		setMeanigPlural(meanigPlural);
		setPropertiesList(propertiesList);
	}

	@Override
	public String getGenus() {
		return genus;
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

	@Override
	public void setWord(String wordSingular) {
		this.word = wordSingular;
	}

	@Override
	public String getWord() {
		return word;
	}

	public String[] getNoun() {
		return noun;
	}

	@Override
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	@Override
	public String getMeaning() {
		return meaning;
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

	public String getMeaningPlural() {
		return meanigPlural;
	}

	public void setMeanigPlural(String meanigPlural) {
		this.meanigPlural = meanigPlural;
	}

	@Override
	public int getOid() {
		return oid;
	}

	public String getMainPart() {
		return mainPart;
	}

	public void setMainPart(String wordSingular) {
		String[] noun = wordSingular.split(" ");
		this.mainPart = noun[1];
	}

	@Override
	public void setOid(int oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return "Noun [ genus: " + genus + ", oid=" + oid + ", noun=" + word + ", meaning: " + meaning + ", article="
				+ article + ", word plural=" + wordPlural + ", meaning plural=" + meanigPlural + ", noun tema: "
				+ mainPart + "]";
	}

	public List<Properties> getPropertiesList(int oid) {
		this.oid = oid;
		String sql = new SqlQuery().getSql("get_noun_property");
		String[] modus = new MyProperties("word.properties").getValuesArray("NOUN_PARAM");

		Properties prop = new NounQueryContractor().getProperties(sql, oid);
		if (prop != null)
			propertiesList.add(prop);

		if (!propertiesList.isEmpty())
			return propertiesList;

		return null;
	}

	// TODO Need to add Builder
	public static class Builder extends Noun {
		private int oid;
		private String[] noun;
		private String word;
		private String meaning;
		private String wordPlural;
		private String meanigPlural;
		private List<Properties> propertiesList;

		public Builder() {
		}

		public Builder withOid(int oid) {
			this.oid = oid;
			return this;
		}

		public Builder withWord(String word) {
			this.word = word;
			return this;
		}

		public Builder withMeaning(String meaning) {
			this.meaning = meaning;
			return this;
		}

		public Builder withWordPlural(String wordPlural) {
			this.wordPlural = wordPlural;
			return this;
		}

		public Builder withMeanigPlural(String meanigPlural) {
			this.meanigPlural = meanigPlural;
			return this;
		}

		public Builder withPropertiesList(List<Properties> propertiesList) {
			this.propertiesList = propertiesList;
			return this;
		}

		public Noun build() {
			return new Noun(oid, word, meaning, wordPlural, meanigPlural, propertiesList);
		}
	}

	public int getOid(String word, String meanig) {
		String sql = new SqlQuery().getSql("get_noun_oid");
		oid = new QueryContractor().getId(sql, word, meanig);
		return oid;
	}

	public Noun prepareById(int id) {
		String sql = new SqlQuery().getSql("get_noun_by_id");
		return new NounQueryContractor().getById(sql, id);
	}

	public int getOid(String word) {
		String sql = new SqlQuery().getSql("get_noun_id_by_word");
		oid = new QueryContractor().getId(sql, word);
		return oid;
	}
}