package qrcode.sample;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class QrCodeView extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4336346870058151630L;
	private String text;
	private BufferedImage bitmap;
	private int y = 48;
	private int x = 10;
	private Color bgcolor;
	private int x2 = 10;
	private int y2 = 80;
	private int width = 300;
	private int height = 300;
	private ImageObserver observer;

	public void setText(String newText) {
		text = newText;
	}

	public String getText() {
		return text;
	}

	public void importBitmap(File input) {
		try {
			bitmap = ImageIO.read(input);
		} catch (IOException e) {
		}
	}

	public void exportBitmap(String formatName, File output) {
		try {
			ImageIO.write(bitmap, formatName, output);
		} catch (IOException e) {
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString(text, x, y);
		g.drawImage(bitmap, x2, y2, width, height, bgcolor, observer);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
