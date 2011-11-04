package com.murderbody.control;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JFrame;

import com.murderbody.model.Engine;
import com.murderbody.model.Level;

/**
 * @author Uri
 *
 */
public class Game extends JApplet implements Runnable, KeyListener {

	
	public static final boolean DEBUG = true;
    public static String TITLE = "MurderBody"; // The title of the game
    public static int WIDTH = 360;
    public static int HEIGHT = 240;
    private static String[] levelNames = {
    
    	"LevelOne"
    	
    };
    private boolean running = true;
    private Engine engine;
    private boolean isUsrTyping = false;
    private String usrInput = "";
    private String responseStr = "";
    private AudioClip currentMusic;
    
    /**
     * Constructor
     * @return 
     */
    public void init() {
        // Make the levels list
        List<Level> listLevels = new ArrayList<Level>();
        
        this.addKeyListener(this);
        
        // Add the levels
        for (String s : levelNames){
        	Level level = null;
            
        	// Reflections, creates the levels
            try {
            	
                level = (Level) Class.forName("com.murderbody.view."+s).newInstance();
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (ClassNotFoundException e) {
            }
            
            // Actually adds the levels to the list
            listLevels.add(level);
            
            // Get the music working
            if ( engine.getLevels().get(engine.getScreenNumber()) == null){
                System.out.println("blah");
            }
            
            currentMusic = loadMusic( engine.getLevels().get(engine.getScreenNumber()).getMusicName() );
            
            // For debugging
            if (DEBUG) { 
            	// Shows the location of where you click
	            this.addMouseListener( new MouseListener() {
					
					public void mouseReleased(MouseEvent e) {}
					public void mousePressed(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					
					public void mouseClicked(MouseEvent e) {
						System.out.println(e.getX()/4 + ", " + e.getY()/4);
					};
				});
            }
        }
        
        // Initialize the model
        engine = new Engine(listLevels);
        
        // Initialize applet stuff
        setSize(WIDTH * 2, HEIGHT * 2);
        setVisible(true);
        setLayout(null);
        
        run();
    }

    public void update(Graphics g, BufferedImage image) {
    	paint(g, image);
    	
    }
    
    public void start() {
    	run();
    }
    
    /**
     * @see java.lang.Runnable#run()
     * This will be where everything happens
     */
    public void run() {
    	requestFocus();
    	update(getGraphics(), engine.getCurrentScreen());
    }
    
    /**
     * Adding the buffered image may cause errors
     * @param g
     * @param image
     */
    public void paint (Graphics g, BufferedImage image) {
    	super.paint(g);
    	
    	g.drawImage(image, 0, 0, 360, 240, 0, 0, 90, 60, null);    	
    	
    	// User input stuff
    	if (isUsrTyping){
    		// Display a black box with their string
    		g.setColor(Color.BLACK);
    		
    		int textPos = HEIGHT - 50;
    		g.fillRect(0, textPos, WIDTH, 50);
    		
    		// Draw the text
    		g.setColor(Color.white);
    		g.setFont(new Font("Courier", Font.PLAIN,  24));
    		g.drawString(usrInput, 0, textPos + 30); // Because string draws from bottom left corner not top left
    		
    	} 
    	
    	// Show the response
    	if (responseStr != null && !responseStr.equals("")) {
    		g.setColor(Color.white);
    		g.fillRect(0, 0, WIDTH, 50);
    		
    		// Draw the text
    		g.setColor(Color.black);
    		g.setFont(new Font("Courier", Font.PLAIN,  24));
    		g.drawString(responseStr, 0, 30); // Because string draws from bottom left corner not top left
    	}
    	
    	
    }
    
    public AudioClip loadMusic(String name) {
    	
    	URL musicURL = null;
    	
    	try {
			musicURL = new URL("resources" + File.separator + "LevelOne-Music-" + name);
		} catch (MalformedURLException e) {
		}
		return getAudioClip(musicURL);
    }
    
    
    public static void main (String[] args) {
    	
        JFrame jf = new JFrame("Test");
    	Game game = new Game();
    	jf.getContentPane().add(game);
    	jf.setVisible(true);
    	jf.setSize(WIDTH, HEIGHT+20);
    	jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
    	jf.setLocationRelativeTo(null);
    	
    	game.init();
    	game.start();
        
    }

    /*********************************************
     * 				   KEY EVENTS 				 *
     *********************************************/
    
	public void keyPressed(KeyEvent e) {
		/**
		 * If the user hits enter, then they want to start something
		 */
		
		if (e.getKeyCode() == e.VK_ENTER) {
			// Toggles input
			if (isUsrTyping){
				isUsrTyping = false;
				
				// Either user was never typing or more likely, user has just finished typing
				if (!usrInput.equals("")){
					responseStr = engine.processInput(usrInput); // Call the engine to do its shit
					
				}
				
			} else {
				isUsrTyping = true;
				// Clear the user input
				usrInput = "";
				responseStr = "";
			}
		} 
		
		/**
		 * Back space functionality
		 */
		else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE ){
    		if (usrInput.length() > 0)
    			usrInput = usrInput.substring(0, usrInput.length() -1); // why is this not working
    	}
		
		/**
		 * Ignore character like shift we do not want this stored in the string	
		 */
		else if (
				e.getKeyCode() == e.VK_CAPS_LOCK ||
				e.getKeyCode() == e.VK_CONTROL ||
				e.getKeyCode() == e.VK_SHIFT ||
				e.getKeyCode() == e.VK_ALT
				) {
			// Do nothing, we do not want these stored in our string
			
		/**
		 * User is typing a word we want to store each character 	
		 */
		} else {
			// Store the user input
			usrInput += e.getKeyChar();
		}
		
		// Update
		update(getGraphics(), engine.getCurrentScreen());
		System.out.println(usrInput);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
