package edu.german.tools;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import edu.german.tools.buttons.ExitButton;

public class MyInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	public MyInternalFrame(int height, int width, String titel) {
		ScreenSetup sp = new ScreenSetup();
		String myIcon = "src/edu/german/img/tak3.gif";
		setFrameIcon(new ImageIcon(myIcon));
		setTitle(titel);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setSize(width, height - sp.BOTTOM_BORDER);
		setResizable(true);
		BorderLayout bl = new BorderLayout(10, 20);
		setLayout(bl);

		ExitButton bExit = new ExitButton();
		bExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JToolBar tb = new JToolBar();
		tb.setRollover(true);
		tb.add(bExit);

		add(tb, BorderLayout.BEFORE_FIRST_LINE);

		setVisible(true);
	}
}