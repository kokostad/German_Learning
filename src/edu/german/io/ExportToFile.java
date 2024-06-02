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
import edu.german.tools.MapOrganizer;
import edu.german.tools.MyFileChooser;
import edu.german.tools.MyFrameProgressBar;
import edu.german.tools.MyInternalFrame;
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
	private ExportConfigPanel exportConfigPanel;
	private List<JButton> buttonList;
	private List<String> list;
	private String filePath;
	
	public ExportToFile(int height, int width, String titel) {
		super(height, width, titel);
		es = Executors.newFixedThreadPool(4);

		list = null;
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
			list = null;
			textArea.setText(null);
			boolean wordOrSentence = exportConfigPanel.sentencesOrWords();

			if (!wordOrSentence) {
				List<Map<String, String>> mapList = new Sentence().getAllAsMapList();
				list = new MapOrganizer(mapList, "SENTECES").sortedList();
				list.forEach(s -> textArea.append(s + "\n"));
			} else {
				String genus = exportConfigPanel.wordGenus();
				List<Map<String, String>> mapList = new LinkedList<Map<String, String>>();
				if (genus != null)
					mapList = new Word().getType(genus);
				else
					mapList = new Word().getAllType();

				String type = exportConfigPanel.sentencesOrWordsAsString();
				list = new MapOrganizer(mapList, type).sortedList();

				list.forEach(s -> textArea.append(s + "\n"));
				textArea.setCaretPosition(0);
				textArea.setEditable(true);
			}
		}

		else if (src == exportBtn) {
			bar = new MyFrameProgressBar("EXPORT_PROGRESS");
			String filePath = null;

			Optional<String> optPath = Optional.ofNullable(exportConfigPanel.getFilePath());
			if (optPath.isEmpty()) {
				new ShowMessage("NO_DATA", "uzupełnij brakujące dane");
				return;
			} else {
				filePath = optPath.get();
			}

			String sentenceOrWord = exportConfigPanel.sentencesOrWordsAsString();
			String exportType = exportConfigPanel.exportType();

			if (list == null) {
				if (sentenceOrWord.equals("SENTENCES")) {
					List<Map<String, String>> mapList = new Sentence().getAllAsMapList();
					List<String> list = new MapOrganizer(mapList, sentenceOrWord).sortedList();
					if ("CSV".equals(exportType))
						es.submit(new ExportListToCSVFile(list, filePath, bar));
					else
						es.submit(new ExportListToJSONFile(list, filePath, sentenceOrWord, bar));
				} else {
					String genus = exportConfigPanel.wordGenus();
					List<Map<String, String>> mapList = new LinkedList<Map<String, String>>();
					if (genus != null)
						mapList = new Word().getType(genus);
					else
						mapList = new Word().getAllType();

					List<String> list = new MapOrganizer(mapList, "WORDS").sortedList();
					if ("CSV".equals(exportType))
						es.submit(new ExportListToCSVFile(list, filePath, bar));
					else
						es.submit(new ExportListToJSONFile(list, filePath, "WORDS", bar));
				}
			} else {
				if (sentenceOrWord.equals("SENTENCES")) {
					if ("CSV".equals(exportType))
						es.submit(new ExportListToCSVFile(list, filePath, bar));
					else
						es.submit(new ExportListToJSONFile(list, filePath, sentenceOrWord, bar));

				} else {
					if ("CSV".equals(exportType))
						es.submit(new ExportListToCSVFile(list, filePath, bar));
					else
						es.submit(new ExportListToJSONFile(list, filePath, "WORDS", bar));

				}
			}

			textArea.setText(null);
			list = null;
		}
	}

	private void clearAll() {
		bar.setNull();
		textArea.setText(null);
		exportConfigPanel.clear();
	}

}
