import java.util.ArrayList;
import java.util.HashMap;

class TrieNode{
	HashMap<Character,TrieNode> children;
	boolean isEnd;
	public TrieNode() {
		children = new HashMap<>();
	}
}
public class Trie {
	TrieNode root;
	public Trie() {
		root = new TrieNode();
	}
	boolean getWord(String word){
		return get(word, root, 0);
	}
	boolean get(String word,TrieNode root,int start){
		if(start == word.length()){
			return root.isEnd;
		}
		char c = word.charAt(start);
		if(!root.children.containsKey(c))
			return false;
		return get(word, root.children.get(c), start+1);
	}
	void addWord(String word){
		add(word,0,root);
	}
	void add(String word,int start,TrieNode root){
		int l = word.length();
		if(l == start){
			root.isEnd = true;
			return;
		}
		char c = word.charAt(start);
		if(!root.children.containsKey(c)){
			root.children.put(c,new TrieNode());				
		}
		TrieNode node = root.children.get(c);
		add(word, start+1, node);
	}
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.addWord("apple");
		trie.addWord("appla");
		System.out.println(trie.getWord("appla"));
		System.out.println(trie.getWord("apple"));
	}
}
