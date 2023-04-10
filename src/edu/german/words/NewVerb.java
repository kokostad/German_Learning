package edu.german.words;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.tools.MyProperties;

public class NewVerb {
	private int woid;
	private int oid;
	private String word;
	private String meaning;
	private String irregular;
	private String separable;
	private List<Properties> propertiesList;

	public NewVerb() {
	}

	public NewVerb(int woid, int oid, String word, String meaning, String irregular, String separable,
			List<Properties> propertiesList) {
		this.woid = woid;
		this.oid = oid;
		this.word = word;
		this.meaning = meaning;
		this.irregular = irregular;
		this.separable = separable;
		this.propertiesList = propertiesList;
	}

	public int getWoid() {
		return woid;
	}

	public void setWoid(int woid) {
		this.woid = woid;
	}

	public int getOid() {
		if (oid <= 0)
			prepereOid();

		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	private void prepereOid() {
		String query = new SqlQuery().getSql("get_verb_oid");
//		System.out.println(query);
		setOid(new QueryContractor().getVoid(query, word, irregular, separable));
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

	public List<Properties> getPropertiesList() {
		if (propertiesList == null)
			preparePropertiesList(woid, oid, word, meaning, irregular, separable);

		return propertiesList;
	}

	public void setPropertiesList(List<Properties> propertiesList) {
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
				Properties prop = new QueryContractor().getVerbProperties(sql, oid, tens, mod);
				if (prop != null)
					list.add(prop);
			}

		if (!list.isEmpty())
			setPropertiesList(list);
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

		public NewVerb build() {
			return new NewVerb(woid, oid, word, meaning, irregular, separable, propertiesList);
		}

	}

	public void showVerb() {
		System.out.println(word + " " + meaning + " " + oid + " " + oid);
//		System.out.println(properties.toString());
	}
}
