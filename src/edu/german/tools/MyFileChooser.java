package edu.german.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class MyFileChooser extends JPanel {
	private static final long serialVersionUID = 1L;
	private String fileName;

	private String getFileToWrite() {
		String path = System.getProperty("user.home") + File.separator + "Documents";
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(path));
		int result = fileChooser.showSaveDialog(this); // if you want to write
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			setFileName(selectedFile.getName());
			return selectedFile.getAbsolutePath();
		}

		return null;
	}

	private String getFileToRead() {
		String path = System.getProperty("user.home") + File.separator + "Documents";
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(path));
		int result = fileChooser.showOpenDialog(this); // if you want to read
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			setFileName(selectedFile.getName());
			return selectedFile.getAbsolutePath();
		}

		return null;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath(String demand) {
		if (demand.equals("WRITE"))
			return getFileToWrite();
		else
			return getFileToRead();
	}

}
