
public class KMP {
	    public int strStr(String haystack, String needle) {
	    	
	    	
	    	int n = needle.length();
	    	int h = haystack.length();
	    	if (n == 0 && h == 0){
	    		return 0;
	    	}
	    	else if (n == 0 || h == 0){
	    		return -1;
	    	}
	    	int state = 0;
	    	int[][] table = this.preprocess(needle);
	    	for (int i=0; i < haystack.length(); i++){
	    		char c = haystack.charAt(i);
	    		state = table[c][state];
	    		if (state == needle.length())
	    			return i - n + 1;
	    	}
	        return -1;
	    }
	    private int[][] preprocess(String needle){
	    	int R = 256;
	    	int l = needle.length();
	    	int[][] table = new int[R][l];
	    	int X = 0;
	    	table[needle.charAt(0)][0] = 1;
	    	for(int i = 1; i < l  ;i++){
	    		for (int c = 0; c < R; c++)
	    			table[c][i] = table[c][X];
	    		char c = needle.charAt(i);
	    		table[c][i] = i + 1;
	    		X = table[c][X];
	    	}
	    	return table;
	    }
    public static void main(String args[]){
    	KMP kmp = new KMP();
    	String haystack = "";
    	String needle = "";
    	System.out.printf("%d", kmp.strStr(haystack, needle));
    }
}
