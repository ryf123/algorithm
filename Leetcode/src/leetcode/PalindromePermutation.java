package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/*
 * Given a string, determine if a permutation of the string could form a palindrome.

	For example, "code" -> False, "aab" -> True, "carerac" -> True.
 */
public class PalindromePermutation {
	public boolean canPermutePalindrome(String s){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c:s.toCharArray()){
			if(!map.containsKey(c))
				map.put(c, 0);
			map.put(c,map.get(c)+1);
		}
		boolean even = s.length() %2 == 0;
		boolean odd = true;
		for(Character c:map.keySet()){
			if(map.get(c)%2 != 0){
				if(even)
					return false;
				else{
					if(odd)
						odd = false;
					else
						return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		PalindromePermutation pp = new PalindromePermutation();
		System.out.println(pp.canPermutePalindrome(""));
	}
}
