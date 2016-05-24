package leetcode;


/*
 * Given a list of words and two words word1 and word2, 
 * return the shortest distance between these two words in the list.

For example, Assume that words = 
["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, 
return 3. Given word1 = "makes", word2 = "coding", return 1.
Note: You may assume that word1 does not equal to word2, 
and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {
	public int shortestDistance(String[] words,String word1,String word2){
		int p1= -1;
		int p2 = -1;
		int shortest = Integer.MAX_VALUE;
		int i=0;
		while(i < words.length){
			if(words[i].equals(word1))
				p1 = i;
			if(p1 != -1 && p2 != -1 && p1 != p2)
				shortest = Math.min(Math.abs(p1-p2),shortest);
			if(words[i].equals(word2))
				p2 = i;
			if(p1 != -1 && p2 != -1 && p1 != p2)
				shortest = Math.min(Math.abs(p1-p2),shortest);
			i++;
		}
		return shortest;	
	}
	public static void main(String[] args) {
		ShortestWordDistance swd = new ShortestWordDistance();
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		System.out.println(swd.shortestDistance(words, "makes", "makes"));
	}
}
