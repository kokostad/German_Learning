package edu.german.tools;

import java.util.HashMap;

import edu.german.sql.GetSqlFromConfigFile;
import edu.german.sql.QueryContractor;

public class WriteRulesToRepository {

	public WriteRulesToRepository() {
	}

	public void addToRepo(HashMap<String, String> map) {

		map.entrySet().forEach(entry -> {
			String titles = "";
			String contents = "";
			String refers = "";

			if (entry.getKey().equals("TITLES"))
				titles = entry.getValue();

			if (entry.getKey().equals("CONTENTS"))
				contents = entry.getValue();

			if (entry.getKey().equals("REFERS_TO_1"))
				refers = entry.getValue();

			if (!titles.contains("") && !contents.contains("") && !refers.contains(""))
				prepareData(titles, contents, refers);

		});
	}

	private void prepareData(String titles, String contents, String refers) {
		String query = new GetSqlFromConfigFile("add_rules").getSql();
		if (new QueryContractor().executeQuery(query, titles, contents, refers))
			new ShowMessage("The rule has been added");
	}

}
