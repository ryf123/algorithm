package leetcode;

public class DistinctSubsequence {
    public int numDistinct(String S, String T) {
        int[][] result = new int[T.length()+1][S.length()+1];
        for(int i=0; i<S.length()+1; i++){
          result[0][i]=1;
        }
        for(int i=1; i<T.length()+1; i++){
          for(int y=i; y<S.length()+1; y++){
            if(T.charAt(i-1)==S.charAt(y-1)){
              result[i][y] = result[i][y-1]+result[i-1][y-1];
            }
            else{
              result[i][y] = result[i][y-1];
            }
          }
        }
        return result[T.length()][S.length()]; 
    }
}
