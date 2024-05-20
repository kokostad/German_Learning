package edu.german.tools;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MyFrameProgressBar extends JFrame {
	private static final long serialVersionUID = 1L;
	private JProgressBar bar;
	private Integer width = 420;
	private Integer height = 150;
	private JPanel panel;

	public MyFrameProgressBar(String title) {
		title = "Postęp ...";
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bar = new JProgressBar();
		bar.setPreferredSize(new Dimension(width - 10, height - (height / 2)));
		bar.setStringPainted(true);
		fill(0);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
//		panel.setSize(width - (getInsets().left + getInsets().right), height - (getInsets().top + getInsets().bottom));
		panel.add(bar);

		this.getContentPane().add(panel);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

	public void fill(int var) {
		bar.setValue(var);
	}

	public void showProgress(int count) {
		bar.setString(count + "%");
	}

	public void done() {
		bar.setString("Zakończono!");
	}

	public void setNull() {
		bar.setValue(0);
	}
}
