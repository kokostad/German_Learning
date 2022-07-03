package edu.german.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int wordSize = 20;
	private JLabel titelLab;
	private JLabel infoLab;
	private JLabel yourAnswerLab;
	private JLabel yourScoreLab;
	private String answerInfo = "Wyraz: ";
	private String scoreInfo = "Statystyki: ";
	private String goodAnswerNumber = "Ilość dobrych odpowiedzi: ";
	private JLabel goodAnswerNumberLab;
	private String wrongAnswerNumber = "Ilość złych odpowiedzi: ";
	private JLabel wrongAnswerNumberLab;
	private String overallResult = "Wynik ogólny: ";
	private JLabel overallResultLab;

	public ResultsPanel(String titel) {
		titelLab = new JLabel();
		titelLab.setText(titel);
		titelLab.setText("Twoje wyniki: ");
		titelLab.setFont(new Font("Serif", Font.BOLD, wordSize));
		titelLab.setForeground(new Color(47, 79, 79));

		infoLab = new JLabel();
		infoLab.setText(titel);
		infoLab.setText("Po wybraniu odpowiedzi naciśnij przycisk [Sprawd\u017A odpowied\u017A] ");
		infoLab.setFont(new Font("Serif", Font.ITALIC, wordSize));
		infoLab.setForeground(new Color(70, 130, 180));

		yourAnswerLab = new JLabel();
		yourAnswerLab.setText(answerInfo);
		yourAnswerLab.setFont(new Font("Serif", Font.ITALIC, wordSize));
		yourAnswerLab.setForeground(new Color(165, 42, 42));

		yourScoreLab = new JLabel();
		yourScoreLab.setText(scoreInfo);
		yourScoreLab.setFont(new Font("Serif", Font.ITALIC, wordSize));
		yourScoreLab.setForeground(new Color(165, 42, 42));

		goodAnswerNumberLab = new JLabel();
		goodAnswerNumberLab.setText(goodAnswerNumber);
		goodAnswerNumberLab.setFont(new Font("Serif", Font.ITALIC, wordSize));
		goodAnswerNumberLab.setForeground(new Color(165, 42, 42));

		wrongAnswerNumberLab = new JLabel();
		wrongAnswerNumberLab.setText(wrongAnswerNumber);
		wrongAnswerNumberLab.setFont(new Font("Serif", Font.ITALIC, wordSize));
		wrongAnswerNumberLab.setForeground(new Color(165, 42, 42));

		overallResultLab = new JLabel();
		overallResultLab.setText(overallResult);
		overallResultLab.setFont(new Font("Serif", Font.ITALIC, wordSize));
		overallResultLab.setForeground(new Color(165, 42, 42));

		this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		this.add(Box.createHorizontalGlue());
		this.add(titelLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(infoLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(yourAnswerLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(yourScoreLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(goodAnswerNumberLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(wrongAnswerNumberLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(overallResultLab);

	}

	public void setYourAnswer(String answer) {
		yourAnswerLab.setText(answerInfo + answer);
	}

	public void setYourScoreLab(String score) {
		yourScoreLab.setText(scoreInfo + " losowanie numer: " + score);
	}

	public void setGoodAnswerNumber(String score) {
		goodAnswerNumberLab.setText(goodAnswerNumber + score);
	}

	public void setWrongAnswerNumber(String score) {
		wrongAnswerNumberLab.setText(wrongAnswerNumber + score);
	}

	public void setOverallResultLab(String score) {
		overallResultLab.setText(overallResult + score);
	}
}
