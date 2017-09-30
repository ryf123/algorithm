
public class BattleShip {
    public int countBattleships(char[][] board) {
        int row = board.length;
        if(row == 0)
            return 0;
        int col = board[0].length;
        int total = 0;
        for(int i=0;i<row;i++){
            int j = 0;
            while(j<col){
                // up and down are not X
                if(board[i][j] == 'X' && (i==0 || board[i-1][j] != 'X') &&  (i==row-1 || board[i+1][j] != 'X')){
                    System.out.println(i+";"+j);
                        total++;
                    while(j<col && board[i][j] == 'X')
                        j++;
                }
                j++;
            }
        }
        for(int j=0;j<col;j++){
            int i = 0;
            while(i<row){
                // left and right are not X
                if(board[i][j] == 'X' && (j==0 || board[i][j-1] != 'X') && (j==col-1 || board[i][j+1] != 'X')){
                    if((i!=0 && board[i-1][j] == 'X') ||  (i!=row-1 && board[i+1][j] == 'X')){
                        total++;
                        while(i<row && board[i][j] == 'X')
                            i++;
                    }
                }
                i++;
            }
        }
        return total;
    }
}
