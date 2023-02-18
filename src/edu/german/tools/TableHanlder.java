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
 * The class to handle a simple table and presents a list of objects
 * according to the header table
 */
public class TableHanlder extends JTable {
	private static final long serialVersionUID = 1L;
	private static final String FILE_CFG = "translation_table.properties";
	private DefaultTableModel model;
	private String[] header;
	private List<String[]> wordsList;

	/**
	 * @param header
	 * @param translated header or not, depends on translate parameters
	 */
	public TableHanlder(String[] header, boolean translate) {
		this.header = header;
		wordsList = new LinkedList<String[]>();
		model = new DefaultTableModel();

		if (translate)
			model.setColumnIdentifiers(translateArray(header));
		else
			model.setColumnIdentifiers(header);

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
		String[] row = new String[header.length];
		for (String str : header)
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
		return new MyProperties(FILE_CFG).getText(str);
	}

	private String[] translateArray(String[] arr) {
		String[] array = new String[arr.length];
		int i = 0;
		for (String s : arr)
			array[i++] = translate(s);

		return array;
	}

	public int getIdx() {
		return this.getSelectedRow();
	}

	public String[] getSelectedRowAsArray() {
		int idx = getIdx();
		String[] array = new String[header.length];

		for (int i = 0; i < header.length; i++)
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

	public List<HashMap<String, String>> getDataAsMapList() {
		List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();

		for (int i = 0; model.getRowCount() > i; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < model.getColumnCount(); j++)
				map.put(header[j].toUpperCase(), (String) model.getValueAt(i, j));

			list.add(map);
		}

		return list;
	}

	public List<HashMap<String, String>> getDataAsMapList(String sign) {
		List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();

		for (int i = 0; model.getRowCount() > i; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < model.getColumnCount(); j++)
				map.put(header[j].toUpperCase(), (String) model.getValueAt(i, j));

			if (sign.equals("WORD")) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					if (!map.containsKey("WORD"))
						map.put("WORD", (String) model.getValueAt(i, 0));
					if (!map.containsKey("MEANING"))
						map.put("WORD_MEANING", (String) model.getValueAt(i, 1));
				}
			}

			list.add(map);
		}

		return list;
	}

	public Map<String, String> getSelectedRowAsMap() {
		int idx = getIdx();
		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < header.length; i++)
			map.put(header[i].toUpperCase(), (String) this.getValueAt(idx, i));

		return map;
	}

	public void showObjectMap(Map<Object, Object> map) {
		String[] row = new String[header.length];

		map.entrySet().forEach(entry -> {
			String key = ((entry.getKey()).toString()).toUpperCase();
			String val = (entry.getValue()).toString();

			for (int i = 0; i < header.length; i++)
				if (key.equals(header[i].toUpperCase()))
					row[i] = val;
		});

		showRow(row);
	}

	public void showList(List<Map<Object, Object>> list) {
		Map<Object, Object> mapTmp = new HashMap<Object, Object>();

		for (Map<Object, Object> map : list)
			if (map != null)
				map.forEach((key, value) -> mapTmp.put(key, value));

		showObjectMap(mapTmp);
	}

}
