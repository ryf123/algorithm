package leetcode;

public class FindCelebrity {
    public int findCelebrity(int n) {
		int c = 0;
	    for(int i=1; i < n; i++ ){
	    		if(this.knows(c, i))
	    			c = i;
	    }
	    return verify(c, n)?c:-1;
    }
	private boolean verify(int c, int n){
		for(int i=0; i<n; i++){
			if(i!=c && knows(c, i))
				return false;
		}
		for(int i=0; i<n; i++){
			if(i!=c && !knows(i, c))
				return false;
		}
		return true;
	}
    private boolean knows(int a,int b){
    		return true;
    }
}
