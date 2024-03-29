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
import edu.german.tools.MyProgressBar;
import edu.german.tools.MyProperties;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.KeyWord;

public class AddSentences extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String SENTENCE_CFG_PATH = "src/edu/german/sentences/cfg/";
	private String SENTENCE_CFG = "sentence.cfg";
	private String WORD_CFG_PATH = "src/edu/german/words/cfg/";
	private String WORD_CFG = "word.cfg";
	private MyProgressBar mbar;
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
	private EditPanel newSentence;
	private ParamPanel parameterization;
	private KeyWord keyWord;

	public AddSentences(int height, int width, String titel) {
		super(height, width, titel);
		es = Executors.newSingleThreadExecutor();

		String[] headers = { "CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "REMOVE_FROM_LIST", "CLEAR_LIST", "EDIT_ROW",
				"ADD_TO_REPOSITORY" };
		bp = new ButtonsPanel(headers);
		int spread = bp.getMyWidth();
		
		clearEditFieldsBtn = bp.getButtonList().get(0);
		clearEditFieldsBtn.addActionListener(this);
		addToListBtn = bp.getButtonList().get(1);
		addToListBtn.addActionListener(this);
		removeBtn = bp.getButtonList().get(2);
		removeBtn.addActionListener(this);
		clearListBtn = bp.getButtonList().get(3);
		clearListBtn.addActionListener(this);
		editRowBtn = bp.getButtonList().get(4);
		editRowBtn.addActionListener(this);
		addToRepoBtn = bp.getButtonList().get(5);
		addToRepoBtn.addActionListener(this);

		header = new MyProperties(SENTENCE_CFG_PATH, SENTENCE_CFG)
				.getValuesArray("SENTENCE_TABLE_HEADER");

		table = new TableHanlder(header, true);

		JPanel edit = new JPanel();
		edit.setLayout(new GridLayout(3, 1, 2, 2));
		newSentence = new EditPanel();
		parameterization = new ParamPanel();
		keyWord = new KeyWord(Titel.setTitel("WRITE_WORD"), Titel.setTitel("MEANING"),
				new MyProperties(WORD_CFG_PATH, WORD_CFG).getValuesArray("WORD_MODE_LIST"));
		edit.add(newSentence);
		edit.add(parameterization);
		edit.add(keyWord);
		
		JScrollPane scp = new JScrollPane();
		scp.setViewportView(table);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, edit, scp);
		sp.setResizeWeight(new ScreenSetup().BIGGER_SPLIT_PANE_FACTOR);

		toolBar.addSeparator();

		mbar = new MyProgressBar("IMPORT_PROGRESS", spread);
		mbar.setInfo("PROGRESS");

		JPanel rightPan = new JPanel();
		rightPan.setLayout(new GridLayout(2, 1, 10, 10));
		rightPan.add(bp);
		rightPan.add(mbar);

		this.add(sp, BorderLayout.CENTER);
		this.add(rightPan, BorderLayout.EAST);
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
			list.add(newSentence.getSentence());
			list.add(newSentence.getMeaning());
			list.add(parameterization.getKindAsMap());
			list.add(parameterization.getTribeAsMap());
			list.add(parameterization.getTensAsMap());
			list.add(keyWord.getWord());
			list.add(keyWord.getMeaning());
			list.add(keyWord.getBoxValue());

			if (!list.isEmpty()) {
				table.showList(list);
				clearEditFiles();
			} else
				new ShowMessage("EMPTY_FIELDS");
		}

		else if (src == editRowBtn) {
			Map<String, String> var = table.getSelectedRowAsMap();
			newSentence.showData(var);
			parameterization.showData(var);
			keyWord.showData(var);
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
		newSentence.clearFields();
		keyWord.clearEditFields();
	}
}
