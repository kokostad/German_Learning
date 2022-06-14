package edu.german.tools;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import edu.german.tools.buttons.ButtonsPanel;

public class AddRules extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static String FILE_CFG = "object_list.properties";
	private ButtonsPanel bp;
	private JButton clearBtn;
	private JButton addBtn;
	private JTextArea area;
	private OneEditableField titles;
	private MyComboBox box;
	private boolean state;

	public AddRules(int height, int width, String titel) {
		super(height, width, titel);
		bp = new ButtonsPanel("CLEAR_EDIT_FIELD", "ADD_TO_REPOSITORY");
		clearBtn = bp.getB1();
		clearBtn.addActionListener(this);
		addBtn = bp.getB2();
		addBtn.addActionListener(this);

		titles = new OneEditableField("Podaj tytuł zasady", "Czego dotyczy zasada", 16, 30);
		String[] tableHeaders = new MyProperties(FILE_CFG).getValuesArray("ALL_OBJECTS");
		box = new MyComboBox("Zasada dotyczy: ", tableHeaders);

		JPanel upPanel = new JPanel();
		upPanel.add(titles);
		upPanel.add(box);

		area = new JTextArea();
		area.setBounds(10, 30, 200, 200);

		JScrollPane scp = new JScrollPane(area);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPanel, scp);

		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);

		setVisible(true);
		repaint();
	}

	private void clearEditFiles() {
		area.setText(null);
		titles.clearField();
	}

	private HashMap<String, String> getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		if (testData(titles.getValue()))
			map.put("TITLES", titles.getValue());

		if (testData(box.getValue()))
			map.put("REFERS_TO_1", box.getValue());

		if (testData(area.getText()))
			map.put("CONTENTS", area.getText());

		return map;
	}

	private boolean testData(String s) {
		if (s != null)
			return true;

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearBtn) {
			clearEditFiles();
		}

		else if (src == addBtn) {
			if (!testData(titles.getValue()) || !testData(box.getValue()) || !testData(area.getText())) {
				new ShowMessage("EMPTY_FIELDS");
			} else {
				HashMap<String, String> var = getData();
				WriteRulesToRepository write = new WriteRulesToRepository();
				write.addToRepo(var);
				clearEditFiles();
			}
		}

	}

}
