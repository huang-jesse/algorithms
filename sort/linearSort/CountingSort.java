package linearSort;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Counting Sort
 */
public class CountingSort {

    /**
     * @description: Counting Sort
     * @param a target array
     * @param k the max num of a
     * @return {*}
     */
    public static void sort(Integer[] a, int k) {

        Integer[] auxB = new Integer[a.length];
        int[] auxC = new int[k+1];

        for (int i = 0; i < a.length; i++) {
            auxB[i] = a[i];
            auxC[a[i]] += 1;
        }
        for (int i = 1; i <= k; i++) {
            // now value of i that is the count number of less and equals than i
            auxC[i] += auxC[i-1];
        }

        for (int i = auxB.length-1; i >= 0; i--) {
            // the sorted index of auxB[i]
            Integer sortedIndex = auxC[auxB[i]] - 1;
            a[sortedIndex] = auxB[i];
            auxC[auxB[i]]--;
        }
    }

    private static void show(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> aList = new Random().ints(1, 100).limit(10).boxed().collect(Collectors.toList());
        Integer[] a = aList.toArray(Integer[]::new);
        int k = aList.stream().max(Integer::compareTo).get();
        sort(a, k);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}