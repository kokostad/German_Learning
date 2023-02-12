package edu.german.games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.german.tools.OneEditField;
import edu.german.tools.ScreenSetup;

public class GuessTheMeaningGamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel roundLabel;
	private JLabel informationLabel;
	private OneEditField answerField;
	private String roundInfo = "Runda: ";
	private int roundCount = 0;
	private String answerInfo = "Wyraz do odgadniecia: ";
	private String wordInfo = "Wpisz znaczenie wyrazu: ";

	public GuessTheMeaningGamePanel() {
		ScreenSetup ss = new ScreenSetup();
		int fontSize = ss.GAME_FONT_SIZE;
		String fontArt = ss.GAME_FONT_ART;
		Color labClr = new ScreenSetup().LABEL_COLOR;
		Color labAnsw = new ScreenSetup().LABEL_COLOR_2;
		Font font = new Font(fontArt, Font.ITALIC, fontSize);

		JPanel labPane = new JPanel();
		labPane.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		roundLabel = new JLabel();
		roundLabel.setFont(font);
		roundLabel.setForeground(labAnsw);
		labPane.add(roundLabel);

		JPanel infoPane = new JPanel();
		infoPane.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		informationLabel = new JLabel();
		informationLabel.setFont(font);
		informationLabel.setForeground(labAnsw);
		infoPane.add(informationLabel);

		JPanel answerPane = new JPanel();
		answerPane.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		answerField = new OneEditField.Builder()
				.withTitle(wordInfo)
				.withHint("Wpisz odpowiedź")
				.withFont(font)
//				.withColor(labClr)
				.withWidth(130)
				.withHeight(30)
				.build();
		answerPane.add(answerField);

		setRound(0);
		setInfromation(answerInfo);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
		this.add(Box.createVerticalGlue());
		this.add(labPane);
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		this.add(infoPane);
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		this.add(answerPane);
	}

	public void setRound(int count) {
		roundCount = roundCount + count;
		roundLabel.setText(roundInfo + roundCount);
	}
	
	public String getAnswer() {
		return answerField.getValue();
	}

	public void setAnswer(String answer) {
		answerField.setValue(answerInfo + answer);
	}

	public void clear() {
		roundLabel.setText("Runda: 0");
	}

	public void setInfromation(String infromation) {
		informationLabel.setText(infromation);
	}

	public void setWord(String choosenWord) {
		informationLabel.setText(answerInfo + choosenWord);
	}

	public void clearWord() {
		answerField.clearField();
	}
}
