package edu.german.words.verbs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;

public class AddVerbs extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton showListBtn;
	private JButton addListToRepoBtn;
	private JTabbedPane tb;
	private VerbIndikativ indikativ;
	private VerbKonjunktiv konjunktiv;
	private VerbImperativPartizip iperativAndPartizip;
	private List<Map<String, List<Map<String, String>>>> verbList;
	private ExecutorService es;

	public AddVerbs(int height, int width, String setTitel) {
		super(height, width, setTitel);
		es = Executors.newSingleThreadExecutor();
		verbList = new LinkedList<Map<String, List<Map<String, String>>>>();

		bp = new ButtonsPanel("CLEAR_EDIT_FIELDS", "ADD_TO_LIST", "SHOW_LIST", "CLEAR_LIST", "ADD_LIST_TO_REPOSITORY");
		clearEditFieldsBtn = bp.getB1();
		clearEditFieldsBtn.addActionListener(this);
		addToListBtn = bp.getB2();
		addToListBtn.addActionListener(this);
		showListBtn = bp.getB3();
		showListBtn.addActionListener(this);
		clearListBtn = bp.getB4();
		clearListBtn.addActionListener(this);
		addListToRepoBtn = bp.getB5();
		addListToRepoBtn.addActionListener(this);

		indikativ = new VerbIndikativ("INDIKATIV");
		konjunktiv = new VerbKonjunktiv("KONJUNKTIV");
		iperativAndPartizip = new VerbImperativPartizip("IMPERATIV UND UNPERSÖNLICHE FORMEN");

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		new Titles();
		tb.add("INDIKATIV", indikativ);
		tb.add("KONJUNKTIV", konjunktiv);
		tb.add("IMPERATIV UND UNPERSÖNLICHE FORMEN", iperativAndPartizip);

		setLayout(new BorderLayout());
		add(tb, BorderLayout.CENTER);
		add(bp, BorderLayout.EAST);
		add(toolBar, BorderLayout.BEFORE_FIRST_LINE);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == clearEditFieldsBtn) {
			clearAllEditFields();
		}

		else if (src == addToListBtn) {
			Map<String, List<Map<String, String>>> map = indikativ.getMap();
			verbList.add(map);
			clearAllEditFields();
		}

		else if (src == showListBtn) {

		}

		else if (src == clearListBtn) {
			verbList.clear();
		}

		else if (src == addListToRepoBtn) {
			es.submit(new PutVerbIntoRepository(verbList));
			verbList.clear();
			clearAllEditFields();
		}

	}

	private void clearAllEditFields() {
		indikativ.clearEditFields();
	}

}
