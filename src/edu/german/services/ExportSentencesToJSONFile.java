package edu.german.services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONObject;

import edu.german.tools.SentenceJSONParser;

public class ExportSentencesToJSONFile implements Runnable {
	private List<String> toExport;
	private String filePath;

	public ExportSentencesToJSONFile(List<String> toExport, String filePath) {
		this.toExport = toExport;
		this.filePath = filePath;
		deleteIfFileExist();
	}

	@Override
	public void run() {
		putIntoJSONFile();
	}

	private void putIntoJSONFile() {
		toExport.forEach((line) -> putLineIntoFile(line));
	}

	private void putLineIntoFile(String line) {
		JSONObject var = new SentenceJSONParser(line).getJSONItem();
		try {
			FileWriter writer = new FileWriter(filePath, true);
			String val = var.toString();
			writer.write(val);
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean deleteIfFileExist() {
		try {
			return Files.deleteIfExists(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
