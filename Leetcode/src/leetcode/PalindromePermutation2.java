package leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

	For example:

	Given s = "aabb", return ["abba", "baab"].

	Given s = "abc", return [].
 */
public class PalindromePermutation2 {
	Character odd;
	int length = 0;
	public List<String> generatePalindromes(String s){
		this.length = s.length();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c:s.toCharArray()){
			if(!map.containsKey(c))
				map.put(c, 0);
			map.put(c,map.get(c)+1);
		}
		ArrayList<Character> evens = new ArrayList<Character>();
		for(Character c:map.keySet()){
			int count = map.get(c);
			if( count % 2 != 0){
				while(count >1){
					count/=2;
					evens.add(c);
				}
				odd = c;
			}
				
			else{
				while(count >1){
					count/=2;
					evens.add(c);
				}
			}
		}
		Collections.sort(evens);
		return this.dfs(evens);
	}
	private List<String> dfs(ArrayList<Character> evens){
		List<String> ret = new ArrayList<String>();
		if(evens.size() == 0 ){
			if(this.length %2 != 0)
				ret.add(Character.toString(this.odd));
			else
				ret.add("");
		}
			
		for(int i=0;i<evens.size();i++){
			Character even = evens.get(i);
			if(i-1>=0 && evens.get(i-1) == evens.get(i))
				continue;
			evens.remove(i);
			for(String sub:dfs(evens)){
				StringBuilder bs = new StringBuilder(sub);
				bs.insert(0, even);
				bs.insert(bs.length(), even);
				ret.add(bs.toString());				
			}
			evens.add(i, even);
		}
		return ret;
	}
	public static void main(String[] args) {
		PalindromePermutation2 pp2 = new PalindromePermutation2();
		System.out.println(pp2.generatePalindromes("aabbc"));
	}
}
