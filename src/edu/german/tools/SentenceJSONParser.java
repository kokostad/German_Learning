package edu.german.tools;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class SentenceJSONParser {
	private JSONObject json;

	@SuppressWarnings("unchecked")
	public SentenceJSONParser(String line) {
		String[] array = line.split(";");
		json = new JSONObject();

		for (int i = 0; i < array.length; i++) {
			Map<String, String> map = new LinkedHashMap<>();
			if (i == 0)
				map.put("SENTENCE", (array[i].toString()).replaceAll("\t", ""));
			if (i == 1)
				map.put("MEANING", (array[i].toString()).replaceAll("\t", ""));
			if (i == 2)
				map.put("GENUS", array[i].toString());
			if (i == 3)
				map.put("CATEGORY", array[i].toString());
			if (i == 4)
				map.put("TENS", array[i].toString());
			if (i == 5)
				map.put("WORD", (array[i].toString()).replaceAll("\t", ""));

			json.putAll(map);
		}
	}

	public JSONObject getJSONItem() {
		return json;
	}

}
