package leetcode;

import java.util.*;

public class FlipGame2 {
    public boolean canWin(String s) {
        return generatePossibleNextMoves(s,1);
    }
    public boolean generatePossibleNextMoves(String s,int counter) {
    		boolean canmove = false;
        char[] array = s.toCharArray();
        for(int i=0; i<array.length-1; i++){
            if(array[i] == '+' && array[i+1] == '+'){
            		canmove = true;
                flip(array,i,i+1);
                String sub = new String(array);
                if (generatePossibleNextMoves(sub, counter+1))
                		return true;
                flip(array,i,i+1);
            }
        }
        if(!canmove && counter % 2 == 0) return true;
        return false;
    }
    private void flip(char[] array,int i, int j){
        if(array[i] == '+'){
            array[i] = '-';
            array[j] = '-';
        }
        else{
            array[i] = '+';
            array[j] = '+';            
        }
    }
    public static void main(String[] args) {
    		FlipGame2 fg = new FlipGame2();
    		System.out.println(fg.canWin("+--++++++"));
	}
}
