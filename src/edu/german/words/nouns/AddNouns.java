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
import edu.german.words.Noun;

public class AddNouns extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String PATH = "src/edu/german/words/cfg/";
	private String CFG_FILE = "word.properties";
	private ButtonsPanel bp;
	private JButton check;
	private JButton clearEditFields;
	private JButton clearList;
	private JButton addToList;
	private JButton putListIntoRepo;
	private NounEditPanel editPanel;
	private int id;
	private List<Noun> words;
	private List<Properties> propertyList;
	private String[] header;
	private TableHanlder tb;
	private ExecutorService es;

	public AddNouns(int height, int width, String setTitel) {
		super(height, width, setTitel);
		es = Executors.newSingleThreadExecutor();
		id = -1;
		propertyList = new LinkedList<Properties>();
		words = new LinkedList<Noun>();

		bp = new ButtonsPanel("CHECK_IN_DATABASE", "CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "CLEAR_LIST",
				"ADD_LIST_TO_REPOSITORY");
		check = bp.getB1();
		check.addActionListener(this);
		clearEditFields = bp.getB2();
		clearEditFields.addActionListener(this);
		addToList = bp.getB3();
		addToList.addActionListener(this);
		clearList = bp.getB4();
		clearList.addActionListener(this);
		putListIntoRepo = bp.getB5();
		putListIntoRepo.addActionListener(this);

		editPanel = new NounEditPanel();

		JPanel workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		workPanel.setLayout(new BorderLayout());
		workPanel.add(editPanel, BorderLayout.NORTH);

		header = new MyProperties(PATH, CFG_FILE).getValuesArray("NOUN_SIMPLE_TABLE");
		tb = new TableHanlder(header, true);
		JScrollPane scp = new JScrollPane(tb);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, scp);

		this.add(sp, BorderLayout.CENTER);
		this.add(bp, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == check) {
			id = new Noun().getOid(editPanel.getWordSingular());

			if (id > 0)
				editPanel.setValues(new Noun().nounPreparedById(id));
		}

		else if (src == clearEditFields) {
			editPanel.clearEditFields();
		}

		else if (src == addToList) {
			Noun noun = new Noun();
			if (id > 0)
				noun.setOid(id);

			noun.setWord(editPanel.getWordSingular());
			noun.setMeaning(editPanel.getMeanigSingular());
			noun.setWordPlural(editPanel.getWordPlural());
			noun.setMeanigPlural(editPanel.getMeanigPlural());
			words.add(noun);

			if (words != null) {
				showData(words);
				editPanel.clearEditFields();
			}
		}

		else if (src == clearList) {
			words.clear();
			propertyList.clear();
			tb.clearTable();
		}

		else if (src == putListIntoRepo) {
			if (words != null)
				es.submit(new ExecutorPutNounIntoDatabase(words));

			tb.clearTable();
		}
	}

	private void showData(List<Noun> nouns) {
		for (Noun noun : nouns) {
			String[] row = { noun.getWord(), noun.getMeaning(), noun.getWordPlural(), noun.getMeaningPlural() };
			tb.showRow(row);
		}
		repaint();
	}

}
