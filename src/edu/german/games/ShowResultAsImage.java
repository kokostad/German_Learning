package edu.german.games;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.german.tools.ImgUtils;


/**
 * @author Tadeusz Kokotowski. The class that displays the icons 
 * The class displays icons depending on the result. The default icon is neutral
 */
public class ShowResultAsImage extends JPanel {
	private static final long serialVersionUID = 1L;
	private static String sImageNothing = "src/edu/german/img/nothing.png";
	private static String sImageGood = "src/edu/german/img/good.png";
	private static String sImageAngry = "src/edu/german/img/angry.png";
	private Integer width = 130;
	private Integer hight = 130;
	private ImageIcon imgIcon;
	private BufferedImage bi;
	private JLabel myImage;
	private ImgUtils iu;

	public ShowResultAsImage() {
		iu = new ImgUtils();
		myImage = new JLabel();
		myImage.setBounds(0, 0, width, hight);
		myImage.setBorder(BorderFactory.createLineBorder(Color.black));

		bi = iu.scaleImage(width, hight, sImageNothing);
		imgIcon = new ImageIcon(bi);
		myImage.setIcon(imgIcon);
		add(myImage);
	}

	public ShowResultAsImage(int width, int hight) {
		this.width = width;
		this.hight = hight;
		iu = new ImgUtils();
		myImage = new JLabel();
		myImage.setBounds(0, 0, width, hight);
		myImage.setBorder(BorderFactory.createLineBorder(Color.black));

		bi = iu.scaleImage(width, hight, sImageNothing);
		imgIcon = new ImageIcon(bi);
		myImage.setIcon(imgIcon);
		add(myImage);
	}

	/**
	 * Show the icon depending on the result.
	 */
	public void showScore(boolean state) {
		if (state) {
			bi = iu.scaleImage(width, hight, sImageGood);
			imgIcon.setImage(bi);
			myImage.setIcon(imgIcon);
		} else {
			bi = iu.scaleImage(width, hight, sImageAngry);
			imgIcon.setImage(bi);
			myImage.setIcon(imgIcon);
		}
		repaint();
	}

	/**
	 * Show neutral icon
	 */
	public void showIndifference() {
		bi = iu.scaleImage(width, hight, sImageNothing);
		imgIcon.setImage(bi);
		myImage.setIcon(imgIcon);
		repaint();
	}

}
