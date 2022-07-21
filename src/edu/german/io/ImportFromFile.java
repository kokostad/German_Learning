package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.german.sentences.SentencesSetting;
import edu.german.services.ExecutorSentenceTask;
import edu.german.services.ExecutorWordTask;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.SentencesListPreparation;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordsSetting;

public class ImportFromFile extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private WordsSetting wordSettingPanel;
	private SentencesSetting sentenceSettingPanel;
	private ButtonsPanel bp;
	private JButton clearEditFieldBtn;
	private JButton showBtn;
	private JButton importBtn;
	private JTextArea txtArea;
	private ExecutorService es;
	private JTabbedPane tp;
	private String type;
	private MyProgressBar bar;

	public ImportFromFile(int height, int width) {
		super(height, width, Titles.setTitel("IMPORT"));
		es = Executors.newSingleThreadExecutor();
		type = null;

		txtArea = new JTextArea(15, 70);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);

		JScrollPane jp = new JScrollPane();
		jp.getViewport().setView(txtArea);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		wordSettingPanel = new WordsSetting();
		sentenceSettingPanel = new SentencesSetting();

		tp = new JTabbedPane();
		tp.add("Konfiguracja parametrów słów", wordSettingPanel);
		tp.add("Konfiguracja parametrów zdań", sentenceSettingPanel);

		bp = new ButtonsPanel("CLEAR_EDIT_FIELD", "SHOW_FILE", "IMPORT");
		bp.setFontSize(20);
		clearEditFieldBtn = bp.getB1();
		clearEditFieldBtn.addActionListener(this);
		showBtn = bp.getB2();
		showBtn.addActionListener(this);
		importBtn = bp.getB3();
		importBtn.addActionListener(this);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tp, jp);
		sp.setOneTouchExpandable(true);
		sp.setResizeWeight(0.3);

		bar = new MyProgressBar("Postęp importu:");

		JPanel rightPan = new JPanel();
		rightPan.setLayout(new GridLayout(2, 1, 10, 10));
		rightPan.add(bp);
		rightPan.add(bar);

		this.add(sp, BorderLayout.CENTER);
		this.add(rightPan, BorderLayout.EAST);
	}

	private String getFilePath() {
		if (tp.getSelectedIndex() == 0) {
			type = "WORD";
			return wordSettingPanel.getFilePath();
		} else {
			type = "SENTENCE";
			return sentenceSettingPanel.getFilePath();
		}
	}

	private String getSeparationSign() {
		if (tp.getSelectedIndex() == 0)
			return wordSettingPanel.getSeparationSign();
		else
			return sentenceSettingPanel.getSeparationSign();
	}

	private String getGenus() {
		if (tp.getSelectedIndex() == 0)
			return wordSettingPanel.getGenus();
		else
			return sentenceSettingPanel.getGenus();
	}

	private void clearAll() {
		bar.setNull();
		txtArea.setText(null);

		if (tp.getSelectedIndex() == 0) {
			wordSettingPanel.clearEditField();
		} else {
			sentenceSettingPanel.clearEditField();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldBtn) {
			clearAll();
			repaint();
		} else if (src == importBtn) {
			String filePath = getFilePath();
			if (filePath != null && getSeparationSign() != null) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(filePath));
					if (br != null) {
						if (type.equals("SENTENCE")) {
							SentencesListPreparation sPrep = new SentencesListPreparation(br, getSeparationSign());
							es.submit(new ExecutorSentenceTask(sPrep.getList(), type, bar,
									sentenceSettingPanel.getOrder()));
						} else {
							es.submit(new ExecutorWordTask(br, getSeparationSign(), getGenus(), bar,
									wordSettingPanel.getOrder()));
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				clearAll();
				txtArea.setText(null);
				repaint();
			}
		} else if (src == showBtn) {
			bar.setNull();
			txtArea.setText(null);
			String filePath = getFilePath();
			if (filePath != null) {
				try {
					String line;
					try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
						while ((line = br.readLine()) != null) {
							txtArea.append(line + "\n");
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
