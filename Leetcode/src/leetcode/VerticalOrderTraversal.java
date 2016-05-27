package leetcode;

import java.util.*;

import sun.misc.Queue;

public class VerticalOrderTraversal {
	HashMap<Integer, ArrayList<Integer>> map =new HashMap<Integer, ArrayList<Integer>>();
	int min = Integer.MIN_VALUE;
    public List<List<Integer>> verticalOrder(TreeNode root) {
    		List<List<Integer>> ret = new ArrayList<List<Integer>>();
    		dfs(root, 0);
    		ArrayDeque<Pair<TreeNode, Integer>> queue = new ArrayDeque<Pair<TreeNode, Integer>>();
    		if(root == null) return ret;
    		Pair<TreeNode, Integer> pair = new Pair<TreeNode, Integer>(root, 0);
    		queue.offer(pair);
    		while(!queue.isEmpty()){
    			pair = queue.poll();
    			if(pair.)
    			int[] right = {}
    		}
    		while(map.containsKey(min)){
    			ret.add(map.get(min));
    			min++;
    		}
    		return ret;
    }
    private void dfs(TreeNode root,int column){
    		if(root == null) return;
    		min = Math.min(min, column);
    		if(!map.containsKey(column))
    			map.put(column, new ArrayList<Integer>());
    		map.get(column).add(root.val);
    		this.dfs(root.left, column-1);
    		this.dfs(root.right, column+1);
    		
    }
    public static void main(String[] args) {
		
	}
}
