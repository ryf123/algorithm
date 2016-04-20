import java.util.*;
public class wordladder2 {
	
	private int length;
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	wordList.remove(endWord);
    	wordList.remove(beginWord);
        this.length = 0;
        this.bfs(beginWord, endWord, wordList);
        if(this.length > 0){
        	return this.length;
        }
        return 0;
    }
    
    private void bfs(String beginWord, String endWord, Set<String> wordList){
    	HashMap<String,Integer> s1 = new HashMap<String,Integer>();
    	HashMap<String,Integer> s2 = new HashMap<String,Integer>();
    	s1.put(beginWord, 1);
    	s2.put(endWord, 1);

    	HashMap<String,String> path = new HashMap<String,String>(); // to store the mapping, how the word is transformed
    	
    	// we create two queues for two end BFS
    	Queue<String> q1 = new ArrayDeque<String>();
    	Queue<String> q2 = new ArrayDeque<String>();
    	q1.offer(beginWord);
    	q2.offer(endWord);
    	// 
    	String node;
    	boolean flag = true;
    	while(q1.size() > 0 || q2.size() > 0){
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
        				path.put(copyString, node);
        				wordList.remove(copyString);
        				if(flag){
        					q1.add(copyString);
        					s1.put(copyString, s1.get(node) + 1);        					
        				}
        				else{
        					q2.add(copyString);
        					s2.put(copyString, s2.get(node) + 1);
        				}
        			}
    				if (flag){
    					if(s2.containsKey(copyString)){
    						this.length = s1.get(node) + s2.get(copyString);
    						return;
    					}
//    					else{
//    						q1.add(copyString);
//    					}
//    					s1.put(copyString, s1.get(node) + 1);
    				}
    				else{
    					if(s1.containsKey(copyString)){
    						this.length = s2.get(node) + s1.get(copyString);
    						return;
    					}
//    					else{
//    						q2.add(copyString);
//    					}
//    					s2.put(copyString, s2.get(node) + 1);
    				}
        		}
        		copy[j] = c;
    		}
    		
    	}
    }
    public static void main(String args[]){
    	wordladder2 wd = new wordladder2();
    	String beginWord = "hit";
    	String endWord = "cog";
    	String[] wordListArr = {""};
    	Set<String> wordList = new HashSet<String>();
    	for(String word: wordListArr){
    		wordList.add(word);
    	}
    	System.out.printf("ladderLength: %d",wd.ladderLength(beginWord, endWord, wordList));
    }
}
