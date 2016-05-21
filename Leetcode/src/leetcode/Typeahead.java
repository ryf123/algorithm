package leetcode;
import java.util.*;
public class Typeahead {
    // @param dict A dictionary of words dict
    Trie t = new Trie();
    public Typeahead(Set<String> dict) {
        // do initialize if necessary
        for(String word:dict){
            for(int i=0; i < word.length(); ++i){
            		System.out.printf("%s\n", word.substring(i,word.length()));
                t.insert(word.substring(i,word.length()),word);
            }
        }
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        // Write your code here
        return this.t.search(str);
    }
    public static void main(String[] args) {
    		Set<String> dict = new HashSet<String>();
    		dict.add("Run Code");
    		dict.add("Lint Code");
    		dict.add("Problem");
    		Typeahead typeahead = new Typeahead(dict);
    		for(String ret:typeahead.search("_")){
    			System.out.println(ret);
    		}
	}
}
