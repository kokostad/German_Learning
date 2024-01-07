package edu.german.tools;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MyFrameProgressBar extends JFrame {
	private static final long serialVersionUID = 1L;
	private JProgressBar bar;
	private Integer width = 300;
	private JPanel panel;

	public MyFrameProgressBar() {
		bar = new JProgressBar();
		bar.setBounds(0, 10, width, 60);
		bar.setStringPainted(true);
		fill(0);

		panel = new JPanel();
		panel.add(bar);

		this.setTitle("Postęp");
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(320, 70);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
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
}
