import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	// finds all line segments containing 4 or more points
	private List<LineSegment> ls;
    private Point[] points;
    private HashMap<String, String> table;
	public FastCollinearPoints(Point[] points){
		// finds all line segments containing 4 points
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
	   this.table = new HashMap<String, String>();
	   this.points = points;
	   ls = new ArrayList<LineSegment>();
	}
// the number of line segments
   public int numberOfSegments(){
	   return this.ls.size();
   }
   private void createSeg(List<Point> path){
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
	   LineSegment _ls = new LineSegment(min,max);
	   LineSegment _ls_rev = new LineSegment(max,min);
	   if ( !this.table.containsKey(_ls.toString()) && !this.table.containsKey(_ls_rev.toString())){
		   this.table.put(_ls.toString(), "");
		   this.table.put(_ls_rev.toString(), "");
		   this.ls.add(_ls);
	   }
   }
   // the line segments
   public LineSegment[] segments(){
	   for (int i = 0; i < this.points.length; i++){
		   Point[] copyPoints = new Point[this.points.length];
		   System.arraycopy(this.points, 0, copyPoints, 0, this.points.length);
		   java.util.Arrays.sort(copyPoints, this.points[i].slopeOrder());
		   int j = 1;
		   int count;
		   while (j < this.points.length){
			   count = 1;
			   if (this.points[i].compareTo(copyPoints[j-1]) != 0){
				   List<Point> l = new ArrayList<Point>();
				   l.add(this.points[i]);
				   l.add(copyPoints[j-1]);
				   while (j < points.length && points[i].slopeTo(copyPoints[j-1]) == points[i].slopeTo(copyPoints[j])){
					   if (this.points[i].compareTo(copyPoints[j]) != 0){
						   l.add(copyPoints[j]);
						   count ++;						   
					   }
					   j ++;
				   }
				   if(count >= 3){
					   this.createSeg(l);
					   continue;
				   }
				   j ++;
			   }
			   else{
				   j++;
			   }
		   }
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
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	}
}