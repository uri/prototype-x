package com.murderbody.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Sprites and stuff
 */
public abstract class Sprite {

	protected boolean holdable;
    protected String levelName;
    protected String name;
    protected Point loc;            
    protected BufferedImage image;
    protected List<List<String>> commandList;
    protected String[][] actions;
    protected List<List<String>> holdingList;
    protected String[][] holdingActions;
    protected String[] responsesArray;
    protected String[] synonyms;

    public Sprite() {
    }
    
    public Sprite(String levelName, Point loc, 
    		String[][] actions, String[][] holdingActions, 
    		String[] responsesArray, String[] synonyms ) {
        
        this.name = getClass().getSimpleName();
        this.levelName = levelName;
        this.loc = loc;
        this.actions = actions;
        this.holdingActions = holdingActions;
        this.responsesArray = responsesArray;
        this.synonyms = synonyms;
        initializeLists();
        image = loadImage();
    }
    
    /**
     * Since we can only initialize arrays in our classes we need to transform them
     * into Lists
     */
    public void initializeLists() {
    	commandList = new ArrayList<List<String> >();
    	
        for (int x = 0; x < actions.length; x++) {
            commandList.add(Arrays.asList(actions[x]));
        }
        
        // For objects like door that have no holding actions
        if (holdingActions != null){
        	
        	holdingList = new ArrayList<List<String>>();
        	
        	for (int x = 0; x < holdingActions.length; x++) {
                holdingList.add(Arrays.asList(holdingActions[x]));
            }
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, loc.x, loc.y, null);
    }
    
    /**
     * Loads the image from the resource folder
     * @return
     */
    protected BufferedImage loadImage() {
     // Load the background
        File imageloc = new File("resources" + File.separator + levelName + "-" + name + ".png");
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(imageloc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }
    
    public List<String> getObjectNames() {
        return Arrays.asList(synonyms);
    }
    
    public List<List<String>> getHoldingList() {
        return holdingList;
    }
    
    public List<List<String>> getCommandList(){
    	return commandList;
    }
    
    public String getResponse(int loc) {
    	return responsesArray[loc];
    }
}
