package leetcode;

import java.util.ArrayList;
import java.util.*;

/*
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
 */
public class GroupShifted {
	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    public List<List<String>> groupStrings(String[] strings) {
        for(String s:strings){
        		StringBuilder sb = new StringBuilder();
        		for(int i=0;i<s.length();i++){
        			Integer diff = (s.charAt(i) - s.charAt(0));
        			if(diff < 0)
        				diff+=26;
        			sb.append(diff);
        			sb.append(",");
        		}
        		String key = new String(sb);
        		if(!map.containsKey(key)){
        			map.put(key, new ArrayList<String>());
        		}
        		map.get(key).add(s);
        	}
		List<List<String>> ret = new ArrayList<List<String>>();
		for(String key:map.keySet()){
			System.out.println(key);
			ret.add(map.get(key));
		}
		return ret;
    }
    public static void main(String[] args) {
		GroupShifted gs = new GroupShifted();
		String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
		for(List<String> s:gs.groupStrings(strings)){
			System.out.println(Arrays.toString(s.toArray(new String[s.size()])));
		}
	}
}
