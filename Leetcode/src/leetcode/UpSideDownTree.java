package leetcode;



public class UpSideDownTree {
	public TreeNode upsideDownBinaryTree(TreeNode root){
		if(root.left == null)
			return root;
		TreeNode node = upsideDownBinaryTree(root.left);
		if(root.right != null)
			root.left.left = root.right;
		root.left.right = root;
		root.right = null;
		root.left = null;
		return node;
	}
	public static void main(String[] args) {
		SerializeTree st = new SerializeTree();
		UpSideDownTree usd = new UpSideDownTree();
		TreeNode node = usd.upsideDownBinaryTree(st.deserialize("1,2,X,X,X"));
		System.out.println(node.val);
//		System.out.println(node.right.left.val);
		System.out.println(st.serialize(usd.upsideDownBinaryTree(st.deserialize("1,2,X,X,X"))));
	}
}
