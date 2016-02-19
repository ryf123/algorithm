import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;;
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
    	MinPQ<SearchNode> pq = new MinPQ<SearchNode>(cmp);
    	pq.insert(new SearchNode(initial,null,0,initial.hamming()));
    	SearchNode target = null; 
    	while (!pq.isEmpty()){
    		SearchNode sn = pq.delMin();
    		for(Board neighbor:sn.board.neighbors()){
    			if(sn.prev == null || neighbor.toString() != sn.prev.board.toString()){
    				SearchNode neighbor_sn = new SearchNode(neighbor,sn,sn.move+1,neighbor.hamming());
    				if(neighbor.isGoal()){
    					target = neighbor_sn;
    					break;
    				}
    				pq.insert(neighbor_sn);
    			}
    		}
    		if(target != null)
    			break;
    	}
    	if(target == null){
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
    	return this.solution();
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
    	
    }
}