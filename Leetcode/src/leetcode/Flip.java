package leetcode;
import java.util.*;
public class Flip {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> ret = new ArrayList<String>();
        char[] array = s.toCharArray();
        for(int i=0; i<array.length-1; i++){
            if(array[i] == '+' && array[i+1] == '+'){
                flip(array,i,i+1);
                ret.add(new String(array));
                flip(array,i,i+1);
            }
        }
        return ret;
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
    		Flip f = new Flip();
    		String s = "++++--++";
    		System.out.println(f.generatePossibleNextMoves(s));
	}
}
