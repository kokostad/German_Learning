package edu.german.words;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titles;
import edu.german.tools.TwoEditableFieldsPanel;
import edu.german.tools.buttons.ButtonsPanel;

public class AddAdjective extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "table_headers.properties";
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton addToListBtn;
	private JButton removeFromListBtn;
	private JButton clearListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private TableHanlder st;
	private TwoEditableFieldsPanel equel;
	private TwoEditableFieldsPanel comparative;
	private TwoEditableFieldsPanel supreme;

	public AddAdjective(int height, int width, String setTitel) {
		super(height, width, setTitel);
		bp = new ButtonsPanel("CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "REMOVE_FROM_LIST", "CLEAR_LIST", "EDIT_ROW",
				"ADD_TO_REPOSITORY");
		bp.setFontSize(20);

		clearEditFieldsBtn = bp.getB1();
		clearEditFieldsBtn.addActionListener(this);
		addToListBtn = bp.getB2();
		addToListBtn.addActionListener(this);
		removeFromListBtn = bp.getB3();
		removeFromListBtn.addActionListener(this);
		clearListBtn = bp.getB4();
		clearListBtn.addActionListener(this);
		editRowBtn = bp.getB5();
		editRowBtn.addActionListener(this);
		addToRepoBtn = bp.getB6();
		addToRepoBtn.addActionListener(this);

		String[] tableHeaders = new MyProperties(FILE_NAME).getValuesArray("ADJEKTIV_GRADATION");
		st = new TableHanlder(tableHeaders, false);

		JScrollPane scp = new JScrollPane();
		scp.setViewportView(st);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel editPanel = new JPanel();
		editPanel.setLayout(new GridLayout(1, 3, 5, 5));
		equel = new TwoEditableFieldsPanel(Titles.setTitel("EQUEL_DEGREE"), "przymiotnik", " znaczenie");
		comparative = new TwoEditableFieldsPanel(Titles.setTitel("COMPARATIVE_DEGREE"), "przymiotnik", " znaczenie");
		supreme = new TwoEditableFieldsPanel(Titles.setTitel("SUPREME_DEGREE"), "przymiotnik", " znaczenie");
		editPanel.add(equel);
		editPanel.add(comparative);
		editPanel.add(supreme);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, scp);
		sp.setResizeWeight(0.3);

		add(sp, BorderLayout.CENTER);
		add(bp, BorderLayout.EAST);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			clearEditFields();
		}

		else if (src == addToListBtn) {
			st.showRow(getData());
			clearEditFields();
		}

		else if (src == removeFromListBtn) {
			if (st.getIdx() > -1)
				st.removeRow();
		}

		else if (src == clearListBtn) {
			st.clearWordsList();
			st.clearTable();
		}

		else if (src == editRowBtn) {
			String[] array = st.getSelectedRowAsArray();
			showData(array);
			if (st.getIdx() > -1)
				st.removeRow();
		}

		// NOTICE you have to redo it, match the keys for the map
		else if (src == addToRepoBtn) {
			List<HashMap<String, String>> list = st.getDataAsMapList();
			if (!list.isEmpty()) {
				// TODO need to improve this resolve
				AddWordsToDatabase addToRepo = new AddWordsToDatabase();
				addToRepo.addWordsList(list);
				for (HashMap<String, String> hashMap : list) {
					hashMap.entrySet().forEach(entry -> {
						System.out.println(entry.getKey() + ": " + entry.getValue());
					});
				}
				st.clearWordsList();
				st.clearTable();
			}
		}

	}

	private void showData(String[] array) {
		equel.setAdjective(array[0]);
		equel.setMeaning(array[1]);
		comparative.setAdjective(array[2]);
		comparative.setMeaning(array[3]);
		supreme.setAdjective(array[4]);
		supreme.setMeaning(array[5]);
	}

	private void clearEditFields() {
		equel.clear();
		comparative.clear();
		supreme.clear();
	}

	private String[] getData() {
		String[] array = new String[6];
		array[0] = equel.getAdjective();
		array[1] = equel.getMeaning();
		array[2] = comparative.getAdjective();
		array[3] = comparative.getMeaning();
		array[4] = supreme.getAdjective();
		array[5] = supreme.getMeaning();

		return array;
	}

}
