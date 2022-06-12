package edu.german.sentences;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.german.tools.MyComboBox;
import edu.german.tools.OneEditableField;
import edu.german.tools.Titles;

public class SentenceEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OneEditableField sentence;
	private OneEditableField meaning;
	private MyComboBox box;

	public SentenceEditPanel(String labelInfo1, String labelInfo2, String[] selectionList) {
		sentence = new OneEditableField(labelInfo1, null, 16, 70);
		meaning = new OneEditableField(labelInfo2, null, 16, 70);
		box = new MyComboBox(Titles.setTitel("CHOOSE_SENTENCE_TYP"), selectionList);

		this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		this.add(Box.createHorizontalGlue());
		this.add(sentence);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(meaning);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(box);
	}

	String[] getValues() {
		String[] array = new String[3];
		String newSentence = sentence.getValue();
		String newMeaning = meaning.getValue();
		String var = box.getValue();

		if (newSentence != null && newMeaning != null && var != null) {
			array[0] = newSentence;
			array[1] = newMeaning;
			array[2] = var;

			return array;
		}

		return null;
	}

	void setValues(String newSentence, String newMeaning, String typ) {
		sentence.setValue(newSentence);
		meaning.setValue(newMeaning);
		box.setValue(typ);
	}

}
