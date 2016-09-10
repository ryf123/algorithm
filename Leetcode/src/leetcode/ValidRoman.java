package leetcode;

public class ValidRoman {

	boolean validroman(String s){
	    char[] romans = {'M','D','C','L','X','V','I'};
		int[] values = {1000,500,100,50,10,5,1};
		int l = s.length();
		if(l== 0)
			return false;
		int count = 1;
		boolean can_reverse = true; // next char index can be smaller than current char index; 
		for(int i=0;i<l-1;i++){
			int current = findindex(romans,s.charAt(i));
			int next = findindex(romans,s.charAt(i+1));
			if(current == -1 || next == -1)
				return false;
			if(current == next){
				count++;
				if(count == 4)
					return false;
				if(current %2 == 0)
					continue;
			}
			else{
				count = 1;
				if(current < next)
					continue;
	
				else if(current - next == 2){
					if(i == 0)
						continue;
					if(i>0){
						int prev = findindex(romans, s.charAt(i-1));
						if(prev < next)
							continue;
					}
				}
			}
			return false;
		}
		return true;
	}
	int findindex(char[] romans,char c){
	    for(int i=0;i<romans.length;i++){
	        if(romans[i] == c)
	            return i;
	    }
	    return -1;
	}
	public static void main(String[] args) {
		ValidRoman vr = new ValidRoman();
		System.out.println(vr.validroman("VX") == false);
		System.out.println(vr.validroman("VV") == false);
		System.out.println(vr.validroman("II") == true);
		System.out.println(vr.validroman("IIX") == false);
		System.out.println(vr.validroman("IX") == true);
		System.out.println(vr.validroman("IM") == false);
		System.out.println(vr.validroman("XM") == false);
		System.out.println(vr.validroman("CM") == true);
		System.out.println(vr.validroman("MMMDCCCC") == false);
	}
}
// VX false
// VV false
// II true
// IIX false
// IX true
// IM false
// XM false
// CM true
