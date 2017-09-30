package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/*
 * You are given a m x n 2D grid initialized with these three possible values.

	-1 - A wall or an obstacle.
	0 - A gate.
	INF - Infinity means an empty room. 
	We use the value 231 - 1 = 2147483647 to represent INF as 
	you may assume that the distance to a gate is less than 2147483647.
	Fill each empty room with the distance to its nearest gate. 
	If it is impossible to reach a gate, it should be filled with INF.
	
	For example, given the 2D grid:
	INF  -1  0  INF
	INF INF INF  -1
	INF  -1 INF  -1
	  0  -1 INF INF
	After running your function, the 2D grid should be:
	  3  -1   0   1
	  2   2   1  -1
	  1  -1   2  -1
	  0  -1   3   4
 */
public class WallGates {
    public void wallsAndGates(int[][] rooms) {
    		final int INFINITY = Integer.MAX_VALUE;
    		int row = rooms.length;
    		if(row == 0) return;
    		int col = rooms[0].length;
        boolean[][] visited = new boolean[row][col];
        ArrayDeque<Integer[]> queue = new ArrayDeque<Integer[]>();
        for(int i=0; i< row;i++){
        		for(int j=0; j< col; j++){
        			if(rooms[i][j] == 0){
        				Integer[] index = {i,j};
        				queue.push(index);
        				visited[i][j] = true;
        			}
        		}
        }
        while(!queue.isEmpty()){
//        		System.out.println(Arrays.deepToString(rooms));
        		Integer[] index = queue.poll();
        		int i = index[0];
        		int j = index[1];
        		int[][] offsets = {{-1,0},{1,0},{0,1},{0,-1}};
        		for(int[] offset:offsets){
        			int _i = i + offset[0];
        			int _j = j + offset[1];
            		if(_i >=0 && _i < row && _j >=0 && _j <col && 
            		!visited[_i][_j] && rooms[_i][_j] == INFINITY){
            			rooms[_i][_j] = rooms[i][j] + 1;
            			visited[_i][_j] = true;
            			Integer[] _index = {_i,_j};
            			queue.offer(_index);
            		}        			
        		}
        }
        
    }
    public static void main(String[] args) {
    		final int INF = Integer.MAX_VALUE;
    		WallGates wg = new WallGates();
    		int[][] rooms = {{INF,-1,0,INF},{INF,INF,INF,-1},{INF,-1,INF,-1},{0,-1,INF,INF}};
    		wg.wallsAndGates(rooms);
    		System.out.println(Arrays.deepToString(rooms));
	}
}
