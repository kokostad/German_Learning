package edu.german.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import edu.german.io.ImportFromFile;
import edu.german.tools.buttons.ExitButton;
import edu.german.tools.buttons.ImportButton;

public class MyFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JToolBar toolbar;
	private JButton importBtn;
	private JButton exitBtn;

	public MyFrame(String title) {
		ScreenSetup sp = new ScreenSetup();
		Image img = new ImageIcon("src/edu/german/img/tak2.gif").getImage();
		this.setIconImage(img);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(sp.SCR_DEFAULT_WIDTH, sp.SCR_DEFAULT_HEIGHT);
		this.setResizable(true);

		importBtn = new ImportButton();
		importBtn.addActionListener(this);

		exitBtn = new ExitButton();
		exitBtn.addActionListener(this);

		toolbar = new MyToolbar();
		toolbar.add(exitBtn);
		toolbar.addSeparator();
		toolbar.add(importBtn);
		
		Container contentPane = this.getContentPane();
		contentPane.add(toolbar, BorderLayout.NORTH);

	}

	public JButton getImport() {
		return importBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == exitBtn) {
			System.exit(0);
		} 

	}
}
