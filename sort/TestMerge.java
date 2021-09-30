import java.util.Random;

public class TestMerge {
    private static Comparable[] aux;
    /**
     * Merge sort
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int start, int end) {
        if (start >= end) 
            return;
        int mid = start + ((end - start) >> 1);
        // sort left
        sort(a, start, mid);
        // sort right
        sort(a, mid+1, end);
        // when mid less then mid+1, the sub array is sorted
        if (less(a[mid], a[mid+1]))
            return;
        // merge sub array
        merge(a, start, mid, end);
    }

    private static void merge(Comparable[] a, int start, int mid, int end) {
        int left = start;
        int right = mid + 1;
        for (int i = start; i <= end; i++) {
            aux[i] = a[i];
        }
        for (int i = start; i <= end; i++) {
            if (left > mid) {
                a[i] = aux[right++];
            } else if (right > end) {
                a[i] = aux[left++];
            } else if (less(aux[left], aux[right])) {
                a[i] = aux[left++];
            } else {
                a[i] = aux[right++];
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
        Integer[] a = new Random().ints(1, 100).limit(10).boxed().toArray(Integer[]::new);
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}