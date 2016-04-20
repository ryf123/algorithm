import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class CourseSchedule2 {

	HashSet<Integer> visited = new HashSet<Integer>();
	HashSet<Integer> sorted = new HashSet<Integer>();
	HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
	int[] arr;
	int pointer;
	// topological sort
	// it's ok to be in the sorted list
	// but in dfs the node can't be visited before
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	int[] empty = {};
    	for (int[] pre:prerequisites){
    		if(!map.containsKey(pre[0])){
    			ArrayList<Integer> a = new ArrayList<Integer>();
    			a.add(pre[1]);
    			map.put(pre[0], a);
    		}
    		else
    			map.get(pre[0]).add(pre[1]);
    			
    	}
    	arr = new int[numCourses];
    	pointer = 0;
        for(int i = 0; i< numCourses; i++){
        	if (!this.dfs(i))
        		return empty;
        }
        if(pointer == numCourses)
        	return arr;
        else
        	return empty;
    }
    private boolean dfs(int course){
    	if(sorted.contains(course))
    		return true;
    	else if(visited.contains(course))
    		return false;
    	this.visited.add(course);
    	if(map.containsKey(course))
	    	for(int pre:map.get(course)){
	    		if(!this.dfs(pre))
	    			return false;
	    	}
    	arr[pointer++] = course;
    	sorted.add(course);
    	this.visited.remove(course);
    	return true;
    }
    
    public static void main(String args[]){
    	CourseSchedule2 cs2 = new CourseSchedule2();
    	
    	int[][] arr = {{1,0},{2,0},{3,1},{3,2}};
    	for(int course: cs2.findOrder(4,arr)){
    		System.out.printf("%d\n",course);
    	}
    }
}
