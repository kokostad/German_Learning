package edu.german.games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import edu.german.services.ExecutorWordTask;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.ResultsPanel;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordPkg;
import edu.german.words.WordSelectionPanel;
import edu.german.words.model.Noun;

public class GuessArticle extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private WordSelectionPanel selectionPan;
	private ButtonsPanel bp;
	private ButtonsPanel bpAnser;
	private JButton derBtn;
	private JButton dieBtn;
	private JButton dasBtn;
	private JButton drawBtn;
	private JButton nextBtn;
	private JButton checkBtn;
	private JButton repeatBtn;
	private JLabel gameWordLbl;
	private String[] fullWord;
	private String answer;
	private String gameWord;
	private JLabel answerLab;
	private String article;
	private String wordMeaning;
	private String controlWord;
	private String newWord;
	private static final int size = 26;
	private static final int wordSize = 36;
	private ShowResultAsImage showImage;
	private List<Noun> allNounList;
	private List<Noun> severalNouns;
	private int actualDraw = 0;
	private int numberOfWords;
	private int actualScore = 0;
	private int goodAnswer = 0;
	private int wrongAnswer = 0;
	private ResultsPanel resultPan;
//	private ExecutorService es;

	public GuessArticle(int height, int width, String setTitel) {
		super(height, width, setTitel);
		severalNouns = new LinkedList<Noun>();
//		es = Executors.newSingleThreadExecutor();
		allNounList = new WordPkg().getNounList();
		showImage = new ShowResultAsImage(200, 200);

		bp = new ButtonsPanel("NEW_WORD", "CHECK_ANSWER", "NEXT", "RELOAD");
		drawBtn = bp.getB1();
		drawBtn.addActionListener(this);
		checkBtn = bp.getB2();
		checkBtn.addActionListener(this);
		nextBtn = bp.getB3();
		nextBtn.addActionListener(this);
		repeatBtn = bp.getB4();
		repeatBtn.addActionListener(this);

		selectionPan = new WordSelectionPanel();

		bpAnser = new ButtonsPanel("DER", "DIE", "DAS");
		derBtn = bpAnser.getB1();
		derBtn.setPreferredSize(new Dimension(100, 54));
		derBtn.setFont(new Font("MV Boli", Font.ITALIC, size));
		derBtn.addActionListener(this);
		dieBtn = bpAnser.getB2();
		dieBtn.setPreferredSize(new Dimension(100, 54));
		dieBtn.setFont(new Font("MV Boli", Font.ITALIC, size));
		dieBtn.addActionListener(this);
		dasBtn = bpAnser.getB3();
		dasBtn.setPreferredSize(new Dimension(100, 54));
		dasBtn.setFont(new Font("MV Boli", Font.ITALIC, size));
		dasBtn.addActionListener(this);

		gameWordLbl = new JLabel();
		gameWordLbl.setText(Titles.setTitel("WORD_TO_CHECK"));
		gameWordLbl.setFont(new Font("MV Boli", Font.ITALIC, wordSize));
		gameWordLbl.setForeground(new Color(34, 139, 34));
		gameWordLbl.setHorizontalAlignment(JTextField.LEFT);

		JPanel labelPane = new JPanel();
		labelPane.setLayout(new GridLayout(3, 1, 20, 5));
		labelPane.add(gameWordLbl);

		JPanel leftPanel = new JPanel();
		leftPanel.add(showImage);
		leftPanel.add(bpAnser);

		answerLab = new JLabel();
		answerLab.setFont(new Font("Serif", Font.ITALIC, wordSize));
		answerLab.setForeground(new Color(165, 42, 42));
		answerLab.setHorizontalAlignment(JTextField.LEFT);

		resultPan = new ResultsPanel(Titles.setTitel("YOUR_RESULTS"));

		JPanel workPanel = new JPanel();
		workPanel.setLayout(new GridLayout(1, 3, 10, 5));
		workPanel.add(leftPanel);
		workPanel.add(labelPane);

		JPanel centralPan = new JPanel();
		centralPan.setLayout(new GridLayout(2, 1, 10, 5));
		centralPan.add(workPanel);
		centralPan.add(resultPan);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, selectionPan, centralPan);

		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void nextWord(int number) {
		if (number < severalNouns.size())
			setGameWord(number, severalNouns);
		else
			new ShowMessage("NO_MORE_WORDS");
	}

	private void setGameWord(int actuelNumber, List<Noun> nounList) {
		Noun nounFromList = nounList.get(actuelNumber);
		if (actuelNumber == 0)
			controlWord = nounFromList.getWord();

		newWord = nounFromList.getWord();
		String[] noun = nounFromList.getWord().split(" ");
		article = noun[0];
		gameWord = noun[1];
		wordMeaning = nounFromList.getMeaning();
		gameWordLbl.setText(" " + gameWord + " ");
	}

	private String getAnswer() {
		return answer;
	}

	private void setAnswer(String answer) {
		this.answer = answer;
	}

	public String[] getFullWord() {
		return fullWord;
	}

	public void setFullWord(String[] fullWord) {
		this.fullWord = fullWord;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public int getNumberOfWords() {
		return numberOfWords;
	}

	public void setNumberOfWords(int actualNumber) {
		this.numberOfWords = actualNumber;
	}

	public int getActualDraw() {
		return actualDraw;
	}

	public void setActualDraw(int actualDraw) {
		this.actualDraw = actualDraw;
	}

	private boolean gameControler(String answerArticel) {
		if (getActualDraw() > 0 && getActualDraw() != getNumberOfWords() && controlWord.equals(newWord)) {
			new ShowMessage("THE_SAME_WORD");
			return false;
		}

		if (answerArticel == null) {
			new ShowMessage("NO_ANSWER");
			return false;
		}

		if (article.equals(answerArticel) && article != null)
			return true;

		return false;
	}

	private void setInitialParams() {
		resultPan.setYourAnswer(" ");
		article = null;
		wordMeaning = null;
	}

	private List<Noun> getSeveral(int number) {
		List<Noun> list = new LinkedList<>();
		for (int i = 0; i < number; i++) {
			list.add(allNounList.get(i));
			allNounList.remove(i);
		}

		return list;
	}

	private void positiveScoreUpdate() {
		controlWord = newWord;
		setActualDraw(getActualDraw() + 1);
		actualScore = actualScore + 1;
		goodAnswer = goodAnswer + 1;
		showImage.showScore(true);
		resultPan.setYourScoreLab(" " + actualDraw);
		resultPan.setGoodAnswerNumber(" " + goodAnswer);
		resultPan.setOverallResultLab(" " + actualScore);
		resultPan.setYourAnswer(getArticle() + " " + gameWord + ", " + Titles.setTitel("MEANING") + ": " + wordMeaning);
	}

	private void negativeScoreUpdate() {
		actualScore = actualScore - 1;
		wrongAnswer = wrongAnswer + 1;
		showImage.showScore(false);
		resultPan.setYourScoreLab(" " + actualDraw);
		resultPan.setWrongAnswerNumber(" " + wrongAnswer);
		resultPan.setOverallResultLab(" " + actualScore);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == drawBtn) {
			showImage.showIndifference();
			if (severalNouns.isEmpty()) {
				setNumberOfWords((int) selectionPan.getNumber());
				severalNouns = getSeveral(getNumberOfWords());
				setActualDraw(0);
				actualScore = 0;
			} else
				setInitialParams();

			nextWord(getActualDraw());

			if (getActualDraw() > 0 && getActualDraw() < getNumberOfWords() && controlWord.equals(newWord))
				new ShowMessage("THE_SAME_WORD");
		}

		else if (src == checkBtn) {
			if (gameControler(answer))
				positiveScoreUpdate();
			else
				negativeScoreUpdate();
		}

		else if (src == derBtn) {
			setAnswer("der");
			resultPan.setYourAnswer(getAnswer() + " " + gameWord);
		}

		else if (src == dieBtn) {
			setAnswer("die");
			resultPan.setYourAnswer(getAnswer() + " " + gameWord);
		}

		else if (src == dasBtn) {
			setAnswer("das");
			resultPan.setYourAnswer(getAnswer() + " " + gameWord);
		}

		else if (src == nextBtn) {
			gameWordLbl.setText(Titles.setTitel("NEW_ROUND"));
			setNumberOfWords((int) selectionPan.getNumber());
			if (allNounList.size() > getNumberOfWords()) {
				severalNouns = getSeveral(getNumberOfWords());
			} else {
				new ShowMessage("NO_MORE_WORDS");
			}
		}

	}

}
