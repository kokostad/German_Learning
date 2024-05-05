package edu.german.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.simple.JSONObject;

import edu.german.tools.SentenceJSONParser;
import edu.german.tools.WordJSONParser;

public class ExportListToJSONFile implements Runnable {
	private List<String> list;
	private String filePath;
	private String type;
	private Long count;
	private int i = 0;

	public ExportListToJSONFile(List<String> list, String filePath, String type) {
		this.list = list;
		this.filePath = filePath;
		this.type = type;

		deleteIfFileExist();
		count = (long) list.size();
	}

	@Override
	public void run() {
		StringBuilder sb = new StringBuilder();
		try {
			FileWriter writer = new FileWriter(filePath, true);

			list.forEach(l -> {
				if ("SENTENCES".equals(type.toUpperCase()))
					try {
						writer.write(prepareJSONSentence(i, l));
						writer.write("\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				else
					try {
						writer.write(prepareJSONOWord(i, l));
						writer.write("\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				i++;
			});

			sb.append("]");
			sb.append("}");
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String prepareJSONSentence(int i, String line) {
		StringBuilder sb = new StringBuilder();
		if (i == 0) {
			JSONObject var = new SentenceJSONParser(line).getJSONItem();
			sb.append("{\"SENTENCES\":");
			sb.append("[");
			sb.append("\n");
			sb.append(var.toJSONString());
			sb.append(",");
		} else {
			JSONObject var = new SentenceJSONParser(line).getJSONItem();
			if (i < count - 1) {
				sb.append(var.toJSONString());
				sb.append(",");
			} else {
				sb.append(var.toJSONString());
			}
		}
		return sb.toString();
	}

	private String prepareJSONOWord(int i, String line) {
		StringBuilder sb = new StringBuilder();
		if (i == 0) {
			JSONObject var = new WordJSONParser(line).getJSONItem();
			sb.append("{\"WORDS\":");
			sb.append("[");
			sb.append("\n");
			sb.append(var.toJSONString());
			sb.append(",");
		} else {
			JSONObject var = new WordJSONParser(line).getJSONItem();
			if (i < count - 1) {
				sb.append(var.toJSONString());
				sb.append(",");
			} else {
				sb.append(var.toJSONString());
			}
		}
		return sb.toString();
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
