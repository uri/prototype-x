package com.murderbody.view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.murderbody.model.Sprite;

public class GarbageBag extends Sprite {
    private static String levelName = "LevelOne";
    private static Point location = new Point(60,39);
    // Other possible names for this object
    
    private static String[] synonyms = {"bag", "garbage", "corpse" }; 
    
    // List of commands the user can use
    private static String[][] commandActions = {
        {"kick", "prod", "punch", "stomp"},
        {"pick", "grab", "lift", "hold"},
        {"inspect","look", "view"},
        {"stab", "break", "tear","open"}, 
        {"fire", "burn", "explode", "heat"} };
    
    // The extra list of commands that the user can get if he is holding something
    
    // Array version, should get transformed into a list in the initlizeLists method in
    private static String[][] holdingActions = {{"throw", "toss", "drop", "launch"}, {"eat", "devour", "bite", "nomnomnom"}};

    // Responses
    private static String[] responsesArray ={"The bag moved, nothing happened", "You are now holding the bag, it smells and is oozing blood.",
                                                "This is a garbage bag containing the corpse of your friend. You will have to get rid of it.",
                                                "You just spent twenty minutes getting him in there, you probably should leave the bag as is.",
                                                "Please don't attempt to desacrate your friends corpse... YOU FREAK.", 
                                                // These are the responses to actions done while holding the bag
                                                "You tossed the bag on the floor.", "This game does not support canabilism, freak"};
                                            
    
    public GarbageBag() {
        super(levelName, location,
        		 commandActions, 
        		 holdingActions,
        		responsesArray, synonyms);
    }
}
