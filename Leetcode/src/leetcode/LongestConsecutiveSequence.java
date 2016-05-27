package leetcode;

public class LongestConsecutiveSequence {
    int length = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root,null,0);
        return length;
    }
    private void dfs(TreeNode root,TreeNode prev,int path){
        if(root == null){
        		length = Math.max(length,path); 
        		return;
        	}
        if(prev != null && root.val == prev.val+1){
            path++;
            length = Math.max(length,path);  
        }
        else{
            path = 1;
        }
        dfs(root.left,root,path);
        dfs(root.right,root,path);
    }
    public static void main(String[] args) {
    		LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
		SerializeTree sTree  = new SerializeTree();
		TreeNode root = sTree.deserialize("2,X,X");
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(5);
		System.out.println(lcs.longestConsecutive(root));
	}
}
