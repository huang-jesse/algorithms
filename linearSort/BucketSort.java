import java.util.Random;

/**
 * Bucket Sort
 */
public class BucketSort {

    public static void sort(int[] a) {

    }

    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = new Random().ints(1, 100).limit(10).toArray();
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}