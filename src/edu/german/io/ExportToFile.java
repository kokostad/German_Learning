package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import edu.german.tools.MyFileChooser;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyFrameProgressBar;
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
	private JButton indicateFileBtn;
	private JButton clearEditFieldBtn;
	private JButton showBtn;
	private JButton exportBtn;
	private JTextArea textArea;
	private ExecutorService es;
	private JTabbedPane tp;
	private MyFrameProgressBar bar;
	private boolean textImportState = false;
	private ExportConfigPanel exportConfigPanel;
	private List<JButton> buttonList;
	private String data;
	private String filePath;
	
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

		String[] buttonNames = { "INDICATE_FILE", "CLEAR", "SHOW_DATA", "EXPORT" };
		bp = new ButtonsPanel(buttonNames);
		bp.setFontSize(20);

		buttonList = bp.getButtonList();
		indicateFileBtn = buttonList.get(0);
		indicateFileBtn.addActionListener(this);
		clearEditFieldBtn = buttonList.get(1);
		clearEditFieldBtn.addActionListener(this);
		showBtn = buttonList.get(2);
		showBtn.addActionListener(this);
		exportBtn = buttonList.get(3);
		exportBtn.addActionListener(this);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tp, jp);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(new ScreenSetup().SPLIT_PANE_FACTOR);

		JPanel rightPan = new JPanel();
		rightPan.setLayout(new GridLayout(2, 1, 10, 10));
		rightPan.add(bp);

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

		else if (src == indicateFileBtn) {
			MyFileChooser mfc = new MyFileChooser();
			filePath = mfc.getFilePath("WRITE");
			exportConfigPanel.setPath(filePath);
		}

		else if (src == showBtn) {
			textArea.setText(null);
			boolean wordOrSentence = exportConfigPanel.sentencesOrWords();

			if (!wordOrSentence) {
				List<Map<String, String>> list = new Sentence().getAllAsMapList();
				list.forEach(s -> textArea.append(s + "\n"));
				setData(textArea.getText());
			} else {
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
			bar = new MyFrameProgressBar("EXPORT_PROGRESS");

			Optional<String> optPath = Optional.ofNullable(getFilePath());
			if (optPath.isEmpty()) {
				new ShowMessage("NO_DATA", "uzupełnij brakujące dane");
				return;
			}

			String exportType = exportConfigPanel.exportType();
			boolean wordOrSentence = exportConfigPanel.sentencesOrWords();
			String genus = exportConfigPanel.wordGenus();

			if (data == null || data.isBlank()) {
				List<Map<String, String>> mydata = getDataFromDatabase(exportType, wordOrSentence, genus);
				List<String> list = prepareList(mydata);

				if (wordOrSentence) {
					if ("CSV".equals(exportType))
						es.submit(new ExportListToCSVFile(list, getFilePath(), bar));
					else
						es.submit(new ExportListToJSONFile(list, getFilePath(), "WORDS", bar));
				} else {
					if ("CSV".equals(exportType))
						es.submit(new ExportListToCSVFile(list, getFilePath(), bar));
					else
						es.submit(new ExportListToJSONFile(list, getFilePath(), "SENTENCES", bar));
				}
			}

			textArea.setText(null);
		}
	}

	/**
	 * @param type of export, CSV or JSON
	 * @param wordOrSentence - choose word or sentences to export
	 * @param genus - kind of words to export
	 * @return data as map list
	 */
	private List<Map<String, String>> getDataFromDatabase(String type, boolean wordOrSentence, String genus) {
		List<Map<String, String>> mapList = new LinkedList<>();
		if (wordOrSentence) {
			if (genus != null && genus != "")
				mapList = new Word().getType(genus);
			else
				mapList = new Word().getAllAsMapList();
		} else {
			mapList = new Sentence().getAllAsMapList();
		}

		return mapList;
	}

	/**
	 * @param mapList
	 * @return List of string prepared to export
	 */
	private List<String> prepareList(List<Map<String, String>> mapList) {
		List<String> list = new LinkedList<>();
		mapList.forEach(m -> {
			StringBuilder sb = new StringBuilder();
			m.forEach((key, val) -> {
				sb.append(key + ":");
				sb.append(val + ";");
			});
			list.add(sb.toString());
		});

		return list;
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

	public boolean isTextImportState() {
		return textImportState;
	}

	public void setTextImportState(boolean textImportState) {
		this.textImportState = textImportState;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
