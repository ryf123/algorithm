package leetcode;

public class InorderSuccessor {
	TreeNode prev = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;
        TreeNode left = null;
        TreeNode right = null;
        if(p.val < root.val){
        		left = inorderSuccessor(root.left, p);
        }
        if(left != null) return left;
        if(prev == p){
        		return root;
        }
        prev = root;
        if(p.val >= root.val){
        		right = inorderSuccessor(root.right, p);
        }
        return right;
    }
    public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(1);
		root.right = new TreeNode(5);
		root.right.left = new TreeNode(4);
		InorderSuccessor is = new InorderSuccessor();
		System.out.println(is.inorderSuccessor(root, root.right.left).val);
	}
}
