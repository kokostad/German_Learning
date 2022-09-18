package edu.german.words.verbs;

import java.util.List;
import java.util.Map;

import edu.german.sql.QueryContractor;
import edu.german.tools.GetQuery;

public class PutVerbIntoRepository implements Runnable {
	private List<Map<String, List<Map<String, String>>>> verbList;

	public PutVerbIntoRepository(List<Map<String, List<Map<String, String>>>> verbList) {
		this.verbList = verbList;
	}

	@Override
	public void run() {
		verbList.forEach(m -> {
			m.forEach((modus, list) -> {
				list.forEach(m2 -> {
					prepareVerb(modus, m2);
				});
			});
		});
	}

	private void prepareVerb(String modus, Map<String, String> map) {
		String tens = map.get("TENS");
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

		if (wir != null && !wir.equals("") && !wir.equals(null)) {
			// TODO need to add to main table if not exist
			String query = new GetQuery().getSql("check_verbs_conjugation");
			int id = new QueryContractor().getId(query, wir, tens);

			if (id == -1) {
				String sql = new GetQuery().getSql("add_verbs_conjugation");
				new QueryContractor().addVerb(sql, modus, tens, ich, ja, du, ty, erSieEs, onOnaOno, wir, my, ihr, wy,
						sieSie, oniPanstwo);
			}
		}
	}

}
