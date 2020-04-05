/* *****************************************************************************
 *  Name:              Luoo Chen
 *  Coursera User ID:  123456
 *  Last modified:     1/15/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be bigger than 0");
        double[] xt = new double[trials];
        for (int i = 0; i < trials; ++i) {
            xt[i] = threshold(n);
        }
        mean = StdStats.mean(xt);
        stddev = StdStats.stddev(xt);
        final double CONSTANT_1_DOT_96 = 1.96;
        confidenceLo = mean - (CONSTANT_1_DOT_96 * stddev / Math.sqrt(xt.length));
        confidenceHi = mean + (CONSTANT_1_DOT_96 * stddev / Math.sqrt(xt.length));
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    private double threshold(int n) {
        Percolation perco = new Percolation(n);
        while (!perco.percolates()) {
            int p = StdRandom.uniform(1, n + 1);
            int q = StdRandom.uniform(1, n + 1);
            if (perco.isOpen(p, q)) continue;
            perco.open(p, q);
        }
        return perco.numberOfOpenSites() / (double) (n * n);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("stopwatch time is       = " + stopwatch.elapsedTime());
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                               + percolationStats.confidenceHi() + "]");
    }

}
