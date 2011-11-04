package com.uri.testing;

import com.murderbody.model.Level;
import com.murderbody.view.LevelOne;

public class LevelCreation {

	public static void main (String[] args) {
		
		Level a= null;
		try {
			a = (Level) Class.forName("com.murderbody.view.LevelOne").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Woooooooop"+ a.getLevelName()
				);
	}
}
