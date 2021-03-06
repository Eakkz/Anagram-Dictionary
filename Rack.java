// Name: Ekachai Suriyakreingkri
// USC NetID: suriyakr@usc.edu
// CS 455 PA4
// Spring 2018

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {

    /**
     * Wrapper Method
     */
    public static ArrayList<String> allSubsets(String input) {
        String unique = "";
        Map<Character, Integer> uniqueMap = new TreeMap<Character, Integer>();
        int[] mult;
        int indexMult = 0;
    
        for (int i = 0; i < input.length(); i++) {
            char charInput = input.charAt(i);
            if (uniqueMap.containsKey(charInput))
                uniqueMap.put(charInput, uniqueMap.get(charInput) + 1);
            else
                uniqueMap.put(charInput, 1);
        }
        
        mult = new int[uniqueMap.size()];
        
        // Concatenating all string in map to generate sorted unique word
        for (Map.Entry<Character, Integer> mapEntry : uniqueMap.entrySet()) {
            unique = unique + mapEntry.getKey();
            mult[indexMult] = mapEntry.getValue();
            indexMult = indexMult + 1;
        }
        return allSubsets(unique, mult, 0);
    }

    /**
     * Finds all subsets of the multiset starting at position k in unique and mult.
     * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
     *      unique.charAt(i).
     * PRE: mult.length must be at least as big as unique.length()
     *      0 <= k <= unique.length()
     * @param unique a string of unique letters
     * @param mult the multiplicity of each letter from unique.  
     * @param k the smallest index of unique and mult to consider.
     * @return all subsets of the indicated multiset
     * @author Claire Bono
     */
    private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
        ArrayList<String> allCombos = new ArrayList<>();

        if (k == unique.length()) {  // multiset is empty
            allCombos.add("");
            return allCombos;
        }

        // get all subsets of the multiset without the first unique char
        ArrayList<String> restCombos = allSubsets(unique, mult, k+1);

        // prepend all possible numbers of the first char (i.e., the one at position k) 
        // to the front of each string in restCombos.  Suppose that char is 'a'...

        String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
        for (int n = 0; n <= mult[k]; n++) {   
            for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                // we found in the recursive call
                // create and add a new string with n 'a's in front of that subset
                allCombos.add(firstPart + restCombos.get(i));  
            }
            firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
        }

        return allCombos;
    }
    
    
    public static void printAndCalcuateScrabble(ArrayList<String> scrabble) {
        // Map for storing all scrabble word sorting by score from highest to lowest
        // Collections.reverseOrder() storing key value in reverse order from normal tree map
        Map<Integer, ArrayList<String>> scrabbleMap = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
        ArrayList<String> temp = new ArrayList<String>();
        ScoreTable scoreTable = new ScoreTable();
        
        for (int i = 0; i < scrabble.size(); i++)
        {
            int score = scoreTable.calculateScore(scrabble.get(i));
            if (scrabbleMap.containsKey(score))
                temp = scrabbleMap.get(score);
            else
                temp = new ArrayList<String>();
            
            temp.add(scrabble.get(i));
            scrabbleMap.put(score, temp);
        }
        
        for (Map.Entry<Integer, ArrayList<String>> entry : scrabbleMap.entrySet()) {
            ArrayList<String> str = entry.getValue();
            Collections.sort(str);
            for (int i = 0; i < str.size(); i++) {
                System.out.println(entry.getKey() + ": " + str.get(i));
            }
        }
    }

}
