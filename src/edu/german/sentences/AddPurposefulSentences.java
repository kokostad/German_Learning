package edu.german.sentences;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditableField;
import edu.german.tools.TableHanlder;
import edu.german.tools.buttons.ButtonsPanel;

public class AddPurposefulSentences extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "sentence.properties";
	private ButtonsPanel bp;
	private SentenceEditPanel sentenceEditPanel;
	private OneEditableField sentence;
	private OneEditableField meaning;
	private TableHanlder st;
	private String[] tableHeaders;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JButton removeBtn;

	public AddPurposefulSentences(int height, int width, String setTitel) {
		super(height, width, setTitel);
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

		String[] selectionList = new MyProperties(FILE_NAME).getValuesArray("TYPE");
		sentenceEditPanel = new SentenceEditPanel("Wpisz zdanie niemieckie", "Wpisz polskie znaczenie", selectionList);

		tableHeaders = new MyProperties(FILE_NAME).getValuesArray("TABLE_HEADER");
		st = new TableHanlder(tableHeaders);

		JScrollPane scp = new JScrollPane();
		scp.setViewportView(st);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sentenceEditPanel, scp);
		add(sp, BorderLayout.CENTER);

//		add(sentenceEditPanel, BorderLayout.CENTER);
		add(bp, BorderLayout.EAST);
		setVisible(true);
		repaint();
	}

	private String[] getValues() {
		return sentenceEditPanel.getValues();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
