package edu.german.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * ExportWordsToCSVFile.java
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 * 
 * The class saves data to CVS files
 */
public class ExportWordsToCSVFile implements Runnable {
	private List<String> toExport;
	private String path;

	public ExportWordsToCSVFile(List<String> toExport, String path) {
		this.toExport = toExport;
		this.path = path;
	}

	@Override
	public void run() {
		writeToFile();
	}

	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(path));
			toExport.forEach((s) -> {
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