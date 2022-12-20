package edu.german.sentences;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import edu.german.tools.MyFont;

public class OneSentencePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String title;
	private int height;
	private int width;
	private String hint;
	private JTextArea sentenceArea;
	private int fontSize = 16;

	public OneSentencePanel() {
		// TODO Nothing
	}

	public OneSentencePanel(String title, int height, int width, String hint) {
		this.title = title;
		this.height = height;
		this.width = width;
		this.hint = hint;

		JLabel infoLab = new JLabel(title);
		infoLab.setFont(new MyFont().myFont(fontSize));

		sentenceArea = new JTextArea();
		sentenceArea.setFont(new Font("Serif", Font.ITALIC, fontSize));
		sentenceArea.setPreferredSize(new Dimension(width, height));
		sentenceArea.setLineWrap(true);
		sentenceArea.setWrapStyleWord(false);
		sentenceArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		if (hint != null)
			sentenceArea.setToolTipText(hint);

		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getViewport().add(sentenceArea);

		add(infoLab);
		add(scroll);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getValue() {
		return sentenceArea.getText();
	}

	public void setValue(String sentence) {
		sentenceArea.setText(sentence);
	}

	public void clearField() {
		sentenceArea.setText(null);
	}

	public static class Builder {
		private String title;
		private int height;
		private int width;
		private String hint;

		public Builder() {
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public void setHint(String hint) {
			this.hint = hint;
		}

		public OneSentencePanel build() {
			return new OneSentencePanel(title, height, width, hint);
		}
	}
}
