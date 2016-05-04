package leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WildCard {
	HashMap<Integer, ArrayList<Integer>> adj; // edge from the state
    public boolean isMatch(String s, String p) {
    		if(p == null || s == null)
    			return false;
    		// remove leading *
    		for (int i = p.length()-1; i >= 0 ; i--) {
			if(i==0 || p.charAt(i) != '*') {
				p = p.substring(0, Math.min(i+1, p.length()-1));
				break;
			}
		}
    		System.out.println(p);
    		adj = new HashMap<Integer, ArrayList<Integer>>();
    		this.compile(p);
    		int M = p.length() ; //when it's here it matches
    		ArrayList<Integer> matches = new ArrayList<Integer>();
    		matches.add(0);
    		for (int i = 0; i < s.length(); i++) {
    			char c = s.charAt(i);
    			HashSet<Integer> next = new HashSet<Integer>();
    			for(int match:matches){
    				if(match < M)
	    				if(c == p.charAt(match) || p.charAt(match) == '?' || p.charAt(match) == '*'){
	    					this.dfs(match, next);
	    				}
    			}
    			matches.clear();
    			for(int nextState:next)
    				matches.add(nextState);
    			if(matches.size() == 0)
    				return false;
		}
//    		System.out.print(matches);
		for(int nextState:matches)
			if(nextState == M )
				return true;
		
    		return false;
    }

    // run dfs on these points
    private void dfs(int match,HashSet<Integer> next){
    		if(next.contains(match))
    			return;
    		next.add(match);
    		if(adj.containsKey(match)){
    			for(int neighbor:adj.get(match)){
    				this.dfs(neighbor, next);
    			}	
		}
    }
    // first build the pattern
    private void compile(String p){

    		adj.put(0, new ArrayList<Integer>());
    		
    		// if the asterisk is at the 1st 
    		for (int i = 0; i < p.length(); i++) {
    			adj.put(i, new ArrayList<Integer>());
    			adj.get(i).add(i+1);
    			if(p.charAt(i) == '*' && i>0){
    				adj.get(i).add(i);
    			}
		}
    }
    public static void main(String args[]) {
    		WildCard wc = new WildCard();
//		assert wc.isMatch("aa","aa");
//		
//		assert(wc.isMatch("aa","?a"));
//		
//		assert(wc.isMatch("aaa", "*"));
//		
//		assert(wc.isMatch("aa", "a*"));
//		
//		assert(wc.isMatch("ab", "*"));
//		
//		assert(!wc.isMatch("aab", "c*a*"));
//		assert(!wc.isMatch("", "c*a*"));
		assert(wc.isMatch("", "****"));
		
    }
}
