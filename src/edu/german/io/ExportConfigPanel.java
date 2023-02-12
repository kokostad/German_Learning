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
import edu.german.tools.Titles;

public class ExportConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String WORD_FILE_CFG = "words_genus.properties";
	private String labelInfo = "Ścieżka do pliku: ";
	private JButton chooseBtn;
	private JLabel pathLab;
	private String filePath;
	private MyCheckBox orderBox;
	private MyCheckBox kindBox;
	private MyComboBox wordKindBox;
	private MyComboBox exportBox;

	public ExportConfigPanel() {
		filePath = null;
		pathLab = new JLabel(labelInfo);

		JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		orderBox = new MyCheckBox("Zmień porządek eksportu: ", "polski/niemiecki",
				"Porządek eksportu (niemiecki/polski)");

		kindBox = new MyCheckBox("Ustaw eksport wyrazów: ", "Wyrazy", "Eksport (domyślnie: zdania)");

		String[] selectionList = new MyProperties(WORD_FILE_CFG).getValuesArray("GENUS_LIST");
		wordKindBox = new MyComboBox(Titles.setTitel("WORDS_GENUS"), selectionList);

		FileKind[] fileKind = FileKind.values();
		exportBox = new MyComboBox("Typ eksportu", fileKind);

		upPanel.add(kindBox);
		upPanel.add(orderBox);
		upPanel.add(wordKindBox);
		upPanel.add(exportBox);
		upPanel.repaint();

		JPanel downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		chooseBtn = new JButton(Titles.setTitel("CHOOSE_FILE"));
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

	public String getFilePath() {
		return filePath;
	}

	public void clearFilePath() {
		pathLab.setText(labelInfo);
	}

	private boolean getOrder() {
		return orderBox.result();
	}

	private boolean getKind() {
		return kindBox.result();
	}

	public String getExportKind() {
		return exportBox.getValue();
	}

	public HashMap<String, String> getParam() {
		String kind = "sentence";
		if (getKind())
			kind = "word";
		String order = "ge";
		if (getOrder())
			order = "pl";

		HashMap<String, String> map = new HashMap<>();
		map.put("KIND", kind);
		map.put("ORDER", order);
		if (kind.equals("word"))
			map.put("WORD_KIND", wordKindBox.getValue());

		return map;
	}

	public void clear() {
		filePath = null;
		orderBox.clear();
		kindBox.clear();
		wordKindBox.setValue(null);
		clearFilePath();
	}

}
