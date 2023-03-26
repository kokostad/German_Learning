package edu.german.words.model;

import java.util.List;
import java.util.Properties;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.tools.OneEditField;
import edu.german.words.verbs.VerbRegular;

public class Verb extends Word {
	private final String genus = "das Verb";
	// TODO make Builder inner class
	private int woid;
	private int oid;
	private String word;
	private String meaning;
	private boolean irregular;
	private String separable;
	private Properties prop = new Properties();

	public Verb() {

	}

	public Verb(String verb) {
		String query = new SqlQuery().getSql("check_verb_in_main_tab");
		boolean exist = new QueryContractor().executeQuery(query);

		if (exist) {
			query = new SqlQuery().getSql("get_woid_from_main_tab");
			setWoid(new QueryContractor().getId(query, verb, "das Verb"));
			query = new SqlQuery().getSql("get_oid_from_main_tab");
			setOid(new QueryContractor().getId(query, verb, "das Verb"));
		}
	}

	public Verb(int woid, int oid, String word, String meaning, boolean irregular) {
		this.woid = woid;
		this.oid = oid;
		this.word = word;
		this.meaning = meaning;
		this.irregular = irregular;
	}

	/*
	 * NOTICE need to build verb from database if exists
	 */
	public Verb(String word, String regular, String separable) {
		this.word = word;
		String query = new QueryBuilder().getSimpleVerb(word, regular, separable);
		
		System.out.println(query);
		
		Verb verb = new QueryContractor().getSimpleVerb(query);

		query = new SqlQuery().getSql("get_woid_from_main_tab");
		System.out.println(query);
		setWoid(new QueryContractor().getWoidByWord(query, word, "das Verb"));
		query = new SqlQuery().getSql("check_verb");
		System.out.println(query);
		setOid(verb.getOid());
		setMeaning(verb.getMeaning());
	}

	public Properties getProp() {
		return prop;
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
//		else if (word != null) {
//			String sql = new SqlQuery().getSql("check_verb");
//			return new QueryContractor().getVerb(sql, word, "woid");
//		} else
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
		if (!word.isBlank())
			return word;

		return null;
	}

	@Override
	public String getWord() {
		if (!word.isBlank())
			return word;

		return null;
	}

	@Override
	public String getMeaning() {
		return meaning;
	}

	@Override
	public String[] getMeanings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWoid(int woid) {
		this.woid = woid;
		if(woid > 0)
			prop.setProperty("WOID", String.valueOf(woid));
	}

	@Override
	public void setOid(int oid) {
		this.oid = oid;
		prop.setProperty("OID", String.valueOf(oid));
	}

	@Override
	public void setMainWord(String word) {
		this.word = word;
	}

	@Override
	public void setMeaning(String meaning) {
		this.meaning = meaning;
//		prop.setProperty("MEANING", meaning);
	}

	@Override
	public void setMeanings(String[] meanings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWord(String word) {
		this.word = word;
		prop.setProperty("WORD", word);
	}

	/*
	 * NOTICE these two queries below should be different
	 */
	public boolean isExist(String word, String regular, String trennbar) {
		return new QueryContractor().executeQuery(new SqlQuery().getSql("check_verb"));
	}

	@Override
	public boolean isExist(String word, String trennbar) {
		return new QueryContractor().executeQuery(new SqlQuery().getSql("check_verb"));
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

	/*
	 * NOTICE Prepare Verb from database
	 */
	public static class Builder {
		private int woid;
		private int oid;
		private String word;
		private String meaning;
		private boolean irregular;
		private String separable;

		public Builder() {
		}

		public Verb build() {
			return new Verb();
		}

	}

}
