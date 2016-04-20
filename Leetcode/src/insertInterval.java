import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class insertInterval {
	class compareInterval implements Comparator<Interval>{
		public int compare(Interval i1, Interval i2){
			if( i1.start == i2.start)
				return i1.end - i2.end;
			return i1.start - i2.start;
		}
	}
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	if(newInterval == null)
    		return intervals;
    	List<Interval> ret = new ArrayList<Interval>();
    	if (intervals == null){
    		ret.add(newInterval);
    		return ret;
    	}
    	intervals.add(newInterval);
        Interval[] arr = new Interval[intervals.size()];
        
        intervals.toArray(arr);
        java.util.Arrays.sort(arr,new compareInterval());
        for(int i = 0; i < arr.length;){
        	int j = i;
        	int start = arr[i].start;
        	int end = arr[i].end;
        	while(i+1 < arr.length && end >= arr[i+1].start){
        		i++;
        		end = Math.max(arr[i].end,end);
        	}
        	if (i > j){
        		ret.add(new Interval(start,end));
        	}
        	else{
        		ret.add(arr[j]);
        	}
        	i++;
        }
        return ret;
    }
    public static void main(String args[]){
    	Interval[] arr = {new Interval(1,5)};
    	insertInterval ii = new insertInterval();
    	List<Interval> intervals = new ArrayList<Interval>();
    	for(Interval i:arr)
    		intervals.add(i);
    	for(Interval i:ii.insert(intervals, new Interval(1,3))){
    		System.out.printf("start: %d, end:%d\n",i.start,i.end);
    	}
    }
}
