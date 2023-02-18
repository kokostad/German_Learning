package edu.german.sentences;

import java.util.HashMap;
import java.util.Map;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.sql.SqlQueryBuilder;

public class Sentence {
	private int oid;
	private int woid;
	private String sentence;
	private String meaning;
	private String type;
	private String category;
	private String tens;
	private String word;
	private Map<String, Object> sentenceMap;

	public Sentence() {
		oid = -1;
		woid = -1;
		sentenceMap = new HashMap<String, Object>();
	}

	// sentence, meaning, type(genus), category(mode), tens, word, woid)
	public Sentence(String sentence, String meaning, String type, String category, String tens, String word) {
		oid = -1;
		woid = -1;
		this.sentence = sentence;
		this.meaning = meaning;
		this.type = type;
		this.category = category;
		this.tens = tens;
		this.word = word;
		sentenceMap = new HashMap<String, Object>();
	}

	public int getWoid() {
		if (woid < 0)
			setWoid(new QueryContractor().getWoidFromSentence(new SqlQuery().getSql("get_woid_from_sentence"),
					getOid()));

		return -1;
	}

	public void setWoid(int woid) {
		this.woid = woid;
		sentenceMap.put("WOID", woid);
	}

	public int getOid() {
		if (oid < 0) {
			oid = new QueryContractor().getId(new SqlQuery().getSql("get_sentence_oid"), sentence);
			sentenceMap.put("OID", oid);
		}

		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
		sentenceMap.put("OID", oid);
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
		sentenceMap.put("SENTENCE", sentence);
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
		sentenceMap.put("MEANING", meaning);
	}

	public Map<String, Object> getSentenceMap() {
		return sentenceMap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		sentenceMap.put("TYPE", type);
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		sentenceMap.put("CATEGORY", category);
		this.category = category;
	}

	public String getTens() {
		return tens;
	}

	public void setTens(String tens) {
		sentenceMap.put("TENS", tens);
		this.tens = tens;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		sentenceMap.put("WORD", word);
		this.word = word;
	}

	public void setSentenceMap(Map<String, Object> sentenceMap) {
		this.sentenceMap = sentenceMap;
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
	 * NOTICE have to check are this methods below needed?
	 * If they are needed, are they like this?
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

	// sentence, meaning, type(genus), category(mode), tens, word, woid)
	// sentence, meaning, type, category, tens
	public void addToRepository(int woid) {
		String sql = new SqlQueryBuilder().addNewSentenceWithWoid(sentence, meaning, type, category, tens, word, woid);
		new QueryContractor().simpleQueryExecution(sql);
	}

	public void addToRepository(String sql, String sentence, String meaning, String genus) {
		if (sentence != null && (!sentence.isBlank() && !meaning.isBlank() && !genus.isBlank()))
			new QueryContractor().executeQuery(sql, sentence, meaning, genus);
	}

}
