package edu.german.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WriteRulesToRepository {

	public WriteRulesToRepository() {
	}

	public void addToRepo(HashMap<String, String> var) {
		System.out.println("First method");
		var.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});

		System.out.println("Second method");
		for (Map.Entry<String, String> entry : var.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		System.out.println(Arrays.asList(var)); // method 3
		System.out.println(Collections.singletonList(var)); // method 4
	}

}
