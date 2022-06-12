package edu.german.words;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.ShowMessage;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;

/**
 * @author <a href="mailto:t.kokotowski@gmail.com">Tadeusz Kokotowski</a> Simple
 *         word addition
 * 
 */
public class SimpleWordAddition extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "words_genus.properties";
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JButton removeBtn;
	private EditWordsPanel editPanel;
	private String[] tableHeaders;
	private TableHanlder st;

	public SimpleWordAddition(int height, int width, String title) {
		super(height, width, title);
		bp = new ButtonsPanel("CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "REMOVE_FROM_LIST", "CLEAR_LIST", "EDIT_ROW",
				"ADD_TO_REPOSITORY");
		bp.setFontSize(20);
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

		tableHeaders = new MyProperties(FILE_NAME).getValuesArray("SIMPLE_WORDS_TABLE_HEADERS");

		st = new TableHanlder(tableHeaders);
		JScrollPane scp = new JScrollPane();
		scp.setViewportView(st);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		String[] selectionList = new MyProperties(FILE_NAME).getValuesArray("GENUS_LIST");
		editPanel = new EditWordsPanel("Wpisz słowo", "Wpisz znaczenie", selectionList);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, scp);

		add(sp, BorderLayout.CENTER);
		add(bp, BorderLayout.EAST);
		setVisible(true);
		repaint();
	}

	private void clearEditFields() {
		editPanel.setEditFields(null);
		editPanel.setEditFields(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			clearEditFields();
		} else if (src == addToListBtn) {
			if (editPanel.checkCorrectness()) {
				st.showRow(editPanel.getValuAsArray());
				clearEditFields();
			} else
				new ShowMessage("EMPTY_FIELDS");

		} else if (src == clearListBtn) {
			st.clearTable();
		} else if (src == removeBtn) {
			if (st.getIdx() > -1)
				st.removeRow();
		} else if (src == clearListBtn) {
			st.clearWordsList();
			st.clearTable();
		} else if (src == editRowBtn) {
			String[] array = st.getSelectedRowAsArray();
			editPanel.showData(array[0].toString(), array[1].toString(), array[2].toString());
			if (st.getIdx() > -1)
				st.removeRow();
		} else if (src == addToRepoBtn) {
			List<HashMap<String, String>> list = st.getDataAsMap();
			if (!list.isEmpty()) {
				AddWordsToDatabase addToRepo = new AddWordsToDatabase();
				addToRepo.addWordsList(list);
				st.clearWordsList();
				st.clearTable();
			}

		}

	}

}
