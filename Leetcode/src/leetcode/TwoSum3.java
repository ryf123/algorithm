package leetcode;

import java.util.Arrays;

/*
Design and implement a TwoSum class. 
It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/
class TwoSum3{
    int[] nums;
    TwoSum3(){
        
    }
    public void add(int num){
        if(nums == null){
            this.nums  = new int[]{num};
        }
        else{
            int[] newnew = new int[nums.length+1];
            for(int i=0; i<newnew.length; i++){
                if(i<nums.length&&nums[i]<num){
                    newnew[i]=nums[i];
                }
                else if(i<nums.length&&nums[i]>=num){
                    newnew[i]=num;
                    while(i<nums.length){
                        newnew[i+1]=nums[i];
                        i++;
                    }
                    break;
                }
                else{
                    newnew[nums.length]=num;
                }
            }
            nums = newnew;
        }
    }
    public boolean find(int target){
            int start = 0;
            int end = nums.length-1;
            while(start < end){
            int sum  = nums[start] + nums[end];
            if(sum == target)
                return true;
            else if(sum > target)
                end--;
            else {
                start++;
            }
            
            }
            return false;
    }
    public static void main(String[] args) {
    		TwoSum3 ts3 = new TwoSum3();
    		ts3.add(1);
    		ts3.add(3);
    		ts3.add(5);
    		System.out.println(ts3.find(1));
    		System.out.println(ts3.find(4));
    		System.out.println(ts3.find(7));
    		System.out.println(ts3.find(8));
    		ts3.add(2);
    		System.out.println(ts3.find(7));
	}
}
