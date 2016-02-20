import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
public class Solver {
	private boolean isSolvable;
	private int moves;
	private ArrayList<Board> solution;
	private class SearchNode{
		private Board board;
		private SearchNode prev;
		private int move;
		private int priority;
		SearchNode(Board b,SearchNode prev,int move,int priority){
			this.board = b;
			this.prev = prev;
			this.move = move;
			this.priority = priority;
		}
	};
    public Solver(Board initial)           // find a target to the initial board (using the A* algorithm)
    {
    	if(initial == null){
    		throw new java.lang.NullPointerException();
    	}
    	this.solution = new ArrayList<Board>();
    	Comparator<SearchNode> cmp = new Comparator<SearchNode>(){
			@Override
			public int compare(SearchNode s1, SearchNode s2) {
				if(s1.priority < s2.priority)
					return -1;
				else if(s1.priority > s2.priority)
					return 1;
				return 0;
			}
    		
    	};
    	MinPQ<SearchNode> initial_pq = new MinPQ<SearchNode>(cmp);
    	MinPQ<SearchNode> twin_pq = new MinPQ<SearchNode>(cmp);
    	Board twin = initial.twin();
    	initial_pq.insert(new SearchNode(initial,null,0,initial.manhattan()));
    	twin_pq.insert(new SearchNode(twin,null,0,twin.manhattan()));
    	SearchNode target = null;
    	int count = 0;
    	MinPQ<SearchNode> pq;
    	HashMap<String, String> table = new HashMap<String, String>();
    	if (initial.isGoal()){
    		target = initial_pq.delMin();
    	}
    	if(twin.isGoal()){
    		target = twin_pq.delMin();
    	}
    	while(true){
    		if(count %2 ==0){
    			pq = initial_pq;
    		}
    		else{
    			pq = twin_pq;
    		}
        	if (!pq.isEmpty()){
        		SearchNode sn = pq.delMin();
        		for(Board neighbor:sn.board.neighbors()){
        			if(sn.prev == null || neighbor.toString() != sn.prev.board.toString()){
        				SearchNode neighbor_sn = new SearchNode(neighbor,sn,sn.move+1,sn.move+1 + neighbor.manhattan());
        				if(neighbor.isGoal()){
        					target = neighbor_sn;
        					break;
        				}
        				else{
        					if (!table.containsKey(neighbor.toString())){
        						pq.insert(neighbor_sn);
//        						StdOut.print(neighbor);
        					}	
        				}
        				
        			}
        		}
        		table.put(sn.board.toString(),"");
        	}
        	
        	if (target != null){
            	if (count%2 !=0){
            		this.isSolvable = false;
            		this.moves = -1;
            	}
            	else{
            		this.isSolvable = true;
            		this.moves = target.move;
            		while(target != null){
            			solution.add(target.board);
            			target = target.prev;
            		}
            	}
        		break;
        	}
        	if(initial_pq.size() ==0 && twin_pq.size() == 0)
        		break;
        	count++;
    	}
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
    	return this.isSolvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
    	return this.moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	Collections.reverse(this.solution);
    	if(this.solution.size() == 0){
    		return null;
    	}
    	return this.solution;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
    	
    }
}