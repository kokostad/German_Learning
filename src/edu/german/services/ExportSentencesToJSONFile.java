package edu.german.services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.simple.JSONObject;

import edu.german.tools.SentenceJSONParser;

public class ExportSentencesToJSONFile implements Runnable {
	private String path;
	private StringBuilder sb;
	private int count;
	private String[] newExport;

	public ExportSentencesToJSONFile(List<String> toExport, String path) {
		this.path = path;
		sb = new StringBuilder();
		deleteIfFileExist();
		count = toExport.size();
		int i = 0;
		for (String s : toExport) {
			prepareJSONObject(i, s);
			i += 1;
		}

		newExport = (sb.toString()).split(System.lineSeparator());
	}

	@Override
	public void run() {
		putIntoJSONFile();
	}

	private void putIntoJSONFile() {
		for (String l : newExport)
			putLineIntoFile(l);
	}

	private void putLineIntoFile(String line) {
		try {
			FileWriter writer = new FileWriter(path, true);
			writer.write(line);
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

	private void prepareJSONObject(int i, String line) {
		JSONObject var = new SentenceJSONParser(line).getJSONItem();
		if (i == 0) {
			sb.append("{\"SENTENCES\":");
			sb.append("[");
			sb.append("\n");
			sb.append(var.toJSONString());
			sb.append(",");
			sb.append("\n");
		} else if (i < count - 1) {
			sb.append(var.toJSONString());
			sb.append(",");
			sb.append("\n");
		} else {
			sb.append(var.toJSONString());
			sb.append("\n");
			sb.append("]");
			sb.append("}");
		}
	}

}
