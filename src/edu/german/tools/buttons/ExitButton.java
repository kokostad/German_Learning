package edu.german.tools.buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.german.cfg.MyIcon;
import edu.german.tools.ImgUtils;
import edu.german.tools.ScreenSetup;
import edu.german.tools.Titles;

public class ExitButton extends JButton {
	private static final long serialVersionUID = 1L;
	ScreenSetup sp = new ScreenSetup();

	public ExitButton() {
		ImgUtils myImg = new ImgUtils();
		ImageIcon image = new ImageIcon(myImg.scaleImage(sp.ICON_WIDTH, sp.ICON_HEIGHT, MyIcon.btn_path + MyIcon.exit));
		this.setIcon(image);
		this.setToolTipText(Titles.setTitel("EXIT"));
	}

}
