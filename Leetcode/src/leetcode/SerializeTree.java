package leetcode;

import java.util.*;

public class SerializeTree {
	    private static final String spliter = ",";
	    private static final String NN = "X";

	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
	        StringBuilder sb = new StringBuilder();
	        buildString(root, sb);
	        return sb.toString();
	    }

	    private void buildString(TreeNode node, StringBuilder sb) {
	        if (node == null) {
	            sb.append(NN).append(spliter);
	        } else {
	            sb.append(node.val).append(spliter);
	            buildString(node.left, sb);
	            buildString(node.right,sb);
	        }
	    }
	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
	        Deque<String> nodes = new ArrayDeque<String>();
	        nodes.addAll(Arrays.asList(data.split(spliter)));
	        return buildTree(nodes);
	    }

	    private TreeNode buildTree(Deque<String> nodes) {
	        String val = nodes.pop();
	        if (val.equals(NN)) return null;
	        else {
	            TreeNode node = new TreeNode(Integer.valueOf(val));
	            node.left = buildTree(nodes);
	            node.right = buildTree(nodes);
	            return node;
	        }
	    }
	    public static void main(String[] args) {
	    		SerializeTree sTree  = new SerializeTree();
	    		sTree.deserialize("1,X,X");
		}
	
}
