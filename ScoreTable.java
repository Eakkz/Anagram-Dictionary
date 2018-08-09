// Name: Ekachai Suriyakreingkri
// USC NetID: suriyakr@usc.edu
// CS 455 PA4
// Spring 2018

import java.util.HashMap;
import java.util.Map;

public class ScoreTable {

    private static Map<Character, Integer> score;

    /** Create score for each English alphabet **/
    // Using hashmap because we do not care about the order of alphabet in the Map
    public ScoreTable() {

        this.score = new HashMap<Character, Integer>();
        this.score.put('a', 1);
        this.score.put('b', 3);
        this.score.put('c', 3);
        this.score.put('d', 2);
        this.score.put('e', 1);
        this.score.put('f', 4);
        this.score.put('g', 2);
        this.score.put('h', 4);
        this.score.put('i', 1);
        this.score.put('j', 8);
        this.score.put('k', 5);
        this.score.put('l', 1);
        this.score.put('m', 3);
        this.score.put('n', 1);
        this.score.put('o', 1);
        this.score.put('p', 3);
        this.score.put('q', 10);
        this.score.put('r', 1);
        this.score.put('s', 1);
        this.score.put('t', 1);
        this.score.put('u', 1);
        this.score.put('v', 4);
        this.score.put('w', 4);
        this.score.put('x', 8);
        this.score.put('y', 4);
        this.score.put('z', 10);
    }

    /* Method for calculating the score of each word */
    public int calculateScore (String word) {
        int wordScore = 0;
        /* Iterating thorugh every character in each word
           and get sum the score of each word then return back
        */
        for (int i = 0; i < word.length(); i++) 
        {
            wordScore = wordScore + this.score.get(word.charAt(i));
        }
        return wordScore;
    }

}
