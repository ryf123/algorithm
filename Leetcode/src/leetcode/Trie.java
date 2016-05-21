package leetcode;

import java.util.*;


class TrieNode {
    // Initialize your data structure here.
	Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	Boolean end;
	Set<String> words;
    public TrieNode() {
        this.end = false;
        this.words = new HashSet<String>();
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word,String origin_word) {
        dfs_insert(root, word, 0,origin_word);
    }
    private void dfs_insert(TrieNode node,String word,int start,String origin_word){
    		if(word.length() == start){
    			node.end = true;
    			return;
    		}
    		Character character = word.charAt(start);
    		if(!node.children.containsKey(character)){
    			TrieNode tn = new TrieNode();
    			node.children.put(character, tn);
    		}
    		node.words.add(origin_word);
    		this.dfs_insert(node.children.get(character), word, ++start,origin_word);
    }
    private ArrayList<String> dfs_search(TrieNode node,String word,int start,boolean prefix){
    		if(word.length() == start){
    			ArrayList<String> ret = new ArrayList<String>();
    			ret.addAll(node.words);
    			if(!prefix)
    				
    				return node.end?ret:new ArrayList<String>();
    			return ret;
    		}
    		if(!node.children.containsKey(word.charAt(start))){
    			return new ArrayList<String>();
    		}
    		return this.dfs_search(node.children.get(word.charAt(start)), word, ++start,prefix);
    }
    // Returns if the word is in the trie.
    public ArrayList<String> search(String word) {
        return this.dfs_search(this.root, word, 0,true);
    }
    public String serialize(TrieNode root) {
        // Write your code here
    		StringBuilder sb = new StringBuilder();
    		this.dfs_serialize(root, sb,' ');
    		return sb.toString();
    }
    private void dfs_serialize(TrieNode root,StringBuilder sb,Character val){
    		sb.append(val);
    		if(root.children.size() > 0){
    			sb.append("(");
	    		for(Character c:root.children.keySet()){
	    			this.dfs_serialize(root.children.get(c), sb,c);
	    		}
	    		sb.append(")");
    		}
    }

    public TrieNode deserialize(String data) {
        // Write your code here
    		TrieNode root = new TrieNode();
    		int p = 1;
    		Stack<TrieNode> stack = new Stack<TrieNode>();
    		stack.push(root);
    		int l = data.length(); 
    		while(p < l && stack.size() >0){
    			Character c = data.charAt(p);
    			if(c != '(' && c != ')'){
    				TrieNode t = new TrieNode();
    				stack.peek().children.put(c, t);
        			if(p+1 < l && data.charAt(p+1) == '('){
        				stack.push(t);
        			}
    			}
    			if(c == ')')
    				stack.pop();
    			p++;
    		}   		
    		return root;
    }
    public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("abc", "");
		t.insert("aaa", "");
		t.insert("abb", "");
		String string = t.serialize(t.root);
		TrieNode root = t.deserialize(string);
		System.out.println(string);
		System.out.printf("%s",t.serialize(root));
	}
    
}
