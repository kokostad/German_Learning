package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.german.sentences.SentencesSetting;
import edu.german.services.ExecutorTaskAddSentenceToRepository;
import edu.german.services.ExecutorWordTask;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.SentencesListPreparationFromCSVFile;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordsSetting;

/**
 * ImportFromFile.java
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 * The class for importing data from JSON and CSV files
 */
public class ImportFromFile extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearEditFieldBtn;
	private JButton showBtn;
	private JButton importBtn;
	private JTextArea txtArea;
	private ExecutorService es;
	private JTabbedPane tp;
	private String what;
	private String fileType;
	private MyProgressBar bar;
	private ImportConfigPanel importConfigPanel;
	private String separationSign = ";";

	public ImportFromFile(int height, int width, String titel) {
		super(height, width, titel);
		es = Executors.newSingleThreadExecutor();
		what = null;

		txtArea = new JTextArea(15, 70);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);

		JScrollPane jp = new JScrollPane();
		jp.getViewport().setView(txtArea);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		importConfigPanel = new ImportConfigPanel.Builder()
				.withFirstParamTitle("Zmień porządek importu: ")
				.withFirstHint("Porządek importu (niemiecki/polski)")
				.withSecondParamTitle("Ustaw import wyrazów: ")
				.withSecondHint("Import (domyślnie: zdania)")
				.build();

		tp = new JTabbedPane();
		tp.add(Titel.setTitel("IMPORT_CONFIGURATION"), importConfigPanel);

		bp = new ButtonsPanel("CLEAR", "SHOW_DATA", "IMPORT");
		bp.setFontSize(20);
		clearEditFieldBtn = bp.getB1();
		clearEditFieldBtn.addActionListener(this);
		showBtn = bp.getB2();
		showBtn.addActionListener(this);
		importBtn = bp.getB3();
		importBtn.addActionListener(this);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tp, jp);
		sp.setOneTouchExpandable(true);

		bar = new MyProgressBar("Postęp importu:");

		JPanel rightPan = new JPanel();
		rightPan.setLayout(new GridLayout(2, 1, 10, 10));
		rightPan.add(bp);
		rightPan.add(bar);

		this.add(sp, BorderLayout.CENTER);
		this.add(rightPan, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldBtn) {
			clearAll();
			repaint();
		}

		else if (src == importBtn) {
			String filePath = getFilePath();
			if (filePath != null) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(filePath));
					if (br != null) {
						what = importConfigPanel.sentencesOrWordsAsString();
						fileType = importConfigPanel.fileType();
						if (fileType.equals("CSV")) {
							if (what.equals("SENTENCE")) {
								SentencesListPreparationFromCSVFile sPrep = new SentencesListPreparationFromCSVFile(br,
										separationSign);
								List<String[]> list = sPrep.getList();
								if (list != null)
									es.submit(new ExecutorTaskAddSentenceToRepository(list, bar, getOrder()));
							} else {
								es.submit(new ExecutorWordTask(br, separationSign, getGenus(), bar, getOrder()));
							}
						}

						else {
							// NOTICE file type equals JSON
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				txtArea.setText(null);
				repaint();
			}
		}

		else if (src == showBtn) {
			bar.setNull();
			txtArea.setText(null);
			String filePath = getFilePath();
			if (filePath != null) {
				try {
					String line;
					try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
						while ((line = br.readLine()) != null) {
							if (line.length() > 1)
								txtArea.append(line + "\n");
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			txtArea.setCaretPosition(0);
			txtArea.setEditable(true);
		}
	}

	private String getFilePath() {
		return importConfigPanel.getFilePath();
	}

	private String getGenus() {
		return importConfigPanel.wordGenus();
	}

	private boolean getOrder() {
		return importConfigPanel.order();
	}

	private void clearAll() {
		bar.setNull();
		txtArea.setText(null);
		importConfigPanel.clear();
	}

	/*
	 * NOTICE need to check source file meets the criteria if not show message
	 */
	private void checkSource() {

	}

}
