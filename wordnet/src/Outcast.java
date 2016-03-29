import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class Outcast {
	private static final int NEGINFINITY = Integer.MIN_VALUE;
	private WordNet wn;
   public Outcast(WordNet wordnet)         // constructor takes a WordNet object
   {
	   this.wn = wordnet;
   }
   public String outcast(String[] nouns)   // given an array of WordNet nouns, return an outcast
   {
	String ret = nouns[0];
	int dist = NEGINFINITY;
	for(String noun:nouns){
		int d = 0;
		for (String _noun:nouns){
			int _d = wn.distance(noun, _noun);
//			StdOut.printf("%s %s %s: %d\n",noun,_noun,wn.sap(noun, _noun),_d);
			d += _d;
		}
		
		if (d > dist){
			dist = d;
			ret = noun;
		}
	}
	return ret;
   }
   public static void main(String[] args) {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
}