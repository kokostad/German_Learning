package edu.german.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandler {
	private Optional<String> optData;

	public JSONHandler() {

	}

	public JSONHandler(Optional<String> optData) {
		this.optData = optData;
	}

	public List<Map> mapListFromJSON() {

		List<Map> lm = new LinkedList<>();
//		String[] headers = new MyProperties("src/edu/german/words/cfg/", "headers.cfg").getValuesArray("ALL_HEADERS");

		optData.stream().forEach(var -> {
			String[] arr = var.split("\\n");
			for (int i = 0; i < arr.length; i++) {
				String jsonString = arr[i].toString();
				jsonString.lines().forEach(line -> {
					if (!line.contains("WORDS") && !line.contains("]}")) {
						JSONObject jsonObject = new JSONObject(line);
						lm.add(toMap(jsonObject));
					}
				});
			}
		});

		return lm;
	}

	public static Map<String, String> toMap(JSONObject jsonobj) throws JSONException {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> keys = jsonobj.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = (String) jsonobj.get(key);
			map.put(key, value);
		}
		return map;
	}

	public List<String[]> arrayListFromJSON() {
		String[] headers = new MyProperties("src/edu/german/sentences/cfg/", "headers.cfg")
				.getValuesArray("SENTENCE_SHORT");
		List<String[]> list = new LinkedList<>();
		optData.stream().forEach(var -> {
			String[] arr = var.split("\\n");
			for (int i = 0; i < arr.length; i++) {
				String jsonString = arr[i].toString();
				jsonString.lines().forEach(line -> {
					if (!line.contains("SENTENCES") && !line.contains("]}")) {
						JSONObject jsonObject = new JSONObject(line);
						List<String> al = new ArrayList<>();
						for (String h : headers) {
							if (jsonObject.has(h.toUpperCase())) {
								String l = jsonObject.getString(h.toUpperCase());
								al.add(l);
							}
						}

						String[] ati = new String[al.size()];
						for (int j = 0; j < al.size(); j++) {
							ati[j] = al.get(j);
						}

						if (ati != null)
							list.add(ati);
					}
				});
			}
		});

		return list;
	}
}
