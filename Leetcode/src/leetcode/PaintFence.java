package leetcode;
/*
 * There is a fence with n posts, 
 * each post can be painted with one of the k colors.

	You have to paint all the posts 
	such that no more than two adjacent fence posts have the same color.

	Return the total number of ways you can paint the fence.

	Note: n and k are non-negative integers.
 */
public class PaintFence {
	public int numWays(int n, int k) {
		int first = k;
		int second  = k*k;
		if(n == 0) return 0;
		else if(n == 1) return first; 
		else if(n == 2) return second;
		else{
			int i=2;
			while(i<n){
				int temp = (k-1)*(first+second);
				first = second;
				second = temp;
				i++;
			}
			return second;
		}
	}
	public static void main(String[] args) {
		PaintFence paintFence = new PaintFence();
		System.out.println(paintFence.numWays(3, 2));
	}
}
