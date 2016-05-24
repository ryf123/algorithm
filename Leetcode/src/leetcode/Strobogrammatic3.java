package leetcode;
/*
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down). 

	Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high. 

	For example, 
	Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers. 

	Note: 
	Because the range might be a large number, the low and high numbers are represented as string. 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Strobogrammatic3 {
	String[] map = {"11","88","69","96","00"};
	String[] single = {"1","0","8"};
	int total = 0;
	public int strobogrammaticInRange(String start,String end){
		ArrayList<String> ret = new ArrayList<String>();
		int counter = 0;
		int i = start.length();
		int j = end.length();
		for(int x=i;x<=j;x++){
			for(String s:this.findStrobogrammatic(x)){
				if(compareString(start, s) && compareString(s, end)){
					ret.add(s);
					counter++;
				}
					
			}
		}
//		System.out.println(Arrays.toString(ret.toArray(new String[ret.size()])));
		return counter;
	}
	private boolean compareString(String s,String t){
		if(s.length() != t.length()){
			return t.length() > s.length();
		}
		for(int i=0; i<t.length(); i++){
			if(s.charAt(i) != t.charAt(i))
				return Character.getNumericValue(t.charAt(i)) > Character.getNumericValue(s.charAt(i));
		}
		return true;
	}
	public List<String> findStrobogrammatic(int n){
		this.total = n;
		return this.wrapper(n);
	}
	private List<String> wrapper(int n){
		ArrayList<String> ret = new ArrayList<String>();
		if(n == 2){
			for(String ss:map){
				if(n == this.total && ss.equals("00"))
					continue;
				ret.add(ss);
			}
			return ret;
		}
		if(n==1)
			return Arrays.asList(single);
		List<String> subs = this.wrapper(n-2);
		for(String s:map){
			if(n == this.total && s.equals("00"))
				continue;
			for(String sub:subs)
				ret.add(new String(s.charAt(0) + sub + s.charAt(1)));
		}
		return ret;
	}
	public static void main(String[] args) {
		Strobogrammatic3 s = new Strobogrammatic3();
		System.out.println(s.strobogrammaticInRange("0", "100"));
	}
}
