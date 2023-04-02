package edu.german.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import edu.german.cfg.MyIcon;
import edu.german.games.GuessArticle;
import edu.german.games.GuessTheMeaning;
import edu.german.io.ExportToFile;
import edu.german.io.ImportFromFile;
import edu.german.sentences.AddSentences;
import edu.german.tools.AddRule;
import edu.german.tools.ImgUtils;
import edu.german.tools.MyToolbar;
import edu.german.tools.ScreenSetup;
import edu.german.tools.Titel;
import edu.german.tools.buttons.ModelButton;
import edu.german.words.AddAdjective;
import edu.german.words.SimpleWordAddition;
import edu.german.words.verbs.AddVerbs;

public class Learning extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JDesktopPane dsk;
	private JMenuBar mbar;
	private JMenuItem miImport;
	private JMenuItem miExport;
	private JMenuItem exit;
	private JMenuItem miSimpleWordsAddition;
	private JMenuItem miAddAdjective;
	private JMenuItem miAddVerbs;
	private JMenuItem miSentencesAddition;
	private JMenuItem miAddRules;
	private JMenuItem miWhatKind;
	private JMenuItem guessTheMeaning;
	private ModelButton importBtn;
	private ModelButton exportBtn;
	private ModelButton exitBtn;
	private ModelButton addRulesBtn;
	private ModelButton addSentenceBtn;

	public Learning() {
		ScreenSetup scr = new ScreenSetup();
		ImgUtils myImg = new ImgUtils();
		int iconWidth = scr.ICON_WIDTH;
		int iconHeight = scr.ICON_HEIGHT;

		ImageIcon img = new ImageIcon(myImg.scaleImage(iconWidth, iconHeight, MyIcon.mainIcon));
		this.setIconImage(img.getImage());
		this.setTitle(Titel.setTitel("MAIN_TITLE_GERMAN"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(scr.SCR_DEFAULT_WIDTH, scr.SCR_DEFAULT_HEIGHT);
		this.setResizable(true);

		ImageIcon importIcon = new ImageIcon(myImg.scaleImage(iconWidth, iconHeight, MyIcon.importItem));
		ImageIcon exportIcon = new ImageIcon(myImg.scaleImage(iconWidth, iconHeight, MyIcon.exportItem));
		ImageIcon exitIcon = new ImageIcon(myImg.scaleImage(iconWidth, iconHeight, MyIcon.exit));

		miImport = new JMenuItem(Titel.setTitel("IMPORT"), importIcon);
		miImport.addActionListener(this);
		miExport = new JMenuItem(Titel.setTitel("EXPORT"), exportIcon);
		miExport.addActionListener(this);

		exit = new JMenuItem(Titel.setTitel("EXIT"), exitIcon);
		exit.addActionListener(this);

		JMenu mOption = new JMenu(Titel.setTitel("OPTION"));
		mOption.add(miImport);
		mOption.add(miExport);
		mOption.addSeparator();
		mOption.add(exit);
		
		miSimpleWordsAddition = new JMenuItem(Titel.setTitel("SIMPLE_WORDS_ADDITION"));
		miSimpleWordsAddition.addActionListener(this);

		miSentencesAddition = new JMenuItem(Titel.setTitel("SENTENCES_ADDITION"));
		miSentencesAddition.addActionListener(this);

		JMenu mAddWords = new JMenu(Titel.setTitel("WORDS_ADDITION_AND_EDITION"));
		miAddVerbs = new JMenuItem(Titel.setTitel("EDIT_AND_ADD_VERBS"));
		miAddVerbs.addActionListener(this);
		miAddAdjective = new JMenuItem(Titel.setTitel("ADD_ADJECTIVE"));
		miAddAdjective.addActionListener(this);
		mAddWords.add(miAddAdjective);
		mAddWords.add(miAddVerbs);

		miAddRules = new JMenuItem(Titel.setTitel("ADD_RULES"));
		miAddRules.addActionListener(this);

		JMenu mAddition = new JMenu(Titel.setTitel("MENU_ADDITION"));
		mAddition.add(miSimpleWordsAddition);
		mAddition.add(mAddWords);
		mAddition.add(new JSeparator());
		mAddition.add(miSentencesAddition);
		mAddition.add(new JSeparator());
		mAddition.add(miAddRules);

		guessTheMeaning = new JMenuItem(Titel.setTitel("GUESS_THE_MEANING"));
		guessTheMeaning.addActionListener(this);
		miWhatKind = new JMenuItem(Titel.setTitel("WHAT_ARTICLE"));
		miWhatKind.addActionListener(this);

		JMenu mGuessing = new JMenu(Titel.setTitel("QUESSING"));
		mGuessing.add(miWhatKind);
		mGuessing.add(guessTheMeaning);

		JMenu mGames = new JMenu(Titel.setTitel("LEARNING_GAMES"));
		mGames.add(mGuessing);

		exitBtn = new ModelButton.Builder()
				.setTitle("Exit")
				.setIconName(MyIcon.exit)
				.setHint(Titel.setTitel("EXIT"))
				.build();
		exitBtn.addActionListener(this);

		importBtn = new ModelButton.Builder()
				.setTitle("Import")
				.setIconName(MyIcon.importItem)
				.setHint(Titel.setTitel("IMPORT"))
				.build();
		importBtn.addActionListener(this);

		exportBtn = new ModelButton.Builder()
				.setTitle("Export")
				.setIconName(MyIcon.exportItem)
				.setHint(Titel.setTitel("EXPORT"))
				.build();
		exportBtn.addActionListener(this);

		addRulesBtn = new ModelButton.Builder()
				.setTitle("Rules")
				.setIconName(MyIcon.rules)
				.setHint(Titel.setTitel("ADD_RULES"))
				.build();
		addRulesBtn.addActionListener(this);

		addSentenceBtn = new ModelButton.Builder()
				.setTitle("Add Sentence")
				.setIconName(MyIcon.add_sentence)
				.setHint(Titel.setTitel("ADD_SENTENCE"))
				.build();
		addSentenceBtn.addActionListener(this);
		
		MyToolbar toolbar = new MyToolbar();
		toolbar.add(exitBtn);
		toolbar.addSeparator();
		toolbar.add(importBtn);
		toolbar.add(exportBtn);
		toolbar.addSeparator();
		toolbar.add(addRulesBtn);
		toolbar.addSeparator();
		toolbar.add(addSentenceBtn);

		Container con = this.getContentPane();
		con.add(toolbar, BorderLayout.BEFORE_FIRST_LINE);

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

		else if (src == miSentencesAddition || src == addSentenceBtn) {
			AddSentences as = new AddSentences(dsk.getHeight(), dsk.getWidth(), Titel.setTitel("SENTENCES_ADDITION"));
			dsk.add(as);
			dsk.moveToFront(as);
			dsk.repaint();
		}

		else if (src == miSimpleWordsAddition) {
			SimpleWordAddition swa = new SimpleWordAddition(dsk.getHeight(), dsk.getWidth(),
					Titel.setTitel("SIMPLE_WORDS_ADDITION"));
			dsk.add(swa);
			dsk.moveToFront(swa);
			dsk.repaint();
		}

		else if (src == miAddAdjective) {
			AddAdjective aa = new AddAdjective(dsk.getHeight(), dsk.getWidth(), Titel.setTitel("ADD_ADDJECTIVES"));
			dsk.add(aa);
			dsk.moveToFront(aa);
			dsk.repaint();
		}

		else if (src == miAddVerbs) {
			AddVerbs av = new AddVerbs(dsk.getHeight(), dsk.getWidth(), Titel.setTitel("ADD_VERBS"));
			dsk.add(av);
			dsk.moveToFront(av);
			dsk.repaint();
		}

		else if (src == miImport || src == importBtn) {
			ImportFromFile iff = new ImportFromFile(dsk.getHeight(), dsk.getWidth(), Titel.setTitel("IMPORT"));
			dsk.add(iff);
			dsk.moveToFront(iff);
			dsk.repaint();
		}

		else if (src == miExport || src == exportBtn) {
			ExportToFile etf = new ExportToFile(dsk.getHeight(), dsk.getWidth(), Titel.setTitel("EXPORT"));
			dsk.add(etf);
			dsk.moveToFront(etf);
			dsk.repaint();
		}

		else if (src == miWhatKind) {
			GuessArticle guessArticle = new GuessArticle(dsk.getHeight(), dsk.getWidth(),
					Titel.setTitel("WHAT_ARTICLE"));
			dsk.add(guessArticle);
			dsk.moveToFront(guessArticle);
			dsk.repaint();
		}

		else if (src == guessTheMeaning) {
			GuessTheMeaning guessMeaning = new GuessTheMeaning(dsk.getHeight(), dsk.getWidth(),
					Titel.setTitel("GUESS_THE_MEANING"));
			dsk.add(guessMeaning);
			dsk.moveToFront(guessMeaning);
			dsk.repaint();
		}

		else if (src == miAddRules || src == addRulesBtn) {
			AddRule addRule = new AddRule(dsk.getHeight(), dsk.getWidth(), Titel.setTitel("ADD_RULES"));
			dsk.add(addRule);
			dsk.moveToFront(addRule);
			dsk.repaint();
		}

	}

}
