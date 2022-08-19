package edu.german.words;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.Titles;

public class AddAdjective extends MyInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tb;
	private AdjectiveGradation adjectiveGradation;

	public AddAdjective(int height, int width, String setTitel) {
		super(height, width, setTitel);
		adjectiveGradation = new AdjectiveGradation();

		tb = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		new Titles();
		tb.add(Titles.setTitel("ADJECTIVE_GRADATION"), adjectiveGradation);

		add(tb, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}
}
