package edu.german.io;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import edu.german.sentences.Sentence;
import edu.german.tools.MyProperties;

public class SentenceFromJSON {
//	private String[] headers;

	public SentenceFromJSON(String data, String fileType, boolean order) {
		// TODO Auto-generated constructor stub
		String[] headers = new MyProperties("table_headers.properties").getValuesArray("SENTENCES");
		try {
			Object obj = new JSONParser().parse(data);
			JSONObject jObj = (JSONObject) obj;
//			System.out.println(jObj.get("SENTENCES"));
			JSONArray arr = (JSONArray) jObj.get("SENTENCES");

			for (int i = 0; i < arr.size(); i++) {
				JSONObject o = (JSONObject) arr.get(i);
				for (int k = 0; k < headers.length; k++) {
					if (o.get(headers[k].toUpperCase()) != null) {
						System.out.print(o.get(headers[k].toUpperCase()));
					}
				}
				System.out.println();
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public List<Sentence> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
