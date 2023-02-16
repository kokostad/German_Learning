package edu.german.tools;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

public class WordJSONParser {
	private JSONObject json;

	public WordJSONParser(String line) {
		String[] array = line.split(";");
		json = new JSONObject();
		Map<String, String> map = new LinkedHashMap<>();

		for (int i = 0; i < array.length; i++) {
			if (i == 0)
				map.put("WORD", array[i]);
			if (i == 1)
				map.put("MEANING", array[i]);
			if (i == 2)
				map.put("GENUS", array[i]);
		}
		map.forEach((k, v) -> json.append(k, v));
	}

	public JSONObject getJSONItem() {
		return json;
	}

}
