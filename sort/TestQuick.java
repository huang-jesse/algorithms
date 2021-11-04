import java.util.Random;

public class TestQuick {
    /**
     * Quick sort
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int start, int end) {
        if (start >= end)
            return;
        int[] pivots = partition(a, start, end);
        // sort left
        sort(a, start, pivots[0]-1);
        // sort right
        sort(a, pivots[1]+1, end);
    }

    private static int[] partition(Comparable[] a, int start, int end) {
        Comparable pivot = a[start];
        int lt = start;
        int gt = end;
        int i = start+1;
        while (i <= gt) {
            int comp = a[i].compareTo(pivot);
            if (comp < 0) {
                exch(a, i, lt);
                i++;
                lt++;
            } else if (comp > 0) {
                exch(a, i, gt);
                gt--;
            } else {
                // equals
                i++;
            }
        }
        return new int[]{lt, gt};
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
        Integer[] a = new Random().ints(1, 100).limit(10).boxed().toArray(Integer[]::new);
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}