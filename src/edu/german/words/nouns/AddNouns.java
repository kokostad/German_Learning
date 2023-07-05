package edu.german.words.nouns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.model.Noun;

public class AddNouns extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton checkBtn;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton showListBtn;
	private JButton addListToRepoBtn;
	private NounEditPanel editPanel;
	private MainNounPanel nounPanel;
	private List<Properties> list;

	public AddNouns(int height, int width, String setTitel) {
		super(height, width, setTitel);

		bp = new ButtonsPanel("CHECK_IN_DATABASE", "CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "SHOW_LIST", "CLEAR_LIST",
				"ADD_LIST_TO_REPOSITORY");
		checkBtn = bp.getB1();
		checkBtn.addActionListener(this);
		clearEditFieldsBtn = bp.getB2();
		clearEditFieldsBtn.addActionListener(this);
		addToListBtn = bp.getB3();
		addToListBtn.addActionListener(this);
		showListBtn = bp.getB4();
		showListBtn.addActionListener(this);
		clearListBtn = bp.getB5();
		clearListBtn.addActionListener(this);
		addListToRepoBtn = bp.getB6();
		addListToRepoBtn.addActionListener(this);

		editPanel = new NounEditPanel();

		JPanel workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		workPanel.setLayout(new BorderLayout());
		workPanel.add(editPanel, BorderLayout.NORTH);

		nounPanel = new MainNounPanel();

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, nounPanel);

		this.add(sp, BorderLayout.CENTER);
		this.add(bp, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == checkBtn) {
			int id = new Noun().getOid(editPanel.getWordSingular());

			if (id > 0) {
				editPanel.setValues(new Noun().prepareById(id));
			}
		}
	}

}
