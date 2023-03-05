package edu.german.tools;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class WordJSONParser {
	private JSONObject json;

	@SuppressWarnings("unchecked")
	public WordJSONParser(String line) {
		String[] array = line.split(";");
		json = new JSONObject();

		for (int i = 0; i < array.length; i++) {
			Map<String, Object> map = new LinkedHashMap<>();
			if (i == 0)
				map.put("WORD", array[i].toString());
			if (i == 1)
				map.put("MEANING", array[i].toString());
			if (i == 2)
				map.put("GENUS", array[i].toString());

			json.putAll(map);
		}
	}

	public JSONObject getJSONItem() {
		return json;
	}

}
