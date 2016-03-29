import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
public class SAP {
	private Digraph graph;
	private static final int INFINITY = Integer.MAX_VALUE;
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G){
	   this.graph = G;
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w){
	   ArrayList<Integer> alv = new ArrayList<Integer>();
	   ArrayList<Integer> alw = new ArrayList<Integer>();
	   alv.add(v);
	   alw.add(w);
	   return this.get_dist_ancestor(alv, alw, true);
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w){
	   ArrayList<Integer> alv = new ArrayList<Integer>();
	   ArrayList<Integer> alw = new ArrayList<Integer>();
	   alv.add(v);
	   alw.add(w);
	   return this.get_dist_ancestor(alv, alw, false);
   }
   // if retDist is set, then return shortest dist 
   // else return shortest ancestor
   private int get_dist_ancestor(Iterable<Integer> v, Iterable<Integer> w,boolean retDist){
	   int dist = INFINITY;
	   int synset = -1;
	   BreadthFirstDirectedPaths bfsA = new BreadthFirstDirectedPaths(this.graph,v);
	   BreadthFirstDirectedPaths bfsB = new BreadthFirstDirectedPaths(this.graph,w);
	   for(int i=0;i<this.graph.V();i++){
		   if(bfsA.distTo(i) != INFINITY && bfsB.distTo(i) != INFINITY){
			   int currentDist = bfsA.distTo(i) + bfsB.distTo(i);
			   if (currentDist < dist){
				   synset = i;
				   dist = currentDist;
			   }			   
		   }
	   }
	   if (dist == INFINITY){
		   return -1;
	   }
	   if (retDist){
		   return dist;
	   }
	   else{
		   return synset;
	   }
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w){
	   return this.get_dist_ancestor(v, w, true);
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
	   return this.get_dist_ancestor(v, w, false);
   }

   // do unit testing of this class
   public static void main(String[] args){
	   
   }
}
