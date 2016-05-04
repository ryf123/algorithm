import java.util.*;

public class countSmaller {
	int[] smaller = {};
	private int[][] merge(int[][] first,int[][] second){
		int l1 = first.length;
		int l2 = second.length;
		int[][] aux = new int[l1+l2][2];
		int pos = l1 + l2 - 1;
		for(int i=l1-1, j=l2-1; i >= 0 || j >= 0;){
			if(i == -1){
				aux[pos--] = second[j--];
			}
			else if(j == -1){
				aux[pos--] = first[i--];
			}
			else{
				if(first[i][0] > second[j][0]){
					this.smaller[first[i][1]] += j+1;
					aux[pos--] = first[i--];
					
				}
				else {
					aux[pos--] = second[j--];
				}
			}
		}
		return aux;
	}
	private int[][] sort(int[][] nums,int start,int end){
		int[][] ret;
		if (end == start) {
			ret = new int[1][2];
			ret[0] = nums[end];
			return ret;
		}
		int mid = (end+start)/2;
		int[][] first = this.sort(nums,start,mid);
		int[][] second = this.sort(nums, mid+1, end);
		ret = this.merge(first,second);
		return ret;
	}
    public List<Integer> countSmaller(int[] nums) {
    		List<Integer> ret = new  ArrayList<Integer>();
    		if(nums == null || nums.length == 0)
    			return ret;
    		// use array of array to mark position
    		// the first one is number, second one is position
    		int[][] arr = new int[nums.length][2];
    		for(int i= 0; i < nums.length ; i++){
    			int[] temp = {nums[i],i};
    			arr[i] = temp;
    		}
    		this.smaller = new int[nums.length];
    		
    		sort(arr,0,nums.length-1);
    		for(int num:this.smaller){
    			ret.add(num);
    		}
    		return ret;
    }
    public static void main(String args[]) {
    		int[] nums = {4,3,5,2,1};
		countSmaller cs =  new countSmaller();
		for(int num:cs.countSmaller(nums))
			System.out.printf("%d", num);
		
	}
}
