package edu.german.tools;

import java.util.HashMap;

import edu.german.sql.SqlQuery;
import edu.german.sql.QueryContractor;

public class WriteRulesToRepository {
	private String title = null;
	private String contents = null;
	private String tips = null;

	public WriteRulesToRepository() {
	}

	public void addToRepo(HashMap<String, String> map) {
		map.entrySet().forEach(entry -> {
			if (entry.getKey().equals("TITLE"))
				title = entry.getValue();

			if (entry.getKey().equals("CONTENTS"))
				contents = entry.getValue();

			if (entry.getKey().equals("TIPS"))
				tips = entry.getValue();
		});

		prepareData(title, contents, tips);
	}

	private void prepareData(String title, String contents, String tips) {
		if (new QueryContractor().executeQuery(new SqlQuery().getSql("add_rules"), title, contents, tips))
			new ShowMessage("ADDED_SUCCESSFULLY");
	}

}
