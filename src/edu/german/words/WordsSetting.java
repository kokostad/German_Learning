package edu.german.words;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.german.tools.MyCheckBox;
import edu.german.tools.MyComboBox;
import edu.german.tools.MyFileChooser;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditableField;
import edu.german.tools.Titel;

public class WordsSetting extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Integer fontSize = 16;
	private static final String FILE_NAME = "words_genus.properties";
	private OneEditableField chooseFile;
	private OneEditableField separationSign;
	private MyComboBox box;
	private JButton chooseBtn;
	private String filePath;
	private MyCheckBox cBox;

	public WordsSetting() {
		chooseFile = new OneEditableField(Titel.setTitel("CHOOSE_FILE"), "Wybierz odpowiedni plik", fontSize, 16);
		separationSign = new OneEditableField(Titel.setTitel("CHOOSE_SEPARATION_SIGN"),
				"Wpisz odpowieni znak rozdzielajacy", fontSize, 2);
		chooseBtn = new JButton(Titel.setTitel("CHOOSE_FILE"));
		chooseBtn.setPreferredSize(new Dimension(200, 32));
		chooseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MyFileChooser mfc = new MyFileChooser();
				filePath = mfc.getFilePath("READ");
				chooseFile.setValue(mfc.getFileName());
			}
		});

		filePath = null;

		String[] selectionList = new MyProperties(FILE_NAME).getValuesArray("GENUS_LIST");
		box = new MyComboBox(Titel.setTitel("CHOOSE_GENUS"), selectionList);
		cBox = new MyCheckBox(" Zmień kolejność w pliku: ", "polski/niemiecki",
				"Kolejność wyrazów (domyślnie: niemiecki/polski)");

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		this.add(chooseFile);
		this.add(chooseBtn);
		this.add(separationSign);
		this.add(box);
		this.add(cBox);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}

	public String getFilePath() {
		return filePath;
	}

	public String getSeparationSign() {
		return separationSign.getValue();
	}

	public String getGenus() {
		return box.getValue();
	}

	public void clearEditField() {
		chooseFile.setValue(null);
	}

	public void clearSeparationSign() {
		separationSign.setValue(null);
	}

	public boolean getOrder() {
		return cBox.result();
	}

}
