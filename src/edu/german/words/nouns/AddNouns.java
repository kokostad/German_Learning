package edu.german.words.nouns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.services.ExecutorPutNounIntoDatabase;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.TableHanlder;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.model.Noun;
import edu.german.words.model.Word;

public class AddNouns extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton checkBtn;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton addListToRepoBtn;
	private NounEditPanel editPanel;
	private int id;
	private List<Noun> wordList;
	private List<Properties> propertiesList;
	private String[] header;
	private TableHanlder tb;
	private ExecutorService es;

	public AddNouns(int height, int width, String setTitel) {
		super(height, width, setTitel);
		es = Executors.newSingleThreadExecutor();
		id = -1;
		propertiesList = new LinkedList<Properties>();
		wordList = new LinkedList<Noun>();

		bp = new ButtonsPanel("CHECK_IN_DATABASE", "CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "CLEAR_LIST",
				"ADD_LIST_TO_REPOSITORY");
		checkBtn = bp.getB1();
		checkBtn.addActionListener(this);
		clearEditFieldsBtn = bp.getB2();
		clearEditFieldsBtn.addActionListener(this);
		addToListBtn = bp.getB3();
		addToListBtn.addActionListener(this);
		clearListBtn = bp.getB4();
		clearListBtn.addActionListener(this);
		addListToRepoBtn = bp.getB5();
		addListToRepoBtn.addActionListener(this);

		editPanel = new NounEditPanel();

		JPanel workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		workPanel.setLayout(new BorderLayout());
		workPanel.add(editPanel, BorderLayout.NORTH);

		header = new MyProperties("word.properties").getValuesArray("NOUN_SIMPLE_TABLE");
		tb = new TableHanlder(header, true);
		JScrollPane scp = new JScrollPane(tb);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, scp);

		this.add(sp, BorderLayout.CENTER);
		this.add(bp, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == checkBtn) {
			id = new Noun().getOid(editPanel.getWordSingular());

			if (id > 0) {
				editPanel.setValues(new Noun().prepareById(id));
			}
		}

		else if (src == clearEditFieldsBtn) {
			editPanel.clearEditFields();
		}

		else if (src == addToListBtn) {
			Noun noun = new Noun();
			if (id < 0) {
				id = new Noun().getOid(editPanel.getWordSingular());
				noun.setOid(id);
			}
//			noun.setOid(-1);
			noun.setWord(editPanel.getWordSingular());
			noun.setMeaning(editPanel.getMeanigSingular());
			noun.setWordPlural(editPanel.getWordPlural());
			noun.setMeanigPlural(editPanel.getMeanigPlural());
			wordList.add(noun);

			if (wordList != null) {
				showList(wordList);
				editPanel.clearEditFields();
			}
		}

		else if (src == clearListBtn) {
			propertiesList.clear();
			tb.clearTable();
		}

		else if (src == addListToRepoBtn) {
			if (wordList != null)
				es.submit(new ExecutorPutNounIntoDatabase(wordList));
		}
	}

	private void showList(List<Noun> wordList) {
		for (Noun word : wordList) {
			String[] fullWord = { word.getWord(), word.getMeaning(), word.getWordPlural(), word.getMeaningPlural() };
			tb.showRow(fullWord);
		}
		repaint();
	}

}
