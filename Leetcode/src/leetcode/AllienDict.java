package leetcode;


import java.util.*;

/*
 * There is a new alien language which uses the latin alphabet. However, 
 * the order among letters are unknown to you. 
 * You receive a list of words from the dictionary, 
 * wherewords are sorted lexicographically by the rules of this new language. 
 * Derive the order of letters in this language.

	For example,
	Given the following words in dictionary,
	
	[
	  "wrt",
	  "wrf",
	  "er",
	  "ett",
	  "rftt"
	]
	The correct order is: "wertf".
	
	Note:
	
	You may assume all letters are in lowercase.
	If the order is invalid, return an empty string.
	There may be multiple valid order of letters, return any one of them is fine.
 */
public class AllienDict {
	StringBuilder sb = new StringBuilder();
	HashSet<Character> set = new HashSet<Character>();
	HashMap<Character, HashSet<Character>>  map;
	public String alienOrder(String[] words){
		map = new HashMap<Character, HashSet<Character>>();	
		for(int i=0; i < words.length;i++){
			for(int j=0;j<words[i].length();j++){
				if(!map.containsKey(words[i].charAt(j)))
					map.put(words[i].charAt(j), new HashSet<Character>());
			}
			if(i>0){
				String word1 = words[i-1];
				String word2 = words[i];
				for(int j=0;j < Math.min(word1.length(),word2.length());j++){
					
					Character c = word1.charAt(j);

					if(word1.charAt(j) != word2.charAt(j))
						map.get(c).add(word2.charAt(j));					
					
				}				
			}
//			System.out.println(map);
		}

		HashSet<Character> visited = new HashSet<Character>();
		for(Character c:map.keySet()){
			
			if(!this.dfs(visited, c))
				return "";
		}
		return sb.reverse().toString();
	}
	private boolean dfs(HashSet<Character> visited,Character c){
		
		if(set.contains(c))
			return true;
		if(visited.contains(c))
			return false;
		visited.add(c);
		if(map.containsKey(c)){
			for(Character child:map.get(c)){
				if(!this.dfs(visited, child))
					return false;
			}			
		}
		visited.remove(c);
		sb.append(c);
		set.add(c);
		return true;
	}
	public static void main(String[] args) {
		String[] dict = {"wrt","wrf","er","ett","rftt"};
		AllienDict ad = new AllienDict();
		System.out.println(ad.alienOrder(dict));
		AllienDict ad1 = new AllienDict();
		String[] dict1 = {"ab","adc"};
		System.out.println(ad1.alienOrder(dict1));
	}
}
