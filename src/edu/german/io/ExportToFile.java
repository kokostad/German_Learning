package edu.german.io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
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

import org.json.JSONObject;

import edu.german.services.GetWordsAsList;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProgressBar;
import edu.german.tools.ScreenSetup;
import edu.german.tools.SentenceJSONParser;
import edu.german.tools.ShowMessage;
import edu.german.tools.buttons.ButtonsPanel;

public class ExportToFile extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearEditFieldBtn;
	private JButton showBtn;
	private JButton exportBtn;
	private JTextArea txtArea;
	private ExecutorService es;
	private JTabbedPane tp;
	private MyProgressBar bar;
	private List<String> toExport;
	private String genos;
	private ExportConfigPanel exportConfigPanel;

	public ExportToFile(int height, int width, String titel) {
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

		exportConfigPanel = new ExportConfigPanel();

		tp = new JTabbedPane();
		tp.add("Konfiguracja parametrów eksportu", exportConfigPanel);

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

		bar = new MyProgressBar("Postęp eksportu:");

		JPanel rightPan = new JPanel();
		rightPan.setLayout(new GridLayout(2, 1, 10, 10));
		rightPan.add(bp);
		rightPan.add(bar);

		this.add(sp, BorderLayout.CENTER);
		this.add(rightPan, BorderLayout.EAST);
	}

	private String getFilePath() {
		return exportConfigPanel.getFilePath();
	}

	private void clearAll() {
		bar.setNull();
		txtArea.setText(null);
		toExport.clear();
		exportConfigPanel.clear();
	}

	private List<String> prepareDataToExport(HashMap<String, String> var) {
		List<String> list = new LinkedList<>();
		Optional<String> option = var.entrySet().stream().filter(e -> "word".equals(e.getValue()))
				.map(Map.Entry::getKey).findFirst();

		if (option.isPresent()) {
			list = new GetWordsAsList().getAllWordListToExport();
		} else {
			list = new GetListOfSentences().getList(genos);
		}

		return list;
	}

	private void exportToCSVFile(String exportKind) {
		if (getFilePath() == null || toExport.isEmpty()) {
			new ShowMessage("NO_DATA", "uzupełnij brakujące dane");
		} else if (getFilePath() != null && !toExport.isEmpty()) {
			es.submit(new ExportDataToFile(toExport, getFilePath(), exportKind));
			clearAll();
		} else if (getFilePath() != null && toExport.isEmpty()) {
			HashMap<String, String> var = exportConfigPanel.getParam();
			toExport = prepareDataToExport(var);
			es.submit(new ExportDataToFile(toExport, getFilePath(), exportKind));
			clearAll();
		}
	}

	// TODO change and improve this method and export into other class
	private void exportToJSONFile(JSONObject var) {
		try {
			String val = var.toString();
			FileWriter writer = new FileWriter(new File(getFilePath()), true);
			writer.write(val);
			writer.write("\n");
			writer.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldBtn) {
			clearAll();
			repaint();
		}

		else if (src == showBtn) {
			HashMap<String, String> var = exportConfigPanel.getParam();
			toExport = prepareDataToExport(var);
			toExport.forEach((s) -> txtArea.append(s));
		}

		else if (src == exportBtn) {
			String exportKind = exportConfigPanel.getExportKind();
			if (exportKind.equals("CSV")) {
				exportToCSVFile(exportKind);
			} else {
				// TODO you need to find some resolve
				int lines = txtArea.getLineCount();
				StringReader sr = new StringReader(txtArea.getText());
				BufferedReader br = new BufferedReader(sr);
				String nextLine = "";
				try {
					while ((nextLine = br.readLine()) != null) {
						JSONObject var = new SentenceJSONParser(nextLine).getJSONItem();
						exportToJSONFile(var);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
	}
}
