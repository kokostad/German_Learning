package edu.german.tools;

import java.io.BufferedReader;
import java.io.Reader;
import java.nio.Buffer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

public class SentenceJSONParser {
	JSONObject json;
//	JSONArray arry = new JSONArray();
	Map<String, String> map;

	public SentenceJSONParser() {
		json = new JSONObject();
		map = new LinkedHashMap<String, String>();
		map.put("SENTENCE", "Das ist ein Satz.");
		map.put("MEANING", "To jest zdanie.");
//		if(map.size() > 2)
		map.put("GENUS", "das Satz");

		map.forEach((k, v) -> json.append(k, v));
	}

	// TODO change and improve this method
	public SentenceJSONParser(String text) {
		String[] array = text.split(";");
		json = new JSONObject();
		map = new LinkedHashMap<String, String>();
		for (int i = 0; i < array.length; i++) {
			if (i == 0)
				map.put("SENTENCE", array[i]);
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
