
public class BreakInteger {
	// for each number i from 1 to n
	// we break at position 1 to (i-1)/2
    public int integerBreak(int n) {
     int[] dp  = new int[n+1];
     dp[1] = 1;
     for(int i=2; i < n+1 ; i++){
    	 for(int j=1; j<= i/2; j++){ // break point
    		 dp[i] = Math.max(dp[i], Math.max(dp[j], j) * Math.max(dp[i-j], i-j));
    	 }
     }
     return dp[n];
    }
    public static void main(String args[]){
    	BreakInteger bi = new BreakInteger();
    	System.out.println(bi.integerBreak(20));
    }
}
