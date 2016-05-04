package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {
	public int lengthOfLIS(int[] nums) {		
		int l = nums.length;
		if(l == 0)
			return 0;
		int[] arr = new int[l];
		int currentLength = 0;
		for (int num:nums) {
			int index = Arrays.binarySearch(arr,0,currentLength,num);
			//(-(insertion point) - 1) 
			if(index<0)
				index = -(index +1); 
			arr[index] = num;
			if(index == currentLength)
				currentLength++;
		}
		System.out.print(Arrays.toString(arr));
		return currentLength;
	}
	public static void main(String[] args) {
		int[] nums = {0,1,0,-3,-2,-1};
		LIS lis = new LIS();
		System.out.print(lis.lengthOfLIS(nums));
	}
}
