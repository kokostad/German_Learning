package edu.german.games;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.german.tools.MyInternalFrame;
import edu.german.tools.MyProperties;
import edu.german.tools.buttons.ButtonsPanel;

public class GuessTheMeaning extends MyInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final String SCREEN_PARAM_FILE = "screen.properties";
	private ButtonsPanel bp;
	private JButton drawBtn;
	private JButton nextBtn;
	private JButton checkBtn;
	private JButton repeatBtn;
	private SettingsPanel selectionPan;

	public GuessTheMeaning(int height, int width, String titel) {
		super(height, width, titel);
		int fontSize = Integer.parseInt(new MyProperties(SCREEN_PARAM_FILE).getValue("DEFAULT_FONT_SIZE"));
		int gamesWordfontSize = Integer.parseInt(new MyProperties(SCREEN_PARAM_FILE).getValue("WORD_FOR_GAME"));
		
		bp = new ButtonsPanel("NEW_WORD", "CHECK_ANSWER", "NEXT", "RELOAD");
		
		selectionPan = new SettingsPanel();
		
		
		this.add(bp, BorderLayout.EAST);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
