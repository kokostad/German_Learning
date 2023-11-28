package edu.german.tools.buttons;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.german.tools.MyFont;
import edu.german.tools.MyProperties;
import edu.german.tools.ScreenSetup;
import edu.german.tools.Translator;

public class ButtonsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Color bgColor;
	private MyFont myFont;
	private Integer defaultFontSize;
	private List<JButton> buttons;
	private int myWidth;
	
	public ButtonsPanel() {
		myFont = new MyFont();
		bgColor = ScreenSetup.BG_COLOR;
		defaultFontSize = new MyProperties("screen.properties")
				.getIntValue("DEFAULT_BUTTON_FONT_SIZE");
		setMyWidth(this.getWidth());
	}

	public void setFontSize(Integer size) {
		this.defaultFontSize = size;
	}

	public List<JButton> getButtonList() {
		return buttons;
	}

	public ButtonsPanel(Object[] objects) {
		bgColor = ScreenSetup.BG_COLOR;
		defaultFontSize = new MyProperties("screen.properties")
				.getIntValue("DEFAULT_BUTTON_FONT_SIZE");
		int size = objects.length;
		buttons = new ArrayList<>(size);
		myFont = new MyFont();
		GridLayout gl = new GridLayout(size, 1, 5, 15);
		JPanel p = new JPanel();
		p.setBackground(bgColor);
		p.setLayout(gl);

		for (int i = 0; i < objects.length; i++) {
			String t = new Translator().translate(objects[i].toString());
			if (t != null) {
				JButton button = new JButton(t);
				buttons.add(button);
				button.setFont(myFont.myFont(defaultFontSize));
				setMyWidth(button.getWidth());
				p.add(button);
				p.revalidate();
				this.add(p);
			} else {
				JButton button = new JButton(objects[i].toString());
				button.setFont(myFont.myFont(defaultFontSize));
				setMyWidth(button.getWidth());
				p.add(button);
				p.revalidate();
				this.add(p);
				buttons.add(button);
			}
		}
		this.setBackground(bgColor);
		setMyWidth(this.getWidth());
	}

	public int getMyWidth() {
		if(myWidth > 0)
			return myWidth;
		
		return 200;
	}

	public void setMyWidth(int myWidth) {
		if (myWidth < getMyWidth())
			this.myWidth = myWidth;
	}
}
