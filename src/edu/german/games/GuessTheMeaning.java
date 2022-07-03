package edu.german.games;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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

import edu.german.services.ExecutorDoCallWord;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditableField;
import edu.german.tools.ResultsPanel;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordSelectionPanel;
import edu.german.words.model.Noun;
import edu.german.words.model.Word;

public class GuessTheMeaning extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final String SCREEN_PARAM_FILE = "screen.properties";
	private List<Word> allWordList;
	private List<Word> severalWords;
	private ButtonsPanel bp;
	private JButton drawBtn;
	private JButton checkBtn;
	private JButton repeatBtn;
	private WordSelectionPanel selectionPanel;
	private ShowResultAsImage showImage;
	private JPanel gamePanel;
	private JLabel choosenWordLabel;
	private OneEditableField answer;
	private String information;
	private ResultsPanel resultPan;
	private String choosenWord;
	private String controlWord;
	private String meaning;
	private String[] meanings;
	private int numberOfWords;
	private int actualDraw = 0;
	private int actualScore = 0;
	private int goodAnswer = 0;
	private int wrongAnswer = 0;

	public GuessTheMeaning(int height, int width, String titel) {
		super(height, width, titel);
		int fontSize = Integer.parseInt(new MyProperties(SCREEN_PARAM_FILE).getValue("DEFAULT_FONT_SIZE"));
		int gamesWordfontSize = Integer.parseInt(new MyProperties(SCREEN_PARAM_FILE).getValue("WORD_FOR_GAME"));
		showImage = new ShowResultAsImage(200, 200);
		information = "Słowo do odgadnięcia: ";

		allWordList = tryToGetList();
		severalWords = new LinkedList<Word>();

		bp = new ButtonsPanel("NEW_WORD", "CHECK_ANSWER", "RELOAD");
		drawBtn = bp.getB1();
		drawBtn.addActionListener(this);
		checkBtn = bp.getB2();
		checkBtn.addActionListener(this);
		repeatBtn = bp.getB3();
		repeatBtn.addActionListener(this);

		selectionPanel = new WordSelectionPanel(true);

		JPanel leftPanel = new JPanel();
		leftPanel.add(showImage);

		answer = new OneEditableField("Twoja odpowiedź", "wpisz po niemiecku", gamesWordfontSize, fontSize);

		choosenWordLabel = new JLabel();
		choosenWordLabel.setFont(new Font("Serif", Font.BOLD, gamesWordfontSize));
		choosenWordLabel.setText(information);

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2, 1, 10, 10));
		labelPanel.add(choosenWordLabel);
		labelPanel.add(answer);

		resultPan = new ResultsPanel(Titles.setTitel("YOUR_RESULTS"));

		gamePanel = new JPanel();
		gamePanel.add(leftPanel);
		gamePanel.add(labelPanel);

		JPanel centralPan = new JPanel();
		centralPan.setLayout(new GridLayout(2, 1, 10, 5));
		centralPan.add(gamePanel);
		centralPan.add(resultPan);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, selectionPanel, centralPan);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == drawBtn) {
			showImage.showIndifference();
			if (severalWords.isEmpty()) {
				setNumberOfWords((int) selectionPanel.getNumber());
				severalWords = getSeveral(Integer.parseInt(getNumberOfWords().toString()));
				actualDraw = 0;
				actualScore = 0;
				setNextWord(actualDraw);
			} else {
				actualDraw = actualDraw + 1;
				setNextWord(actualDraw);
			}

			if (actualDraw > 0 && actualDraw < severalWords.size() && controlWord.equals(choosenWord))
				new ShowMessage("THE_SAME_WORD");

		}

		else if (src == checkBtn) {
			if (gameControler(answer.getValue()))
				positiveScoreUpdate();
			else
				negativeScoreUpdate();
		}

	}

	private void negativeScoreUpdate() {
		wrongAnswer = wrongAnswer - 1;
		resultPan.setWrongAnswerNumber(String.valueOf(wrongAnswer));
		actualScore = actualScore - 1;
//		resultPan.setYourScoreLab(String.valueOf(actualScore));
		resultPan.setOverallResultLab(String.valueOf(actualScore));
		showImage.showScore(false);
	}

	private void positiveScoreUpdate() {
		goodAnswer = goodAnswer + 1;
		resultPan.setYourAnswer(choosenWord + " " + Titles.setTitel("MEANING") + ": " + meaning);
		resultPan.setGoodAnswerNumber(String.valueOf(goodAnswer));
		actualScore = actualScore + 1;
//		resultPan.setYourScoreLab(String.valueOf(actualScore));
		resultPan.setOverallResultLab(String.valueOf(actualScore));
		showImage.showScore(true);
	}

	private boolean gameControler(String str) {
		if (actualDraw > 0 && actualDraw < severalWords.size() && controlWord.equals(str)) {
			new ShowMessage("THE_SAME_WORD");
			return false;
		}

		if (str == null) {
			new ShowMessage("NO_ANSWER");
			return false;
		}

		// TODO need to change it,m check if space sign exists?
		if (str != null) {
			for (String toCheck : meanings) {
				if (str.equals(toCheck))
					return true;
			}
		}

		return false;
	}

	private void setNextWord(int number) {
		if (number < severalWords.size()) {
			Word var = severalWords.get(number);
			choosenWord = var.getWord();
			if (number == 0)
				controlWord = choosenWord;
			meaning = var.getMeaning();
			meanings = meaning.split(", ");
			choosenWordLabel.setText(information + choosenWord);
			resultPan.setYourAnswer(choosenWord);
			resultPan.setYourScoreLab(String.valueOf(actualDraw + 1));
		} else
			new ShowMessage("NO_MORE_WORDS");
	}

	private List<Word> getSeveral(Integer number) {
		List<Word> list = new LinkedList<>();
		for (int i = 0; i < number; i++) {
			list.add(allWordList.get(i));
			allWordList.remove(i);
		}

		return list;
	}

	private Object getNumberOfWords() {
		return numberOfWords;
	}

	private void setNumberOfWords(int number) {
		this.numberOfWords = number;

	}

}
