package edu.german.words;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.util.HashMap;
import java.util.List;

import edu.german.tools.MyProperties;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titles;
import edu.german.tools.TwoEditableFieldsPanel;

public class AdjectiveGradation extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "table_headers.properties";
	private TableHanlder st;
	private TwoEditableFieldsPanel equel;
	private TwoEditableFieldsPanel comparative;
	private TwoEditableFieldsPanel supreme;

	public AdjectiveGradation() {
		String[] tableHeaders = new MyProperties(FILE_NAME).getValuesArray("ADJEKTIV_GRADUATION");
		st = new TableHanlder(tableHeaders, true);

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

		this.setLayout(new GridLayout(1, 1));
		this.add(sp);
	}

	public void showData(String[] array) {
		equel.setAdjective(array[0]);
		equel.setMeaning(array[1]);
		comparative.setAdjective(array[2]);
		comparative.setMeaning(array[3]);
		supreme.setAdjective(array[4]);
		supreme.setMeaning(array[5]);
	}

	public void clearEditFields() {
		equel.clear();
		comparative.clear();
		supreme.clear();
	}

	public String[] getData() {
		String[] array = new String[6];
		array[0] = equel.getAdjective();
		array[1] = equel.getMeaning();
		array[2] = comparative.getAdjective();
		array[3] = comparative.getMeaning();
		array[4] = supreme.getAdjective();
		array[5] = supreme.getMeaning();

		return array;
	}

	public void showRow(String[] data) {
		st.showRow(data);
	}

	public int getIdx() {
		return st.getIdx();
	}

	public void removeRow() {
		st.removeRow();
	}

	public void clearWordsList() {
		st.clearTable();
	}

	public void clearTable() {
		st.clearTable();
	}

	public String[] getSelectedRowAsArray() {
		return st.getSelectedRowAsArray();
	}

	public List<HashMap<String, String>> getDataAsMapList(String string) {
		return st.getDataAsMapList();
	}

}
