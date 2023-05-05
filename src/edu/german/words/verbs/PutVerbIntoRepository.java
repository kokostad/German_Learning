package edu.german.words.verbs;

import java.util.List;
import java.util.Properties;

import edu.german.sql.QueryBuilder;
import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.words.NewVerb;

public class PutVerbIntoRepository implements Runnable {
	private int oid;
	private List<NewVerb> verbList;

	// TODO add fields into database and make new query for "imperative" and "impersonal forms"
	public PutVerbIntoRepository(List<NewVerb> verbList) {
		this.verbList = verbList;
	}

	@Override
	public void run() {
		verbList.forEach(verb -> {
			List<Properties> propList = verb.getPropertiesList();
			oid = verb.getOid();
			String word = verb.getWord();
			String meaning = verb.getMeaning();
			String irregular = verb.getIrregular();
			String separable = verb.getSeparable();

			if (oid < 0) {
				// NOTICE need to write into log file
//				System.out.println("Need to add that verb into repository and then get oid from repository");
				String query = new SqlQuery().getSql("add_new_verb");
				new QueryContractor().addVerb(query, word, meaning, irregular, separable);
				oid = new QueryContractor().getVerbId(meaning, word, irregular, separable);
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
	 * Method checks if data exist in the database, if not, it saves it to the database.
	 */
	private void putVerbIntoRepository(int oid, String word, String irregular, String separable, Properties prop) {
		if (!exist(oid, prop)) {
			String query = new QueryBuilder().addVerbConjugation(oid, word, irregular, separable, prop);
			if (query != null)
				new QueryContractor().addVerbProperties(query);
		} else {
			// NOTICE need to write into log file
			System.out.println("Row exist");
		}
	}

	/**
	 * @param oid
	 * @param prop
	 * @return true if data exists in the database.
	 * Method checks if data exist in the database.
	 */
	private boolean exist(int oid, Properties prop) {
		String query = new QueryBuilder().existVerbConjugation(oid, prop.getProperty("TENS"),
				prop.getProperty("MODUS"));

		return new QueryContractor().existVerbConjugation(query);
	}

}
