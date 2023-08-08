package edu.german.words;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import edu.german.sql.NounQueryContractor;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.tools.MyProperties;
import edu.german.words.model.IWord;
import edu.german.words.model.Word;

/**
 * Noun.java
 * 
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 *
 */
public class Noun extends Word implements IWord {
	private final static String genus = "das Substantiv";
	private int oid;
	private String noun;
	private String meaning;
	private String article;
	private String nounDomain;
	private String wordPlural;
	private String meanigPlural;
	private List<Properties> propertyList = new LinkedList<>();
	private String[] meanings;

	public Noun() {
	}

	public Noun(int oid, String word, String meanig, String wordPlural, String meanigPlural,
			List<Properties> propertiesList) {
		super(word, meanig, genus);

		if (oid <= 0)
			setOid(word, meanig);
		else
			this.oid = oid;

		if (word != null)
			this.noun = word;

		if (wordPlural != null)
			this.wordPlural = wordPlural;

		if (meanigPlural != null)
			this.meanigPlural = meanigPlural;

		if (propertiesList != null)
			this.propertyList = propertiesList;

		setNoun(word);
		setArticle(word);
		setNounDomain(word);
		setMeaning(meanig);
		setWordPlural(wordPlural);
		setMeanigPlural(meanigPlural);
		setPropertyList(propertiesList);
	}

	@Override
	public String getGenus() {
		return genus;
	}

	private void setOid(String word, String meanig) {
		this.oid = new QueryContractor().getId(new SqlQuery().getSql("get_noun_oid"), word, meanig);
	}

	public String getArticle() {
		return article;
	}

	public void setNoun(String word) {
		this.noun = word;
	}

	@Override
	public void setWord(String word) {
		this.noun = word;
	}

	@Override
	public String getWord() {
		return noun;
	}

	public String getNoun() {
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
		String[] arr = word.split(" ");
		this.article = arr[0];
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

	public String getNounDomain() {
		return nounDomain;
	}

	public void setNounDomain(String word) {
		String[] arr = word.split(" ");
		this.nounDomain = arr[1];
	}

	@Override
	public void setOid(int oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return "Noun [ genus: " + genus + ", oid=" + oid + ", noun=" + noun + ", meaning: " + meaning + ", article="
				+ article + ", word plural=" + wordPlural + ", meaning plural=" + meanigPlural + ", noun tema: "
				+ nounDomain + "]";
	}

	public String[] getModus() {
		String[] modus = new MyProperties("word.properties").getValuesArray("NOUN_PARAM");
		return modus;
	}

	public List<Properties> getPropertyList(int oid) {
		this.oid = oid;
		String sql = new SqlQuery().getSql("get_noun_property");

		Properties prop = new NounQueryContractor().getProperties(sql, oid);
		if (prop != null)
			propertyList.add(prop);

		if (!propertyList.isEmpty())
			return propertyList;

		return null;
	}

	public int getOid(String word) {
		String sql = new SqlQuery().getSql("get_noun_id_by_word");
		return new QueryContractor().getId(sql, word);
	}

	public int getOid(String word, String meanig) {
		String sql = new SqlQuery().getSql("get_noun_oid");
		return new QueryContractor().getId(sql, word, meanig);
	}

	public Noun nounPreparedById(int id) {
		String sql = new SqlQuery().getSql("get_noun_by_id");
		return new NounQueryContractor().getById(sql, id);
	}

	public static class Builder extends Noun {
		private int oid;
		private String noun;
		private String meaning;
		private String wordPlural;
		private String meanigPlural;
		private List<Properties> propertyList;

		public Builder() {
		}

		public Builder withOid(int oid) {
			this.oid = oid;
			return this;
		}

		public Builder withNoun(String word) {
			this.noun = word;
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

		public Builder withPropertyList(List<Properties> propertyList) {
			this.propertyList = propertyList;
			return this;
		}

		public Noun build() {
			return new Noun(oid, noun, meaning, wordPlural, meanigPlural, propertyList);
		}
	}

	@Override
	public int getWoid(String word, String genus) {
		return super.getWoid(word, genus);
	}

	@Override
	public int hashCode(int oid, String word) {
		return Objects.hash(oid, word);
	}

	@Override
	public String getMainWord() {
		return getNounDomain();
	}

	@Override
	public String[] getMeanings() {
		if (meanings == null)
			return getMeaning().split(" ");

		return meanings;
	}

	@Override
	public Properties getProperites() {
		return super.getProperites();
	}

	@Override
	public List<Properties> getPropertyList() {
		if (oid > 0)
			return super.getPropertyList(oid);

		return null;
	}

	@Override
	public List<Word> getAllWords() {
		// TODO nothing to do
		return null;
	}

	@Override
	public void setGenus(String genus) {
		// TODO nothing to do
	}

	@Override
	public void setWoid(int woid) {
		// TODO nothing to do
	}

	@Override
	public void setMainWord(String word) {
		this.nounDomain = word;
	}

	@Override
	public void setMeanings(String[] meanings) {
		this.meanings = meanings;
	}

	@Override
	public void setProperites(Properties properties) {
		this.setProperties(properties);
	}

	@Override
	public void setPropertyList(List<Properties> propertyList) {
		this.propertyList = propertyList;
	}

	@Override
	public boolean isExist(String word, String genus) {
		return super.isExist(word, genus);
	}

	@Override
	public void putIntoRepository(String word, String meaning, String genus) {
		super.putIntoRepository(word, meaning, genus);
	}

}