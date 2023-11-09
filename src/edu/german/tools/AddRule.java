package edu.german.tools;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import edu.german.tools.buttons.ButtonsPanel;

public class AddRule extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearBtn;
	private JButton addBtn;
	private JTextArea area;
	private OneEditField titles;
	private OneEditField tips;
	private int editFieldWidth = 440;
	private int editFieldHeight = 30;
	private Font font = new ScreenSetup().DEFAULT_FONT;

	public AddRule(int height, int width, String titel) {
		super(height, width, titel);
		ScreenSetup scr = new ScreenSetup();
		String[] headers = { "CLEAR_EDIT_FIELD", "ADD_TO_REPOSITORY" };
		bp = new ButtonsPanel(headers);
		clearBtn = bp.getButtonList().get(0);
		clearBtn.addActionListener(this);
		addBtn = bp.getButtonList().get(1);
		addBtn.addActionListener(this);

		editFieldHeight = scr.EDIT_FIELD_WIDTH;
		editFieldHeight = scr.EDIT_FIELD_HEIGHT;
		int fontSize = scr.DEFAULT_FONT_SIZE;
		double factor = scr.SPLIT_PANE_FACTOR;

		titles = new OneEditField.Builder()
				.withTitle("Podaj tytuł reguły")
				.withHint(null)
				.withFont(font)
				.withWidth(editFieldWidth)
				.withHeight(editFieldHeight)
				.build();

		tips = new OneEditField.Builder()
				.withTitle("Wskazówka")
				.withHint("Czego dotyczy")
				.withFont(font)
				.withWidth(editFieldWidth)
				.withHeight(editFieldHeight)
				.build();

		JPanel upPanel = new JPanel();
		upPanel.add(titles);
		upPanel.add(tips);

		area = new JTextArea();
		area.setFont(new MyFont().myFont(fontSize));

		JScrollPane scp = new JScrollPane(area);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPanel, scp);
		sp.setResizeWeight(factor);

		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);

		setVisible(true);
		repaint();
	}

	private void clearEditFiles() {
		area.setText(null);
		titles.clearField();
		tips.clearField();
	}

	private HashMap<String, String> getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		if (testData(titles.getValue()))
			map.put("TITLE", titles.getValue());

		if (testData(tips.getValue()))
			map.put("TIPS", tips.getValue());

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
			HashMap<String, String> map = getData();
			if (map.size() < 2) {
				new ShowMessage("EMPTY_FIELDS");
			} else if (!map.isEmpty()) {
				new WriteRulesToRepository().addToRepo(map);
				clearEditFiles();
			}

		}
	}

}
