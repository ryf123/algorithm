package leetcode;

import java.util.Arrays;
import java.util.HashSet;

import com.sun.org.apache.bcel.internal.generic.SWAP;

public class ReverseVowel {
	public String reverseVowels(String s) {
		if(s.length() == 0 || s == null)
			return "";
		char[] arr = s.toCharArray();
		int begin = 0;
		int end = s.length() - 1;
		HashSet<Character> vowels = new HashSet<Character>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		
		while(begin < end){
			while(begin <= end && !vowels.contains(Character.toLowerCase(arr[begin])) )
				begin++;
			while(end>=0 && !vowels.contains(Character.toLowerCase(arr[end])))
				end--;
			if(begin < end && vowels.contains(Character.toLowerCase(arr[begin])) && vowels.contains(Character.toLowerCase(arr[end]))){
				swap(arr, begin++, end--);
			}	
		}
		return new String(arr);
	}
	private void swap(char[] arr,int i,int j){
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static void main(String[] args) {
		String s = "aA";
		ReverseVowel rv = new ReverseVowel();
		System.out.print(rv.reverseVowels(s));
	}
}
