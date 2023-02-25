package edu.german.io;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.german.cfg.FileKind;
import edu.german.tools.MyCheckBox;
import edu.german.tools.MyComboBox;
import edu.german.tools.MyFileChooser;
import edu.german.tools.MyProperties;
import edu.german.tools.Titel;

public class ImportConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String WORD_FILE_CFG = "words_genus.properties";
	private String labelInfo = "Ścieżka do pliku: ";
	private JButton chooseBtn;
	private JLabel pathLab;
	private String filePath;
	private String firstParamTitle;
	private String secondParamTitle;
	private String firstHint;
	private String secondHint;
	private MyCheckBox orderBox;
	private MyCheckBox sentencesOrWordsBox;
	private MyComboBox wordGenusBox;
	private MyComboBox fileTypeBox;

	public ImportConfigPanel(String firstParamTitle, String secondParamTitle, String firstHint, String secondHint) {
		this.firstParamTitle = firstParamTitle;
		this.secondParamTitle = secondParamTitle;
		this.firstHint = firstHint;
		this.secondHint = secondHint;
		filePath = null;
		
		pathLab = new JLabel(labelInfo);

		JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		orderBox = new MyCheckBox(firstParamTitle, "polski/niemiecki", firstHint);
		sentencesOrWordsBox = new MyCheckBox(secondParamTitle, "Wyrazy", secondHint);

		String[] selectionList = new MyProperties(WORD_FILE_CFG).getValuesArray("GENUS_LIST");
		wordGenusBox = new MyComboBox(Titel.setTitel("WORDS_GENUS"), selectionList);

		FileKind[] fileKind = FileKind.values();
		fileTypeBox = new MyComboBox("Typ eksportu", fileKind);

		upPanel.add(sentencesOrWordsBox);
		upPanel.add(orderBox);
		upPanel.add(wordGenusBox);
		upPanel.add(fileTypeBox);
		upPanel.repaint();

		JPanel downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		chooseBtn = new JButton(Titel.setTitel("CHOOSE_FILE"));
		chooseBtn.setPreferredSize(new Dimension(200, 32));

		downPanel.add(chooseBtn);
		downPanel.add(pathLab);

		chooseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MyFileChooser mfc = new MyFileChooser();
				filePath = mfc.getFilePath("WRITE");
				pathLab.setText(labelInfo + " " + filePath);
			}
		});

		this.setLayout(new GridLayout(2, 1, 5, 5));
		this.add(upPanel);
		this.add(downPanel);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}

	public static class Builder {
		private String firstParamTitle;
		private String secondParamTitle;
		private String firstHint;
		private String secondHint;

		public Builder() {
		}

		public Builder withFirstParamTitle(String firstParamTitle) {
			this.firstParamTitle = firstParamTitle;
			return this;
		}

		public Builder withSecondParamTitle(String secondParamTitle) {
			this.secondParamTitle = secondParamTitle;
			return this;
		}

		public Builder withFirstHint(String firstHint) {
			this.firstHint = firstHint;
			return this;
		}

		public Builder withSecondHint(String secondHint) {
			this.secondHint = secondHint;
			return this;
		}

		public ImportConfigPanel build() {
			return new ImportConfigPanel(firstParamTitle, secondParamTitle, firstHint, secondHint);
		}

	}
	public String getFilePath() {
		return filePath;
	}

	public void clearFilePath() {
		pathLab.setText(labelInfo);
	}

	public boolean order() {
		return orderBox.result();
	}

	public String orderAsString() {
		if (order())
			return "pl";
		else
			return "ge";

	}

	public boolean sentencesOrWords() {
		return sentencesOrWordsBox.result();
	}

	public String sentencesOrWordsAsString() {
		if (sentencesOrWordsBox.result())
			return "WORDS";

		return "SENTENCE";
	}
	
	public String fileType() {
		return fileTypeBox.getValue();
	}

	public String wordGenus() {
		return wordGenusBox.getValue();
	}

	public HashMap<String, String> exportConfigParam() {
		String kind = "sentence";
		if (sentencesOrWords())
			kind = "word";
		String order = "ge";
		if (order())
			order = "pl";

		HashMap<String, String> exportConfigMap = new HashMap<>();
		exportConfigMap.put("EXPORT_TYPE", fileType());
		exportConfigMap.put("GENUS", kind);
		exportConfigMap.put("ORDER", order);
		if (kind.equals("word"))
			exportConfigMap.put("WORD_GENUS", wordGenus());

		return exportConfigMap;
	}

	public void clear() {
		filePath = null;
		orderBox.clear();
		sentencesOrWordsBox.clear();
		wordGenusBox.setValue(null);
		clearFilePath();
	}

}

