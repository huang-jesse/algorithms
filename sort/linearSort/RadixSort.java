package linearSort;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * RadixSort
 */
public class RadixSort {

    /**
     * Radix Sort
     * default decimal(10)
     */
    public static <Item> void sort(Integer[] sortArr, int b) {
        int len = sortArr.length;
        int[] auxArr = new int[len];
        for (int i = 1; i <= b; i++) {
            // create auxArr
            for (int j = 0; j < len; j++) {
                auxArr[j] = getDigitNum(sortArr[j], i);
            }

            // sort
            countingSort(sortArr, auxArr, 10);
        }

    }

    private static int getDigitNum(int number, int digit) {
        int temp = number % (int)(Math.pow(10, digit));
        if (digit == 1) {
            return temp;
        } else {
            return temp / (int)(Math.pow(10, digit - 1));
        }
    }

    /**
     * Counting Sort
     * auxArr is the sort base of sortArr
     */
    public static <Item> void countingSort(Item[] sortArr, int[] auxArr, int k) {
        int len = sortArr.length;
        // buckup target array of the sort
        Item[] buckupArr = (Item[]) new Object[len];
        for (int i = 0; i < len; i++) {
            buckupArr[i] = sortArr[i];
        }

        // count k for auxArr
        int[] arrk = new int[k+1];
        for (int i = 0; i < len; i++) {
            arrk[auxArr[i]]++;
        }
        // accumulate k
        for (int i = 1; i <= k; i++) {
            arrk[i] += arrk[i-1];
        }

        // sort
        for (int i = len-1; i >= 0; i--) {
            int auxNum = auxArr[i];
            int sortedIndex = arrk[auxNum]-1;
            Item targetItem = buckupArr[i];
            sortArr[sortedIndex] = targetItem;

            arrk[auxNum]--;
        }
    }

    
    public static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) return false;
        }
        return true;
    }
    
    private static void show(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxDigits = 4;
        int maxNum = (int)Math.pow(10, maxDigits) - 1;
        int n = 100;
        List<Integer> aList = new Random().ints(1, maxNum).limit(n).boxed().collect(Collectors.toList());
        Integer[] a = aList.toArray(Integer[]::new);
        sort(a, maxDigits);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}