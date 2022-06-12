package edu.german.tools;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class MyProgressBar extends JPanel {
	private static final long serialVersionUID = 1L;
	JProgressBar bar;
	JLabel titleLabel;
	Integer width = 180;

	public MyProgressBar(String title) {
		titleLabel = new JLabel(title);
		titleLabel.setBounds(0, 0, width, 30);
		bar = new JProgressBar();
		fill(0);
		bar.setBounds(0, 35, width, 40);
		bar.setStringPainted(true);

		this.setLayout(null);
		this.add(titleLabel);
		this.add(bar);
	}

	public void fill(int var) {
		bar.setValue(var);
	}

	public void setInfo() {
		bar.setString("Import");
	}

	public void showProgress(int count) {
		bar.setString(count + "%");
	}

	public void setNull() {
//		bar.setUI(new BasicProgressBarUI());
		bar.setValue(0);
//		bar.setString("0%");
		this.repaint();
	}

	public void done() {
		bar.setString("Zakończono!");
	}
}
