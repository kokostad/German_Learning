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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

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
	private JLabel wordTocheckLbl;
	private String[] fullWord;
	private String answer;
	private String wordToCheck;
	private JLabel answerLab;
	private String article;
	private String wordMeaning;
	private String noun;
	private static final int size = 26;
	private static final int wordSize = 36;
	private ShowResultAsImage showImage;
	private List<Noun> nounList;
	private static String genus = "Substantiv";
	private int actualDraw = 0;
	private int numberOfWords;
	private int actualScore = 0;
	private int goodAnswer = 0;
	private int wrongAnswer = 0;
	private ResultsPanel resultPan;

	public GuessArticle(int height, int width, String setTitel) {
		super(height, width, setTitel);
		nounList = new LinkedList<Noun>();
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

		wordTocheckLbl = new JLabel();
		wordTocheckLbl.setText(" Wyraz do sprawdzenia ");
		wordTocheckLbl.setFont(new Font("MV Boli", Font.ITALIC, wordSize));
		wordTocheckLbl.setForeground(new Color(34, 139, 34));
		wordTocheckLbl.setHorizontalAlignment(JTextField.LEFT);

		JPanel labelPane = new JPanel();
		labelPane.setLayout(new GridLayout(3, 1, 20, 5));
		labelPane.add(wordTocheckLbl);

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
		if (number < nounList.size()) {
			setGameWord(number, nounList);
			actualDraw += 1;
		} else {
			new ShowMessage("NO_MORE_WORDS");
		}
	}

	private void setGameWord(int actuelNumber, List<Noun> nounList) {
		Noun var = nounList.get(actuelNumber);
		String[] word = var.getMainWord().split(" ");
		article = word[0];
		wordToCheck = word[1];
		noun = var.getMainWord();
		wordMeaning = var.getMeaning();
		wordTocheckLbl.setText(" " + wordToCheck + " ");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == drawBtn) {
			if (nounList.isEmpty()) {
				setNumberOfWords((int) selectionPan.getNumber());
				nounList = new WordPkg(genus, getNumberOfWords()).getNounList(getNumberOfWords());
				setActualDraw(0);
				actualScore = 0;
			}

			nextWord(getActualDraw());
			showImage.showIndifference();
		}

		else if (src == checkBtn) {
			if (article.equals(getAnswer())) {
				numberOfWords = 0;
				actualScore = actualScore + 1;
				goodAnswer = goodAnswer + 1;
				showImage.showScore(true);
				resultPan.setYourScoreLab(" " + actualDraw);
				resultPan.setGoodAnswerNumber(" " + goodAnswer);
				resultPan.setOverallResultLab(" " + actualScore);
				resultPan.setYourAnswer(noun + ", znaczenie: " + wordMeaning);
			} else {
				actualScore = actualScore - 1;
				wrongAnswer = wrongAnswer + 1;
				showImage.showScore(false);
				resultPan.setYourScoreLab(" " + actualDraw);
				resultPan.setWrongAnswerNumber(" " + wrongAnswer);
				resultPan.setOverallResultLab(" " + actualScore);
			}
		}

		else if (src == derBtn) {
			setAnswer("der");
			resultPan.setYourAnswer(getAnswer() + " " + wordToCheck);
		}

		else if (src == dieBtn) {
			setAnswer("die");
			resultPan.setYourAnswer(getAnswer() + " " + wordToCheck);
		}

		else if (src == dasBtn) {
			setAnswer("das");
			resultPan.setYourAnswer(getAnswer() + " " + wordToCheck);
		}

		else if (src == nextBtn) {

		}

	}

}
