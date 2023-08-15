package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.german.sentences.Sentence;
import edu.german.services.ExecutorAddSentenceListToRepository;
import edu.german.services.ExecutorAddSentenceToRepository;
import edu.german.services.ExecutorWordTask;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.SentencesListPreparationFromCSVFile;
import edu.german.tools.SentenceListPreparationFromJSONFile;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;

/**
 * ImportFromFile.java
 * 
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com The class for
 *         importing data from JSON and CSV files
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
				.withFirstParamTitle(Titel.setTitel("CHANGE_ORDER_OF_IMPORT"))
				.withFirstHint("Porządek importu (niemiecki/polski)").withSecondParamTitle("Ustaw import wyrazów: ")
				.withSecondHint("Import (domyślnie: zdania)").build();

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

		bar = new MyProgressBar("IMPORT_PROGRESS");
		bar.setInfo("PROGRESS");

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
				String data = txtArea.getText();
//				BufferedInputStream bis = null;
//				try {
//					bis = new BufferedInputStream(new FileInputStream(getFilePath()));
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

				if (data.trim().length() > 0) {
					// TODO code
					what = importConfigPanel.sentencesOrWordsAsString();
					fileType = importConfigPanel.fileType();

					if (fileType.equals("CSV") && what.equals("SENTENCE")) {
						// TODO code
//						System.out.println(data);
						SentencesListPreparationFromCSVFile prepCSV = new SentencesListPreparationFromCSVFile(data,
								separationSign);
						List<Sentence> list = prepCSV.getSentenceList();
//						list.forEach(s -> s.toString());
						// is an ID (oid) needed? maybe for removing from list?
						if (list != null)
							es.submit(new ExecutorAddSentenceListToRepository(list, bar, getOrder()));

					}

					if (fileType.equals("JSON") && what.equals("SENTENCE")) {
						// TODO code
						System.out.println(data);
					}

					if (fileType.equals("CSV") && what.equals("WORDS")) {
						// TODO code
						System.out.println(data);
					}

					if (fileType.equals("JSON") && what.equals("WORDS")) {
						// TODO code
						System.out.println(data);
					}
				} else {
					try {
						BufferedReader br = new BufferedReader(new FileReader(filePath));
						BufferedInputStream bis = new BufferedInputStream(new FileInputStream(getFilePath()));
						if (br != null) {
							what = importConfigPanel.sentencesOrWordsAsString();
							fileType = importConfigPanel.fileType();
							if (fileType.equals("CSV") && what.equals("SENTENCE")) {
								SentencesListPreparationFromCSVFile prepCSV = new SentencesListPreparationFromCSVFile(
										br, separationSign);
								List<String[]> list = prepCSV.getList();
								if (list != null)
									es.submit(new ExecutorAddSentenceToRepository(list, bar, getOrder()));
								else {
									es.submit(new ExecutorWordTask(br, separationSign, getGenus(), bar, getOrder()));
								}
							}

							else {
								// NOTICE file type equals JSON
								if (what.equals("SENTENCE")) {
									SentenceListPreparationFromJSONFile prepJSON = new SentenceListPreparationFromJSONFile(
											br);
									prepJSON.readFromBR();
								}
							}
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
				txtArea.setText(null);
				repaint();
			}
		}

		else if (src == showBtn)

		{
			bar.setNull();
			txtArea.setText(null);
			String filePath = getFilePath();
			if (filePath != null) {
				Path p = Paths.get(filePath);
				try {
					Files.lines(p).forEach(s -> txtArea.append(s + "\n"));
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

	private void showJSONData(SentenceListPreparationFromJSONFile prepJSON) {
		// TODO to improve. Is it needed?
		List<String[]> list = prepJSON.getList(fileType);
		if (list != null) {
			for (String[] array : list) {
				for (String str : array)
					System.out.println(str);
			}
		}
	}
}
