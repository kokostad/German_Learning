package edu.german.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import edu.german.games.GuessArticle;
import edu.german.games.GuessTheMeaning;
import edu.german.io.ExportToFile;
import edu.german.io.ImportFromFile;
import edu.german.sentences.AddPurposefulSentences;
import edu.german.sentences.AddSentences;
import edu.german.tools.AddRule;
import edu.german.tools.MyToolbar;
import edu.german.tools.ScreenSetup;
import edu.german.tools.Titles;
import edu.german.tools.buttons.ExitButton;
import edu.german.tools.buttons.ExportButton;
import edu.german.tools.buttons.ImportButton;
import edu.german.words.AddAdjective;
import edu.german.words.SimpleWordAddition;
import edu.german.words.WordAddition;

public class Learning extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JDesktopPane dsk;
	private JMenuBar mbar;
	private JMenu mOption;
	private JMenuItem miImport;
	private JMenuItem miExport;
	private JMenuItem exit;
	private JMenu mAddition;
	private JMenuItem miSimpleWordsAddition;
	private JMenu miAddWords;
	private JMenuItem miAddAdjective;
	private JMenuItem miSentencesAddition;
	private JMenuItem miAddPurposefulSentences;
	private JMenuItem miAddRules;
	private JMenu mGames;
	private JMenu mGuessing;
	private JMenuItem miWhatKind;
	private JMenuItem guessTheMeaning;
	private JToolBar toolbar;
	private JButton importBtn;
	private JButton exportBtn;
	private JButton exitBtn;

	public Learning() {
		ScreenSetup sp = new ScreenSetup();
		ImageIcon img = new ImageIcon("src/edu/german/img/tak2.gif");
		this.setIconImage(img.getImage());
		setTitle(Titles.setTitel("MAIN_TITLE_GERMAN"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(sp.SCR_DEFAULT_WIDTH, sp.SCR_DEFAULT_HEIGHT);
		setResizable(true);

		miImport = new JMenuItem(Titles.setTitel("IMPORT"));
		miImport.addActionListener(this);
		miExport = new JMenuItem(Titles.setTitel("EXPORT"));
		miExport.addActionListener(this);

		exit = new JMenuItem(Titles.setTitel("EXIT"));
		exit.addActionListener(this);
		mOption = new JMenu(Titles.setTitel("OPTION"));
		mOption.add(miImport);
		mOption.add(miExport);
		mOption.addSeparator();
		mOption.add(exit);

		miSimpleWordsAddition = new JMenuItem(Titles.setTitel("SIMPLE_WORDS_ADDITION"));
		miSimpleWordsAddition.addActionListener(this);
		miSentencesAddition = new JMenuItem(Titles.setTitel("SENTENCES_ADDITION"));
		miSentencesAddition.addActionListener(this);
		miAddWords = new JMenu(Titles.setTitel("WORDS_ADDITION"));
		miAddWords.addActionListener(this);
		miAddPurposefulSentences = new JMenuItem(Titles.setTitel("PURPOSEFUL_SENTENCES"));
		miAddPurposefulSentences.addActionListener(this);

		miAddAdjective = new JMenuItem(Titles.setTitel("ADD_ADJECTIVE"));
		miAddAdjective.addActionListener(this);

		miAddWords.add(miAddAdjective);

		miAddRules = new JMenuItem(Titles.setTitel("ADD_RULES"));
		miAddRules.addActionListener(this);

		mAddition = new JMenu(Titles.setTitel("MENU_ADDITION"));
		mAddition.add(miSimpleWordsAddition);
		mAddition.add(miAddWords);
		mAddition.add(new JSeparator());
		mAddition.add(miSentencesAddition);
		mAddition.add(miAddPurposefulSentences);
		mAddition.add(new JSeparator());
		mAddition.add(miAddRules);

		guessTheMeaning = new JMenuItem(Titles.setTitel("GUESS_THE_MEANING"));
		guessTheMeaning.addActionListener(this);
		miWhatKind = new JMenuItem(Titles.setTitel("WHAT_ARTICLE"));
		miWhatKind.addActionListener(this);

		mGuessing = new JMenu(Titles.setTitel("QUESSING"));
		mGuessing.add(miWhatKind);
		mGuessing.add(guessTheMeaning);

		mGames = new JMenu(Titles.setTitel("LEARNING_GAMES"));
		mGames.add(mGuessing);

		// NOTICE Buttons settings
		exitBtn = new ExitButton();
		exitBtn.addActionListener(this);
		importBtn = new ImportButton();
		importBtn.addActionListener(this);
		exportBtn = new ExportButton();
		exportBtn.addActionListener(this);

		toolbar = new MyToolbar();
		toolbar.add(exitBtn);
		toolbar.addSeparator();
		toolbar.add(importBtn);
		toolbar.add(exportBtn);
		this.add(toolbar, BorderLayout.BEFORE_FIRST_LINE);

		mbar = new JMenuBar();
		mbar.add(mOption);
		mbar.add(mAddition);
		mbar.add(mGames);

		this.setJMenuBar(mbar);

		Color myColor = new Color(255, 222, 173);
		dsk = new JDesktopPane();
		dsk.setBackground(myColor);

		this.add(dsk);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == exit || src == exitBtn) {
			System.exit(0);
		}

		else if (src == miSimpleWordsAddition) {
			SimpleWordAddition swa = new SimpleWordAddition(dsk.getHeight(), dsk.getWidth(),
					Titles.setTitel("SIMPLE_WORDS_ADDITION"));
			dsk.add(swa);
			dsk.moveToFront(swa);
			dsk.repaint();
		}

		else if (src == miAddAdjective) {
			AddAdjective aa = new AddAdjective(dsk.getHeight(), dsk.getWidth(), Titles.setTitel("ADD_ADDJECTIVES"));
			dsk.add(aa);
			dsk.moveToFront(aa);
			dsk.repaint();
		}

		else if (src == miSentencesAddition) {
			AddSentences ssa = new AddSentences(dsk.getHeight(), dsk.getWidth(), Titles.setTitel("SENTENCES_ADDITION"));
			dsk.add(ssa);
			dsk.moveToFront(ssa);
			dsk.repaint();
		}

		else if (src == miImport || src == importBtn) {
			ImportFromFile iff = new ImportFromFile(dsk.getHeight(), dsk.getWidth());
			dsk.add(iff);
			dsk.moveToFront(iff);
			dsk.repaint();
		}

		else if (src == miExport || src == exportBtn) {
			ExportToFile etf = new ExportToFile(dsk.getHeight(), dsk.getWidth(), Titles.setTitel("EXPORT"));
			dsk.add(etf);
			dsk.moveToFront(etf);
			dsk.repaint();
		}

		else if (src == miAddWords) {
			WordAddition wAdd = new WordAddition(dsk.getHeight(), dsk.getWidth(), Titles.setTitel("WORDS_ADDITION"));
			dsk.add(wAdd);
			dsk.moveToFront(wAdd);
			dsk.repaint();
		}

		else if (src == miWhatKind) {
			GuessArticle guessArticle = new GuessArticle(dsk.getHeight(), dsk.getWidth(),
					Titles.setTitel("WHAT_ARTICLE"));
			dsk.add(guessArticle);
			dsk.moveToFront(guessArticle);
			dsk.repaint();
		}

		else if (src == guessTheMeaning) {
			GuessTheMeaning guessMeaning = new GuessTheMeaning(dsk.getHeight(), dsk.getWidth(),
					Titles.setTitel("GUESS_THE_MEANING"));
			dsk.add(guessMeaning);
			dsk.moveToFront(guessMeaning);
			dsk.repaint();
		}

		else if (src == miAddPurposefulSentences) {
			AddPurposefulSentences addPSent = new AddPurposefulSentences(dsk.getHeight(), dsk.getWidth(),
					Titles.setTitel("PURPOSEFUL_SENTENCES"));
			dsk.add(addPSent);
			dsk.moveToFront(addPSent);
			dsk.repaint();
		}

		else if (src == miAddRules) {
			AddRule addRule = new AddRule(dsk.getHeight(), dsk.getWidth(), Titles.setTitel("ADD_RULES"));
			dsk.add(addRule);
			dsk.moveToFront(addRule);
			dsk.repaint();
		}

	}

}
