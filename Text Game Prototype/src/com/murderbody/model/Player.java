package com.murderbody.model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;


public class Player extends Sprite {

	Point loc;	// Maybe every level will have the player loc
	BufferedImage image;
	List<String>[] heldCommands;
	boolean holding = false;
	String holdingName = null;

	public Player() {
		super(); // Don't do anything
		
		loc = null;
		image = loadImage();
		heldCommands = null;
		holding = false;
	}
	
	public boolean isHolding(){
		return holding;
	}
	
	/**
	 * Getter for holding name
	 * @return - the name/string of the object being held.
	 */
	public String getHeldObject(){
		return holdingName;
	}
	
	/**
	 * Setter for holdingName
	 * @param s
	 */
	public void pickUp(String s){
		holdingName = s;
	}

	public static void main(String[] args) {
		
	}

}








