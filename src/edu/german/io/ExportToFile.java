package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.german.services.ExportSentencesToCSVFile;
import edu.german.services.ExportSentencesToJSONFile;
import edu.german.services.GetWordsAsList;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.TextCleaner;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;

/**
 * ExportToFile.java
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com 
 * The class exports data to CSV and JSON files
 */
public class ExportToFile extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearEditFieldBtn;
	private JButton showBtn;
	private JButton exportBtn;
	private JTextArea textArea;
	private ExecutorService es;
	private JTabbedPane tp;
	private MyProgressBar bar;
	private List<String> toExport = null;
	private boolean textImportState = false;
	private ExportConfigPanel exportConfigPanel;

	public ExportToFile(int height, int width, String titel) {
		super(height, width, titel);
		es = Executors.newFixedThreadPool(4);

		textArea = new JTextArea(15, 70);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane jp = new JScrollPane();
		jp.getViewport().setView(textArea);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		exportConfigPanel = new ExportConfigPanel();

		tp = new JTabbedPane();
		tp.add(Titel.setTitel("EXPORT_CONFIGURATION"), exportConfigPanel);

		bp = new ButtonsPanel("CLEAR", "SHOW_DATA", "EXPORT");
		bp.setFontSize(20);
		clearEditFieldBtn = bp.getB1();
		clearEditFieldBtn.addActionListener(this);
		showBtn = bp.getB2();
		showBtn.addActionListener(this);
		exportBtn = bp.getB3();
		exportBtn.addActionListener(this);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tp, jp);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(new ScreenSetup().SPLIT_PANE_FACTOR);

		bar = new MyProgressBar("EXPORT_PROGRESS");
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

		else if (src == showBtn) {
			textArea.setText(null);
			toExport = prepareDataToExport(exportConfigPanel.exportConfigParam());
			setTextImportState(true);
			putIntoTextArea(toExport);
		}

		else if (src == exportBtn) {
			if (exportConfigPanel.getFilePath() == null) {
				new ShowMessage("NO_DATA", "uzupełnij brakujące dane");
				return;
			}

			if (!isTextImportState())
				toExport = prepareDataToExport(exportConfigPanel.exportConfigParam());

			/*
			 * NOTICE Default is SENTENCE if word put words into file if CSV put into CSV file else
			 * put into JSON file into JSON file
			 */
			boolean val = exportConfigPanel.sentencesOrWords(); // true is WORD, false is SENTENCE
			if (val)
				if ((exportConfigPanel.exportType()).equals("CSV"))
					es.submit(new ExportWordsToCSVFile(toExport, getFilePath()));
				else
					es.submit(new ExportWordsToJSONFile(toExport, getFilePath()));

			/*
			 * NOTICE If parameter equals "sentence" then chose only sentences and than put into
			 * file, if CSV put into CSV file otherwise into JSON file
			 */
			else if ((exportConfigPanel.exportType()).equals("CSV"))
				es.submit(new ExportSentencesToCSVFile(toExport, getFilePath()));
			else
				es.submit(new ExportSentencesToJSONFile(toExport, getFilePath()));

			setTextImportState(false);
		}
	}

	private String getFilePath() {
		return exportConfigPanel.getFilePath();
	}

	private void clearAll() {
		bar.setNull();
		textArea.setText(null);
		toExport.clear();
		exportConfigPanel.clear();
		setTextImportState(false);
	}

	/**
	 * The method completes and return the data in the form of a list of strings
	 * @param map - Map of Words or Sentences
	 * @return list of strings
	 */
	private List<String> prepareDataToExport(HashMap<String, String> map) {
		List<String> list = new LinkedList<>();
		Optional<String> option = map.entrySet().stream().filter(e -> "word".equals(e.getValue()))
				.map(Map.Entry::getKey).findFirst();

		String wordGenus = null;
		if (map.containsKey("WORD_GENUS"))
			wordGenus = map.get("WORD_GENUS");

		// TODO need to use DoCall - Callable<>
		if (option.isPresent()) {
			if (wordGenus != null && !wordGenus.isBlank())
				list = new GetWordsAsList().getGenusList(wordGenus, getOrder());
			else
				list = new GetWordsAsList().getList(getOrder());
		} else {
			list = new GetSentenceList().getList(getOrder());
		}

		return list;
	}

	private void putIntoTextArea(List<String> toExport) {
		toExport.forEach((s) -> textArea.append(s + "\n"));
	}

	public boolean isTextImportState() {
		return textImportState;
	}

	public void setTextImportState(boolean textImportState) {
		this.textImportState = textImportState;
	}

	private String getOrder() {
		return exportConfigPanel.orderAsString();
	}

}
