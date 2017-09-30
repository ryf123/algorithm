import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
class LFUNode{
	int freq;
	// all the keys with this freq
	ArrayList<Integer> keys;
	LFUNode next;
	LFUNode prev;
	public LFUNode(int freq,int key) {
		this.freq = freq;
		keys = new ArrayList<Integer>();
		keys.add(key);
	}
}
public class LFU {
	HashMap<Integer, LFUNode>  map = new HashMap<>();
	LFUNode head;
	int capacity;
	int count = 0;
	public LFU(int capacity) {
		this.capacity = capacity;
	}
	boolean get(int key){
		if(!map.containsKey(key))
			return false;
		addFreq(key);
		return true;
	}
	void add(int key){
		if(map.containsKey(key))
			return;
		if(count+1 > capacity){
			evit();
		}
		addFreq(key);
	}
	void evit(){
		count--;
		if(head == null)
			return;
		int key = head.keys.get(0);
		map.remove(key);
		head.keys.remove(0);
		if(head.keys.size() == 0){
			if(head.next != null){
				head.next.prev = null;	
			}
			head = head.next;
		}
		
	}
	void addFreq(int key){
		// not in map
		if(!map.containsKey(key)){
			LFUNode node = null;
			// add new key
			count++;
			if(head == null){
				// first node
				node = new LFUNode(1,key);
				head = node;
			}
			else{
				// compare with head
				if(head.freq != 1){
					node = new LFUNode(1,key);
					node.next = head;
					head.prev = node;
					head = node;
				}
				else{
					// this first key is 1
					node = head;
					head.keys.add(key);
				}
			}
			map.put(key, node);
		}// does not contain key
		else{
			LFUNode node = map.get(key);
			// only one key
			if(node.keys.size() == 1){
				node.freq++;
			}
			else{
				int index = node.keys.indexOf(key);
				node.keys.remove(index);
				if(node.next == null){
					// add new node
					node.next = new LFUNode(node.freq+1,key);
					node.next.prev = node;
					map.put(key, node.next);
				}
				else{
					if(node.next.freq == node.freq+1){
						//directly add new node
						node.next.keys.add(key);
					}
					else{
						// add new node in the middle
						LFUNode newnode = new LFUNode(node.freq+1,key);
						// break the link
						LFUNode next = node.next;
						node.next = newnode;
						newnode.prev = node;
						next.prev = newnode;
						newnode.next = next;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		LFU lfu = new LFU(2);
		lfu.add(1);
		lfu.add(2);
		lfu.get(2);
		lfu.add(3);
		lfu.add(4);
		System.out.println(lfu.get(1));
		System.out.println(lfu.get(3));
		
	}
}
