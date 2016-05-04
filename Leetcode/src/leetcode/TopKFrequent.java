package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
    		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
        		if(map.containsKey(num)){
        			map.put(num, map.get(num) + 1);
        		}
        		else	
        			map.put(num, 1);
		}
        int[][] arr = new int[map.size()][2];
        int index = 0;
        for(int num:map.keySet()){
        		int[] temp = {map.get(num),num};
        		arr[index++] = temp;
        }
        ArrayList<Integer> ret = new ArrayList<Integer>();
        topK(arr, k, 0, arr.length-1);
        int pointer = arr.length-1;
        while(ret.size() < k && pointer >=0)
        		ret.add(arr[pointer--][1]);
        return ret;
    }
    private void topK(int[][] arr,int k,int start, int end){
    		if(start >= end)
    			return;
    		int pivotIndex = start;
    		int initialEnd = end;
    		int pivot = arr[pivotIndex][0];
    		while(start < end){
    			while(start<= end && arr[start][0] <= pivot)
    				start++;
    			while(start<= end && arr[end][0] >= pivot)
    				end--;
    			if(start < end && arr[start][0] > pivot && arr[end][0] < pivot)
    				swap(arr, start++, end--);
    		}
    		swap(arr, pivotIndex, end);
    		int top = arr.length - (end + 1);
    		if(top ==  k)
    			return;
    		else if (top >  k) {
			topK(arr, k, end + 1, initialEnd);
		}
    		else{
    			topK(arr, k, pivotIndex, end - 1);
    		}
    }
    private void swap(int[][] arr,int i,int j){
    		int[] temp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = temp;
    }
    public static void main(String[] args) {
		int[] nums = {1,1,2,2,3,4,5,6,7};
		TopKFrequent tf = new TopKFrequent();
		for(int num:tf.topKFrequent(nums, 2)){
			System.out.println(num);
		}
	}
}
