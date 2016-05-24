package leetcode;
import java.util.*;
class Strobogrammatic{
	HashMap<Character,Character> map;
    Strobogrammatic(){
        map = new HashMap<Character,Character>();
        map.put('8','8');
        map.put('0','0');
        map.put('1','1');
        map.put('6','9');
        map.put('9','6');
    }
    public boolean isStrobogrammatic(String s){
        if(s==null||s.length()==0) return false;
        int i=0;
        int y=s.length()-1;
        char[] ss = s.toCharArray();
        while(i<y){
            if(!map.containsKey(ss[i]) || map.get(ss[i])!=ss[y]) return false;
            i++;
            y--;
        }
        if(i==y){
            if(ss[i]=='0'||ss[i]=='8'||ss[i]=='1') return true;
            else return false;
        }
        return true;
    }
    public static void main(String[] args) {
    		Strobogrammatic strobogrammatic = new Strobogrammatic();
    		System.out.println(strobogrammatic.isStrobogrammatic("0"));
    		System.out.println(strobogrammatic.isStrobogrammatic("818"));
    		System.out.println(strobogrammatic.isStrobogrammatic("88"));
    		System.out.println(strobogrammatic.isStrobogrammatic("69"));
    		System.out.println(strobogrammatic.isStrobogrammatic("8228"));
    		System.out.println(strobogrammatic.isStrobogrammatic("858"));
	}
}
