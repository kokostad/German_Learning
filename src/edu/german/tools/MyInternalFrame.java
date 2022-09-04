package edu.german.tools;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import edu.german.tools.buttons.ExitButton;

public class MyInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	protected JToolBar toolBar;

	public MyInternalFrame(int height, int width, String titel) {
		String myIcon = "src/edu/german/img/tak3.gif";
		this.setFrameIcon(new ImageIcon(myIcon));
		this.setTitle(titel);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setSize(width, height - ScreenSetup.BOTTOM_BORDER);
		this.setResizable(true);

		ExitButton bExit = new ExitButton();
		bExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.add(bExit);

		this.add(toolBar, BorderLayout.BEFORE_FIRST_LINE);
		setVisible(true);
	}
}