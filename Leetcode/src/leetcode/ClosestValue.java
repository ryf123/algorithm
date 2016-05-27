package leetcode;

public class ClosestValue {
	double closest=Double.MAX_VALUE;
	TreeNode closeNode = null;
    public int closestValue(TreeNode root, double target) {
    	traverse(root,target);
        return closeNode.val;
    }
    public void traverse(TreeNode root,double target){
    		if(root == null) return;
    		if(closest > Math.abs(root.val-target)){
    			closest = Math.abs(root.val-target);
    			closeNode = root;
    		}
    		if(target < root.val){
    			traverse(root.left, target);
    		}
    		else if(target == root.val){
    			return;
    		}
    		else{
    			traverse(root.right, target);
    		}
    }
    public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(100);
		root.right.left = new TreeNode(4);
		ClosestValue cv = new ClosestValue();
		System.out.println(cv.closestValue(root,4.132));
	}
}
