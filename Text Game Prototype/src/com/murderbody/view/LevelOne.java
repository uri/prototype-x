package com.murderbody.view;

import java.awt.Point;

import com.murderbody.model.Level;

public class LevelOne extends Level {

	private static Point playerPos = new Point (5,5); // Where the player is in the level
    private static String nameStatic = "LevelOne";
    private static String[] entityNames = {"GarbageBag", "Door"};
    private static String music = "1";
    
    public LevelOne(){
        super(nameStatic, entityNames, playerPos, music);
    }

}










