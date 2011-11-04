package com.murderbody.prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestingList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[][] array = { {"1", "2", "3", "4"}, {"9", "10"}};
		
		List< List<String> > list = new ArrayList<List<String> >();
		
		for (int x = 0; x < array.length;  x++){
			list.add(Arrays.asList(array[x]));
		}
	}

}
