import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * QuickSort
 */
public class Quick3Way {

    private static int M = 0;
    private static int[] pivots = new int[2];

    public static void sort(Comparable[] a) {
        // sort
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo + M >= hi) {
            return;
        }

        int lt = lo;
        int gt = hi;
        Comparable pivot = a[lo];
        int i = lo+1;
        while(i <= gt) {
            int comp = a[i].compareTo(pivot);
            if (comp < 0) exch(a, lt++, i++);
            else if (comp > 0) exch(a, i, gt--);
            else i++;
        }
        // now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        // return the pivots index

        // int[] pivots = partition(a, lo, hi);
        // sort pivot left
        sort(a, lo, lt-1);
        // sort pivot right
        sort(a, gt+1, hi);
    }

    private static int[] partition(Comparable[] a, int lo, int hi) {
        int lt = lo;
        int gt = hi;
        Comparable pivot = a[lo];
        int i = lo+1;
        while(i <= gt) {
            int comp = a[i].compareTo(pivot);
            if (comp < 0) exch(a, lt++, i++);
            else if (comp > 0) exch(a, i, gt--);
            else i++;
        }
        // now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        // return the pivots index
        pivots[0] = lt;
        pivots[1] = gt;
        return pivots;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
    // Implementing Fisher–Yates shuffle
    public static void shuffleArray(Comparable[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Comparable a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Random().ints(1, 100).limit(10).boxed().toArray(Integer[]::new);
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}