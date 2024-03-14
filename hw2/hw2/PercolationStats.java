package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] result;
    private Percolation task;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        result = new double[T];
        int[] op = new int[N * N];
        for (int i = 0; i < op.length; i++) {
            op[i] = i;
        }
        for (int i = 0; i < T; i++) {
            task = pf.make(N);
            StdRandom.shuffle(op);
            int[] xy = new int[2];
            int j = 0;
            while (!task.percolates()) {
                numToXY(op[j], N, xy);
                task.open(xy[0], xy[1]);
                j++;
            }
            result[i] =  (double) task.numberOfOpenSites() / (N * N);
        }
    }
    public double mean() {
        return StdStats.mean(result);
    }
    public double stddev() {
        return StdStats.stddev(result);
    }
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(result.length);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev()) / Math.sqrt(result.length);
    }
    private void numToXY(int n, int length, int[] xy) {
        xy[0] = n / length;
        xy[1] = n % length;
    }
    /*
    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats t = new PercolationStats(20, 1000, pf);
        System.out.println(t.mean());
        System.out.println(t.stddev());
        System.out.println(t.confidenceLow());
        System.out.println(t.confidenceHigh());
    }*/
}


