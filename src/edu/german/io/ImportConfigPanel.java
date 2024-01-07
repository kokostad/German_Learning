package edu.german.io;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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
	private String PATH = "src/edu/german/words/cfg/";
	private static final String WORD_FILE_CFG = "word.cfg";
	private String labelInfo = "Ścieżka do pliku: ";
	private JLabel pathLab;
	private String filePath;
//	private String firstParamTitle;
//	private String secondParamTitle;
//	private String firstHint;
//	private String secondHint;
	private MyCheckBox order;
	private MyCheckBox sentenceOrWord;
	private MyComboBox wordGenus;
	private MyComboBox fileType;

	public ImportConfigPanel(String firstParamTitle, String secondParamTitle, String firstHint, String secondHint) {
//		this.firstParamTitle = firstParamTitle;
//		this.secondParamTitle = secondParamTitle;
//		this.firstHint = firstHint;
//		this.secondHint = secondHint;
		filePath = null;

		pathLab = new JLabel(labelInfo);

		JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		order = new MyCheckBox(firstParamTitle, "polski/niemiecki", firstHint);
		sentenceOrWord = new MyCheckBox(secondParamTitle, "Wyrazy", secondHint);

		String[] selectionList = new MyProperties(PATH, WORD_FILE_CFG).getValuesArray("GENUS_LIST");
		wordGenus = new MyComboBox(Titel.setTitel("WORDS_GENUS"), selectionList, "Puste oznacza wszystko");

		FileKind[] fileKind = FileKind.values();
		fileType = new MyComboBox("Typ pliku", fileKind);

		upPanel.add(sentenceOrWord);
		upPanel.add(order);
		upPanel.add(wordGenus);
		upPanel.add(fileType);
		upPanel.repaint();

		JPanel downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		downPanel.add(pathLab);

//		JButton chooseBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MyFileChooser mfc = new MyFileChooser();
//				filePath = mfc.getFilePath("WRITE");
//				pathLab.setText(labelInfo + " " + filePath);
//			}
//		});

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
		return order.result();
	}

	public String orderAsString() {
		if (order())
			return "pl";
		else
			return "ge";

	}

	public boolean sentencesOrWords() {
		return sentenceOrWord.result();
	}

	public String sentencesOrWordsAsString() {
		if (sentenceOrWord.result())
			return "WORDS";

		return "SENTENCE";
	}

	public String fileType() {
		return fileType.getValue();
	}

	public String wordGenus() {
		return wordGenus.getValue();
	}

	public Map<String, String> exportConfigParam() {
		String kind = "sentence";
		if (sentencesOrWords())
			kind = "word";
		String order = "ge";
		if (order())
			order = "pl";

		Map<String, String> map = new HashMap<String, String>();
		map.put("EXPORT_TYPE", fileType());
		map.put("GENUS", kind);
		map.put("ORDER", order);
		if (kind.equals("word"))
			map.put("WORD_GENUS", wordGenus());

		return map;
	}

	public void setPathLab(String path) {
		filePath = path;
		pathLab.setText(labelInfo + " " + path);
	}

	public void clear() {
		filePath = null;
		wordGenus.setValue(null);
		clearFilePath();
	}
}
