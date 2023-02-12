package edu.german.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportDataToFile implements Runnable {
	private List<String> toExport;
	private String path;
	private String exportKind;

	public ExportDataToFile(List<String> toExport, String path, String exportKind) {
		this.toExport = toExport;
		this.path = path;
		this.exportKind = exportKind;
	}

	@Override
	public void run() {
		writeToFile();
	}

	// TODO make new way to export data in JSON format - parameter: exportKind
	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(path));
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