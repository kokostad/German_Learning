package edu.german.tools;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MyProgressBar extends JPanel {
	private static final long serialVersionUID = 1L;
	private JProgressBar bar;
	private JLabel titleLabel;
	private Integer width = 200;

	public MyProgressBar(String title) {
		titleLabel = new JLabel(Titel.setTitel(title));
		titleLabel.setBounds(0, 0, width, 30);
		bar = new JProgressBar();
		fill(0);
		bar.setBounds(0, 35, width, 40);
		bar.setStringPainted(true);

		this.setLayout(null);
		this.add(titleLabel);
		this.add(bar);
	}

	public MyProgressBar(String title, int width) {
		this.width = width;
		titleLabel = new JLabel(Titel.setTitel(title));
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

	public void setInfo(String info) {
		bar.setString(Titel.setTitel(info));
	}

	public void showProgress(int count) {
		bar.setString(count + "%");
	}

	public void setNull() {
		bar.setValue(0);
		this.repaint();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void done() {
		bar.setString("Zakończono!");
	}
}
