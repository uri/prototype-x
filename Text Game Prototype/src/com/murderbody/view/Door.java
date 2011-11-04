package com.murderbody.view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.murderbody.model.Sprite;

public class Door extends Sprite {

    private static String levelName = "LevelOne";
    private static Point location = new Point(25,5);
    private static String[] synonyms = {"door"};
    private static List<List<String>> commandsList; // List of Arraylists
    private static String[][] commandActions = {{"inspect"},
                                                    {"kick", "ram", "punch", "shove"},
                                                        {"push", "open", "lean"}, 
                                                            {"pull", "tug"}, 
                                                                {"knock", "bang", "ring"}};
    
    /**
     * How are we going to deal with object that can't be held
     */
    private static String[][] holdingActions;

    private static List<List <String>> holdingList; // List of lists
    
    private static String[] responsesArray ={"This is the front door to your house, it is locked from the outside... how conveniently odd.",
                                                "You broke the door...its a good thing you're not worried about drawing attention.", 
                                                    "The door is locked; you don't have your key, your dealer took it as colateral",
                                                        "This door opens outwards.",
                                                            "This is the front door to your house, no one is going to answer from outside"};
                                            
    
    public Door() {
        super(levelName, location,
                commandActions, 
                holdingActions,
                responsesArray, 
                synonyms);
    }
}
