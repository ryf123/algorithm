package leetcode;
import java.util.*;
public class ThreeSumSmaller {
	public int threeSumSmaller(int[] nums, int target){
		Arrays.sort(nums);
		int counter = 0;
		for(int i=0;i<nums.length-2;i++){
			counter += twosum(nums,i+1,target-nums[i]);
		}
		return counter;
	}
	private int twosum(int[] nums,int start, int target) {
	    int ret = 0;
		while(start<nums.length){
            for(int i=start+1;i<nums.length;i++){
                if(nums[start]+nums[i] < target)
                    ret++;
                else
                    break;
            }
            start++;
		}
		return ret;
	}
}
