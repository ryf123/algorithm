public class Percolation {
	private int[][] points;
	// create N-by-N grid, with all sites blocked
	private int size;
	private int[] roots;
	private int[] weight;
	public Percolation(int N) {
		if (N <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
	    points = new int[N][N];
	    size = N;
	    roots = new int[N * N];
	    for(int i=0; i<N * N; i++) {
	    	roots[i] = i;
	    }
	    weight = new int[N * N];
	    java.util.Arrays.fill(weight,1);
	}
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
	   if(i<=0 || i>size || j<=0 || j>size)
		   throw new java.lang.IndexOutOfBoundsException();		
		i -= 1;
		j -= 1;
		int[][] positions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
		if(points[i][j] == 0){
		   points[i][j] =1;
		   int[] p1 = new int[]{i,j};
		   for(int count=0; count<positions.length; count++){
			   int x = i+positions[count][0];
			   int y = j+positions[count][1];
			   if(x>=0 && x<size && y>=0 && y<size && isFull(x + 1,y + 1)){
				   int[] p2 = new int[]{x, y};
				   union(p1, p2);
			   }
		   }
		   for(int count=0; count<positions.length; count++){
			   int x = i+positions[count][0];
			   int y = j+positions[count][1];
			   if(x>=0 && x<size && y>=0 && y<size && isOpen(x + 1,y + 1)){
				   int[] p2 = new int[]{x, y};
				   union(p1, p2);
			   }
		   }
		}
	}         
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
	   if(i<=0 || i>size || j<=0 || j>size)
		   throw new java.lang.IndexOutOfBoundsException();
	   i -= 1;
	   j -= 1;
	   if(root(index(i,j)) < size && points[i][j]==1)return true;
	   return false;
	}     
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j){
	   if(i<=0 || i>size || j<=0 || j>size)
		   throw new java.lang.IndexOutOfBoundsException();
	   if(isFull(i,j))return true;
	   i -= 1;
	   j -= 1;
	   return points[i][j] == 1;
	}     
	// does the system percolate?
	public boolean percolates() {
	   for(int i=0;i<size;i++) {
		   if(points[size- 1][i] != 0)
			   if(isFull(size, i + 1))
				   return true;
	   }
	   return false;
	}
	private int index(int i,int j) {
		return i*size + j;
	}
	private int root(int id){
		while (roots[id] != id) {
			roots[id] = roots[roots[id]];
			id = roots[id];
		}
		return id;
	}
	private void union(int[] point1,int[] point2){
		int index1 = index(point1[0], point1[1]);
		int index2 = index(point2[0], point2[1]);
		int root1 = root(index1);
		int root2 = root(index2);
		if (root1 != root2) {
			if (root1 < size) {
				roots[root2] = root1;
			}
			else if (root2 < size) {
				roots[root1] = root2;
			}
			else if(weight[root1] >= weight[root2]){
				roots[root2] = root1;
				weight[root1] += weight[root2];
			}
			else {
				roots[root1] = root2;
				weight[root2] += weight[root1];
			}
		}
	}
	public static void main(String[] args){
	   
	}  
}