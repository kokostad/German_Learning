package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import edu.german.sentences.Sentence;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.model.Word;

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
	private boolean textImportState = false;
	private ExportConfigPanel exportConfigPanel;
	private List<JButton> buttonList;
	private String data;

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

		String[] buttonNames = { "CLEAR", "SHOW_DATA", "EXPORT" };

		bp = new ButtonsPanel(buttonNames);
		bp.setFontSize(20);
		buttonList = bp.getButtonList();
		clearEditFieldBtn = buttonList.get(0);
		clearEditFieldBtn.addActionListener(this);
		showBtn = buttonList.get(1);
		showBtn.addActionListener(this);
		exportBtn = buttonList.get(2);
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
			String exportType = exportConfigPanel.exportType();
			boolean wordOrSentence = exportConfigPanel.sentencesOrWords();

			System.out.println("Export type: " + exportType + " words: " + wordOrSentence);

			// TODO make this method
			if (!wordOrSentence) {
				List<Map<String, String>> list = new Sentence().getAllAsMapList();
				list.forEach(s -> textArea.append(s + "\n"));
				setData(textArea.getText());
			}

			else {
				// TODO get data from database
				String genus = exportConfigPanel.wordGenus();
				List<Map<String, String>> mapList = new Word().getType(genus);
				List<String> list = prepareList(mapList);

				list.forEach(s -> textArea.append(s + "\n"));
				setData(textArea.getText());
				textArea.setCaretPosition(0);
				textArea.setEditable(true);
			}
		}

		else if (src == exportBtn) {
			Optional<String> optPath = Optional.ofNullable(getFilePath());
			if (optPath.isEmpty()) {
				new ShowMessage("NO_DATA", "uzupełnij brakujące dane");
				return;
			}
			
			String exportType = exportConfigPanel.exportType();
			boolean wordOrSentence = exportConfigPanel.sentencesOrWords();

			// NOICE what if text area is null?
			if (data.isBlank()) {
				List<Map<String, String>> mapList = new Word().getType("nouns");
				List<String> list = prepareList(mapList);
			}

			if (wordOrSentence) {
				if (data.trim().length() > 0 && "CSV".equals(exportType))
					es.submit(new ExportDataToCSVFile(data, getFilePath()));
				else
					es.submit(new ExportDataToJSONFile(data, getFilePath(), "WORDS"));
			} else {
				if ("CSV".equals(exportType))
					es.submit(new ExportDataToCSVFile(data, getFilePath()));
				else
					es.submit(new ExportDataToJSONFile(data, getFilePath(), "SENTENCES"));
			}
			textArea.setText(null);
		}
	}

	private List<String> prepareList(List<Map<String, String>> mapList) {
		List<String> list = new LinkedList<>();
		mapList.forEach(m -> {
			StringBuilder sb = new StringBuilder();
			m.forEach((key, val) -> {
				sb.append(val + ";");
			});
			list.add(sb.toString());
		});

		return list;
	}

	private String getString(List<String> toExport) {
		StringBuilder sb = new StringBuilder();

		toExport
		.stream()
		.forEach(l -> sb.append(l + "\n"));

		return sb.toString();
	}

	private String getFilePath() {
		return exportConfigPanel.getFilePath();
	}

	private void clearAll() {
		bar.setNull();
		textArea.setText(null);
		exportConfigPanel.clear();
		setTextImportState(false);
	}

	/**
	 * The method completes and return the data in the form of a list of strings
	 * @param map - Map of Words or Sentences
	 * @return list of strings
	 */
//	private List<String> prepareDataToExport(HashMap<String, String> map) {
//		List<String> list = new LinkedList<>();
//		Optional<String> option = map
//				.entrySet()
//				.stream()
//				.filter(e -> "word".equals(e.getValue()))
//				.map(Map.Entry::getKey)
//				.findFirst();
//
//		String wordGenus = null;
//		if (map.containsKey("WORD_GENUS"))
//			wordGenus = map.get("WORD_GENUS");
//
//		// TODO need to use DoCall - Callable<>
//		if (option.isPresent()) {
//			if (wordGenus != null && !wordGenus.isBlank())
//				list = new GetWordsAsList().getGenusList(wordGenus, getOrder());
//			else
//				list = new GetWordsAsList().getList(getOrder());
//		} else {
//			list = new GetSentenceList().getList(getOrder());
//		}
//
//		return list;
//	}

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
