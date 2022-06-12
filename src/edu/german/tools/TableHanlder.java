package edu.german.tools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 * The class to handle a simple table
 * the class presents a list of objects according to the header table
 */
public class TableHanlder extends JTable {
	private static final long serialVersionUID = 1L;
	private static final String FILE_CFG = "translation_table.properties";
	private DefaultTableModel model;
	private String[] headers;
	private List<String[]> wordsList;

	public TableHanlder(String[] headers) {
		this.headers = headers;
		wordsList = new LinkedList<String[]>();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(translateArray(headers));
		setModel(model);
	}

	public void showRow(String[] row) {
		if (row != null && row.length > 0) {
			if (row.length > 1) {
				model.addRow(row);
				wordsList.add(row);
			}
		}
	}

	public void showMap(Map<String, String> map) {
		int i = 0;
		String[] row = new String[headers.length];
		for (String str : headers)
			if (map.containsKey(str))
				row[i++] = map.get(str);

		showRow(row);
	}

	public int getRowCount() {
		return model.getRowCount();
	}

	public void clearTable() {
		model.setRowCount(0);
	}

	private String translate(String str) {
		return new MyProperties(FILE_CFG).getValue(str);
	}

	private String[] translateArray(String[] arr) {
		String[] array = new String[arr.length];
		int i = 0;
		for (String s : arr) {
			array[i++] = translate(s);
		}

		return array;
	}

	public int getIdx() {
		return this.getSelectedRow();
	}

	public String[] getSelectedRowAsArray() {
		int idx = getIdx();
		String[] array = new String[headers.length];

		for (int i = 0; i < headers.length; i++)
			array[i] = (String) this.getValueAt(idx, i);

		return array;
	}

	public void removeRow() {
		wordsList.remove(getIdx());
		int[] row = this.getSelectedRows();
		TableModel tm = this.getModel();
		((DefaultTableModel) tm).removeRow(this.convertRowIndexToModel(row[0]));
		this.clearSelection();
	}

	public void clearWordsList() {
		wordsList.clear();
	}

	public List<HashMap<String, String>> getDataAsMap() {
		List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();

		for (int i = 0; model.getRowCount() > i; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < model.getColumnCount(); j++) {
				map.put(headers[j].toUpperCase(), (String) model.getValueAt(i, j));
			}
			list.add(map);
		}

		return list;
	}

}
