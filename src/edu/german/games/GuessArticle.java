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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import edu.german.services.ExecutorDoCallNoun;
import edu.german.services.ExecutorPrepareNounView;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.ResultsPanel;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordSelectionPanel;
import edu.german.words.Noun;

/**
 * GuessArticle.java
 * 
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 *
 */
public class GuessArticle extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private WordSelectionPanel selectionPan;
	private ButtonsPanel functionalButtons;
	private JButton drawBtn;
	private ButtonsPanel gameButtons;
	private JButton der;
	private JButton die;
	private JButton das;
	private JLabel gameWordLbl;
	private String[] fullWord;
	private String answer;
	private String gameWord;
	private JLabel answerLab;
	private String article;
	private String wordMeaning;
	private String controlWord;
	private String newWord;
	private String example;
	private ShowResultAsImage showImage;
	private List<Noun> nouns;
	private List<Noun> severalNouns;
	private int actualDraw = 0;
	private int numberOfWords;
	private int actualScore = 0;
	private int goodAnswer = 0;
	private int wrongAnswer = 0;
	private ResultsPanel resultPan;
	private AnswerPanel answerPanel;
	private ExecutorService es;

	public GuessArticle(int height, int width, String setTitel) {
		super(height, width, setTitel);
		ScreenSetup scr = new ScreenSetup();

		severalNouns = new LinkedList<Noun>();

		es = Executors.newSingleThreadExecutor();
		es.submit(new ExecutorPrepareNounView());

		int bigFontSize = scr.GAME_BIG_FONT_SIZE;
		String fontArt = scr.GAME_FONT_ART;

		showImage = new ShowResultAsImage(200, 200);

		functionalButtons = new ButtonsPanel("NEW_DRAW");
		drawBtn = functionalButtons.getB1();
		drawBtn.addActionListener(this);

		selectionPan = new WordSelectionPanel(false);

		String[] arr = { "DER", "DIE", "DAS" };
		gameButtons = new ButtonsPanel(arr);

		List<JButton> list = gameButtons.getButtonList();

		der = list.get(0);
		der.setPreferredSize(new Dimension(100, 54));
		der.setFont(new Font(fontArt, Font.ITALIC, bigFontSize));
		der.addActionListener(this);

		die = list.get(1);
		die.setPreferredSize(new Dimension(100, 54));
		die.setFont(new Font(fontArt, Font.ITALIC, bigFontSize));
		die.addActionListener(this);

		das = list.get(2);
		das.setPreferredSize(new Dimension(100, 54));
		das.setFont(new Font(fontArt, Font.ITALIC, bigFontSize));
		das.addActionListener(this);

		gameWordLbl = new JLabel();
		gameWordLbl.setText(Titel.setTitel("WORD_TO_CHECK"));
		gameWordLbl.setFont(new Font(fontArt, Font.ITALIC, bigFontSize));
		gameWordLbl.setForeground(new Color(34, 139, 34));
		gameWordLbl.setHorizontalAlignment(JTextField.LEFT);

		JPanel labelPane = new JPanel();
		labelPane.setLayout(new GridLayout(3, 1, 20, 5));
		labelPane.add(gameWordLbl);

		JPanel leftPanel = new JPanel();
		leftPanel.add(showImage);
		leftPanel.add(gameButtons);

		answerLab = new JLabel();
		answerLab.setFont(new Font(fontArt, Font.ITALIC, bigFontSize));
		answerLab.setForeground(new Color(165, 42, 42));
		answerLab.setHorizontalAlignment(JTextField.LEFT);

		answerPanel = new AnswerPanel();
		resultPan = new ResultsPanel(Titel.setTitel("YOUR_RESULTS"));

		JPanel workPanel = new JPanel();
		workPanel.setLayout(new GridLayout(1, 3, 10, 5));
		workPanel.add(leftPanel);
		workPanel.add(labelPane);

		JPanel centralPan = new JPanel();
		centralPan.setLayout(new GridLayout(3, 1, 10, 5));
		centralPan.add(workPanel);
		centralPan.add(answerPanel);
		centralPan.add(resultPan);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, selectionPan, centralPan);
		sp.setResizeWeight(new ScreenSetup().SPLIT_PANE_FACTOR);

		setInitialParams();

		this.add(functionalButtons, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private List<Noun> tryToGetList() {
		List<Noun> list = new LinkedList<Noun>();
		Future<List<Noun>> tmplist = es.submit(new ExecutorDoCallNoun());
		try {
			list = tmplist.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return list;
	}

	private void nextWord(int number) {
		if (number < severalNouns.size())
			setGameWord(number, severalNouns);
		else if (number == severalNouns.size())
			gameWordLbl.setText(Titel.setTitel("END_OF_ROUND"));
		else if (number > severalNouns.size())
			new ShowMessage("NO_MORE_WORDS");
		else
			new ShowMessage("UNIDENTIFIED_ERROR");
	}

	private void setGameWord(int actuelNumber, List<Noun> nounList) {
		Noun nounFromList = nounList.get(actuelNumber);
		if (actuelNumber == 0)
			controlWord = nounFromList.getWord();

		newWord = nounFromList.getWord();
		String[] arr = nounFromList.getWord().split(" ");
		article = arr[0];
		gameWord = arr[1];
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

		if (article.equals(answerArticel) && article != null) {

			return true;
		}

		return false;
	}

	private void setInitialParams() {
		nouns = tryToGetList();
		article = null;
		wordMeaning = null;
	}

	private List<Noun> getSeveral(int number) {
		List<Noun> list = new LinkedList<>();
		for (int i = 0; i < number; i++) {
			if (nouns.size() > i)
				list.add(nouns.get(i));
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
		answerPanel.setMeaning(wordMeaning);
		answerPanel.setExample(example);
		nextWord(getActualDraw());
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

			if (nouns.size() == 0) {
				new ShowMessage("EMPTY_LIST");
			}

			else if (severalNouns.isEmpty()) {
				setNumberOfWords((int) selectionPan.getNumber());
				severalNouns = getSeveral(getNumberOfWords());
				setInitScore();

				for (int i = 0; i < severalNouns.size(); i++) {
					nouns.remove(0);
				}
			}

			else if (!severalNouns.isEmpty()) {
				setNumberOfWords((int) selectionPan.getNumber());
				severalNouns = getSeveral(getNumberOfWords());
				setInitScore();

				for (int i = 0; i < severalNouns.size(); i++) {
					nouns.remove(0);
				}
			}

			if (severalNouns.size() > 0)
				nextWord(getActualDraw());
			else if (getActualDraw() > 0 && getActualDraw() < getNumberOfWords() && controlWord.equals(newWord))
				new ShowMessage("THE_SAME_WORD");
		}

		else if (src == der) {
			setAnswer("der");
			answerPanel.setAnsweredWord(getAnswer() + " " + gameWord);

			if (gameControler(answer)) {
				positiveScoreUpdate();
			} else
				negativeScoreUpdate();
		}

		else if (src == die) {
			setAnswer("die");
			answerPanel.setAnsweredWord(getAnswer() + " " + gameWord);

			if (gameControler(answer)) {
				positiveScoreUpdate();
			} else
				negativeScoreUpdate();
		}

		else if (src == das) {
			setAnswer("das");
			answerPanel.setAnsweredWord(getAnswer() + " " + gameWord);

			if (gameControler(answer)) {
				positiveScoreUpdate();
			} else
				negativeScoreUpdate();
		}
	}

	private void setInitScore() {
		setActualDraw(0);
		actualScore = 0;
	}
}
