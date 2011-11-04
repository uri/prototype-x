package com.murderbody.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.murderbody.control.Game;

public abstract class Level {

	protected Point playerPos;
    protected String name;              // Name of the level
    protected List<Sprite> sprites;
    protected BufferedImage background;
    protected String[] entityNames;
    protected String musicName;
    
    /**
     * Constructor
     */
    public Level(String name, String[] entityNames, Point playerPos, String musicStr) {
        
    	this.playerPos = playerPos;
    	this.entityNames = entityNames;
    	this.name = name;
    	musicName = musicStr;
    	
        // These have to be called after
        background = loadBackground();
        sprites = loadEntities();
    }
    
    public Level() {
    }
    
    /**
     * This can make an image draw stuff on it and the return the image
     * @param g
     */
    public BufferedImage draw() {
        
        BufferedImage returning = new BufferedImage(Game.WIDTH, Game.HEIGHT, ColorSpace.TYPE_RGB);
        // Draw the image
        Graphics g = returning.getGraphics();
        
        // Draw our loaded image
        g.drawImage(background, 0, 0, null);
        
        // Then draw the entities
        for (Sprite e : sprites) {
            e.draw(g);
        }
        
        return returning;
    }
    

    /**
     * Loads the background Image    
     * @return
     */
    private BufferedImage loadBackground() {
        
        // Load the background
        File imageloc = new File("resources"+ File.separator + name + ".png");
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(imageloc);
        } catch (IOException ex) {
        	System.out.println("Level image not found");
        }
        
        return image;
    }
    
    
    /**
     * Every subclass of screen should be able to loadEntites
     * but they will all be different thus no partial
     * implementation
     * @return
     */
    protected List<Sprite> loadEntities() {

        List<Sprite> entities = new ArrayList<Sprite>();

        for (String s : entityNames) {

            Sprite ent = null;
            
            String t = "com.murderbody.view.";
            t+= s;
            
            try {
                ent = (Sprite) Class.forName(t).newInstance();
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (ClassNotFoundException e) {
            }
            
            entities.add(ent);
        }

        return entities;
    }
     
    /**************************************************
     * Getters
     *************************************************/
     
     /**
     * @return The list of entities on the screen
     */
    public List<Sprite> getSprites(){
        return sprites;
    }
    
    public Point getPlayerPosition(){
    	return playerPos;
    }
    
    public String getLevelName() {
    	return name;
    }
    
    public String getMusicName() {
    	return musicName;
    }
    
}
