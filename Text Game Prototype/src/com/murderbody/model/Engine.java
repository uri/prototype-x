package com.murderbody.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Engine {

	/**
	 * Responses when a player enters something invalid.
	 * This could also be changed to an array and located in every
	 * level so there would be individual responses
	 */
	private static final String BAD_QUERY = "I don't know what that means.";
	
    /**
     * The current integer locaation of the screen contained
     * in the 'levels' list
     */
    private int currentScreen;

    /** 
     * All possible screens for the game, all of the levels.
     */
    private List<Level> levels;
    
    /**
     * Constructor
     */
    public Engine(List<Level> levels) {
        currentScreen = 0;
        this.levels = levels;
    }
    


    /*************************************************************
     * Methods
     *************************************************************/

    /**
     * Need to figure out when to call this
     * 
     * @return Returns false if it's out of bounds, i.e. there are no more screens left
     * maybe this can also signify that the user has ended the game
     */
    private boolean advanceScreen() {
        currentScreen++;
        return (currentScreen < levels.size() - 1);
    }
    
    /**
     * The heart of the model
     * 
     * Do we filter out numbers?
     */
    public String processInput(String input) {
    	
    	// Get the input in a list with no duplicates
    	String[] potentialCmds = input.split(" ");
    	Set<String> noDubs = new HashSet<String>(Arrays.asList(potentialCmds));
    	List<String> cmds = new ArrayList<String>();
    	cmds.addAll(noDubs);

    	// Get all the objects on the current level
    	List<Sprite> spriteList = levels.get(currentScreen).getSprites();
    	
    	Sprite _currentObject = null;		// If players input matches we hold on to the object
    	String commandToRemove = null;     	// We use this so we can remove it later from the list
    	
    	// Find the object
    	for (String s : cmds){
    	    for (Sprite sp : spriteList){
    	        if (sp.getObjectNames().contains(s)){
    	            // If the users string is in the object names then we have found it
    	            _currentObject = sp;
    	            commandToRemove = s;
    	            break;
    	        }
    	    }
    	}
    	
    	// If we have found no object then the user has failed
    	if (_currentObject == null) return BAD_QUERY;
    	
    	// Remove this because it cannot be an action and has already been processed
    	cmds.remove(commandToRemove);

    	
    	/**
    	 * At this point we have the sprite, now we check for the commands
    	 * or actions. 
    	 */
    	
    	int responseLoc = -1;	// The location in the array
    	for (int i = 0; i < _currentObject.getCommandList().size(); i++) {
    		
    		// The current list of commands
    		List<String> currentList = _currentObject.getCommandList().get(i);
    		
    		// Check the remaining commands
    		for (String s : cmds) {
    			if (currentList.contains(s)) {
    				// We have found an action
    				responseLoc = i; // Store this for the response
    			} // if
    		} // for-each
    	} // for
    	
    	// If we didn't find the right action fail.
    	if (responseLoc == -1) return BAD_QUERY;
    	
    	return _currentObject.getResponse(responseLoc);
    }

    
    
    
    /************************************
     * Getters
     ************************************/
    
    public List<Level> getLevels() {
    	return levels;
    }
    
    public BufferedImage getCurrentScreen() {
        return levels.get(currentScreen).draw();
    }
    
    public int getScreenNumber() {
    	return currentScreen;
    }
}













