import java.util.*;




public class wordladder2 {
	
	private int length;
	private String endWord;
	// create adj list for each vertex 
	// to find bfs path later
	// each edge here will be directed edge from s to t
	private HashMap<String, HashSet<String>> adj;
	private List<List<String>> ret;
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
    		char[] rev = new char[1];
    		ret = new ArrayList<List<String>>();
    		ArrayDeque<String> path = new ArrayDeque<String>(); // this will be used as stack
    		this.length = 0;
    		wordList.remove(endWord);
    		wordList.remove(beginWord);
        this.adj = new HashMap<String, HashSet<String>>();
        this.bfs(beginWord, endWord, wordList);
        if(this.length > 0){
        		this.endWord = endWord;
        		path.add(beginWord);
        		this.findPathDFS(beginWord,path);
        		
        }
        return ret;
    }
    private void findPathDFS(String from,ArrayDeque<String> path){
    		
//    		System.out.printf("%s,%d\n",from,path.size());

    		if(path.size() == this.length){
    			if(from == this.endWord){
    				ArrayList<String> al  = new ArrayList<String>();
    				for(Iterator<String> itr = path.iterator();itr.hasNext();){
    					al.add(itr.next());
    				}
    				this.ret.add(al);
    			}
    			return;
    		}
    		if(this.adj.containsKey(from))
			for(String neighbor:this.adj.get(from)){
				path.offerLast(neighbor);
				this.findPathDFS(neighbor, path);
				path.pollLast();
			}
    }
    private void bfs(String beginWord, String endWord, Set<String> wordList){
    	HashMap<String,Integer> s1 = new HashMap<String,Integer>();
    	HashMap<String,Integer> s2 = new HashMap<String,Integer>();
    	s1.put(beginWord, 1);
    	s2.put(endWord, 1);
    	HashSet<String> v1 = new HashSet<String>();
    	HashSet<String> v2 = new HashSet<String>();
    	v1.add(beginWord);
    	v2.add(endWord);
    	// we create two queues for two end BFS
    	Queue<String> q1 = new ArrayDeque<String>();
    	Queue<String> q2 = new ArrayDeque<String>();
    	q1.offer(beginWord);
    	q2.offer(endWord);
    	// 
    	String node;
    	boolean flag = true;
    	while(q1.size() > 0 || q2.size() > 0){
    		int currentLength = 0;
    		flag = true;
    		if (q1.size() < q2.size() && q1.size() >0){
    			node = q1.poll();
    		}
    		else if(q2.size()> 0){
    			flag = false;
    			node = q2.poll();
    		}
    		else{
    			break;
    		}
    		char[] copy = node.toCharArray();
    		for(int j=0; j < copy.length; j++){
    			char c = copy[j];
        		for(char ch='a'; ch<='z'; ch++ ){
        			copy[j] = ch;
        			String copyString = new String(copy);
        			if (copyString.equals(node))
        				continue;
        			if (wordList.contains(copyString) ){
        				if(flag && !v1.contains(copy)){
        					q1.add(copyString);
        					s1.put(copyString, s1.get(node) + 1);
        					if(this.length > 0 && s1.get(node) >= this.length)
        						return;
        					addAdj(node,copyString);
        					v1.add(copyString);
        				}
        				else if(!flag && !v2.contains(copyString)){
        					q2.add(copyString);
        					s2.put(copyString, s2.get(node) + 1);
        					if(this.length > 0 && s2.get(node) >= this.length)
        						return;
        					addAdj(copyString, node);
        					v2.add(copyString);
        				}
        			}
    				if (flag){
    					if(s2.containsKey(copyString)){
    						currentLength = s1.get(node) + s2.get(copyString);
    						addAdj(node, copyString);
    					}
    				}
    				else{
    					if(s1.containsKey(copyString)){
    						currentLength = s2.get(node) + s1.get(copyString);
    						addAdj(copyString, node);
    					}
    				}
        		}
        		copy[j] = c;
    		}
    		
    		if(this.length < currentLength && this.length > 0)
    			return;
    		if(currentLength > 0)
    			this.length = currentLength;
    		
    	}
    }
    // add the edge to adj list
    private void addAdj(String from,String to){
    		if(this.adj.containsKey(from)){
    			this.adj.get(from).add(to);
    		}
    		else{
    			HashSet<String> al = new HashSet<String>();
    			al.add(to);
    			this.adj.put(from, al);
    		}
    }
    public static void main(String args[]){
	    	wordladder2 wd = new wordladder2();
	    	String beginWord = "hit";
	    	String endWord = "cog";
	    	String[] wordListArr = {"hot","dot","dog","lot","log"};
	    	Set<String> wordList = new HashSet<String>();
	    	for(String word: wordListArr){
	    		wordList.add(word);
	    	}
	    	for(List<String> list:wd.findLadders(beginWord, endWord, wordList)){
	    		for(String word:list)
	    			System.out.printf("%s,",word);
	    		System.out.println();
	    	}
    }
}
