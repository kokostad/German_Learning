package edu.german.words;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.german.sql.VerbQueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.tools.MyProperties;
import edu.german.words.model.Word;

public class Verb extends Word {
	private int woid;
	private int oid;
	private String word;
	private String meaning;
	private String irregular;
	private String separable;
	private List<Properties> propertiesList;

	public Verb() {
	}

	public Verb(int woid, int oid, String word, String meaning, String irregular, String separable,
			List<Properties> propertiesList) {
		this.woid = woid;
		this.oid = oid;
		this.word = word;
		this.meaning = meaning;
		this.irregular = irregular;
		this.separable = separable;
		this.propertiesList = propertiesList;
	}

	@Override
	public int getOid() {
		if (oid <= 0)
			prepereOid();

		return oid;
	}

	@Override
	public void setOid(int oid) {
		this.oid = oid;
	}

	private void prepereOid() {
		int id = new VerbQueryContractor().getVoid(new SqlQuery().getSql("get_verb_oid"), word, irregular, separable);

		if (id < 0) {
			id = new VerbQueryContractor().getVoid(new SqlQuery().getSql("get_simple_verb_oid"), word);
			new VerbQueryContractor().updateVerb(new SqlQuery().getSql("update_simple_verb_with_oid"), id, word,
					irregular, separable);
		}

		setOid(id);
	}

	private void prepereMeaning() {
		String query = new SqlQuery().getSql("get_verb_meaning");
		setMeaning(new VerbQueryContractor().getVerbMeaning(query, word, irregular, separable));
	}

	@Override
	public String getWord() {
		return word;
	}

	@Override
	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String getMeaning() {
		if (meaning == null && word != null)
			prepereMeaning();

		return meaning;
	}


	@Override
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

	@Override
	public List<Properties> getPropertyList() {
		if (propertiesList == null)
			preparePropertiesList(woid, oid, word, meaning, irregular, separable);

		return propertiesList;
	}

	@Override
	public List<Properties> getPropertyList(int oid) {
		String sql = new SqlQuery().getSql("get_verb_property");
		String[] tenses = new MyProperties("word.properties").getValuesArray("VERB_TENS");
		String[] modus = new MyProperties("word.properties").getValuesArray("VERB_MODUS");

		for (String mod : modus)
			for (String tens : tenses) {
				Properties prop = new VerbQueryContractor().getProperties(sql, getOid(), tens, mod);
				if (prop != null)
					propertiesList.add(prop);
			}

		if (!propertiesList.isEmpty())
			return propertiesList;

		return null;
	}

	@Override
	public void setPropertyList(List<Properties> propertiesList) {
		this.propertiesList = propertiesList;
	}
	
	public void preparePropertiesList(int woid, int oid, String word, String meaning, String irregular,
			String separable) {
		List<Properties> list = new LinkedList<>();
		String sql = new SqlQuery().getSql("get_verb_property");
		String[] tenses = new MyProperties("word.properties").getValuesArray("VERB_TENS");
		String[] modus = new MyProperties("word.properties").getValuesArray("VERB_MODUS");

		for (String mod : modus)
			for (String tens : tenses) {
				Properties prop = new VerbQueryContractor().getProperties(sql, oid, tens, mod);
				if (prop != null)
					list.add(prop);
			}

		if (!list.isEmpty())
			setPropertyList(list);
	}

	public static class Builder {
		private int woid;
		private int oid;
		private String word;
		private String meaning;
		private String irregular;
		private String separable;
		private List<Properties> propertiesList;

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

		public Builder withPropertiesList(List<Properties> propertiesList) {
			this.propertiesList = propertiesList;
			return this;
		}

		public Verb build() {
			return new Verb(woid, oid, word, meaning, irregular, separable, propertiesList);
		}

	}

}
