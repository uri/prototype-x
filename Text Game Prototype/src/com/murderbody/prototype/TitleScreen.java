package com.murderbody.prototype;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 * This will be the entry point application
 * @author Uri
 */
public class TitleScreen extends JApplet implements KeyListener {

    public boolean running = true;
    String input;
    String output;
    Set<String> foundStrings;
    Map<String, String> outputDataBase;
    
    private static int width = 640;
    private static int height = 380;
    
    
    private String title = "TEXT-BASED SUPER ACTION EXPLODING GAME";
    private String subTitle = "RipFuckingCord Productions. TEST";
    
    // Animation variables
    int trans = height - 100;		// Starting ps
    int subTrans = height - 100;	// Starting pos
    Color toDraw = Color.BLACK;
    
    public void init(){
        input = "";
        output = "";
        toDraw = Color.black;
        outputDataBase = populateOutput();
        foundStrings = new HashSet<String>();
        
        setSize(width, height);
        setVisible(true);
        setLayout(null);
        
        addKeyListener(this);
        requestFocus();
    }
    
    public void start(){
        run();
    }
    
    private Map<String, String> populateOutput() {
    	Map<String, String> returning = new HashMap<String, String>();
    	
    	returning.put("play"," Sorry, you can't play the proof of concept.");
    	returning.put("win","Yeah ok, you win!");
    	returning.put("stab","What are you a psycho?!");
    	returning.put("quit","Fine whatever just leave the page, I don't care!");
    	returning.put("help","No figure it out yourself");
    	returning.put("uri","Yeah he's pretty cool.");
    	returning.put("tv","No, this is your computer...");
    	returning.put("xbox","This is better.");
    	returning.put("ps3","More like ps-whatever!");
    	returning.put("zombie","Of course.");
    	returning.put("fuck","Why are you so angry :(");
    	returning.put("minecraft","That game is too popular");
    	returning.put("facebook","Yeah be sure to add me.");
    	returning.put("rainbow","Yeah colours!");
    	returning.put("omg","nomgnomgnomg");
    	returning.put("test","What are you doing..");
    	returning.put("dog","cat");
    	returning.put("cat","dog");
    	returning.put("hi","sup");
    	returning.put("harry potter","That movie blew so hard.");
    	returning.put("moon","stars!");
    	returning.put("robot","beep boop boop");
    	returning.put("alien","Soon....");
    	
    	return returning;
	}

	public void run () {
    	
		URL musicURL = null;
    	try {
			
    	    File musicFile = new File("resources" + File.separator + "LevelOne-Music-" + "1" +".wav");
    	    musicURL = musicFile.toURI().toURL();
			
		} catch (MalformedURLException e) {
			System.out.println("test");
		}
		AudioClip clip = getAudioClip(musicURL); 
		
		clip.play();
		
    	while (running){
    		update(getGraphics());
    		try {
        		Thread.sleep(30);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
    	}
    }
    
    public void paint(Graphics g) {
    	super.paint(g);

    	// Set the background
    	g.setColor(toDraw);
    	g.fillRect(0, 0, width , height);

    	// Now draw the text
    	g.setColor(Color.WHITE);

    	g.setFont(new Font("Courier", Font.PLAIN,  24));
    	
    	// Move the text
    	if (trans > 80) {
    		g.drawString(title, 15, trans);
    	} else if (subTrans > 105) {
    		g.drawString(title, 15, trans);
    		g.drawString(subTitle, 15, subTrans);
    	} else {
    		g.drawString(title, 15, trans);
    		g.setColor(Color.PINK);
    		g.drawString(subTitle, 15, subTrans);
    		g.setColor(Color.WHITE);
    		
    		
    		// Let the user type
    		g.drawString(input, 10, height - 30);
    		
    		g.setColor(Color.red);
    		g.drawString(output, 10, height - 60);
    		
    		// Counter
    		g.drawString(foundStrings.size() + "/" + outputDataBase.size(), width - 70, height - 90);
    	}
    	
    }
    
    public void update(Graphics g){
    	// Move the text
    	if (trans > 80) {
    		trans -= 3;
    	} else if (subTrans > 105) {
    		subTrans -= 5;
    	} else {
    		running = false;
    	}
    	
    	paint(g);
    }

    public void keyPressed(KeyEvent e) {

    	output = "";
    	if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE ){
    		if (input.length() > 0)
    			input = input.substring(0, input.length() -1); // why is this not working
    	}
    	
    	else if (e.getKeyCode() == (KeyEvent.VK_ENTER)) { // if enter is hit then do
            processInput(input);
            input = "";
        } else {
        	
        	if (e.getKeyCode() == KeyEvent.VK_SHIFT) return;
        	
        	input+= e.getKeyChar();
        }
    	
    	update(getGraphics());
    	
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    /** 
     * The Brains
     * @param input
     */
    public void processInput(String input) {
    	if (outputDataBase.containsKey(input)){
    		output = outputDataBase.get(input);
    		foundStrings.add(input);
    		
    	} else {
    		output = "I don't know what that means :(";
    	}
    }

    public static void main(String[] args) {
    	JFrame jf = new JFrame("Test");
    	TitleScreen game = new TitleScreen();
    	jf.getContentPane().add(game);
    	jf.setVisible(true);
    	jf.setSize(width, height + 30);
    	jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
    	jf.setLocationRelativeTo(null);
    	
    	game.init();
    	game.start();
    }
}