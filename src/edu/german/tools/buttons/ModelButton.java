package edu.german.tools.buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.german.tools.ImgUtils;
import edu.german.tools.ScreenSetup;

public class ModelButton extends JButton {
	private static final long serialVersionUID = 1L;
	private String iconPath = "src/edu/german/img/btn/";
	private String title;
	private String hint;
	private String iconName;
	private ImageIcon icon;

	public ModelButton() {
	}

	public ModelButton(String title, String iconName, String hint) {
		ScreenSetup sp = new ScreenSetup();
		ImgUtils myImg = new ImgUtils();
		this.setToolTipText(title);
		this.title = title;
		this.iconName = iconName;
		this.setToolTipText(hint);
		this.setIcon(new ImageIcon(myImg.scaleImage(sp.ICON_WIDTH, sp.ICON_HEIGHT, iconPath + iconName)));
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public static class Builder {
		private String title;
		private String hint;
		private String iconName;

		public Builder() {
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setIconName(String iconName) {
			this.iconName = iconName;
			return this;
		}

		public Builder setHint(String hint) {
			this.hint = hint;
			return this;
		}

		public ModelButton build() {
			return new ModelButton(title, iconName, hint);
		}

	}

}
