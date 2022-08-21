package edu.german.words;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.buttons.ButtonsPanel;

public class AddVerbs extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton showListBtn;
	private JButton addListToRepoBtn;

	public AddVerbs(int height, int width, String setTitel) {
		super(height, width, setTitel);
		bp = new ButtonsPanel("CLEAR_EDIT_FIELDS", "CLEAR_LIST", "ADD_TO_LIST", "SHOW_LIST", "ADD_LIST_TO_REPOSITORY");
		clearEditFieldsBtn = bp.getB1();
		clearEditFieldsBtn.addActionListener(this);
		clearListBtn = bp.getB2();
		clearListBtn.addActionListener(this);
		addToListBtn = bp.getB3();
		addToListBtn.addActionListener(this);
		showListBtn = bp.getB4();
		showListBtn.addActionListener(this);
		addListToRepoBtn = bp.getB5();
		addListToRepoBtn.addActionListener(this);

		setLayout(new BorderLayout());
//		add(sp, BorderLayout.CENTER);
		add(bp, BorderLayout.EAST);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
