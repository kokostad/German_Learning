package edu.german.words.verbs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.NewVerb;
import edu.german.words.model.Verb;

public class AddVerbs extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonsPanel bp;
	private JButton checkBtn;
	private JButton clearEditFieldsBtn;
	private JButton clearListBtn;
	private JButton addToListBtn;
	private JButton showListBtn;
	private JButton addListToRepoBtn;
	private JTabbedPane tb;
	private VerbIndikativ indikativ;
	private VerbKonjunktiv konjunktiv;
	private VerbImperativAndImpersonal imperativAndPartizip;
//	private List<Map<String, List<Map<String, String>>>> verbList;
	private List<NewVerb> verbList;
	private ExecutorService es;
	private String mainWord;
	private MainVerbPanel verbPanel;
	private String separable = null; // = (VerbSeparable.untrennbare).toString();
	private String regular = null; // = (VerbRegular.regelmäßig).toString();

	public AddVerbs(int height, int width, String setTitel) {
		super(height, width, setTitel);
		es = Executors.newSingleThreadExecutor();
		verbList = new LinkedList<NewVerb>();
		verbPanel = new MainVerbPanel();

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

		indikativ = new VerbIndikativ("INDIKATIV");
		konjunktiv = new VerbKonjunktiv("KONJUNKTIV");
		imperativAndPartizip = new VerbImperativAndImpersonal("IMPERATIV UND UNPERSÖNLICHE FORMEN");

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tb.add("INDIKATIV", indikativ);
		tb.add("KONJUNKTIV", konjunktiv);
		tb.add("IMPERATIV UND UNPERSÖNLICHE FORMEN", imperativAndPartizip);

		JPanel workPanel = new JPanel();
		workPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		workPanel.setLayout(new BorderLayout());
		workPanel.add(verbPanel, BorderLayout.NORTH);
		workPanel.add(tb, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(toolBar, BorderLayout.BEFORE_FIRST_LINE);
		this.add(workPanel, BorderLayout.CENTER);
		this.add(bp, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == checkBtn) {
			// NOTICE to improve
			String word = verbPanel.getWord();
			separable = verbPanel.getSeparatable();
			regular = verbPanel.getRegular();

			NewVerb newVerb = new NewVerb().prepareVerbFromRepository(word, regular, separable);
			Properties prop = newVerb.getProperties();

			String var = null;
			if(prop.containsKey("MODUS"))
				var = prop.get("MODUS").toString();

			if (var != null && var.equals("INDIKATIV")) {
				indikativ.fieldsFilling(prop);
			}

			if (var != null && var.contains("KONJUMKTIV")) {
				konjunktiv.fieldsFilling(prop);
			}

		}

		else if (src == clearEditFieldsBtn) {
			clearAllEditFields();
		}

		else if (src == addToListBtn) {
			List<Properties> propertiesList = new LinkedList<>();
			separable = verbPanel.getSeparatable();
			regular = verbPanel.getRegular();

			propertiesList.addAll(indikativ.getPropertiesList());
			propertiesList.addAll(konjunktiv.getPropertiesList());
			propertiesList.addAll(imperativAndPartizip.getPropertiesList());

			propertiesList.forEach(prop -> System.out.println(prop));

//			String mainWord = verbPanel.getWord();
//			if (mainWord != null)
//				setMainWord(mainWord);
//			else
//				setMainWord(indikativ.getMainWord());
//
//			Map<String, List<Map<String, String>>> indikativMap = indikativ.getMap();
//			if (indikativMap != null && indikativMap.size() > 0)
//				verbList.add(indikativMap);
//
//			Map<String, List<Map<String, String>>> konjunktivMapI = konjunktiv.getMapOne();
//			if (konjunktivMapI != null && konjunktivMapI.size() > 0)
//				verbList.add(konjunktivMapI);
//
//			Map<String, List<Map<String, String>>> konjunktivMapII = konjunktiv.getMapTwo();
//			if (konjunktivMapII != null)
//				verbList.add(konjunktivMapII);
//
//			Map<String, List<Map<String, String>>> imperative = imperativAndPartizip.getMapImperativ();
//			if (imperative != null)
//				verbList.add(imperative);
//
//			Map<String, List<Map<String, String>>> impersonal = imperativAndPartizip.getMapImpersonal();
//			if (impersonal != null)
//				verbList.add(impersonal);
//
		}

		else if (src == addListToRepoBtn) {
			if (!verbList.isEmpty()) {
				es.submit(new PutVerbIntoRepository(verbList));
			}
				
//				es.submit(new PutVerbIntoRepository(getMainWord(), verbList));
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
//		verbList.forEach(map -> {
//			map.forEach((key, map2) -> {
//				map2.forEach(value -> showMap(key, value));
//			});
//		});
	}

//	private void showMap(String modus, Map<String, String> value) {
//		String tens = "";
//		if (value.containsKey("TENS")) {
//			tens = value.get("TENS");
//			System.out.println("MODUS: " + modus + ", TENS: " + tens);
//			value.remove("TENS");
//		}
//		value.forEach((k, v) -> System.out.println("PERSON: " + k + ", VERB: " + v));
//	}

	private void clearAllEditFields() {
		indikativ.clearEditFields();
		konjunktiv.clearEditFields();
		imperativAndPartizip.clearEditFields();
	}

}
