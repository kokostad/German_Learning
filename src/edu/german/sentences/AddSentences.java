package edu.german.sentences;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.services.ExecutorPutSentenceIntoDatabase;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordEditPanel;

public class AddSentences extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String SENTENCE_CFG = "sentence.properties";
	private String WORD_CFG = "word.properties";
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JButton removeBtn;
	private TableHanlder table;
	private String[] header;
	private List<HashMap<String, String>> mapList;
	private ExecutorService es;
	private SentenceEditPanel editSentence;
	private SentenceParamPanel sentenceParam;
	private WordEditPanel editWord;

	public AddSentences(int height, int width, String titel) {
		super(height, width, titel);
		es = Executors.newSingleThreadExecutor();

		bp = new ButtonsPanel("CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "REMOVE_FROM_LIST", "CLEAR_LIST", "EDIT_ROW",
				"ADD_TO_REPOSITORY");
		clearEditFieldsBtn = bp.getB1();
		clearEditFieldsBtn.addActionListener(this);
		addToListBtn = bp.getB2();
		addToListBtn.addActionListener(this);
		removeBtn = bp.getB3();
		removeBtn.addActionListener(this);
		clearListBtn = bp.getB4();
		clearListBtn.addActionListener(this);
		editRowBtn = bp.getB5();
		editRowBtn.addActionListener(this);
		addToRepoBtn = bp.getB6();
		addToRepoBtn.addActionListener(this);

		header = new MyProperties(SENTENCE_CFG).getValuesArray("SENTENCE_TABLE_HEADER");

		table = new TableHanlder(header, true);

		JPanel edit = new JPanel();
		edit.setLayout(new GridLayout(3, 1, 2, 2));
		editSentence = new SentenceEditPanel();
		sentenceParam = new SentenceParamPanel();
		editWord = new WordEditPanel(Titles.setTitel("WRITE_WORD"), Titles.setTitel("MEANING"),
				new MyProperties(WORD_CFG).getValuesArray("WORD_MODE_LIST"));
		edit.add(editSentence);
		edit.add(sentenceParam);
		edit.add(editWord);

		JScrollPane scp = new JScrollPane();
		scp.setViewportView(table);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, edit, scp);
		sp.setResizeWeight(new ScreenSetup().BIGGER_SPLIT_PANE_FACTOR);

		toolBar.addSeparator();

		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
		this.setVisible(true);
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			clearEditFiles();
		}

		else if (src == addToListBtn) {
			List<Map<Object, Object>> list = new LinkedList<>();
			list.add(editSentence.getSentence());
			list.add(editSentence.getMeaning());
			list.add(sentenceParam.getKindAsMap());
			list.add(sentenceParam.getTribeAsMap());
			list.add(sentenceParam.getTensAsMap());
			list.add(editWord.getWord());
			list.add(editWord.getMeaning());
			list.add(editWord.getBoxValue());

			if (!list.isEmpty()) {
				table.showList(list);
				clearEditFiles();
			} else
				new ShowMessage("EMPTY_FIELDS");
		}

		else if (src == editRowBtn) {
			Map<String, String> var = table.getSelectedRowAsMap();
			editSentence.showData(var);
			sentenceParam.showData(var);
			editWord.showData(var);
			if (table.getIdx() > -1)
				table.removeRow();
		}

		else if (src == addToRepoBtn) {
			mapList = table.getDataAsMapList();
			if (!mapList.isEmpty()) {
				es.submit(new ExecutorPutSentenceIntoDatabase(mapList));
				table.clearWordsList();
				table.clearTable();
			}
		}
	}

	private void clearEditFiles() {
		editSentence.clearEditFields();
		editWord.clearEditFields();
	}

}
