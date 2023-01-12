package edu.german.games;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.german.tools.ScreenSetup;
import edu.german.tools.Titles;

public class AnswerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel answerLab;
	private JLabel infoLab;
	private JLabel exampleLab;
	private String information = Titles.setTitel("YOUR_ANSWER");
	private String meaningInformation = Titles.setTitel("MEANING");
	private String exampleInformation = Titles.setTitel("EXAMPLE");
	private final Color labClr = new Color(139, 69, 19);
	private final Color labAnsw = new Color(51, 51, 255);

	public AnswerPanel() {
		ScreenSetup ss = new ScreenSetup();
		int fontSize = ss.GAME_FONT_SIZE;
		String fontArt = ss.GAME_FONT_ART;

		infoLab = new JLabel();
		infoLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		infoLab.setForeground(labAnsw);
		JLabel infoLabStatic = new JLabel(information + ": ");
		infoLabStatic.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		infoLabStatic.setForeground(labClr);
		JPanel up = new JPanel();
		up.setLayout(new BoxLayout(up, BoxLayout.X_AXIS));
		up.setAlignmentX(Component.LEFT_ALIGNMENT);
		up.add(infoLabStatic);
		up.add(infoLab);

		answerLab = new JLabel();
		answerLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		answerLab.setForeground(labAnsw);
		JLabel answerLabStatic = new JLabel(meaningInformation + ": ");
		answerLabStatic.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		answerLabStatic.setForeground(labClr);
		JPanel mid = new JPanel();
		mid.setLayout(new BoxLayout(mid, BoxLayout.X_AXIS));
		mid.setAlignmentX(Component.LEFT_ALIGNMENT);
		mid.add(answerLabStatic);
		mid.add(answerLab);

		exampleLab = new JLabel();
		exampleLab.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		exampleLab.setForeground(labAnsw);
		JLabel exampleLabStatic = new JLabel(exampleInformation + ": ");
		exampleLabStatic.setFont(new Font(fontArt, Font.ITALIC, fontSize));
		exampleLabStatic.setForeground(labClr);
		JPanel down = new JPanel();
		down.setLayout(new BoxLayout(down, BoxLayout.X_AXIS));
		down.setAlignmentX(Component.LEFT_ALIGNMENT);
		down.add(exampleLabStatic);
		down.add(exampleLab);

		this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		this.add(Box.createHorizontalGlue());
		this.add(up);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(mid);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(down);
	}

	public void setAnsweredWord(String answer) {
		infoLab.setText(" " + answer);
		this.repaint();
	}

	public void setMeaning(String answer) {
		answerLab.setText(" " + answer);
		this.repaint();
	}

	public void setExample(String answer) {
		if (answer != null)
			exampleLab.setText(" " + answer);
		else
			exampleLab.setText(" ");
	}
}
