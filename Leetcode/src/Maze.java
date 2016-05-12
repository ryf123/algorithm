import java.util.Arrays;

public class Maze {
    boolean[][] visit;
    boolean[][] north;
    boolean[][] west;
    int width;
    int height;
    public Maze(int width, int height){
    		this.width = width;
    		this.height = height;
        visit = new boolean[width+2][height+2];
        north = new boolean[width+2][height+2];
        west = new boolean[width+2][height+2];
        // mark line  0 and line height + 1 to be visited
        for(int i=0; i <= width+1; i++){
            visit[0][i] = true;
            visit[height+1][i] = true;
        }
        // mark column 0 and width + 1 to be visited
        for(int i=0; i <= height +1 ;i++){
            visit[i][0] = true;
            visit[i][width+1] = true;
        }
        dfs(1,1);
    }
    private void dfs(int i,int j){
        this.visit[i][j] = true;
        int[][] arr = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!visit[i-1][j]  || !visit[i+1][j] || !visit[i][j-1] || !visit[i][j+1]){
            int random = (int)Math.floor(Math.random() * 4);
            int[] index = arr[random];
            if(!visit[i+index[0]][j+index[1]] ){
                if(random == 3)//south
                    north[i][j] = true;
                else if(random == 2)
                    north[i+1][j] = true;
                else if(random == 1)
                    west[i][j] = true;
                else
                    west[i][j+1] = true;
                dfs(i+index[0],j+index[1]);
            }
        }
    }
    public void printMaze(){
    		for(int i = 1; i <= this.height+1; i++){
    			for(int j=1; j <= this.width+1; j++){
    				System.out.print(this.north[i][j]?"+ ":"+-");
    			}    
    			System.out.println();
    			for(int j=1; j <= this.width+1; j++){
    				System.out.print(this.west[i][j]?"  ":"| ");
    			}
    			System.out.println();
    		}

    }
    public static void main(String[] args) {
		Maze m = new Maze(10,10);
		m.printMaze();
		
	}
}

