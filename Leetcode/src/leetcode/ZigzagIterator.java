package leetcode;
import java.util.*;
public class ZigzagIterator {
	ArrayList<Iterator<Integer>> iterators;
	int counter = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iterators = new ArrayList<Iterator<Integer>>();
        iterators.add(v1.iterator());
        iterators.add(v2.iterator());
    }

    public int next() {
        return iterators.get((counter++)%iterators.size()).next();
    }

    public boolean hasNext() {
    		for(int i=0;i<iterators.size();i++){
    			if(iterators.get(counter%iterators.size()).hasNext())
    				return true;
    			counter++;
    		}
        return false;
    }
    public static void main(String[] args) {
		ArrayList<Integer> v1 = new ArrayList<Integer>();
		ArrayList<Integer> v2 = new ArrayList<Integer>();
		v1.add(3);
		v1.add(4);
		v2.add(1);
		v2.add(2);
		v2.add(0);
		ZigzagIterator zzi = new ZigzagIterator(v1,v2);
		while(zzi.hasNext())
			System.out.println(zzi.next());
	}
}
