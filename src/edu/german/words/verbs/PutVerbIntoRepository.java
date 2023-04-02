package edu.german.words.verbs;

import java.util.List;
import java.util.Map;

import edu.german.sql.QueryContractor;
import edu.german.sql.SqlQuery;
import edu.german.words.NewVerb;

public class PutVerbIntoRepository implements Runnable {
	private static final String genus = "das Verb";
	private List<NewVerb> verbList;
	private int oid = -1;
	private int woid = -1;

	// TODO add fields into database and new query for "imperative" and "impersonal
	// forms"
	public PutVerbIntoRepository(List<NewVerb> verbList) {
		this.verbList = verbList;
	}

	@Override
	public void run() {
		verbList.forEach(verb -> {
			verb.showVerb();
//			m.forEach((modus, list) -> {
//				list.forEach(m2 -> {
//					prepareVerb(modus, m2);
//				});
//			});
		});
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
			setWoid(checkWoid());

			if (getWoid() == -1) // && mainWord != null)
				new QueryContractor().addNewWord(new SqlQuery().getSql("add_new_word"), wir, my, genus);

//			new QueryContractor().addNewWord(new SqlQuery().getSql("add_new_word"), mainWord, my, genus);

			setOid(new QueryContractor()
					.getVerbId(new SqlQuery().getSql("check_verbs_conjugation"), getWoid(), tens));

			if (getOid() == -1)
				new QueryContractor()
				.addVerb(new SqlQuery()
						.getSql("add_new_verbs_conjugation"), modus, getWoid(),
						tens, ich, ja, du, ty, erSieEs, onOnaOno, wir, my, ihr, wy, sieSie, oniPanstwo);
		}

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

			if (getWoid() > -1) {
				System.out.println("check_verbs_impersonal");
				String sql = new SqlQuery().getSql("check_verbs_impersonal");
				int vid = new QueryContractor()
						.getId(sql, null, getWoid(), "UNPERSÖNLICHE FORMEN", "UNPERSÖNLICHE FORMEN");

				System.out.println(vid);

				if (vid == -1) {
					System.out.println("add verb");
					new QueryContractor().addVerb(sql, getWoid(), "UNPERSÖNLICHE FORMEN", "UNPERSÖNLICHE FORMEN",
							presentInfinitiveGe, presentInfinitivePl, infinitivePerfectGe, infinitivePerfectPl,
							participleIGe, participleIPl, participleIIGe, participleIIPl);
				}
			}
		}
	}

	private int getOid() {
		System.out.println(oid);
		return oid;
	}

	private void setOid(int oid) {
		this.oid = oid;
	}

	private int getWoid() {
		return woid;
	}

	private void setWoid(int woid) {
		this.woid = woid;
	}

	private int checkWoid() {
		return new QueryContractor().getId(new SqlQuery().getSql("check_word"), null, "das Verb");
	}
}
