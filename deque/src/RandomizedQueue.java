import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private class Node<Item> {
		Item item;
		Node next;
		Node prev;
   };
   private int size;
   private Node[] s;
   public RandomizedQueue()                 // construct an empty randomized queue
   {
	   this.size = -1;
	   this.s = new Node[2];
   }
   public boolean isEmpty()                 // is the queue empty?
   {
	   return this.size() <= 0;
   }
   public int size()                        // return the number of items on the queue
   {
	   return this.size + 1;
   }
   // double the current array size
   private Node[] resize(Node[] s,int new_size){
//	   System.out.printf("new size: %d\n",new_size);
	   Node[] new_s = new Node[new_size];
	   for(int i=0;i<this.size;i++)
		   new_s[i] = s[i];
	   return new_s;
   }
   public void enqueue(Item item)           // add the item
   {
	   if (item == null)
		   throw new java.lang.NullPointerException();
	   if(++this.size == s.length)
		   s = this.resize(s,s.length*2);
	   Node n = new Node();
	   this.s[this.size] = n;
	   n.item = item;
	   
   }
   public Item dequeue()                    // remove and return a random item
   {
	   if (this.isEmpty())
		   throw new java.util.NoSuchElementException();
	   int seed = this.generateSeed(this.size);
	   Item ret = (Item)this.s[seed].item;
	   this.s[seed].item = this.s[size].item;
	   this.s[size] = null;
	   size--;
	   if(size == this.s.length/4)
		   this.s = resize(s,s.length/2);
	   return ret;
   }
   public Item sample()                     // return (but do not remove) a random item
   {
	   if(this.isEmpty())
		   throw new java.util.NoSuchElementException();
	   int seed = this.generateSeed(this.size());
	   return (Item)this.s[seed].item;
   }
   private int generateSeed(int n){
	   return edu.princeton.cs.algs4.StdRandom.uniform(n);
   }
   private class myIterator<Item> implements Iterator<Item>{
	private int left;
	private int size;
	private Node[] s;
	public myIterator(Node[] s,int size){
		this.size = size;
		left = size;
		this.s = new Node[size]; 
		for(int i = 0; i < size; i++){
			Node n = new Node();
			n.item = s[i].item;
			this.s[i] = n; 
		}
		this.shuffle();
	}
	private void shuffle(){
		for(int i = 0; i < this.size; i++){
			int seed = generateSeed(this.size-i);
			Node tmp = this.s[i];
			this.s[i] = this.s[i+seed];
			this.s[i+seed] = tmp;
		}		
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return this.left>0;
	}

	@Override
	public Item next() {
		// TODO Auto-generated method stub
		if (this.hasNext())
			return (Item)this.s[--this.left].item;
		throw new java.util.NoSuchElementException();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
	   
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
	   return new myIterator(this.s,this.size());
   }
   public static void main(String[] args)   // unit testing
   {
	   RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
	   for(int i=0;i<64;i++)
		   rq.enqueue(i);
	   	   rq.size();
//	   Iterator<Integer> it = rq.iterator();
//	   while (it.hasNext()){
//		   System.out.printf("item is: %d\n", (int)it.next());
//	   }
   }
}