package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] map;
    private int numOfOpen;
    private WeightedQuickUnionUF set;
    private int length;
    private int top;
    private int bottom;
   // private boolean percolation = false;
    private final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1} };


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        map = new int[N][N];
        length = N;
        top = N * N;
        bottom = top + 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = 0;
            }
        }
        numOfOpen = 0;
        set = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            set.union(top, xyto1(0, i));
            set.union(bottom, xyto1(N - 1, i));
        }
    }
    public void open(int row, int col)   {
        if (!valueCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (map[row][col] == 0) {
            map[row][col] = 1;
            numOfOpen++;
            openCheck(row, col);
        }

    }
    public boolean isOpen(int row, int col)  {
        if (!valueCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return map[row][col] == 1;
    }
    public boolean isFull(int row, int col) {
        if (!valueCheck(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return set.connected(top, xyto1(row, col)) && isOpen(row, col);
    } // is the site (row, col) full?
    public int numberOfOpenSites()  {
        return numOfOpen;
    }         // number of open sites
    public boolean percolates() {
        if (length == 1) {
            return isOpen(0, 0);
        }
        //return percolation;
        return set.connected(top, bottom);
    }             // does the system percolate?
    private int xyto1(int x, int y) {
        return x * length + y;
    }
    private void openCheck(int row, int col) {
        for (int[] ints : dir) {
            int rowCheck = row + ints[0];
            int colCheck = col + ints[1];
            if (!valueCheck(rowCheck, colCheck)) {
                continue;
            }
            if (isOpen(rowCheck, colCheck)) {
                set.union(xyto1(row, col), xyto1(rowCheck, colCheck));
            }
        }
    }
    private boolean valueCheck(int row, int col) {
        if (row < 0 || row >= length) {
            return false;
        } else {
            return col >= 0 && col < length;
        }
    }
    public static void main(String[] args) {

    }  // use for unit testing (not required)
}
