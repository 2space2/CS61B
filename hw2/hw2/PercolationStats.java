package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int[] result;
    private Percolation task;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        result = new int[N];
        for (int i = 0; i < T ;i++) {
            task = pf.make(N);
            int randomX = StdRandom.uniform(N);
            int randomY = StdRandom.uniform(N);
            while (!task.percolates()) {
                while (!task.isOpen(randomX, randomY)) {
                    randomX = StdRandom.uniform(N);
                    randomY = StdRandom.uniform(N);
                }
                task.open(randomX, randomY);
            }
            result[i] =  task.numberOfOpenSites();
        }
    }
    public double mean() {
        return StdStats.mean(result);
    }
    public double stddev() {
        return StdStats.stddev(result);
    }
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / result.length;
    }
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev()) / result.length;
    }
}


