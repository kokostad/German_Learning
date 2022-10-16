package edu.german.words.verbs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
	private VerbImperativAndImpersonal imperativAndPartizip;
	private List<Map<String, List<Map<String, String>>>> verbList;
	private ExecutorService es;
	private String mainWord;

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
		imperativAndPartizip = new VerbImperativAndImpersonal("IMPERATIV UND UNPERSÖNLICHE FORMEN");

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		new Titles();
		tb.add("INDIKATIV", indikativ);
		tb.add("KONJUNKTIV", konjunktiv);
		tb.add("IMPERATIV UND UNPERSÖNLICHE FORMEN", imperativAndPartizip);

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
			setMainWord(indikativ.getMainWord());

			Map<String, List<Map<String, String>>> indikativMap = indikativ.getMap();
			if (indikativMap != null && indikativMap.size() > 0)
				verbList.add(indikativMap);

			Map<String, List<Map<String, String>>> konjunktivMapI = konjunktiv.getMapOne();
			if (konjunktivMapI != null  && konjunktivMapI.size() > 0)
				verbList.add(konjunktivMapI);

			Map<String, List<Map<String, String>>> konjunktivMapII = konjunktiv.getMapTwo();
			if (konjunktivMapII != null)
				verbList.add(konjunktivMapII);

			Map<String, List<Map<String, String>>> imperative = imperativAndPartizip.getMapImperativ();
			if (imperative != null)
				verbList.add(imperative);

			Map<String, List<Map<String, String>>> impersonal = imperativAndPartizip.getMapImpersonal();
			if (impersonal != null)
				verbList.add(impersonal);

//			showList();
//			clearAllEditFields();
		}

		else if (src == addListToRepoBtn) {
			if (!getMainWord().isBlank() && !verbList.isEmpty())
				es.submit(new PutVerbIntoRepository(getMainWord(), verbList));

//			verbList.clear();
//			clearAllEditFields();
		}

		else if (src == showListBtn) {

		}

		else if (src == clearListBtn) {
			verbList.clear();
		}

	}

	public String getMainWord() {
		return mainWord;
	}

	public void setMainWord(String mainWord) {
		this.mainWord = mainWord;
	}

	private void showList() {
		verbList.forEach(map -> {
			map.forEach((key, map2) -> {
				map2.forEach(value -> showMap(key, value));
			});
		});
	}

	private void showMap(String modus, Map<String, String> value) {
		String tens = "";
		if (value.containsKey("TENS")) {
			tens = value.get("TENS");
			System.out.println("MODUS: " + modus + ", TENS: " + tens);
			value.remove("TENS");
		}
		value.forEach((k, v) -> System.out.println("PERSON: " + k + ", VERB: " + v));
	}

	private void clearAllEditFields() {
		indikativ.clearEditFields();
		konjunktiv.clearEditFields();
		imperativAndPartizip.clearEditFields();
	}

}
