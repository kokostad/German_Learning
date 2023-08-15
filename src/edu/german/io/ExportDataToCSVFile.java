package edu.german.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportDataToCSVFile implements Runnable {
	private String filePath;
	private String data;

	public ExportDataToCSVFile(String data, String filePath) {
		this.data = data;
		this.filePath = filePath;
	}

	@Override
	public void run() {
		writeToFile();
	}

	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			data
			.lines()
			.forEach((s) -> {
				try {
					writer.write(s + "\n");
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
