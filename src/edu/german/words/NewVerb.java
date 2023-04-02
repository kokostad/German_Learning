package edu.german.words;

import java.util.Properties;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;

public class NewVerb {
	private static final String genus = "das Verb";
	private int woid;
	private int oid;
	private String word;
	private String meaning;
	private String irregular;
	private String separable;
	private Properties properties;

	public NewVerb() {
	}

	public NewVerb(int woid, int oid, String word, String meaning, String irregular, String separable,
			Properties properties) {
		this.woid = woid;
		this.oid = oid;
		this.word = word;
		this.meaning = meaning;
		this.irregular = irregular;
		this.separable = separable;
		this.properties = properties;
	}

	public int getWoid() {
		return woid;
	}

	public void setWoid(int woid) {
		this.woid = woid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getIrregular() {
		return irregular;
	}

	public void setIrregular(String irregular) {
		this.irregular = irregular;
	}

	public String getSeparable() {
		return separable;
	}

	public void setSeparable(String separable) {
		this.separable = separable;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void prepareProperties(int woid, int oid, String word, String meaning, String irregular, String separable) {
		String sql = new SqlQuery().getSql("get_full_verb");
		setProperties(new QueryContractor().getVerbProperties(sql, oid));
	}

	public NewVerb prepareVerbFromRepository(String word, String irregular, String separable) {
		properties = new Properties();
		String query = new SqlQuery().getSql("check_verb_in_main_tab");
		boolean exist = new QueryContractor().executeQuery(query, word, genus);

		if (exist) {
			query = new SqlQuery().getSql("get_woid_from_main_tab");
			setWoid(new QueryContractor().getWoid(query, word, "das Verb"));
			query = new SqlQuery().getSql("get_verb_oid");
			setOid(new QueryContractor().getVerbId(query, word, irregular, separable));
			query = new SqlQuery().getSql("get_verb_meaning");
			setMeaning(new QueryContractor().getVerbMeaning(query, word, irregular, separable));
		}

		prepareProperties(woid, getOid(), word, meaning, irregular, separable);

		return new NewVerb(woid, oid, word, meaning, irregular, separable, getProperties());
	}

	public static class Builder {
		private int woid;
		private int oid;
		private String word;
		private String meaning;
		private String irregular;
		private String separable;
		private Properties properties;

		public Builder() {
		}

		public Builder withWoid(int woid) {
			this.woid = woid;
			return this;
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

		public Builder withIrregular(String irregular) {
			this.irregular = irregular;
			return this;
		}

		public Builder withSeparable(String separable) {
			this.separable = separable;
			return this;
		}

		public Builder withProperties(Properties properties) {
			this.properties = properties;
			return this;
		}

		public NewVerb build() {
			return new NewVerb(woid, oid, word, meaning, irregular, separable, properties);
		}

	}

	public void showVerb() {
		System.out.println(word + " " + meaning + " " + oid + " "  + oid);
		System.out.println(properties.toString());
	}
}
