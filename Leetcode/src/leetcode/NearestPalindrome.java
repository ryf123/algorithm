package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NearestPalindrome {
	int nearest(int num){
		if(num <= 0)
			return 0;
		ArrayList<Integer> array = new ArrayList<Integer>();
		int copy = num;
		while(num > 0){
			array.add(num%10);
			num /= 10;
		}
		num  = copy;
		Collections.reverse(array);
		int l = array.size();
		int a =  toNum(reverse(array, l));
		int ret = a;
		List<Integer> higher = incrementOne(array,l/2);
//		System.out.println(a);
		int b = toNum(reverse(higher, higher.size()));
		if(b > 0){
//			System.out.println(b);
			if(Math.abs(ret-num) > Math.abs(b-num)){
				ret = b;
			}
		}
		List<Integer> lower = decrementOne(array, l/2);
		int c = toNum(reverse(lower, lower.size()));
		if(c > 0){
			if(Math.abs(ret-num) > Math.abs(c-num)){
				ret = c;
			}
		}		
		return ret;
	}
	List<Integer> incrementOne(List<Integer> array,int end){
		int carry = 1;
		List<Integer> ret = new ArrayList<Integer>(array);
		for(int i=end;i>=0;i--){
			if(array.get(i) == 9){
				ret.set(i,0);
			}
			else{
				ret.set(i, array.get(i)+1);
				carry = 0;
				break;
			}
		}
		if(carry == 1)
			ret.add(0, 1);
		return ret;
	}
	List<Integer> decrementOne(List<Integer> array,int end){
		int dec = 1;
		List<Integer> ret = new ArrayList<Integer>(array);
		for(int i=end;i>=0;i--){
			if(array.get(i) == 0){
				ret.set(i, 9);
			}
			else{
				ret.set(i, array.get(i)-1);
				dec = 0;
				break;
			}
		}
		if(dec == 1)
			ret.remove(0);
		return ret;
	}
	List<Integer> reverse(List<Integer> array,int l){
		List<Integer> ret = new ArrayList<Integer>(array);
		int i = 0;
		int j = l-1;
		while(i<j){
			ret.set(j--, array.get(i++));
		}
		return ret;
	}
	int toNum(List<Integer> array){
		long num = 0;
		for(int i=0;i<array.size();i++){
			num *= 10;
			if(num > Integer.MAX_VALUE)
				return -1;
			num += array.get(i);
		}
		return (int)num;
	}
	public static void main(String[] args) {
		NearestPalindrome np = new NearestPalindrome();
		System.out.println(np.nearest(123456));
		System.out.println(np.nearest(123));
		System.out.println(np.nearest(911));
		System.out.println(np.nearest(800));
		System.out.println(np.nearest(9));
		System.out.println(np.nearest(111));
		System.out.println(np.nearest(1999));
	}
}
