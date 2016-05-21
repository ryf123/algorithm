package leetcode;
import java.util.*;
public class ConsistentHash {
    public List<List<Integer>> consistentHashing(int n) {
        // Write your code here
    		PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<ArrayList<Integer>>(n, new Comparator<ArrayList<Integer>>() {
				public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
					int diff = (o1.get(1) - o1.get(0)) - (o2.get(1) - o2.get(0));
					if(diff == 0){
						return o1.get(2) - o2.get(2);
					}
					else if (diff < 0) {
						return 1;
					}
					else{
						return -1;
					}
				}
    			});
    		pq.add(new ArrayList<Integer>(Arrays.asList(0,359,1)));
    		int maxId = 2;
    		while(maxId <= n){
    			ArrayList<Integer> range = pq.poll();
    			int x = range.get(0);
			int y = range.get(1);
			int id = range.get(2);
			ArrayList<Integer> first = new ArrayList<Integer>(Arrays.asList(x,(x+y)/2,id));
			ArrayList<Integer> second = new ArrayList<Integer>(Arrays.asList((x+y)/2+1,y,maxId++));
			pq.offer(first);
			pq.offer(second);
    		}
    		List<List<Integer>> ret = new ArrayList<List<Integer>>();
    		while(!pq.isEmpty()){
    			ret.add(pq.poll());
    		}
    		Collections.sort(ret, new Comparator<List<Integer>>() {
				public int compare(List<Integer> o1, List<Integer> o2) {
					return o1.get(0) - o2.get(0);
				}
		});
    		return ret;
    }
    public static void main(String[] args) {
		ConsistentHash ch = new ConsistentHash();
		for(List<Integer> a:ch.consistentHashing(6)){
			System.out.println(a);
		}
	}
}
