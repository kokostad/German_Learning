package edu.german.words;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;

public class AddAdjective extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton addToListBtn;
	private JButton removeFromListBtn;
	private JButton clearListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JTabbedPane tb;
	private AdjectiveGradation ag;

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

		ag = new AdjectiveGradation();

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		new Titles();
		tb.add(Titles.setTitel("ADJECTIVE_GRADATION"), ag);

		add(tb, BorderLayout.CENTER);
		add(bp, BorderLayout.EAST);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			ag.clearEditFields();
		}

		else if (src == addToListBtn) {
			ag.showRow(ag.getData());
			ag.clearEditFields();
		}

		else if (src == removeFromListBtn) {
			if (ag.getIdx() > -1)
				ag.removeRow();
		}

		else if (src == clearListBtn) {
			ag.clearWordsList();
			ag.clearTable();
		}

		else if (src == editRowBtn) {
			String[] array = ag.getSelectedRowAsArray();
			ag.showData(array);
			if (ag.getIdx() > -1)
				ag.removeRow();
		}

		else if (src == addToRepoBtn) {
			List<HashMap<String, String>> list = ag.getDataAsMapList("WORD");
			if (!list.isEmpty()) {
				AddWordsToDatabase addToRepo = new AddWordsToDatabase("das Adjektiv");
				addToRepo.addWordsList(list);

				ag.clearWordsList();
				ag.clearTable();
			}
		}

	}
}
