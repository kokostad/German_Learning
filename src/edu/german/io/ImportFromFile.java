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
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import edu.german.services.ExecutorStringSentenceIntoDB;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.SentencesFromJSON;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;

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
	private MyProgressBar bar;
	private ImportConfigPanel settingPanel;

	public ImportFromFile(int height, int width, String titel) {
		super(height, width, titel);
		es = Executors.newSingleThreadExecutor();

		txtArea = new JTextArea(15, 70);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);

		JScrollPane jp = new JScrollPane();
		jp.getViewport().setView(txtArea);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		settingPanel = new ImportConfigPanel
				.Builder()
				.withFirstParamTitle(Titel.setTitel("CHANGE_ORDER_OF_IMPORT"))
				.withFirstHint("Porządek importu (niemiecki/polski)")
				.withSecondParamTitle("Ustaw import wyrazów: ")
				.withSecondHint("Import (domyślnie: zdania)")
				.build();

		tp = new JTabbedPane();
		tp.add(Titel.setTitel("IMPORT_CONFIGURATION"), settingPanel);

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
			Optional<String> optPath = Optional.ofNullable(getFilePath());
			Optional<String> optData = Optional.ofNullable(txtArea.getText());

			if (!optPath.isEmpty() && !optData.isEmpty()) {
				String fileType = settingPanel.fileType();
				String what = settingPanel.sentencesOrWordsAsString();
				List<String[]> listToExecute = new LinkedList<>();

				if (what.equals("SENTENCE")) {

					if (fileType.equals("CSV")) {
						SentenceFromData s = new SentenceFromData(optData);

						if (!s.checkData()) {
							listToExecute = s.arrayList(s.getListFromData());
						}

						if (!listToExecute.isEmpty()) {
							es.submit(new ExecutorStringSentenceIntoDB(listToExecute, bar, getOrder()));
						}
					}
					if (fileType.equals("JSON")) {
						SentencesFromJSON s = new SentencesFromJSON(optData);

						if (!s.checkData()) {
							listToExecute = s.arrayListFromJSON();
						}

						if (!listToExecute.isEmpty()) {
							es.submit(new ExecutorStringSentenceIntoDB(listToExecute, bar, getOrder()));
						}
					}
				}

				if (what.equals("WORDS")) {
					if (fileType.equals("CSV")) {
						// TODO check if list is empty
						// TODO check if word exist
						// TODO prepare list to import
						// TODO import list into database
					}

					if (fileType.equals("JSON")) {
						// TODO check if list is empty
						// TODO check if word exist
						// TODO prepare list to import
						// TODO import list into database
					}
				}
			}

			else {
				new ShowMessage().directMessage("Nie podałeś pliku!");
			}
		}

		else if (src == showBtn) {
			bar.setNull();
			txtArea.setText(null);
			Optional<String> optPath = Optional.ofNullable(getFilePath());

			if (!optPath.isEmpty()) {
				try {
					Files.lines(Paths.get(optPath.get()), StandardCharsets.UTF_8)
							.forEach(s -> txtArea.append(s + "\n"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			txtArea.setCaretPosition(0);
			txtArea.setEditable(true);
		}
	}

	private String getFilePath() {
		return settingPanel.getFilePath();
	}

	private String getGenus() {
		return settingPanel.wordGenus();
	}

	private boolean getOrder() {
		return settingPanel.order();
	}

	private String getOrderAsString() {
		return settingPanel.orderAsString();
	}

	private void clearAll() {
		bar.setNull();
		txtArea.setText(null);
		settingPanel.clear();
	}

}
