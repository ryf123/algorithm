import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;


public class graphClone {


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	// use bfs queue to solve this
    	HashMap<Integer,UndirectedGraphNode> visited = new HashMap<Integer,UndirectedGraphNode>();
    	Queue<UndirectedGraphNode> queue = new ArrayDeque<UndirectedGraphNode>();
    	
    	if (node != null)
    		visited.put(node.label, new UndirectedGraphNode(node.label));
    	else
    		return null;
    	queue.offer(node);
    	while(queue.size() > 0){
    		UndirectedGraphNode n = queue.poll();
    		UndirectedGraphNode copy = visited.get(n.label);
    		for(UndirectedGraphNode neighbor:n.neighbors){
    			if(!visited.containsKey(neighbor.label)){ // if the current neighbor is not in queue
    				UndirectedGraphNode temp = new UndirectedGraphNode(neighbor.label);
    				queue.offer(neighbor);
    				visited.put(temp.label, temp);
    			}
    			copy.neighbors.add(visited.get(neighbor.label));
    		}
    	}
		return visited.get(node.label);
    }
    public static void main(String args[]){
    	graphClone graphclone = new graphClone();
    	UndirectedGraphNode root = new UndirectedGraphNode(1);
    	root.neighbors.add(new UndirectedGraphNode(10));
    	UndirectedGraphNode newRoot = graphclone.cloneGraph(root);
    	for(UndirectedGraphNode n:newRoot.neighbors){
    		System.out.println(n.label);
    	}
    }
}
