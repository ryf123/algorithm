package leetcode;

import java.util.*;

public class ValidWordAbbr {
	HashMap<String,String> set = new HashMap<String,String>();
    public ValidWordAbbr(String[] dictionary) {
        for(String s:dictionary){
        		String convert = convert(s);
        		if(set.containsKey(convert) && !set.get(convert).equals(s))
        			set.put(convert,"");
        		else
        			set.put(convert,s);
        }
    }
    private String convert(String s){
    		if(s.length() == 0) return "";
    		char[] arr = {s.charAt(0),(char)('0'+Math.max(0, s.length()-2)),s.charAt(s.length()-1)};
    		return new String(arr);
    }
    public boolean isUnique(String word) {
    		String convert = this.convert(word);
        if( !set.containsKey(convert) || set.get(convert).equals(word))
        		return true;
        return false;
    }
    public static void main(String[] args) {
    		String[] dictionary = {"deer", "door", "cake", "card" };
		ValidWordAbbr vw = new ValidWordAbbr(dictionary);
		System.out.println(vw.set);
		System.out.println(vw.isUnique("dear"));
		System.out.println(vw.isUnique("cart"));
		System.out.println(vw.isUnique("cane"));
		System.out.println(vw.isUnique("make"));
		System.out.println(vw.isUnique("door"));
	}
}
