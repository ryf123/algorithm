package leetcode;
import java.util.*;
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
	ArrayList<Integer> ret;
	Iterator<Integer> it;
    public NestedIterator(List<NestedInteger> nestedList) {
        ret = new ArrayList<Integer>();
        it = ret.iterator();
        for(NestedInteger ni:nestedList ){
        		this.dfs(ni);
        }
    }
    	private void dfs(NestedInteger ni){
    		if(ni.isInteger())
    			this.ret.add(ni.getInteger());
    		
    		else {
			for(NestedInteger child:ni.getList()){
				this.dfs(child);
			}	
		}
    	}
    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

	@Override
	public void remove() {
		// TODO Auto-generated method stub
	}
	public static class MyInteger implements NestedInteger{
		private Integer intValue;
		private List<NestedInteger> list;

		public MyInteger(Integer value) {
			this.intValue = value;
		}
		public MyInteger(List<NestedInteger> l) {
			this.list = l;
		}
		@Override
		public boolean isInteger() {
			// TODO Auto-generated method stub
			return this.list == null;
		}

		@Override
		public Integer getInteger() {
			// TODO Auto-generated method stub
			return this.intValue;
		}

		@Override
		public List<NestedInteger> getList() {
			// TODO Auto-generated method stub
			return this.list;
		}
		
	}
	public static void main(String[] args) {
		ArrayList<NestedInteger> arrayList = new ArrayList<NestedInteger>();
		Integer i = new Integer(1);
		MyInteger m = new MyInteger(i);
		arrayList.add(m);
		NestedIterator ni = new NestedIterator(arrayList);
		while(ni.hasNext())
			ni.next();
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */