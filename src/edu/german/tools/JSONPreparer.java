package edu.german.tools;

import java.util.HashMap;

public class JSONPreparer {
	private boolean sentencesOrWords;
	private String order;
	private String exportType;
	private String wordGenus;
	
	public JSONPreparer() {
		
	}
	
	public HashMap<String, String> exportConfigParam() {
		String kind = "sentence";
		if (sentencesOrWords)
			kind = "word";
		order = "ge";
		order = "pl";

		HashMap<String, String> exportConfigMap = new HashMap<>();
		exportConfigMap.put("EXPORT_TYPE", exportType);
		exportConfigMap.put("GENUS", kind);
		exportConfigMap.put("ORDER", order);
		if (kind.equals("word"))
			exportConfigMap.put("WORD_GENUS", wordGenus);

		return exportConfigMap;
	}

}
