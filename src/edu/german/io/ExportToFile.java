package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.german.sentences.SentencesSetting;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordsSetting;

public class ExportToFile extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private WordsSetting wordSettingPanel;
	private SentencesSetting sentenceSettingPanel;
	private ButtonsPanel bp;
	private JButton clearEditFieldBtn;
	private JButton prepareDataBtn;
	private JButton showBtn;
	private JButton exportBtn;
	private JTextArea txtArea;
	private ExecutorService es;
	private JTabbedPane tp;
	private String type;
	private MyProgressBar bar;
	private List<String[]> wordList;
	private List<String[]> sentenceList;

	public ExportToFile(int height, int width, String titel) {
		super(height, width, titel);
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
		tp.add("Konfiguracja parametrów wyrazów", wordSettingPanel);
		tp.add("Konfiguracja parametrów zdań", sentenceSettingPanel);

		bp = new ButtonsPanel("CLEAR_EDIT_FIELD", "PREPARE_DATA", "SHOW_DATA", "EXPORT");
		bp.setFontSize(20);
		clearEditFieldBtn = bp.getB1();
		clearEditFieldBtn.addActionListener(this);
		prepareDataBtn = bp.getB2();
		prepareDataBtn.addActionListener(this);
		showBtn = bp.getB3();
		showBtn.addActionListener(this);
		exportBtn = bp.getB4();
		exportBtn.addActionListener(this);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tp, jp);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(0.5);

		bar = new MyProgressBar("Postęp eksportu:");

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
		}

		else if (src == prepareDataBtn) {
			prepareDataToExport();
		}

		else if (src == exportBtn) {
			String filePath = getFilePath();
			if (filePath != null && getSeparationSign() != null) {
				int selectedIndex = tp.getSelectedIndex();
				GetListOfWords glw = new GetListOfWords();
				GetListOfSentences gls = new GetListOfSentences();

//				if (selectedIndex == 0)
//					glw.getList(wordSettingPanel.getGenus());
//				else
//					gls.getList(sentenceSettingPanel.getGenus());
			}
		}

		else if (src == showBtn) {

		}

	}

	private void prepareDataToExport() {
		if (tp.getSelectedIndex() == 0) {
			GetListOfWords glw = new GetListOfWords();
			wordList = glw.getList(wordSettingPanel.getFilePath(), wordSettingPanel.getSeparationSign(),
					wordSettingPanel.getGenus());
		} else {
			GetListOfSentences gls = new GetListOfSentences();
			sentenceList = gls.getList(sentenceSettingPanel.getFilePath(), sentenceSettingPanel.getSeparationSign(),
					sentenceSettingPanel.getGenus());
		}
	}

}
