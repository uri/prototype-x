package com.uri.wordmasher;

import java.awt.image.SampleModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordMasher {

    private static String SAVE_FILE_NAME = "saved.txt";
    
    private static List<String> words = new ArrayList<String>();
    private static List<String> saveList = new ArrayList<String>();

    public static void main(String[] args) {

        String[] basic = {"basic.txt"};
        String[] adjectives = {"words.txt"};
        
        String[] basicAndAdj = {"basic.txt", "words.txt"};
        String[] names = {"britcaps.txt"};
        String[] huge = {"brit-a-z.txt", "britcaps"};                           // Warning 20 second load time
        String[] all = {"basic.txt", "brit-a-z.txt", "britcaps.txt", "words"};  // Warning 20 second load time
        
        // Import the files
        importList(basic);

        int input = -1;

        String current = "";
        while (input != 0) {
            System.out.println("1. Single Word ");
            System.out.println("2. Two word Mash");
            System.out.println("3. Save word.");
            System.out.println("0. Save List and Quit.");
            System.out.println();

            Scanner c = new Scanner(System.in);
            input = Integer.parseInt(c.nextLine());

            if (input == 1) {
                // One word

                current = grabWord();
                System.out.println(current);
                System.out.println();
            } else if (input == 2) {

                current = grabWord() + "-" + grabWord();

                System.out.println(current);
                System.out.println();
            } else if (input == 3) {
                saveList.add(current);
            }

            System.out.println();
            System.out.println();

        }

        // Save 
        exportSaveList();

        // End of program
    }

    public static String grabWord() {

        Random r = new Random();
        System.out.println(words.size());
        int num = Math.abs(r.nextInt()) % words.size();

        return words.get(num);
    }

    public static void importList(String[] defArgs) {
        List<String> args = new ArrayList<String>(Arrays.asList(defArgs));

        // Add the saved files to the list
        File saveFile = new File("src\\com\\uri\\" + SAVE_FILE_NAME);
        if (saveFile != null) {

            args.add(SAVE_FILE_NAME);
        }

        System.out.println("Opening files...");
        System.out.println();

        for (String s : args) {

            
            System.out.println("Reading: '" + s + "'");

            try {
                BufferedReader in = new BufferedReader(new FileReader("src\\com\\uri\\wordmasher\\" + s));

                // Checks for the save list
                List<String> currentList;
                if (!s.equals(SAVE_FILE_NAME)) {
                    currentList = words;
                } else {
                    currentList = saveList;
                }

                String current = "";
                int numLines = 0;
                while (in.ready()) {
                    numLines++;

                    // The current string being read - changed to lower case
                    current = in.readLine();
                    current = current.toLowerCase();

                    // If it's not a blank then add

                    // Add the element
                    if (!current.trim().equals("") && !currentList.contains(current))
                        currentList.add(current);

                }

                words.addAll(currentList);
                System.out.println("Read: " + numLines + " lines.");
                System.out.println();
                in.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException e) {
                System.out.println("There was an IO exception");
            }

        } // for loop
        
        

        System.out.println();
        System.out.println("Done!");
        System.out.println("Read: " + words.size() + " words.");
    }

    public static void exportSaveList() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("src\\com\\uri\\" + SAVE_FILE_NAME));

            for (String s : saveList) {

                out.println(s);
            }

            out.close();
        } catch (Exception e) {
        }

    }
}
