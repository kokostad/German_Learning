package edu.german.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONObject;

import edu.german.tools.WordJSONParser;

/**
 * ExportWordsToJSONFile.java
 * 
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 *
 * The class saves data to JSON files
 */
public class ExportWordsToJSONFile implements Runnable {
	private List<String> toExport;
	private String path;

	public ExportWordsToJSONFile(List<String> toExport, String path) {
		this.toExport = toExport;
		this.path = path;
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
		JSONObject var = new WordJSONParser(line).getJSONItem();
		try {
			FileWriter writer = new FileWriter(path, true);
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
			return Files.deleteIfExists(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
