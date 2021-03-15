import java.util.Random;

/**
 * MergeBUSort
 */
public class MergeBUSort {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int len = a.length;
        aux = new Comparable[len];
        for (int sz = 1; sz < len; sz = sz+sz) {
            for (int lo = 0; lo < len-sz; lo += sz+sz) {
                int mid = lo+sz-1;
                int hi = Math.min(lo+sz+sz-1, len-1);
                merge(a, lo, mid, hi);
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
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

    public static void main(String[] args) {
        Integer[] a = new Random().ints(1, 100).limit(6).boxed().toArray(Integer[]::new);
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}