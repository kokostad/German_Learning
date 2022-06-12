package edu.german.tools.buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.german.cfg.MyIcon;
import edu.german.tools.ImgUtils;
import edu.german.tools.ScreenSetup;
import edu.german.tools.Titles;

public class ImportButton extends JButton {
	private static final long serialVersionUID = 1L;

	public ImportButton() {
		ImgUtils myImg = new ImgUtils();
		ImageIcon image = new ImageIcon(
				myImg.scaleImage(ScreenSetup.ICON_WIDTH, ScreenSetup.ICON_HEIGHT, MyIcon.btn_path + MyIcon.importItem));

		this.setIcon(image);
		this.setToolTipText(Titles.setTitel("IMPORT"));
	}

}
