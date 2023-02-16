package edu.german.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportSentencesToCSVFile implements Runnable {
	private List<String> toExport;
	private String filePath;

	public ExportSentencesToCSVFile(List<String> toExport, String filePath) {
		this.toExport = toExport;
		this.filePath = filePath;
	}

	@Override
	public void run() {
		writeToFile();
	}

	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			toExport.forEach((s) -> {
				try {
					writer.write(s);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
