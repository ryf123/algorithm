package leetcode;
/*
 * Given a sorted integer array where the range of elements are [0, 99] inclusive,
 *  return its missing ranges.
	For example, given [0, 1, 3, 50, 75], 
	return [“2”, “4->49”, “51->74”, “76->99”]*/

import java.util.ArrayList;
import java.util.List;


public class MissingRange {
	public List<String> findMissingRanges(int[] nums, int lower, int upper){
		List<String> ret = new ArrayList<String>();
		int prev = lower-1;
		for(int num:nums){
			if(num - prev > 1){
				ret.add(this.addRange(prev+1, num-1));
			}
			prev = num;
		}
		if(nums.length > 0 && nums[nums.length-1] != upper){
			ret.add(this.addRange(nums[nums.length-1]+1, upper));
		}
		if(nums.length == 0){
		    ret.add(this.addRange(lower,upper));
		}
		return ret;
	}
	private String addRange(int start,int end){
		if(start == end)
			return Integer.toString(start);
		else
			return Integer.toString(start) + "->" + Integer.toString(end);
	}
}
