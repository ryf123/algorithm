package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This is a follow up of Shortest Word Distance. 
 * The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

	Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

	For example,
	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

	Given word1 = “coding”, word2 = “practice”, return 3.
	Given word1 = "makes", word2 = "coding", return 1.

	Note:
	You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class WordDistance {
	HashMap<String, ArrayList<Integer>> map =  new HashMap<String, ArrayList<Integer>>();
    public WordDistance(String[] words) {
        for(int i=0;i<words.length;i++){
        		if(!map.containsKey(words[i])){
        			map.put(words[i], new ArrayList<Integer>());
        		}
        		map.get(words[i]).add(i);
        }
    }
    public int shortest(String word1, String word2) {
    		if(word1.equals(word2))
    			return samewordshortest(map.get(word1));
    		return merge(map.get(word1), map.get(word2));
    }
    
    private int samewordshortest(ArrayList<Integer> w1) {
    		int shortest = Integer.MAX_VALUE;
    		int i = 1;
    		while(i<w1.size()){
    			shortest = Math.min(shortest, Math.abs(w1.get(i) - w1.get(i-1)));
    			i++;
    		}
		return shortest;
	}
	private int merge(ArrayList<Integer> w1,ArrayList<Integer> w2){
    		int i=0;
    		int j=0;
    		int shortest = Integer.MAX_VALUE;
    		while(i<w1.size() && j < w2.size()){
    			int diff = w1.get(i) - w2.get(j);
    			shortest = Math.min(shortest, Math.abs(diff));
    			if(diff<0){
    				i++;
    			}
    			else
    				j++;
    			
    		}
    		return shortest;
    }
    public static void main(String[] args) {
    		
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		WordDistance swd = new WordDistance(words);
		System.out.println(swd.shortest("makes", "coding"));
		System.out.println(swd.shortest("practice", "coding"));
		System.out.println(swd.shortest("makes", "makes"));
	}
}
//Your WordDistance object will be instantiated and called as such:
//WordDistance wordDistance = new WordDistance(words);
//wordDistance.shortest("word1", "word2");
//wordDistance.shortest("anotherWord1", "anotherWord2");