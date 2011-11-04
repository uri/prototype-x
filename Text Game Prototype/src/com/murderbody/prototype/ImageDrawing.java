package com.murderbody.prototype;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;


public class ImageDrawing extends JApplet{

	BufferedImage theImage;
	
	public static void main (String[] args) {
		JFrame jf = new JFrame("Test");
    	ImageDrawing imageTesting = new ImageDrawing();
    	jf.getContentPane().add(imageTesting);
    	jf.setVisible(true);
    	jf.setSize(360, 240 + 30);
    	jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
    	jf.setLocationRelativeTo(null);
    	
    	imageTesting.init();
    	imageTesting.start();
	}
	
	public void init() {
		setSize(360, 260);
        setVisible(true);
        setLayout(null);
        
        File imageloc = new File("resources" + File.separator + "LevelOne" + ".png");
        try {
			theImage = ImageIO.read(imageloc);
		} catch (IOException e) {
			System.out.println("Image not found");
			System.exit(-1);
		}
	}
	
	public void update() {
		paint(getGraphics());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
//		g.drawImage(theImage, 0, 0, null);
		g.drawImage(theImage, 0, 0, 360, 240, 0, 0, 90, 60, null);
	}
	
	public void start() {
		update();
	}
}
