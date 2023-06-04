package edu.german.words.verbs;

import java.util.List;
import java.util.Properties;

import edu.german.sql.QueryBuilder;
import edu.german.sql.SqlQuery;
import edu.german.sql.VerbQueryContractor;
import edu.german.words.Verb;

public class PutVerbIntoRepository implements Runnable {
	private int oid;
	private List<Verb> verbList;

	public PutVerbIntoRepository(List<Verb> verbList) {
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
				String query = new SqlQuery().getSql("add_new_verb");
				new VerbQueryContractor().addVerb(query, word, meaning, irregular, separable);
				oid = new VerbQueryContractor().getVerbId(meaning, word, irregular, separable);
			}

			if (!propList.isEmpty()) {
				propList.forEach(prop -> putVerbIntoRepository(oid, prop));
			}
		});
	}

	private void putVerbIntoRepository(int oid, Properties prop) {
		if (prop.contains("UNPERSÖNLICHE FORMEN")) {
			String query = new QueryBuilder().addUnpersonalForms(oid, prop);
			new VerbQueryContractor().addVerbProperties(query);
		} else {
			String query = new QueryBuilder().addVerbConjugation(oid, prop);
			new VerbQueryContractor().addVerbProperties(query);
		}
	}

}
