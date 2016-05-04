import java.util.*;




public class regex {
	HashMap<Integer, ArrayList<Integer>> adj; // edge from the state
    public boolean isMatch(String s, String p) {
    		if(p == null || s == null)
    			return false;
    		// remove leading *
    		for (int i = 0; i < p.length(); i++) {
			if(p.charAt(i) == '*')
				continue;
			else {
				p = p.substring(i);
				break;
			}
		}
    		adj = new HashMap<Integer, ArrayList<Integer>>();
    		this.compile(p);
    		int M = p.length() ; //when it's here it matches
    		ArrayList<Integer> matches = new ArrayList<Integer>();
    		HashSet<Integer> zeronext = new HashSet<Integer>();
    		this.dfs(0,zeronext);
    		for(int nextState:zeronext)
    			matches.add(nextState);
    		for (int i = 0; i < s.length(); i++) {
    			char c = s.charAt(i);
    			HashSet<Integer> next = new HashSet<Integer>();
    			for(int match:matches){
    				if(match < M)
	    				if(c == p.charAt(match) || p.charAt(match) == '.'){
	    					this.dfs(match+1, next);
	    				}
    			}
    			matches.clear();
    			for(int nextState:next)
    				matches.add(nextState);
		}
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
//    			adj.get(i).add(i+1);
    			if(p.charAt(i) == '*' && i>0){
    				adj.get(i-1).add(i+1);
    				adj.get(i).add(i-1);
    			}
		}
    }
    public static void main(String args[]) {
    		regex reg = new regex();
    		assert reg.isMatch("aa","aa");
    		
    		assert(reg.isMatch("aa","aa"));
    		
    		assert(reg.isMatch("aaa", ".*"));
    		
    		assert(reg.isMatch("aa", "a*"));
    		
    		assert(reg.isMatch("ab", ".*"));
    		
    		assert(reg.isMatch("bbbba", ".*a*a"));
    		
    		assert(reg.isMatch("aa", "c*a*"));
    		assert(reg.isMatch("", "c*a*"));
    		
	}
}
