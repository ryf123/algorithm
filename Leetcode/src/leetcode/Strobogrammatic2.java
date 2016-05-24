package leetcode;
/*
 * 
 *  A strobogrammatic number is a number that looks the same when rotated 180 degrees 
 *  (looked at upside down).
 *	Find all strobogrammatic numbers that are of length = n.
 *	For example, Given n = 2, return ["11","69","88","96"].
 */
import java.util.*;
public class Strobogrammatic2 {
	String[] map = {"11","88","69","96","00"};
	String[] single = {"1","0","8"};
	int total = 0;
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
		Strobogrammatic2 s = new Strobogrammatic2();
		for(String ss:s.findStrobogrammatic(2)){
			System.out.println(ss);
		}
	}
}
