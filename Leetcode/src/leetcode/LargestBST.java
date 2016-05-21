package leetcode;

public class LargestBST {
	int maxNum;
	public int findLargest(Node root) {
		this.dfs(root);
		return this.maxNum;
	}
	private int dfs(Node root) {
		int count = -1;
		if(root == null)
			return 0;
		int l = this.dfs(root.left);
		int r = this.dfs(root.right);
		if(l>=0 && r>=0){
			if((root.left == null || root.left.val < root.val) &&
			(root.right == null || root.val < root.right.val))
				count = l + r +1;
		}
		this.maxNum = Math.max(this.maxNum,count);
		return count;
	}
	public static void main(String[] args) {
		
	}
}
