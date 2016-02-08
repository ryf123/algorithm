import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;
public class BruteCollinearPoints {
	// finds all line segments containing 4 points
   private List<LineSegment> ls;
   private Point[] points;
   public BruteCollinearPoints(Point[] points){
	   if (points.length == 0)
		   throw new java.lang.NullPointerException();
	   // throw exception if there are duplicate points
	   for(int i=0; i < points.length; i++){
		   if (points[i] == null){
			   throw new java.lang.NullPointerException();			   
		   }
		   for (int j=i+1; j< points.length; j++){
			   if (points[i].compareTo(points[j]) == 0){
				   throw new java.lang.IllegalArgumentException();
			   }
		   }
	   }
	   this.points = points;
	   ls = new ArrayList<LineSegment>();
   }    
   // the number of line segments
   public int numberOfSegments(){

	   return this.ls.size();
   }
   private void helperNumberofSeg(int left,List<Point> path,int start,double slope,Point refPoint){
	   if (left == 0){
		   this.ls.add(createSeg(path));
		   return;
	   }   
	   for (int i=start; i < this.points.length; i++){
		   if (refPoint.slopeTo(this.points[i]) == slope){
			   path.add(points[i]);
			   this.helperNumberofSeg(left-1,path,i+1,slope,refPoint);
			   path.remove(path.size()-1);
		   }
	   }
   }
   private LineSegment createSeg(List<Point> path){
	   Point max = path.get(0);
	   Point min = path.get(0);
	   for(Point p:path){
		   if (p.compareTo(max) > 0){
			   max = p;
		   }
		   if(p.compareTo(min) < 0){
			   min = p;
		   }
	   }
	   return new LineSegment(min,max);
	   
   }
   // the line segments
   public LineSegment[] segments(){
	   List<Point> path = new ArrayList<Point>();	   
	   for(int i=0; i<points.length; i++){
		   path.add(points[i]);
		   for (int j = i+1; j < points.length; j++){
			   double slope = this.points[i].slopeTo(this.points[j]);
			   path.add(points[j]);
			   this.helperNumberofSeg(2,path,j+1,slope,this.points[i]);
			   path.remove(path.size()-1);
		   }
		   path.remove(path.size()-1);
	   }
	   LineSegment[] lsa = new LineSegment[this.ls.size()];
	   int count = 0;
	   for(LineSegment tmp:this.ls){
		   lsa[count] = tmp;
		   count ++;
	   }
	   return lsa;
   }
   public static void main(String[] args) {

	    // read the N points from a file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    Point[] points = new Point[N];
	    for (int i = 0; i < N; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.show(0);
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();
	    
	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    System.out.printf("%d\n", collinear.numberOfSegments());
	}
}