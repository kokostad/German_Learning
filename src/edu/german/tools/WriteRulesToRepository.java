package edu.german.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.german.sql.GetSqlFromConfigFile;
import edu.german.sql.QueryContractor;

public class WriteRulesToRepository {

	public WriteRulesToRepository() {
	}

	public void addToRepo(HashMap<String, String> map) {

		map.entrySet().forEach(entry -> {
//			System.out.println(entry.getKey() + " " + entry.getValue());
			String titles = "";
			String contents = "";
			String refers = "";

			if (entry.getKey().equals("TITLES"))
				titles = entry.getValue();

			if (entry.getKey().equals("CONTENTS"))
				contents = entry.getValue();

			if (entry.getKey().equals("REFERS_TO_1"))
				refers = entry.getValue();

			prepareData(titles, contents, refers);
		});

//		System.out.println("Second method");
//		for (Map.Entry<String, String> entry : var.entrySet()) {
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}
//
//		System.out.println(Arrays.asList(var)); // method 3
//		System.out.println(Collections.singletonList(var)); // method 4
	}

	private void prepareData(String titles, String contents, String refers) {
		String query = new GetSqlFromConfigFile("add_rules").getSql();
		new QueryContractor().executeQuery(query, titles, contents, refers);
	}

}
