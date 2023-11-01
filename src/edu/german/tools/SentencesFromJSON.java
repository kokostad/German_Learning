package edu.german.tools;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class SentencesFromJSON {
	private BufferedReader br;
	private List<String[]> list;
	private Optional<String> optData;
	private JSONParser parser = new JSONParser();

	public SentencesFromJSON(BufferedReader br) {
		this.br = br;
		list = new LinkedList<String[]>();
	}

	public SentencesFromJSON(Optional<String> optData) {
		this.optData = optData;
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

	public boolean checkData() {
		return optData.isEmpty();
	}

}
