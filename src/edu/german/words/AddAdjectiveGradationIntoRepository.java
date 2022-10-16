package edu.german.words;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.german.sql.SqlQuery;
import edu.german.sql.QueryContractor;

public class AddAdjectiveGradationIntoRepository {

	public AddAdjectiveGradationIntoRepository() {
	}

	public void putIntoRepo(String word, HashMap<String, String> hm) {
		String sql = new SqlQuery().getSql("add_adjective_graduation");
		String[] array = { "EQUAL_DEGREE_GE", "EQUAL_DEGREE_PL", "COMPARATIVE_GE", "COMPARATIVE_PL",
				"HIGHEST_DEGREE_GE", "HIGHEST_DEGREE_PL" };
		String[] goal = new String[array.length];

		Map<String, String> map = new HashMap<String, String>();
		for (Entry<String, String> entry : hm.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (!key.equals("WORD") && !key.equals("MEANING"))
				map.put(key, value);
		}

		for (int i = 0; i < array.length; i++) {
			for (Entry<String, String> entry : map.entrySet()) {
				if (entry.getKey().equals(array[i]))
					goal[i] = entry.getValue();
			}
		}

		boolean state = new QueryContractor().addAdjectiveGraduation(sql, word, goal[0], goal[1], goal[2], goal[3],
				goal[4], goal[5]);

	}

}
