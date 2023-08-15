package edu.german.sentences;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.german.sentences.model.ISentence;
import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

//  TODO need to improve this class
public class Sentence implements ISentence {
	private Integer oid = -1;
	private int woid = -1;
	private String sentence;
	private String meaning;
	private String type;
	private String category;
	private String tens;
	private String word;
	private List<Properties> propertyList;

	public Sentence() {
		this.propertyList = new LinkedList<>();
	}

	public Sentence(String sentence, String meaning, String type, String category, String tens, String word) {
		this.sentence = sentence;
		this.meaning = meaning;
		this.type = type;
		this.category = category;
		this.tens = tens;
		this.word = word;
		this.propertyList = new LinkedList<>();
	}

	public int getWoid() {
		if (woid < 0)
			setWoid(new QueryContractor().getWoidFromSentence(new SqlQuery().getSql("get_woid_from_sentence"),
					getOid()));

		return woid;
	}

	public void setWoid(int woid) {
		this.woid = woid;
	}

	@Override
	public Integer getOid() {
		if (oid < 0) {
			oid = new QueryContractor().getId(new SqlQuery().getSql("get_sentence_oid"), sentence);
		}

		return oid;
	}

	@Override
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	@Override
	public String getSentence() {
		return sentence;
	}

	@Override
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public String getMeaning() {
		return meaning;
	}

	@Override
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public void setCategory(String category) {
		this.category = category;
	}

	public String getTens() {
		return tens;
	}

	public void setTens(String tens) {
		this.tens = tens;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public void setPropertyList(List<Properties> propertyList) {
		this.propertyList = propertyList;
	}

	@Override
	public List<Properties> getPropertyList() {
		return propertyList;
	}

	public static class Builder {
		private String sentence;
		private String meaning;
		private String type;
		private String category;
		private String tens;
		private String word;

		public Builder withSentence(String sentence) {
			this.sentence = sentence;
			return this;
		}

		public Builder withMening(String meaning) {
			this.meaning = meaning;
			return this;
		}

		public Builder withType(String type) {
			this.type = type;
			return this;
		}

		public Builder withCategory(String category) {
			this.category = category;
			return this;
		}

		public Builder withTens(String tens) {
			this.tens = tens;
			return this;
		}

		public Builder withWord(String word) {
			this.word = word;
			return this;
		}

		public Builder() {
		}

		public Sentence build() {
			return new Sentence(sentence, meaning, type, category, tens, word);
		}
	}

	/*
	 * NOTICE have to check are this methods below needed? If they are needed, are
	 * they like this?
	 */
	public boolean isExist(String sentence, String category) {
		if (!sentence.isBlank() && category != null) {
			String sql = new SqlQuery().getSql("check_sentence");
			QueryContractor qc = new QueryContractor();
			int id = qc.getId(sql, sentence, category);
			if (id > -1)
				return true;
		}

		return false;
	}

	public Sentence getSentenceFromRepository(String sentenceStr, String meaningStr) {
		String sql = new SqlQuery().getSql("get_full_sentence");
		QueryContractor qc = new QueryContractor();
		Sentence sentence = qc.getSentence(sql, sentenceStr, meaningStr);
		return sentence;
	}

	/*
	 * NOTICE to describe the methods below, what they do exactly? maybe need to
	 * change the name
	 */
	public void addToRepository(int woid) {
		String sql = new QueryBuilder().addNewSentenceWithWoid(sentence, meaning, type, category, tens, word, woid);
		new QueryContractor().simpleQueryExecution(sql);
	}

	public void addToRepository(String sql, String sentence, String meaning, String genus) {
		if (sentence != null && (!sentence.isBlank() && !meaning.isBlank() && !genus.isBlank()))
			new QueryContractor().executeQuery(sql, sentence, meaning, genus);
	}

	public void addToRepository(String sql, String sentence, String meaning) {
		if (sentence != null && (!sentence.isBlank() && !meaning.isBlank()))
			new QueryContractor().executeQuery(sql, sentence, meaning);
	}

	@Override
	public String toString() {
		return "Sentence [oid=" + oid + ", woid=" + woid + ", sentence=" + sentence + ", meaning=" + meaning + ", type="
				+ type + ", category=" + category + ", tens=" + tens + ", word=" + word + "]";
	}

}
