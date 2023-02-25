package edu.german.games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import edu.german.services.ExecutorDoCallWord;
import edu.german.services.ExecutorPrepareNounView;
import edu.german.services.ExecutorPrepareWordView;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.OneEditField;
import edu.german.tools.OneEditableField;
import edu.german.tools.ResultsPanel;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordSelectionPanel;
import edu.german.words.model.Word;

/**
 * @author Tadeusz Kokotowski, email: t.kokotowski@gmail.com
 *
 */
public class GuessTheMeaning extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private List<Word> allWordList;
	private List<Word> severalWords;
	private ButtonsPanel bp;
	private JButton drawBtn;
	private JButton checkBtn;
	private WordSelectionPanel selectionPanel;
	private ShowResultAsImage showImage;
	private JPanel playPanel;
	private JLabel choosenWordLabel;
	private JLabel communique;
	private OneEditField answer;
	private String information = Titel.setTitel("WORD_TO_GUESS") + ": ";
	private ResultsPanel resultPan;
	private String choosenWord;
	private String controlWord;
	private String meaning;
	private String[] meanings;
	private int numberOfWords;
	private int actualDraw;
	private int actualScore;
	private int goodAnswer;
	private int wrongAnswer;
	private int deal = 1;
	private AnswerPanel answerPanel;
	private GuessTheMeaningGamePanel gamePanel;
	private ExecutorService es;

	public GuessTheMeaning(int height, int width, String titel) {
		super(height, width, titel);
		ScreenSetup scr = new ScreenSetup();
		int fontSize = scr.GAME_FONT_SIZE;
		String fontArt = scr.GAME_FONT_ART;
		severalWords = new LinkedList<Word>();
		showImage = new ShowResultAsImage(200, 200);

		es = Executors.newSingleThreadExecutor();
		es.submit(new ExecutorPrepareWordView());

		bp = new ButtonsPanel("NEW_DEAL", "CHECK_ANSWER");
		drawBtn = bp.getB1();
		drawBtn.addActionListener(this);
		checkBtn = bp.getB2();
		checkBtn.addActionListener(this);

		selectionPanel = new WordSelectionPanel(true);
		gamePanel = new GuessTheMeaningGamePanel();
		answerPanel = new AnswerPanel();

		answer = new OneEditField.Builder()
				.withTitle("Runda")
				.withHint("wpisz odpowiedź")
				.withFont(getFont())
				.withHeight(20)
				.withWidth(30)
				.build();

		communique = new JLabel();
		communique.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		communique.setForeground(Color.red);
		communique.setText("");

		choosenWordLabel = new JLabel();
		choosenWordLabel.setFont(new Font(fontArt, Font.BOLD, fontSize));
		choosenWordLabel.setText(information);

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(3, 1, 10, 10));
		labelPanel.add(communique);
		labelPanel.add(choosenWordLabel);
		labelPanel.add(answer);

		playPanel = new JPanel();
		playPanel.add(showImage);
		playPanel.add(gamePanel);

		resultPan = new ResultsPanel(Titel.setTitel("YOUR_RESULTS"));

		JPanel centralPan = new JPanel();
		centralPan.setLayout(new GridLayout(3, 1, 10, 5));
		centralPan.add(playPanel);
		centralPan.add(answerPanel);
		centralPan.add(resultPan);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, selectionPanel, centralPan);
		sp.setResizeWeight(new ScreenSetup().SPLIT_PANE_FACTOR);

		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
	}

	private List<Word> tryToGetList() {
		List<Word> list = new LinkedList<Word>();
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future<List<Word>> lst = es.submit(new ExecutorDoCallWord());
		try {
			list = lst.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return list;
	}

	private void negativeScoreUpdate() {
		wrongAnswer = wrongAnswer + 1;
		resultPan.setWrongAnswerNumber(String.valueOf(wrongAnswer));
		actualScore = actualScore - 1;
		resultPan.setOverallResultLab(String.valueOf(actualScore));
		showImage.showScore(false);
	}
	
	private void positiveScoreUpdate() {
		goodAnswer = goodAnswer + 1;
		resultPan.setGoodAnswerNumber(String.valueOf(goodAnswer));
		actualScore = actualScore + 1;
		resultPan.setOverallResultLab(String.valueOf(actualScore));
		showImage.showScore(true);
		answerPanel.setMeaning(meaning);
		answerPanel.setExample("example");
		choosenWordLabel.setText(information);
		clearFields();
	}

	private void clearFields() {
		answer.clearField();
		gamePanel.clearWord();
	}

	private void initData() {
		actualDraw = 0;
		actualScore = 0;
		goodAnswer = 0;
		wrongAnswer = 0;

		resultPan.setYourScoreLab(String.valueOf(actualDraw));
		resultPan.setGoodAnswerNumber(String.valueOf(goodAnswer));
		resultPan.setOverallResultLab(String.valueOf(actualScore));
		resultPan.setWrongAnswerNumber(String.valueOf(wrongAnswer));

		showImage.showScore(true);
		communique.setText("Runda: " + deal);
		clearFields();
	}

	private boolean gameControler(String str) {
		if (str == null) {
			new ShowMessage("NO_ANSWER");
			return false;
		}

		if (actualDraw > 1 && actualDraw < severalWords.size() && controlWord.equals(str)) {
			new ShowMessage("THE_SAME_WORD");
			return false;
		}

		if (meaning != null) {
			if ((str.toUpperCase()).equals(meaning.toUpperCase()))
				return true;
		}

		if (meanings != null) {
			for (int i = 0; i < meanings.length; i++) {
				if (str.equals(meanings[i].toString()))
					return true;
			}
		}

		return false;
	}

	private void setNextWord(int number) {
		if (number < severalWords.size()) {
//			actualDraw = +1;
			Word var = severalWords.get(number);
			choosenWord = var.getWord();
			controlWord = choosenWord;
			meaning = var.getMeaning();
			meanings = var.getMeanings();
			gamePanel.setRound(actualDraw);
			gamePanel.setWord(choosenWord);
			resultPan.setYourScoreLab(String.valueOf(actualDraw));
		} else {
			controlWord = null;
			new ShowMessage("NO_MORE_WORDS");
		}
	}
	
	private List<Word> getSeveral(Integer number) {
		if (!allWordList.isEmpty()) {
			List<Word> list = new LinkedList<>();
			for (int i = 0; i < number; i++) {
				list.add(allWordList.get(0));
				allWordList.remove(0);
			}
			return list;
		}
		return null;
	}

	private int getNumberOfWords() {
		return numberOfWords;
	}

	private void setNumberOfWords(int number) {
		this.numberOfWords = number;
	}

	private void checkAnswer() {
		String answer = gamePanel.getAnswer();
		if (gameControler(answer)) {
			actualDraw += 1;
			positiveScoreUpdate();
			setNextWord(actualDraw);
		} else
			negativeScoreUpdate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == drawBtn) {
			showImage.showIndifference();

			if (severalWords.isEmpty()) {
				allWordList = tryToGetList();
				initData();
				setNumberOfWords((int) selectionPanel.getNumber());
				severalWords = getSeveral(getNumberOfWords());
				setNextWord(actualDraw);
			} else {
				setNextWord(actualDraw);
			}

			if (actualDraw > 1 && actualDraw < severalWords.size() && controlWord.equals(choosenWord))
				new ShowMessage("THE_SAME_WORD");

			showImage.showIndifference();
		}

		else if (src == checkBtn) {
			checkAnswer();
		}
	}

}
