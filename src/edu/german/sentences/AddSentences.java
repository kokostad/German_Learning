package edu.german.sentences;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.TableHanlder;
import edu.german.tools.buttons.ButtonsPanel;

public class AddSentences extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String CFG_FILE = "sentence.properties";
	private SentenceEditPanel edit;
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton editRowBtn;
	private JButton addToRepoBtn;
	private JButton removeBtn;
	private TableHanlder st;
	private String[] header;
	private List<HashMap<String, String>> mapList;

	public AddSentences(int height, int width, String titel) {
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

		header = new MyProperties(CFG_FILE).getValuesArray("TABLE_HEADER");
		st = new TableHanlder(header, true);

		edit = new SentenceEditPanel(header, "CHOOSE_SENTENCE_TYPE_LIST", "TENS");

		JScrollPane scp = new JScrollPane();
		scp.setViewportView(st);
		scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, edit, scp);
		sp.setResizeWeight(new ScreenSetup().MOST_BIG_SPLIT_PANE_FACTOR);

		toolBar.addSeparator();

		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			clearEditFiles();
		}

		else if (src == addToListBtn) {
			Map<Object, Object> map = edit.getValueAsMap();

			if (map.containsKey("SENTENCE") && map.containsKey("MEANING")) {
				st.showObjectMap(map);
				clearEditFiles();
			} else
				new ShowMessage("EMPTY_FIELDS");
		}

		else if (src == editRowBtn) {
			edit.showData(st.getSelectedRowAsMap());
			if (st.getIdx() > -1)
				st.removeRow();
		}

		else if (src == addToRepoBtn) {
			mapList = st.getDataAsMapList();
			if (!mapList.isEmpty()) {
				new AddSenteceToDatabase().addList(mapList);
				st.clearWordsList();
				st.clearTable();
				mapList.clear();
			}
		}
	}

	private void clearEditFiles() {
		edit.clearEditFields();
	}

}
