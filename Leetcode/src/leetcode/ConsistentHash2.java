package leetcode;
import java.util.*;


public class ConsistentHash2 {
    // @param n a positive integer
    // @param k a positive integer
    // @return a Solution object
	private static ConsistentHash2 ch2;
	private int k;
	private int n;
	private int[] machines;
    public static ConsistentHash2 create(int n, int k) {
    		if(ch2 == null){
    			return new ConsistentHash2(n,k);
    		}
    		return ConsistentHash2.ch2;
    }
    public ConsistentHash2(int n, int k) {
		this.k = k;
		this.n = n;
		this.machines = new int[n];
	}
    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        // Write your code here
    		ArrayList<Integer> ret = new ArrayList<Integer>();
    		int counter = 0;
    		while(counter < this.k){
    			int hashcode = (int)(Math.random() * this.n);
    			if(machines[hashcode] == 0){
    				ret.add(hashcode);
    				counter++;
    				machines[hashcode] = machine_id;
    			}
    		}
    		return ret;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        // Write your code here
    		for(int i=hashcode; i < n+hashcode; ++i){
    			if(this.machines[i%this.n] != 0){
    				return this.machines[i%this.n];
    			}
    		}
    		return -1;
    }
    public static void main(String[] args) {
    		ConsistentHash2 ch2 = ConsistentHash2.create(100, 3);
    		for(int hashcode:ch2.addMachine(1)){
    			System.out.println(ch2.getMachineIdByHashCode(hashcode));;
    		}
	}
}