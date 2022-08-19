package edu.german.sentences;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.ContextNotEmptyException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.tools.AddRule;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditableField;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.tools.buttons.RulesButton;
import edu.german.words.AddWordsToDatabase;

public class AddPurposefulSentences extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String CFG_FILE = "sentence.properties";
	private ButtonsPanel bp;
	private SentenceEditPanel sentenceEditPanel;
	private OneEditableField sentence;
	private OneEditableField meaning;
	private TableHanlder st;
	private String[] tableHeaders;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JButton removeBtn;
	private RulesButton rulesBtn;
	private List<String[]> sentenceList;
	private List<HashMap<String, String>> mapList;

	public AddPurposefulSentences(int height, int width, String setTitel) {
		super(height, width, setTitel);
		mapList = new LinkedList<>();
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

		tableHeaders = new MyProperties(CFG_FILE).getValuesArray("TABLE_HEADER_PURP");
		st = new TableHanlder(tableHeaders, false);

		sentenceEditPanel = new SentenceEditPanel(tableHeaders.length, null, null);

		JScrollPane scp = new JScrollPane();
		scp.setViewportView(st);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sentenceEditPanel, scp);
		sp.setResizeWeight(0.5);

		rulesBtn = new RulesButton();
		rulesBtn.addActionListener(this);
		tb.addSeparator();
		tb.add(rulesBtn);
		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	private void clearEditFiles() {
		sentenceEditPanel.clearEditFields();
	}

	private String[] getValues() {
		return sentenceEditPanel.getValues();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			clearEditFiles();
		}

		else if (src == addToListBtn) {
			String[] var = sentenceEditPanel.getValuesAsArray();
			if (var != null) {
				st.showRow(var);
				clearEditFiles();
			}
		}

		else if (src == clearListBtn) {
			st.clearTable();
		}

		else if (src == removeBtn) {
			if (st.getIdx() > -1)
				st.removeRow();
		}

		else if (src == editRowBtn) {
//			String[] array = st.getSelectedRowAsArray();
//			sentenceEditPanel.showData(array[0].toString(), array[1].toString(), array[2].toString());
			Map<String, String> map = st.getSelectedRowAsMap();
			sentenceEditPanel.showData(map);
			if (st.getIdx() > -1)
				st.removeRow();
		}

		else if (src == addToRepoBtn) {
			mapList = st.getDataAsMapList();
			if (!mapList.isEmpty()) {
				AddSenteceToDatabase addToRepo = new AddSenteceToDatabase();
				addToRepo.addList(mapList);
				st.clearWordsList();
				st.clearTable();
				mapList.clear();
			}

		}

		else if (src == rulesBtn) {
			int hight = this.getParent().getHeight();
			int width = this.getParent().getWidth();
			AddRule ar = new AddRule(hight, width, Titles.setTitel("ADD_RULES"));
			getDesktopPane().add(ar);
			getDesktopPane().moveToFront(ar);
			getDesktopPane().repaint();

		}

	}

	private void clearEditFiles() {
		sentenceEditPanel.clearEditFields();
	}

	private String[] getValues() {
		return sentenceEditPanel.getValuesAsArray();
	}

}
