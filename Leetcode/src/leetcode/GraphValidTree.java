package leetcode;
/*
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
 * (each edge is a pair of nodes),
 *  write a function to check whether these edges make up a valid tree.

	For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? 
Is this case a valid tree?
Show More Hint Note: you can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphValidTree {
	HashSet<Integer> set = new HashSet<Integer>();
	HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	public boolean validTree(int n, int[][] edges){
		HashSet<Integer> visited = new HashSet<Integer>();
		for(int[] edge:edges){
			if(!map.containsKey(edge[0]))
				map.put(edge[0], new ArrayList<Integer>());
			map.get(edge[0]).add(edge[1]);
			if(!map.containsKey(edge[1]))
				map.put(edge[1], new ArrayList<Integer>());
			map.get(edge[1]).add(edge[0]);			
		}
		if(edges.length == 0) return n==1;
		if(dfs(edges[0][0],null,visited))
			return set.size() == n;
		else {
			return false;
		}
	}
	private boolean dfs(Integer key,Integer parent,HashSet<Integer> visited){
	    if(set.contains(key)) return true;
		if(visited.contains(key))
			return false;
		visited.add(key);
		for(Integer child:map.get(key)){
		    if(child.equals(parent)) continue;
			if(!this.dfs(child, key, visited))
			    return false;
		}
		visited.remove(key);
		set.add(key);
		return true;
	}
}
