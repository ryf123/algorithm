package leetcode;
import java.util.*;
public class Memcache {
    HashMap<Integer,Integer[]> cache;
    int invalid = 2147483647;
    public Memcache() {
        // Initialize your data structure here.
        cache = new HashMap<Integer,Integer[]>();
    }

    public int get(int curtTime, int key) {
        // Write your code here
        if(this.keyExists(curtTime,key)){
            return this.cache.get(key)[0];
        }
        return this.invalid;
    }

    public void set(int curtTime, int key, int value, int ttl) {
        // Write your code here
    		if(this.cache.containsKey(key)){
    			this.cache.get(key)[0] = value;
    			this.cache.get(key)[1] = ttl + curtTime -1;
    		}
    		else{
    			this.cache.put(key, new Integer[]{value,ttl+curtTime-1});
    		}
    }

    public void delete(int curtTime, int key) {
        // Write your code here
        if(this.cache.containsKey(key))
          this.cache.remove(key);
    }
    
    public int incr(int curtTime, int key, int delta) {
        // Write your code here
        if(this.keyExists(curtTime,key)){
          int newValue = this.cache.get(key)[0] + delta;
          this.cache.get(key)[0] = newValue;
          return newValue;
        }
        return this.invalid;
    }
    private boolean keyExists(int curtTime,int key){
      if (this.cache.containsKey(key)){
        if(this.cache.get(key)[1] < curtTime)
          return true;
        else
          this.delete(curtTime,key);
      }
      return false;
    }

    public int decr(int curtTime, int key, int delta) {
        // Write your code here
        return this.incr(curtTime,key,-delta);
    }
}
