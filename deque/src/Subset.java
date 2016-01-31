import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Subset {
   public static void main(String[] args){
	   int k = Integer.valueOf(args[0]);
	   RandomizedQueue<String> rq = new RandomizedQueue<String>();
//	   String[] strs = StdIn.readAllStrings();
	   
	   String[] strs = {"AA","BB","BB","BB","BB","BB","CC","CC"};
	   for(int i = 0; i< strs.length ; i++){
		   rq.enqueue(strs[i]);
	   }
	   Iterator<String> it = rq.iterator();
	   int size = Math.min(rq.size(), k);
	   for (int i = 0; i < size; i++){
		   System.out.printf("%s\n",it.next());
	   }
	   
   }
}