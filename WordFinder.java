// Name: Ekachai Suriyakreingkri
// USC NetID: suriyakr@usc.edu
// CS 455 PA4
// Spring 2018

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordFinder {

    public static void main(String[] args) {
        String fileName = "";
        // Default file name is sowpods, if user input filename is valid change file name to the one that user input.
        try 
        {
            // Default dictionary file
            if (args.length < 1)
                fileName = "sowpods.txt";
            // User;s input dictionary file
            else
                fileName = args[0];
            readRack(fileName);
        } 
        // Throw exception if file is not found
        catch (IOException e)
        {
            System.out.println("ERROR: File not found: " + fileName);
        }
    }
    
    /** Method for prompt interface for user to input the word
     *  Loop until user input "."
     *  Print out the number of output and all solution with score
     *  The output is sorted by score and alphabetical order
     * @param fileName
     * @throws IOException
     */
    private static void readRack(String fileName) throws IOException {
        // Create dictionary for creating anagram
        AnagramDictionary ad = new AnagramDictionary (fileName);
        Rack rack = new Rack();
        // ArrayList for storing possible subset of angram
        ArrayList<String> subsetAnagram = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        String input = "";
        boolean exit = false;
        // Initial program output message
        System.out.println("Type . to quit.");
        //Loop until user type "." (exit equal TRUE)
        while (!exit) 
        {
            System.out.print("Rack? ");
            input = in.nextLine();
            // Removing non alphabet charaters
            String checkedInput = input.replaceAll("[^a-zA-Z]", "");
            // User want to exit and end the program
            if (input.equals("."))
                exit = true;
            else
            {
                // Create all subset from user's input
                ArrayList<String> subsetInput = rack.allSubsets(checkedInput);
                // Output array that shown all of combination from input word that can find in dictionary
                ArrayList<String> scrabble = new ArrayList<String>(); 
                // Iterating through the every subset to get anagram
                for (int i = 0; i < subsetInput.size(); i++)
                {
                    // Get array anagrams from each subset
                    subsetAnagram = ad.getAnagramsOf(subsetInput.get(i));
                    for (int j = 0; j < subsetAnagram.size(); j++)
                    {
                        // Storing every anagrams in arraylist
                        scrabble.add(subsetAnagram.get(j));
                    }
                }

                System.out.println("We can make " + scrabble.size() + " words from " + "\"" + input + "\"");
                if (scrabble.size() != 0) {
                    System.out.println("All of the words with their scores (sorted by score): ");
                }
                // Calculate the score of each anagram and print by decreasing order of score and sort by word for words that have same score
                rack.printAndCalcuateScrabble(scrabble);
            }
        }

    }
}
