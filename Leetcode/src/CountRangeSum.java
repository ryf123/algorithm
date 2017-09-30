import java.util.*;
public class CountRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        TreeMap<Integer,Integer> map = new TreeMap();
        int sum = 0;
        map.put(0,1);
        int total = 0;
        for(int num:nums){
            sum += num;
            // lower<= sum - x <= upper
            // sum -upper <= x <= sum-lower
            // l <= x <= r
            int r = sum-lower;
            int l = sum-upper;
            SortedMap<Integer,Integer> sub = map.subMap(l,r+1); // since it's exclusive
            for(Integer count:sub.values()){
                total += count;
            }
            if(!map.containsKey(sum))
                map.put(sum,0);
            map.put(sum,map.get(sum)+1);
        }
        return total;
    }
    public static void main(String[] args) {
    	int[] data = new int[]{230,136,145};
	}
}
