import java.util.Random;

public class Shell {
    public static void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;

        int len = a.length;
        int h = 1;
        while (h < len / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable a[], int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        if (a == null) return;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        if (a == null) return true;

        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = new Random().ints(0, 1000).limit(100).boxed().toArray(Integer[]::new);
        // Integer[] a = null;
        sort(a);
        System.out.println("Is Sorted: " + isSorted(a));;
        show(a);
    }
}
