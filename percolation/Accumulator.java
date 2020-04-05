import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Accumulator {
    private double m;
    private double s;
    private int N;

    private ArrayList<Double> xArr = new ArrayList<>();
    private double xm;
    private double xs = 0.0;

    public void addDataValue(double x) {
        xArr.add(x);
        N++;
        s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
        m = m + (x - m) / N;
        double xTotal = 0;
        for (double member : xArr) {
            xTotal += member;
        }
        xm = xTotal / N;
        xs = 0.0;
        for (double member : xArr) {
            double temp = member - xm;
            xs += Math.pow(temp, 2);
        }
    }

    public double n() {
        return N;
    }


    public double xs() {
        return xs;
    }

    public double mean() {
        return m;
    }

    public double xmean() {

        return xm;
    }

    public double var() {
        return s / (N - 1);
    }


    public double xvar() {
        return xs / (N - 1);
    }

    public double stddev() {
        return Math.sqrt(this.var());
    }

    public double xstddev() {
        return Math.sqrt(this.xvar());
    }

    public static void main(String[] args) {
        Accumulator accumulator = new Accumulator();
        StdOut.println("Accumulator was intial");
        while (!StdIn.isEmpty()) {
            double n = StdIn.readDouble();
            accumulator.addDataValue(n);
            StdOut.println("N: " + accumulator.n());
            StdOut.println("xs: " + accumulator.xs());

            StdOut.println("====start=====");
            StdOut.println("Mean: " + accumulator.mean());
            StdOut.println("Var: " + accumulator.var());
            StdOut.println("Stddev: " + accumulator.stddev());
            StdOut.println("====x test=====");
            StdOut.println("XMean: " + accumulator.xmean());
            StdOut.println("xVar: " + accumulator.xvar());
            StdOut.println("xStddev: " + accumulator.xstddev());
        }
    }
}
