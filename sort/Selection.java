import java.util.Random;

public class Selection {
    public static void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;

        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
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
        Integer[] a = new Random(1).ints(0, 100).limit(10).boxed().toArray(Integer[]::new);
        // Integer[] a = null;
        sort(a);
        System.out.println("Is Sorted: " + isSorted(a));;
        show(a);
    }
}
