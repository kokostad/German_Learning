package edu.german.tools.buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.german.tools.ImgUtils;
import edu.german.tools.ScreenSetup;

public class ModelButton extends JButton {
	private static final long serialVersionUID = 1L;
	private ScreenSetup sp = new ScreenSetup();
	private ImgUtils myImg = new ImgUtils();
	private String title;
	private String hint;
	private String iconName;

	public ModelButton() {
	}

	public ModelButton(String title, String iconName, String hint) {
		this.title = title;
		this.iconName = iconName;
		this.hint = hint;
		this.setToolTipText(title);
		this.setToolTipText(hint);
		this.setIcon(new ImageIcon(myImg.scaleImage(sp.ICON_WIDTH, sp.ICON_HEIGHT, iconName)));
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public static class Builder {
		private String title;
		private String hint;
		private String iconName;

		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder withIconName(String iconName) {
			this.iconName = iconName;
			return this;
		}

		public Builder withHint(String hint) {
			this.hint = hint;
			return this;
		}

		public ModelButton build() {
			return new ModelButton(title, iconName, hint);
		}
	}

}
