import java.util.ArrayList;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
   private TreeSet<Point2D> pset;
   public PointSET()                               // construct an empty set of points 
   {	
	   this.pset = new TreeSet<Point2D>();
   }
   public boolean isEmpty()                      // is the set empty? 
   {
	   return this.pset.size() == 0;
   }
   public int size()                         // number of points in the set 
   {
	   return this.pset.size();
   }
   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
	   this.pset.add(p);
   }
   public boolean contains(Point2D p)            // does the set contain point p?
   {
	   return this.pset.contains(p);
   } 
   public void draw()                         // draw all points to standard draw
   {
	   for(Point2D p:this.pset){
		   p.draw();
	   }   
   } 
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle
   {
	   ArrayList<Point2D> ret = new ArrayList<Point2D>();
	   for(Point2D p:this.pset){
		   if(rect.contains(p)){
			   ret.add(p);
		   }
	   }
	   return ret;
	   
   } 
   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
   {
	   Point2D nearest = null;
	   double dist = Double.MAX_VALUE;
	   for(Point2D neighbor:this.pset){
		   if(neighbor != p){
			   double temp = p.distanceTo(neighbor);
			   if(temp < dist){
				   dist = temp;
				   nearest = neighbor;
			   }
		   }
	   }
	   return nearest;
   }

   public static void main(String[] args)                  // unit testing of the methods (optional)
   {
	   
   } 
}
