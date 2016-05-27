package leetcode;

import java.util.Arrays;

public class Matrix {
    public int[][] multiply(int[][] A, int[][] B) {
        int rowA = A.length;
        int rowB = B.length;
        if(rowA == 0 || rowB == 0) return new int[0][0];
        int colA = A[0].length;
        int colB = B[0].length;
        int[][] ret = new int[rowA][colB];
        for(int i = 0; i < rowA; i++)
        		for(int j=0; j<colB; j++){
        			for(int k=0; k < colA;k++)
        				ret[i][j] += A[i][k] * B[k][j];
        		}
        return ret;
    }
    public static void main(String[] args) {
    		int[][] A = {{1,2},{3,4}};
    		Matrix matrix = new Matrix();
    		System.out.println(Arrays.deepToString(matrix.multiply(A, A)));
	}
}
