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
	private int fontSize;
	private String fontArt;
	private JLabel titelLab;
	private JLabel yourScoreLab;
	private String scoreInfo = "Losowanie numer: ";
	private String goodAnswerNumber = "Ilość dobrych odpowiedzi: ";
	private JLabel goodAnswerNumberLab;
	private String wrongAnswerNumber = "Ilość złych odpowiedzi: ";
	private JLabel wrongAnswerNumberLab;
	private String overallResult = "Wynik ogólny: ";
	private JLabel overallResultLab;

	public ResultsPanel(String titel) {
		ScreenSetup ss = new ScreenSetup();
		fontSize = ss.GAME_FONT_SIZE;
		fontArt = ss.GAME_FONT_ART;

		titelLab = new JLabel();
		titelLab.setText(titel);
		titelLab.setText("Twoje wyniki: ");
		titelLab.setFont(new Font(fontArt, Font.BOLD | Font.ITALIC, fontSize));
		titelLab.setForeground(new Color(47, 79, 79));

		yourScoreLab = new JLabel();
		yourScoreLab.setText(scoreInfo);
		yourScoreLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		yourScoreLab.setForeground(new Color(165, 42, 42));

		goodAnswerNumberLab = new JLabel();
		goodAnswerNumberLab.setText(goodAnswerNumber);
		goodAnswerNumberLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		goodAnswerNumberLab.setForeground(new Color(165, 42, 42));

		wrongAnswerNumberLab = new JLabel();
		wrongAnswerNumberLab.setText(wrongAnswerNumber);
		wrongAnswerNumberLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		wrongAnswerNumberLab.setForeground(new Color(165, 42, 42));

		overallResultLab = new JLabel();
		overallResultLab.setText(overallResult);
		overallResultLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		overallResultLab.setForeground(new Color(165, 42, 42));

		this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		this.add(Box.createHorizontalGlue());
		this.add(titelLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(yourScoreLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(goodAnswerNumberLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(wrongAnswerNumberLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(overallResultLab);

	}

	public void setYourScoreLab(String score) {
		yourScoreLab.setText(scoreInfo + score);
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
