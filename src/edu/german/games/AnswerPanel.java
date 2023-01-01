package edu.german.games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.german.tools.ScreenSetup;

public class AnswerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel answerLab;
	private JLabel infoLab;
	private JLabel exampleLab;
	private String information = "Twoje odpowiedzi: ";
	private String meaningInformation = "Znaczenie: ";
	private String exampleInformation = "Przykład: ";

	public AnswerPanel() {
		ScreenSetup ss = new ScreenSetup();
		int fontSize = ss.GAME_FONT_SIZE;
		String fontArt = ss.GAME_FONT_ART;

		infoLab = new JLabel(information);
		infoLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		infoLab.setForeground(new Color(165, 42, 42));

		answerLab = new JLabel(meaningInformation);
		answerLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		answerLab.setForeground(new Color(165, 42, 42));

		exampleLab = new JLabel(exampleInformation);
		exampleLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		exampleLab.setForeground(new Color(165, 42, 42));

		this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		this.add(Box.createHorizontalGlue());
		this.add(infoLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(answerLab);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(exampleLab);
	}

	public void setAnsweredWord(String answer) {
		infoLab.setText(information + answer);
		this.repaint();
	}

	public void setMeaning(String answer) {
		answerLab.setText(meaningInformation + answer);
		this.repaint();
	}

	public void setExample(String answer) {
		exampleLab.setText(exampleInformation + answer);
	}
}
