package week1;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	private double[] counts;
	private int T;

	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
        	throw new IllegalArgumentException("Illegal input.");
        }
        
        this.T = T;
		counts = new double[T];

		for (int i = 0; i < T; i++) {
			Percolation per = new Percolation(N);
			counts[i] = 0;
			int number = 0;
	    	while (!per.percolates()) {
	    		int m = (int) (Math.random() * N) + 1;
	    		int n = (int) (Math.random() * N) + 1;
	    		while (per.isOpen(m, n)) {
	    			m = (int) (Math.random() * N) + 1;
		    		n = (int) (Math.random() * N) + 1;
	    		}
	    		per.open(m, n);
	    		number ++;
            }
	    	double percentage = (number) / (double) (N * N);
	    	counts[i] = percentage;
	    	per = null;
	    	
	    }
	}
	

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(counts);
	}          
	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(counts);
	}
 
	
	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		double mean = mean();
		double stddev = stddev();
		double boundLow = mean - 1.96 * stddev / Math.sqrt(T);
		return boundLow;
	}
	
	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		double mean = mean();
		double stddev = stddev();
		double boundHigh = mean + 1.96 * stddev / Math.sqrt(T);
		return boundHigh;
	}

	// test client, described below
	public static void main(String[] args) {
		StdOut.println("please enter N and T:");
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats ps = new PercolationStats(N, T);

		StdOut.println("% java PercolationStats " + N + " " + T);
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
	} 
}
