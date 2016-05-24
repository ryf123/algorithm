package leetcode;

import java.util.Arrays;

//Given two strings S and T, determine if they are both one edit distance apart
public class OneEditDistance {
	public boolean isOneEditDistance(String s,String t){
		int slength = s.length();
		int tlength = t.length();
		int diff = Math.abs(tlength-slength);
		if(diff >= 2)
			return false;
		int sp = 0;
		int tp = 0;
		while(sp < slength && tp < tlength)
		{
			if(s.charAt(sp) != t.charAt(tp)){
				if(diff == 0){
					sp++;
					tp++;
					diff--;
				}
				else if(diff == 1){
					if(slength < tlength){
						diff = -1;
						sp++;
					} // delete on character	
					else if(tlength < slength){
						diff = -1;
						tp++;
					}
				}
				else{
					return false;
				}
			}
			else{
				sp++;
				tp++;
			}
		}
		if(slength == tlength)
			return diff==-1;
		else if(slength > tlength)
			return (diff==-1 && sp == slength && tp == tlength) || (diff==1 && sp< slength);
		else
			return (diff==-1 && sp == slength && tp == tlength) || (diff==1 && tp< tlength);
		
	}
	public static void main(String[] args) {
		OneEditDistance oed = new OneEditDistance();
		System.out.println(oed.isOneEditDistance("a", "ba"));
	}
}
