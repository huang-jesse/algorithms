import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * QuickSort
 */
public class QuickSort {

    private static int M = 0;

    public static void sort(Comparable[] a) {
        // shuffle array for randomly(cause QuickSort expect randomly situation)
        // shuffleArray(a);
        // sort
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo + M >= hi) {
            // InsertionSort.sort(a, lo, hi);
            return;
        }

        int pivot = partition(a, lo, hi);
        // sort pivot left
        sort(a, lo, pivot-1);
        // sort pivot right
        sort(a, pivot+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable pivot = a[lo];
        int i = lo;
        int j = hi + 1;
        while(true) {
            // move to right
            while(less(a[++i], pivot)) if (i >= hi) break;
            // move to left
            while(less(pivot, a[--j]));
            // if i metting j
            if (i >= j) break;
            // exchange
            // when i great or equals the pivot
            // and j less or equals the pivot
            exch(a, i, j);
        }
        exch(a, lo, j);
        // return the pivot index
        return j;
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
        Integer[] a = new Random().ints(1, 100).limit(200).boxed().toArray(Integer[]::new);
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}