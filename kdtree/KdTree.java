import java.awt.Point;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class KdTree {
	private static class Node {
	   private Point2D p;      // the point
	   private RectHV rect;    // the axis-aligned rectangle corresponding to this node
	   private Node lb;        // the left/bottom subtree
	   private Node rt;        // the right/top subtree
	}
	private Node root;
	private int size;
	public KdTree(){
		root = new Node();
		root.p = null;
		this.size = 0;
	}
	public boolean isEmpty()                      // is the set empty?
	{
		return root.p == null;
	} 
   public int size()                         // number of points in the set
   {
	   return this.size;
   } 
   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
	   if(this.root.p == null){
		   this.root.p = p;
		   this.root.rect = new RectHV(0,0,1,1);
	   }
	   else{
		   this.add(this.root, p, true);
	   }
   }
   private void add(Node node,Point2D p,Boolean compareX){
	   if((p.x() < node.p.x() && compareX) || (!compareX && p.y() < node.p.y())){
		   if(node.lb == null){
			   Node newnode = new Node();
			   newnode.p = p;
			   RectHV rect;
			   if(compareX){
				   rect = new RectHV(node.rect.xmin(),node.rect.ymin(),node.p.x(),node.rect.ymax());
			   }
			   else{
				   rect = new RectHV(node.rect.xmin(),node.rect.ymin(),node.rect.xmax(),node.p.y());
			   }
			   newnode.rect = rect;
			   node.lb = newnode;
		   }
		   else
			   this.add(node.lb, p, !compareX);
	   }
	   else{
		   if(node.rt == null){
			   Node newnode = new Node();
			   newnode.p = p;
			   RectHV rect;
			   if(compareX){
				   rect = new RectHV(node.p.x(),node.rect.ymin(),node.rect.xmax(),node.rect.ymax());
			   }
			   else{
				   rect = new RectHV(node.rect.xmin(),node.p.y(),node.rect.xmax(),node.rect.ymax());
			   }
			   newnode.rect = rect;
			   node.rt = newnode;
		   }
		   else 
			   this.add(node.rt, p, !compareX);
	   }  
   }
   public boolean contains(Point2D p)            // does the set contain point p?
   {
	   return this.contain(this.root, p,true);
   }
   private boolean contain(Node node,Point2D p,boolean compareX){
	   if(node == null) return false;
	   else if(node.p == p){
		   return true;
	   }
	   else if(compareX){
		   if(p.x() < node.p.x()){
			   return this.contain(node.lb, p, !compareX);
		   }
		   else{
			   return this.contain(node.rt, p, !compareX);
		   }
	   }
	   else{
		   if(p.y() < node.p.y()){
			   return this.contain(node.lb, p, !compareX);
		   }
		   else{
			   return this.contain(node.rt, p, !compareX);
		   }		   
	   }
   }
   public void draw()                         // draw all points to standard draw
   {
	  this.rec_draw(this.root,true); 
   }
   private void rec_draw(Node node,boolean red){
	   if(node != null){
		   if(red) StdDraw.setPenColor(StdDraw.RED);
		   else StdDraw.setPenColor(StdDraw.BLUE);
		   if(red)
			   StdDraw.line(node.p.x(),node.rect.ymin(), node.p.x(), node.rect.ymax());
		   else
			   StdDraw.line(node.rect.xmin(), node.p.y(),node.rect.xmax(),node.p.y());
		   StdDraw.setPenColor(StdDraw.BLACK);
		   StdDraw.setPenRadius(.01);
		   node.p.draw();
		   this.rec_draw(node.lb, !red);
		   this.rec_draw(node.rt, !red);
	   }
   }
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle
   {
	   ArrayList<Point2D> ret = new ArrayList<Point2D>();
	   this.search(this.root, rect,ret);
	   return ret;
   }
   private void search(Node node,RectHV rect,ArrayList<Point2D> ret){
	   if(node == null) return;
	   if(node.rect.intersects(rect)){
		   ret.add(node.p);
		   this.search(node.lb, rect, ret);
		   this.search(node.rt, rect, ret);
	   }
   }
   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
   {
	   if(p == null)
		   throw new java.lang.NullPointerException();
	   return this.neareast_dist(this.root, p, true);
   } 
   private Point2D neareast_dist(Node node,Point2D p,boolean compareX){
	   if (node == null) return null;
	   Point2D nearest = node.p;
	   if((p.x() < node.p.x() && compareX) || (!compareX && p.y() < node.p.y())){
		   Point2D temp = this.neareast_dist(node.lb, p, !compareX);
		   if (temp != null)
			   if (temp.distanceTo(p) < nearest.distanceTo(p)) 
				   nearest = temp;
		   if (node.rt != null)
			   if(node.rt.rect.distanceTo(p) < nearest.distanceTo(p))
				   temp = this.neareast_dist(node.rt, p, !compareX);
				   if (temp != null && temp.distanceTo(p) < nearest.distanceTo(p)) 
					   nearest = temp;
	   }
	   else{
		   Point2D temp =  this.neareast_dist(node.rt, p, !compareX);
		   if (temp != null)
			   if (temp.distanceTo(p) < nearest.distanceTo(p)) 
				   nearest = temp;
		   if(node.lb != null)
			   if(node.lb.rect.distanceTo(p) < nearest.distanceTo(p))
				   temp = this.neareast_dist(node.lb, p, !compareX);
		   		   if (temp != null && temp.distanceTo(p) < nearest.distanceTo(p)) 
		   			   nearest = temp;
	   }
	   return nearest;
   }
   public static void main(String[] args)                  // unit testing of the methods (optional)
   {
	   
   } 
}
