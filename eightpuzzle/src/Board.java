import java.util.ArrayList;

public class Board{
	private int[][] blocks;
	private int N;
	
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
    {
    	this.blocks = blocks;
    	this.N = blocks.length;


    }
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension()                 // board dimension N
    {
    	return this.N;
    }
    public int hamming()                   // number of blocks out of place
    {
    	int dist = 0;
    	int count = 0;
    	for(int i=0; i<this.N; i++){
    		for(int j=0; j<this.N; j++){
    			count++;
    			if (this.blocks[i][j] == 0)
    				continue;
    			else if(this.blocks[i][j] != count)
    				dist += 1;
    		}
    	} 
    	return dist;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	int dist = 0;
    	for(int i=0; i<this.N; i++){
    		for(int j=0; j<this.N; j++){
    			if (this.blocks[i][j] == 0)
    				continue;
    			int num = this.blocks[i][j];
    			int goalRow = (num-1)/(this.N);
    			int goalCol = (num-1)%(this.N);
    			dist += Math.abs(i - goalRow) + Math.abs(j - goalCol);
    		}
    	} 
    	return dist;
    }
    public boolean isGoal()                // is this board the goal board?
    {
    	int count = 1;
    	for(int i=0; i<this.N; i++){
    		for(int j=0; j<this.N; j++){
    			if(i== this.N-1 && j == this.N-1 && this.blocks[i][j] == 0)
    				break;
    			else if(this.blocks[i][j] != count)
    				return false;
    			count++;
    		}
    	}   	
    	return true;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
    	int[] indexes1 =this.getRandomIndex();
    	int[] indexes2 =this.getRandomIndex();
    	while(true){
    		if(indexes1[0] != indexes2[0] || indexes1[1] != indexes2[1])
    			break;
    		indexes2 =this.getRandomIndex();
    	}
    	int[][] tempB = copyArray(this.blocks);   	
    	tempB[indexes1[0]][indexes1[1]] = this.blocks[indexes2[0]][indexes2[1]];
    	tempB[indexes2[0]][indexes2[1]] = this.blocks[indexes1[0]][indexes1[1]];
    	return new Board(tempB);
    }
    private int[] getRandomIndex(){
    	int[] indexes = new int[2];
    	while(true){
    		int x = edu.princeton.cs.algs4.StdRandom.uniform(this.N);
    		int y = edu.princeton.cs.algs4.StdRandom.uniform(this.N);
    		if(this.blocks[x][y] != 0){
    			indexes[0] = x;
    			indexes[1] = y;
    			break;
    		}
    	}
    	
    	return indexes;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
    	return (String)y == this.toString();
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
    	ArrayList<Board> neighbor = new ArrayList<Board>();;
    	int zeroX = 0;
    	int zeroY = 0;
    	for(int i=0; i<this.N; i++){
    		for(int j=0; j<this.N; j++){
    			if (blocks[i][j] == 0){
    				zeroX = i;
    				zeroY = j;
    				break;
    			}
    		}
    	}
    	// neighbors are -1,0 1,0 0,-1 0,1
    	int[][] positions = {{-1,0},{1,0},{0,-1},{0,1}};
    	for(int[] pos:positions){
    		int i = pos[0];
    		int j = pos[1];
//			System.out.printf("i: %d, j: %d\n", i,j);
			if(zeroX +i >=0 && zeroX+i < this.N && zeroY +j >=0 && zeroY +j < this.N ){
	    		// make a copy of the board and exchange the value of 0 block and it's neighbor
    	    	int[][] tempB = copyArray(this.blocks);
    	    	tempB[zeroX][zeroY] = tempB[zeroX+i][zeroY+j];
    	    	tempB[zeroX+i][zeroY+j] = 0;
    	    	neighbor.add(new Board(tempB));
	    	}
    		
    	}	
    	return neighbor;
    }
    private int[][] copyArray(int[][] o){
    	int[][] c = new int[o.length][o.length];
    	for(int i=0; i< o.length; i++)
    		for(int j=0; j< o.length; j++)
    			c[i][j] = o[i][j];
    	return c;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
    	String ret = Integer.toString(this.N) + "\n";
    	for(int i=0; i<this.N; i++){
    		int j = 0;
    		while (j < this.N-1){
    			ret += Integer.toString(this.blocks[i][j]);
    			ret += " ";
    			j++;
    		}
    		ret += Integer.toString(this.blocks[i][j]);
    		ret += "\n";
    	}
    	return ret;
    }

    public static void main(String[] args) // unit tests (not graded)
    {
    	int[][] a = {{1,2},{3,4}};
    	Board b =  new Board(a);
    	System.out.printf(b.toString());
    }
}