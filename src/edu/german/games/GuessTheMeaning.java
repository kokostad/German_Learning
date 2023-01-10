package edu.german.games;

import java.awt.BorderLayout;
import java.awt.Color;
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

import edu.german.services.ExecutorDoCallWord;
import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.OneEditableField;
import edu.german.tools.ResultsPanel;
import edu.german.tools.ScreenSetup;
import edu.german.tools.ShowMessage;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ButtonsPanel;
import edu.german.words.WordSelectionPanel;
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
	private JLabel communique;
	private OneEditableField answer;
	private String information = "Słowo do odgadnięcia: ";
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
	private int deal = 1;
	private JTextField textField;

	public GuessTheMeaning(int height, int width, String titel) {
		super(height, width, titel);
		ScreenSetup ss = new ScreenSetup();
		int fontSize = ss.GAME_FONT_SIZE;
		String fontArt = ss.GAME_FONT_ART;
		
		showImage = new ShowResultAsImage(200, 200);

		allWordList = tryToGetList();
		severalWords = new LinkedList<Word>();

		bp = new ButtonsPanel("NEW_WORD", "CHECK_ANSWER", "NEW_ROUND");
		drawBtn = bp.getB1();
		drawBtn.addActionListener(this);
		checkBtn = bp.getB2();
		checkBtn.addActionListener(this);
		repeatBtn = bp.getB3();
		repeatBtn.addActionListener(this);

		selectionPanel = new WordSelectionPanel(true);

		JPanel leftPanel = new JPanel();
		leftPanel.add(showImage);

		answer = new OneEditableField("Twoja odpowiedź", "wpisz polskie znaczenie", fontSize, fontSize);
		textField = answer.getTextField();
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkAnswer();
			}
		});

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

		resultPan = new ResultsPanel(Titles.setTitel("YOUR_RESULTS"));

		gamePanel = new JPanel();
		gamePanel.add(leftPanel);
		gamePanel.add(labelPanel);

		JPanel centralPan = new JPanel();
		centralPan.setLayout(new GridLayout(2, 1, 10, 5));
		centralPan.add(gamePanel);
		centralPan.add(resultPan);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, selectionPanel, centralPan);
//		sp.setDividerSize(5);
		sp.setResizeWeight(0.5);

		this.add(bp, BorderLayout.EAST);
		this.add(sp, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == drawBtn) {
			showImage.showIndifference();
			if (severalWords.isEmpty()) {
				initData();
				setNumberOfWords((int) selectionPanel.getNumber());
				severalWords = getSeveral(getNumberOfWords());
				setNextWord(actualDraw);
			} else {
				actualDraw = actualDraw + 1;
				setNextWord(actualDraw);
			}

			if (actualDraw > 0 && actualDraw < severalWords.size() && controlWord.equals(choosenWord))
				new ShowMessage("THE_SAME_WORD");

			showImage.showIndifference();
		}

		else if (src == checkBtn) {
			checkAnswer();
		}

		else if (src == repeatBtn) {
			deal += 1;
			severalWords.clear();
			communique.setText("Runda: " + deal);
			setNumberOfWords((int) selectionPanel.getNumber());
			choosenWordLabel.setText(information);

			if (allWordList.size() > getNumberOfWords()) {
				severalWords = getSeveral(getNumberOfWords());
			} else {
				new ShowMessage("NO_MORE_WORDS");
			}

			initData();
		}

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
		answer.clearField();
		choosenWordLabel.setText(information);
	}

	private void initData() {
		actualDraw = 0;
		actualScore = 0;
		goodAnswer = 0;
		wrongAnswer = 0;

		resultPan.setYourScoreLab(String.valueOf(actualDraw + 0));
		resultPan.setGoodAnswerNumber(String.valueOf(goodAnswer));
		resultPan.setOverallResultLab(String.valueOf(actualScore));
		resultPan.setWrongAnswerNumber(String.valueOf(wrongAnswer));

		showImage.showScore(true);
		communique.setText("Runda: " + deal);
		answer.clearField();
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

		if (meanings != null) {
			for (int i = 0; i < meanings.length; i++) {
				if (str.equals(meanings[i].toString()))
					return true;
			}
		} else if (str != null && meanings == null) {
			if (str.equals(meaning))
				return true;
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
			meanings = var.getMeanings();
			choosenWordLabel.setText(information + choosenWord);
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

	private int getNumberOfWords() {
		return numberOfWords;
	}

	private void setNumberOfWords(int number) {
		this.numberOfWords = number;
	}

	private void checkAnswer() {
		if (gameControler(answer.getValue())) {
			positiveScoreUpdate();
			actualDraw = actualDraw + 1;
			setNextWord(actualDraw);
		}
		else
			negativeScoreUpdate();
	}
}
