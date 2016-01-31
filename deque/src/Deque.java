import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	private class Node<Item> {
		Item item;
		Node next;
		Node prev;
   };
   private Node head;
   private Node tail;
   private int count;
// construct an empty deque
   public Deque(){
	   count = 0;
   }
   // is the deque empty?
   public boolean isEmpty(){
	   return count == 0;
   }
   public int size()                        // return the number of items on the deque
   {
	   return count;
   }
   private boolean checkNull(Item item){
	   return item == null;
   }
   public void addFirst(Item item)          // add the item to the front
   {
	   if (this.checkNull(item))
		   throw new java.util.NoSuchElementException();
	   if (head == null) {
		   head = new Node();
		   head.item = item;
		   tail = head;
	   }
	   else {
		   head.next = new Node();
		   head.next.prev = head;
		   head = head.next;
		   head.item = item;
	   }
	   count += 1;
   }
   public void addLast(Item item)           // add the item to the end
   {
	   if (this.checkNull(item))
		   throw new java.util.NoSuchElementException();
	   if (tail == null){
		   tail = new Node();
		   tail.item = item;
		   head = tail;
	   }
	   else{
		   tail.prev = new Node();
		   tail.prev.next = tail;
		   tail = tail.prev;
		   tail.item = item;
	   }
	   count += 1;
   }
   public Item removeFirst()                // remove and return the item from the front
   {
	   if (count > 0){
		   Node temp = head;
		   head = head.prev;
		   if (head != null){
			   head.next = null;
		   }
		   if (count == 1){
			   tail = null;
		   }
		   count -= 1;
		   return (Item)temp.item;
	   }
	   else
		   throw new java.lang.UnsupportedOperationException();
   }
   public Item removeLast()                 // remove and return the item from the end
   {
	   if(count > 0){
		   Node temp = tail;
		   tail = tail.next;
		   if(tail != null) {
			   tail.prev = null;
		   }
		   if (count == 1) {
			   head = null;
		   }
		   count -= 1;
		   return (Item)temp.item;
	   }
	   else
		   throw new java.lang.UnsupportedOperationException();
   }
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
	   return new myIterator(this.head);
   }
   private class myIterator<Item> implements Iterator<Item>{
	private Node cursor;
	public myIterator(Node start){
		this.cursor = start;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(this.cursor != null)
			return true;
		return false;
	}

	@Override
	public Item next() {
		// TODO Auto-generated method stub
		if(this.hasNext()){
			Item ret = (Item)this.cursor.item;
			this.cursor = this.cursor.prev;
			return ret;
		}
		throw new java.util.NoSuchElementException();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new java.util.NoSuchElementException();
	}
	   
   }
   public static void main(String[] args)   // unit testing
   {
	   Deque<Integer> dq = new Deque<Integer>();
	   dq.addFirst(1);
	   dq.addLast(2);
	   System.out.printf("item is %d\n",dq.removeFirst());
	   System.out.printf("item is %d\n",dq.removeFirst());
	   dq.addLast(3);
	   System.out.printf("item is %d\n",dq.removeFirst());
	   System.out.printf("size is %d\n",dq.size());
	   dq.addLast(4);
	   System.out.printf("item is %d\n",dq.removeFirst());
	   System.out.printf("size is %d\n",dq.size());
	   dq.addFirst(100);
	   dq.addFirst(200);
	   dq.addFirst(300);
	   Iterator it = dq.iterator();
	   System.out.printf("item is %d\n",(int)it.next());
	   if (it.hasNext())
		   System.out.printf("item is %d\n",(int)it.next());
	   System.out.printf("item is %d\n",(int)it.next());
	   
   }
}