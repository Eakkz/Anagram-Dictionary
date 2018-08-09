// Name: Ekachai Suriyakreingkri
// USC NetID: suriyakr@usc.edu
// CS 455 PA4
// Spring 2018

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

    private Map<Character, Integer> wordMap;
    private Map<Map<Character, Integer>, ArrayList<String>> dictionaryMap;

    /**
     * Create an anagram dictionary from the list of words given in the file
     * indicated by fileName.  
     * Read the file name throw if file not found
     * Use Hashmap for storing the word that has same unique characters and same amount of the unique characters
     * The dictionary hashmap uses treemap as a key and arraylist of string as a value
     * PRE: The strings in the file are unique.
     * @param fileName  the name of the file to read from
     * @throws IOException 
     */
    public AnagramDictionary(String fileName) throws IOException {
        
        // Local variable declaration
        String line = "";
        Scanner in = new Scanner(new FileReader(fileName));
        ArrayList<String> anagramList;
        this.dictionaryMap = new HashMap<Map<Character, Integer>, ArrayList<String>>();     
        // Read the word until the end of file
        //while ((line = in.readLine()) != null)
        while (in.hasNext())
        {
            line = in.next();
            // Create tree map for storing each unique characters with number of that characters in that word
            this.wordMap = new TreeMap<Character, Integer>();
            
            // Loop each characters in word
            for (int i = 0; i < line.length(); i++)
            {
                // If the character already in the map, updating the value by 1
                if (this.wordMap.containsKey(line.charAt(i)))
                    this.wordMap.put(line.charAt(i), this.wordMap.get(line.charAt(i))+1); 
                // Creating new key for the characters
                else
                    this.wordMap.put(line.charAt(i), 1);
            }
            
            // Check dictionary that this unique characters word set contain in dictionary
            // If already in dictionary (same amount of keys and each key has same amount of value), add this word into array of that key
            if (this.dictionaryMap.containsKey(this.wordMap))
            {
                anagramList = this.dictionaryMap.get(this.wordMap);
                anagramList.add(line);
                this.dictionaryMap.put(this.wordMap, anagramList);
            }
            //If not contain, create new key with the word and new array as a value
            else
            {
                anagramList = new ArrayList<String>();
                anagramList.add(line);
                this.dictionaryMap.put(this.wordMap, anagramList);
            }
        }
    }
    
    /**
     * Get all anagrams of the given string. This method is case-sensitive.
     * Separating string by storing in tree map with characters as a key and number of the characters in that word as a value
     * Use the tree map to check with dictionary hash map
     * If the tree map contains in dictionary, return the value (arraylist of words)
     * If not, return empty array
     * E.g. "CARE" and "race" would not be recognized as anagrams.
     * @param s string to process
     * @return a list of the anagrams of s
     * 
     */
    public ArrayList<String> getAnagramsOf(String s) {
        Map<Character, Integer> inputMap = new TreeMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++)
        {
            if (inputMap.containsKey(s.charAt(i)))
                inputMap.put(s.charAt(i), inputMap.get(s.charAt(i))+1);
            else
                inputMap.put(s.charAt(i), 1);
        }
        if (!this.dictionaryMap.containsKey(inputMap))
            return new ArrayList<String>();
        
        return this.dictionaryMap.get(inputMap);
    }
}
