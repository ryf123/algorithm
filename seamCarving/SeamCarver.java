import java.awt.Color;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
   private double[][] energies;
   private int w;
   private int h;
   int[][] colors;
   public SeamCarver(Picture picture)                // create a seam carver object based on the given picture
   {
	   this.w = picture.width();
	   this.h = picture.height();
	   this.colors = new int[w][h];
	   for(int i=0; i < h; i++){
		   for(int j=0; j < w; j++){
			   this.colors[j][i] = picture.get(j, i).getRGB();
		   }
	   }
	   this.energies = new double[w][h];
	   for(int y=0; y < h; y++){
		   for(int x=0; x < w; x++){
			   if(x == 0 || x == this.width()-1 || y == 0 || y == this.height() - 1)
				   this.energies[x][y] = 1000;
			   else
				   this.energies[x][y]  = Math.sqrt(calDelta(x-1,y,x+1,y) + calDelta(x,y-1,x,y+1));
		   }
	   }

   }
   public Picture picture()                          // current picture
   {
	   Picture p = new Picture(this.w,this.h);
	   for(int i=0; i< this.w; i++){
		   for(int j=0; j < this.h; j++){
			   Color color = new Color(this.colors[i][j]);
			   p.set(i, j, color);
		   }
	   }
	   return p;
   }
   public int width()                            // width of current picture
   {
	   return this.w;
   }
   public int height()                           // height of current picture
   {
	   return this.h;
   }
   public double energy(int x, int y)               // energy of pixel at column x and row y
   {
	   if(x == 0 || x == this.width()-1 || y == 0 || y == this.height() - 1)
		   return 1000;
	   else
		   return Math.sqrt(calDelta(x-1,y,x+1,y) + calDelta(x,y-1,x,y+1));
   }
   private double calDelta(int x1, int y1, int x2, int y2){
	   
	   Color c1 = new Color(this.colors[x1][y1]);
	   Color c2 = new Color(this.colors[x2][y2]);
	   return Math.pow(c1.getRed() - c2.getRed(), 2) + Math.pow(c1.getBlue() - c2.getBlue(), 2) + Math.pow(c1.getGreen() - c2.getGreen(), 2);
   }
   public int[] findHorizontalSeam()               // sequence of indices for horizontal seam
   {
	   
	   this.transpose();
	   int[] seam = this.findVerticalSeam();
	   this.transpose();
	   return seam;
   }
   
   private void findPathHorizon(int[][] mark,int i, int j,int[] path){
	   path[j] = i;
	   if (j > 0){
//		   System.out.printf("j: %d, i: %d:mark: %d\n",j,i,mark[i][j]);
		   this.findPathHorizon(mark, mark[i][j], j-1, path);
	   }
   }
   
   public int[] findVerticalSeam()                 // sequence of indices for vertical seam
   {
	   System.out.println("remove vertical\n");
	   int w = this.width();
	   int h = this.height();
//	   double[][] energies = this.getEnergyArray();
	   double[][] distTo = new double[h][w];
	   
	   // initialize all distTo to be maximum
	   for(int i = 1; i < h; i++){
		   for(int j = 0; j< w; j++){
			   distTo[i][j] = Double.MAX_VALUE;
		   }
	   }
	   
	   // relax all vertex row by row column by column
	   int[][] mark = new int[h][w];
	   for(int i = 0; i < h-1; i++){
		   for(int j = 0; j< w; j++){
			   if(distTo[i+1][j] > distTo[i][j] + this.energy(j,i+1)){
				   distTo[i+1][j] = distTo[i][j] + this.energy(j,i+1);
				   mark[i+1][j] = j; 
			   }
			   if(j+1 < w && distTo[i+1][j+1] > distTo[i][j] + this.energy(j+1,i+1)){
				   distTo[i+1][j+1] = distTo[i][j] + this.energy(j+1,i+1);
				   mark[i+1][j+1] = j;
			   }
			   if(j-1 >= 0 && distTo[i+1][j-1] > distTo[i][j] + this.energy(j-1,i+1)){
				   distTo[i+1][j-1] = distTo[i][j] + this.energy(j-1,i+1);
				   mark[i+1][j-1] = j;
			   }
//			   System.out.printf("i: %d, j: %d: %f , mark: %d\n",i,j,distTo[i][j],mark[i][j]);
		   }
		   
	   }
	   
	   double min = Double.MAX_VALUE;
	   int index = 0;
	   int[] path = new int [h];
	   for (int x = 0; x < w; x++){
		   if (distTo[h-1][x] < min){
			   min = distTo[h-1][x];
			   index = x;
		   }
	   }
	   this.findPathVertical(mark, h-1, index, path);
	   return path;
   }
   
   private void findPathVertical(int[][] mark,int i, int j,int[] path){
	   path[i] = j;
	   if (i > 0){   
		   this.findPathVertical(mark, i-1, mark[i][j], path);
	   }
   }
   private void transpose(){

	   int[][] colors = new int[this.height()][this.width()];
	   for(int y=0; y < h; y++){
		   for(int x=0; x < w; x++){
				   colors[y][x] = this.colors[x][y];
		   }
	   }
	   int temp = this.width();
	   this.w = this.h;
	   this.h = temp;
	   this.colors = colors;
   }
   public void removeHorizontalSeam(int[] seam)   // remove horizontal seam from current picture
   {
	   System.out.println("remove horizon\n");
	   this.transpose();   
	   this.removeVerticalSeam(seam);
	   this.transpose();
   }
   public void removeVerticalSeam(int[] seam)     // remove vertical seam from current picture
   {
	   int l = seam.length;
	   System.out.printf("w: %d h:%d\n\n", this.w,this.h);
	   for(int i = 0; i < l; i++){
		   int pos = seam[i];
		   for (int j = pos; j< this.width() -1 ; j++){
			   this.colors[j][i] = this.colors[j+1][i];
		   }
//		   int[] rowColor = this.colors[pos];
//		   System.arraycopy(rowColor,i+1, rowColor, i,this.height()-i-1);
	   }
	   this.w -= 1;
   }
   
   public static void main(String[] args){
	   
   }
}