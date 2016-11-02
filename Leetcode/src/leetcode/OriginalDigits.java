package leetcode;

public class OriginalDigits {
	   public String originalDigits(String s) {
	        int[] map = new int[26];
	        int[] count = new int[10];
	        int l = s.length();
	        for(int i=0;i<l;i++){
	            char c = s.charAt(i);
	            map[c-'a']++;
	        }
	        String[] numbers = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
	        for(int index=0;index<10;index++){
	            String number = numbers[index];
	            traverse(number,map,count,0,Integer.MAX_VALUE,index);
	        }
	        StringBuilder sb = new StringBuilder();
	        for(int i=0;i<10;i++){
	            while(count[i]>0)
	                sb.append(String.valueOf(i));
	        }
	        return sb.toString();
	    }
	    // use as many characters as possible
	    void traverse(String number,int[] map,int[] count,int start,int used,int index){
	        if(start == number.length()){
	            count[index]+= used;
	            // update map
	            for(char c:number.toCharArray()){
	                map[c-'a']-=used;
	            }
	            return;
	        }
	        char c = number.charAt(start);
	        if(map[c-'a'] > 0){
	            used = Math.min(used,map[c-'a']);
	            traverse(number,map,count,start+1,used,index);
	        }
	    }
	    
}
