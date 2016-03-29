import java.util.ArrayList;
import java.util.HashMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
	
	private Digraph graph;
	private HashMap<Integer,String> mapR; // used to map id->noun mapping
	private HashMap<String,ArrayList<Integer>> map; // used to map noun->id mapping
	private SAP sapmethod;
   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms){
	   this.checkNull(synsets,hypernyms);
	   In in = new In(synsets);
	   String[] lines = in.readAllLines();
	   int l = lines.length;
	   this.graph = new Digraph(l);
	   this.sapmethod = new SAP(this.graph);
	   this.map = new HashMap<String,ArrayList<Integer>>();
	   this.mapR = new HashMap<Integer,String>();
	   for(int i=0;i<lines.length;i++){
		   String[] infos = lines[i].split(",");
		   int id = Integer.parseInt(infos[0]);
		   String[] words = infos[1].split(" ");
		   this.mapR.put(id, infos[1]);
		   for(String word:words){
			   if(!this.map.containsKey(word)){
				   ArrayList<Integer> a = new ArrayList<Integer>();
				   a.add(id);
				   this.map.put(word, a);
			   }
			   else{
				   this.map.get(word).add(id);
			   }
		   } 
	   }
	   in = new In(hypernyms);
	   lines = in.readAllLines();
	   for(String line:lines){
		   String[] infos = line.split(",");
		   int root = Integer.parseInt(infos[0]);
		   for(int i=1;i<infos.length;i++){
			   this.graph.addEdge(root,Integer.parseInt(infos[i]));
		   }
	   }
//	   System.out.println(this.graph.toString());
   }

   // returns all WordNet nouns
   public Iterable<String> nouns(){
	   ArrayList<String> la = new ArrayList<String>();
	   for(String key:this.map.keySet()){
		   la.add(key);
	   }
	   return la;
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word){
	   this.checkNull(word);
	   return this.map.containsKey(word);
   }
   // check if nounA and nounB exists
   private void checkExist(String nounA, String nounB){
	   this.checkNull(nounA,nounB);
	   if(!this.isNoun(nounA) || !this.isNoun(nounB)){
		   throw new java.lang.IllegalArgumentException();
	   }
   }
   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB){
	   this.checkExist(nounA, nounB);
	   ArrayList<Integer> idA = this.map.get(nounA);
	   ArrayList<Integer> idB = this.map.get(nounB);
	   return this.sapmethod.length(idA, idB);
   }
   private void checkNull(String... nouns){
	   for(String noun:nouns){
		   if(noun == null){
			   throw new java.lang.NullPointerException();
		   }
	   }
   }
   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB){
	   this.checkExist(nounA, nounB);
	   
	   ArrayList<Integer> idA = this.map.get(nounA);
	   ArrayList<Integer> idB = this.map.get(nounB);
	   int ancestor =  this.sapmethod.ancestor(idA, idB);
	   if (this.mapR.get(ancestor) == null) {
		   return "";
	   }
	   return this.mapR.get(ancestor);
   }

   // do unit testing of this class
   public static void main(String[] args){
	   String synset = "synsets3.txt";
	   String hyper = "hypernyms3InvalidTwoRoots.txt";
	   WordNet wn = new WordNet(synset,hyper);
	   String nounA = "inducement";
	   String nounB = "shout";
	   String ret = wn.sap(nounA, nounB);
	   int dist = wn.distance(nounA, nounB);
	   System.out.println(ret);
	   System.out.println(dist);
   }
}