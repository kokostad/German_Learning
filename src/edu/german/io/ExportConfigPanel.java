package edu.german.io;


import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.german.cfg.FileKind;
import edu.german.tools.MyCheckBox;
import edu.german.tools.MyComboBox;
import edu.german.tools.MyProperties;
import edu.german.tools.Titel;

/**
 * ExportConfigPanel.java
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 * Configuration panel for parameterization of data export
 */
public class ExportConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "word.cfg";
	private MyCheckBox sentencesOrWordsChooseBox;
	private MyComboBox wordGenusBox;
	private MyComboBox exportTypeBox;
	private String labelInfo = "Ścieżka do pliku: ";
	private JLabel pathLab;
	private String filePath;

	public ExportConfigPanel() {
		filePath = null;
		pathLab = new JLabel(labelInfo);

		JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		sentencesOrWordsChooseBox = new MyCheckBox("Ustaw eksport wyrazów: ", "Wyrazy", "Eksport (domyślnie: zdania)");

		String[] selectionList = new MyProperties(CFG_FILE).getValuesArray("GENUS_LIST");
		wordGenusBox = new MyComboBox(Titel.setTitel("WORDS_GENUS"), selectionList);

		FileKind[] fileKind = FileKind.values();
		exportTypeBox = new MyComboBox("Typ eksportu", fileKind);

		upPanel.add(sentencesOrWordsChooseBox);
		upPanel.add(wordGenusBox);
		upPanel.add(exportTypeBox);
		upPanel.repaint();

		JPanel downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		downPanel.add(pathLab);

		this.setLayout(new GridLayout(2, 1, 5, 5));
		this.add(upPanel);
		this.add(downPanel);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}

	public boolean sentencesOrWords() {
		return sentencesOrWordsChooseBox.result();
	}

	public String sentencesOrWordsAsString() {
		if (sentencesOrWordsChooseBox.result())
			return "WORDS";
		else
			return "SENTENCES";
	}

	public String exportType() {
		return exportTypeBox.getValue();
	}

	public String wordGenus() {
		return wordGenusBox.getValue();
	}

	public Map<String, String> exportConfigParam() {
		String kind = "sentence";
		if (sentencesOrWords())
			kind = "word";

		Map<String, String> map = new HashMap<String, String>();
		map.put("EXPORT_TYPE", exportType());
		map.put("GENUS", kind);
		if (kind.equals("word"))
			map.put("WORD_GENUS", wordGenus());

		return map;
	}

	public void clear() {
		filePath = null;
		sentencesOrWordsChooseBox.clear();
		wordGenusBox.setValue(null);
		pathLab.setText(labelInfo);
	}

	public void setPath(String filePath) {
		this.filePath = filePath;
		pathLab.setText(labelInfo + filePath);
	}

	public String getFilePath() {
		return filePath;
	}
}
