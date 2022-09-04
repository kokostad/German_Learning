package edu.german.sentences;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.tools.AddRule;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.tools.buttons.RulesButton;

public class AddSentences extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "sentence.properties";
	private SentenceEditPanel edit;
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JButton removeBtn;
	private RulesButton rulesBtn;
	private TableHanlder st;
	private String[] tableHeaders;
	private List<HashMap<String, String>> mapList;

	public AddSentences(int height, int width, String titel) {
		super(height, width, titel);
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

		tableHeaders = new MyProperties(CFG_FILE).getValuesArray("TABLE_HEADER");
		st = new TableHanlder(tableHeaders, false);

		edit = new SentenceEditPanel(tableHeaders.length, "CHOOSE_SENTENCE_GENUS_LIST", "TIMES");

		JScrollPane scp = new JScrollPane();
		scp.setViewportView(st);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, edit, scp);
		sp.setResizeWeight(0.5);

		rulesBtn = new RulesButton();
		rulesBtn.addActionListener(this);
		toolBar.addSeparator();
		toolBar.add(rulesBtn);
		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			clearEditFiles();
		}

		else if (src == addToListBtn) {
			String[] var = edit.getValuesAsArray();
			if (var != null) {
				st.showRow(var);
				clearEditFiles();
			}
		}

		else if (src == editRowBtn) {
//			String[] array = st.getSelectedRowAsArray();
//			edit.showData(array[0].toString(), array[1].toString(), array[2].toString(), array[3].toString(),
//					array[4].toString());

			Map<String, String> map = st.getSelectedRowAsMap();

			edit.showData(map);
			if (st.getIdx() > -1)
				st.removeRow();
		}

		else if (src == addToRepoBtn) {
			mapList = st.getDataAsMap();
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
		edit.clearEditFields();
	}

}
