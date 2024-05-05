package edu.german.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportListToCSVFile implements Runnable {
	private List<String> list;
	private String filePath;

	public ExportListToCSVFile(List<String> list, String filePath) {
		this.list = list;
		this.filePath = filePath;
	}

	@Override
	public void run() {
		writeToFile();
	}

	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			list.forEach((s) -> {
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
