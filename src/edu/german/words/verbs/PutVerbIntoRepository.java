package edu.german.words.verbs;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.words.NewVerb;

public class PutVerbIntoRepository implements Runnable {
	private static final String genus = "das Verb";
	private List<NewVerb> verbList;

	// TODO add fields into database and make new query for "imperative" and
	// "impersonal forms"
	public PutVerbIntoRepository(List<NewVerb> verbList) {
		this.verbList = verbList;
	}

	@Override
	public void run() {
		verbList.forEach(verb -> {
			List<Properties> propList = verb.getPropertiesList();
			int oid = verb.getOid();
			int woid = verb.getWoid();
			String word = verb.getWord();
			String meaning = verb.getMeaning();
			String irregular = verb.getIrregular();
			String separable = verb.getSeparable();

			if (oid < 0) {
				System.out.println("Need to add that verb into repository and then get oid from repository");
				String query = new SqlQuery().getSql("add_new_verb");
				new QueryContractor().addVerb(query, word, meaning, irregular, separable);
			}

			/*
			 * NOTICE need to check if that row exist if yes check if they are differences,
			 * if yes change row if not do nothing, if row is empty write data
			 */

			if (!propList.isEmpty()) {
				propList.forEach(prop -> putVerbIntoRepository(oid, word, irregular, separable, prop));
			}
		});
	}

	
	/**
	 * @param oid
	 * @param word
	 * @param irregular
	 * @param separable
	 * @param prop
	 * Method checks if data exist in the database, if not, it saves it to the database
	 */
	private void putVerbIntoRepository(int oid, String word, String irregular, String separable, Properties prop) {
		if (!exist(oid, prop)) {
			String sql = new QueryBuilder().addVerbConjugation(oid, word, irregular, separable, prop);
			if (sql != null)
				new QueryContractor().addVerbProperties(sql);
		} else {
			System.out.println("Row exist");
		}
	}

	/**
	 * @param oid
	 * @param prop
	 * @return true if data exists in the database
	 * Method checks if data exist in the database.
	 */
	private boolean exist(int oid, Properties prop) {
		String tens = prop.getProperty("TENS");
		String modus = prop.getProperty("MODUS");

		String query = new QueryBuilder().existVerbConjugation(oid, tens, modus);
		boolean state = new QueryContractor().existVerbConjugation(query);

		return state;
	}

	private void prepareVerb(Properties prop) {
		Set<Object> keys = getAllKeys(prop);
		String modus = prop.getProperty("MODUS");
		String tens = prop.getProperty("TENS");
		System.out.println("Modus " + modus + " -> Tens " + tens);

		for (Object k : keys) {
			String value = null;
			String key = (String) k;

			if (!key.equals("MODUS") && !key.equals("TENS")) {
				value = getPropertyValue(key, prop);

				if (value != null && !value.isBlank())
					System.out.println(key + ": " + value);
			}
		}
	}

	public Set<Object> getAllKeys(Properties prop) {
		Set<Object> keys = prop.keySet();
		return keys;
	}

	public String getPropertyValue(String key, Properties prop) {
		return prop.getProperty(key);
	}

	private void prepareVerb(String modus, Map<String, String> map) {
		System.out.println("MODUS: " + modus);
		String tens = null;
		if (map.containsKey("TENS"))
			tens = map.get("TENS");

		if (map.containsKey("ICH")) {
			String ich = map.get("ICH");
			String ja = map.get("JA");
			String du = map.get("DU");
			String ty = map.get("TY");
			String erSieEs = map.get("ER_SIE_ES");
			String onOnaOno = map.get("ON_ONA_ONO");
			String wir = map.get("WIR");
			String my = map.get("MY");
			String ihr = map.get("IHR");
			String wy = map.get("WY");
			String sieSie = map.get("SIE_SIE");
			String oniPanstwo = map.get("ONI_PANSTWO");

			System.out.println("check_if_word_exists");
//			setWoid(checkWoid());

//			if (getWoid() == -1) // && mainWord != null)
			new QueryContractor().addNewWord(new SqlQuery().getSql("add_new_word"), wir, my, genus);

//			new QueryContractor().addNewWord(new SqlQuery().getSql("add_new_word"), mainWord, my, genus);

//			setOid(new QueryContractor().getVerbId(new SqlQuery().getSql("check_verbs_conjugation"), getWoid(), tens));

//			if (getOid() == -1)
//				new QueryContractor().addVerb(new SqlQuery().getSql("add_new_verbs_conjugation"), modus, getWoid(),
//						tens, ich, ja, du, ty, erSieEs, onOnaOno, wir, my, ihr, wy, sieSie, oniPanstwo);
//		}

			if (modus.contains("UNPERSÖNLICHE FORMEN")) {
//			PRESENT_INFINITIVE=lernen, TENS=UNPERSÖNLICHE FORMEN, PARTICIPLE_I=lernend, IMIESLOW_II=uczono, INFINITIVE_PERFECT=gelernt haben, PARTICIPLE_II=gelernt, CZAS_DOKONANY=uczono, IMIESLOW_I=uczono, CZAS_TERAZNIEJSZ=uczyć
//		if (map.containsKey("PRESENT_INFINITIVE")) {
				String presentInfinitiveGe = map.get("PRESENT_INFINITIVE");
				// TODO here is the difficulty
				String presentInfinitivePl = map.get("CZAS_TERAZNIEJSZ");
				String infinitivePerfectGe = map.get("INFINITIVE_PERFECT");
				// NOTICE where is "CZAS_DOKONANY" ? -- jest brany zły element!
				String infinitivePerfectPl = map.get("PRESENT_INFINITIVE");
				String participleIGe = map.get("PARTICIPLE_I");
				String participleIPl = map.get("IMIESLOW_I");
				String participleIIGe = map.get("PARTICIPLE_II");
				String participleIIPl = map.get("IMIESLOW_II");

//			if (getWoid() > -1) {
				System.out.println("check_verbs_impersonal");
				String sql = new SqlQuery().getSql("check_verbs_impersonal");
//				int vid = new QueryContractor().getId(sql, null, getWoid(), "UNPERSÖNLICHE FORMEN",
//						"UNPERSÖNLICHE FORMEN");

//				System.out.println(vid);

//				if (vid == -1) {
//					System.out.println("add verb");
//					new QueryContractor().addVerb(sql, getWoid(), "UNPERSÖNLICHE FORMEN", "UNPERSÖNLICHE FORMEN",
//							presentInfinitiveGe, presentInfinitivePl, infinitivePerfectGe, infinitivePerfectPl,
//							participleIGe, participleIPl, participleIIGe, participleIIPl);
//				}
			}
		}
	}

	private int checkWoid() {
		return new QueryContractor().getId(new SqlQuery().getSql("check_word"), null, "das Verb");
	}

}
