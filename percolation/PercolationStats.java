public class PercolationStats {
	private double[] thresholds;
	private int size;
	private int total;
   // perform T independent experiments on an N-by-N grid
   public PercolationStats(int N, int T) {
	   size = N;
	   total = T;
	   thresholds = new double[T];
	   for(int i=0;i<T;i++) {
		   Percolation pc = new Percolation(N);
		   while(pc.percolates() == false) {
			   int x = edu.princeton.cs.algs4.StdRandom.uniform(N);
			   int y = edu.princeton.cs.algs4.StdRandom.uniform(N);
			   pc.open(x+1, y+1);
		   }
		   thresholds[i] = ratio(pc,N);
	   }
	   java.util.Arrays.sort(thresholds);
//	   System.out.printf("mean%-20s=%.12f\n"," ",mean());
//	   System.out.printf("stddev%-20s=%.12f\n"," ",stddev());
//	   double low  = confidenceLo();
//	   double high = confidenceHi();
//	   System.out.printf("95 confidence interval: %.12f,%.12f\n",low,high);
   }
   // sample mean of percolation threshold
   public double mean() {
	   return edu.princeton.cs.algs4.StdStats.mean(thresholds);
   }
   // sample standard deviation of percolation threshold
   public double stddev() {
	   return edu.princeton.cs.algs4.StdStats.stddev(thresholds);
   }                    
   // low  endpoint of 95% confidence interval
   public double confidenceLo() {
	   Double low = total * 0.05;
	   double[] temp = java.util.Arrays.copyOfRange(thresholds, 0, thresholds.length - low.intValue());
	   return edu.princeton.cs.algs4.StdStats.mean(temp);
   }
   // high endpoint of 95% confidence interval
   public double confidenceHi() {
	   Double low = total * 0.05;
	   double[] temp = java.util.Arrays.copyOfRange(thresholds, low.intValue(), thresholds.length - 1);
	   return edu.princeton.cs.algs4.StdStats.mean(temp);	   
   }              
   private double ratio(Percolation pc, int N) {
	   int count = 0;
	   for(int i=0;i<N;i++) {
		   for(int j=0; j<N; j++) {			   
			   if (pc.isOpen(i+1, j+1) || pc.isFull(i+1, j+1)) {
				   count++;
			   }
		   }
	   }
	   double ret = 1.0*count / (N*N);
	   return ret;
   }
   public static void main(String[] args) {
	   if (args.length == 2) {
		   int n = Integer.parseInt(args[0]);
		   int t = Integer.parseInt(args[1]);
		   edu.princeton.cs.algs4.Stopwatch sw = new edu.princeton.cs.algs4.Stopwatch();
		   new PercolationStats(n, t);
		   System.out.printf("time eclapsed: %.2f\n", sw.elapsedTime());
	   }

   }    // test client (described below)
}