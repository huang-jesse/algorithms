/* *****************************************************************************
 *  Name:              Luoo Chen
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int boundary; // number of rows in the grid
    private WeightedQuickUnionUF wqUF;
    private boolean[] isOpenSites; // the status will be true if it is open site
    private int openSitesCount = 0; // number of open sites
    private int topVirtual;
    private int bottomVirtual;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be bigger than 0");
        boundary = n;
        int powN = (int) Math.pow(n, 2);
        topVirtual = powN;
        bottomVirtual = powN + 1;
        isOpenSites = new boolean[powN];
        wqUF = new WeightedQuickUnionUF(powN + 2);
    }

    private int xyTo1D(int row, int col) {
        validate(row, col);
        return ((row - 1) * boundary + col - 1);
    }

    // validate that row & col is validation
    private void validate(int row, int col) {
        if (row < 1 || row > boundary || col < 1 || col > boundary) {
            throw new IllegalArgumentException(
                    "the row and column indices are integers between 1 and n");
        }
    }

    // connect open neighbors
    private void connectOpenNeighbor(int row, int col) {
        int currentIndex = xyTo1D(row, col);
        // top
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            int topSite = xyTo1D(row - 1, col);
            if (!wqUF.connected(currentIndex, topSite)) wqUF.union(currentIndex, topSite);
        }
        // bottom
        if (row + 1 <= boundary && isOpen(row + 1, col)) {
            int bottomSite = xyTo1D(row + 1, col);
            if (!wqUF.connected(currentIndex, bottomSite)) wqUF.union(currentIndex, bottomSite);
        }
        // left
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            int leftSite = xyTo1D(row, col - 1);
            if (!wqUF.connected(currentIndex, leftSite)) wqUF.union(currentIndex, leftSite);
        }
        // right
        if (col + 1 <= boundary && isOpen(row, col + 1)) {
            int rightSite = xyTo1D(row, col + 1);
            if (!wqUF.connected(currentIndex, rightSite)) wqUF.union(currentIndex, rightSite);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int currentIndex = xyTo1D(row, col);
            isOpenSites[currentIndex] = true;
            openSitesCount++;
            // connect open neighbors
            connectOpenNeighbor(row, col);
            if (row == 1) {
                // virtual-top
                wqUF.union(currentIndex, topVirtual);
            }
            else if (row == boundary) {
                // virtual-bottom
                wqUF.union(currentIndex, bottomVirtual);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return isOpenSites[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return wqUF.connected(xyTo1D(row, col), topVirtual);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqUF.connected(topVirtual, bottomVirtual);
/*        boolean isPercolate = false;
        for (int i = 1; i <= boundary; ++i) {
            if (wqUF.connected(topVirtual, xyTo1D(boundary, i))) {
                isPercolate = true;
                break;
            }
        }
        return isPercolate;*/
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation perco = new Percolation(n);
        StdOut.println("Percolation was intial");
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (perco.isOpen(p, q)) continue;
            perco.open(p, q);
            StdOut.println(p + " " + q);
            StdOut.println("row:" + p + " col:" + q + " is Full:" + perco.isFull(p, q));
            StdOut.println("is percolation:" + perco.percolates());
            if (perco.percolates()) {
                StdOut.println("the number of open sites is " + perco.numberOfOpenSites());
            }
        }
    }
}
