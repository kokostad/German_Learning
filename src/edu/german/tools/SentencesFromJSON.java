package edu.german.tools;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SentencesFromJSON {
	private BufferedReader br;
	private List<String[]> list;
	private Optional<String> optData;

	public SentencesFromJSON(BufferedReader br) {
		this.br = br;
		list = new LinkedList<String[]>();
	}

	public SentencesFromJSON(Optional<String> optData) {
		this.optData = optData;
	}

	public List<String[]> arrayListFromJSON() {
		return new JSONHandler(optData).arrayListFromJSON();
	}

	public boolean checkData() {
		return optData.isEmpty();
	}

}
