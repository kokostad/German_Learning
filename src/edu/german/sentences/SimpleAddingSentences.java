package edu.german.sentences;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.german.tools.AddRule;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.TableHanlder;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.tools.buttons.RulesButton;

public class SimpleAddingSentences extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JButton removeBtn;
	private TableHanlder st;
	private RulesButton rulesBtn;

	public SimpleAddingSentences(int height, int width, String titel) {
		super(height, width, titel);
		bp = new ButtonsPanel("CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "REMOVE_FROM_LIST", "CLEAR_LIST", "EDIT_ROW",
				"ADD_TO_REPOSITORY");

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

		
		rulesBtn = new RulesButton();
		rulesBtn.addActionListener(this);
		tb.addSeparator();
		tb.add(rulesBtn);
		
		add(bp, BorderLayout.EAST);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == rulesBtn) {
			int hight = this.getParent().getHeight();
			int width = this.getParent().getWidth();
			AddRule ar = new AddRule(hight, width, Titles.setTitel("ADD_RULES"));
			getDesktopPane().add(ar);
			getDesktopPane().moveToFront(ar);
			getDesktopPane().repaint();
		}


	}

}
