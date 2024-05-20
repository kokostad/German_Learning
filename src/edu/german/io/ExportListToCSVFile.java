package edu.german.io;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import edu.german.tools.MyFrameProgressBar;

public class ExportListToCSVFile implements Runnable {
	private int sum;
	private MyFrameProgressBar bar;
	private List<String> list;
	private String filePath;
	private int i = 0;

	public ExportListToCSVFile(List<String> list, String filePath, MyFrameProgressBar bar) {
		this.list = list;
		this.filePath = filePath;
		this.bar = bar;
		sum = list.size();
	}

	@Override
	public void run() {
		writeToFile();
	}

	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			list.forEach((s) -> {
				i += 1;
				try {
					writer.write(s + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				fillBar(i);
			});
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fillBar(int i) {
		int result = ((i * 100) / sum);
		bar.fill(result);
		bar.showProgress(result);
		if (result == 100 || i == sum) {
			bar.done();
			Toolkit.getDefaultToolkit().beep();
		}
	}
}
