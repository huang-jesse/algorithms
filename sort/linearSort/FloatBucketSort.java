package linearSort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * FloatBucketSort
 * input is uniformly distributed over a range [0,1)
 */
public class FloatBucketSort {

    public static void sort(float[] a) {
        // num of arr
        int n = a.length;
        int bucketNum = n;

        // create empty buckets
        LinkedList<Float>[] buckets = (LinkedList<Float>[])new LinkedList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new LinkedList<Float>();
        }
        // put array elements to different buckets
        for (int i = 0; i < n; i++) {
            // bucket index
            int index = (int)(a[i] * bucketNum);
            buckets[index].add(a[i]);
        }

        // sort individual buckets
        for (LinkedList<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        // concatenate all buckets to arr[]
        int i = 0;
        for (LinkedList<Float> bucket : buckets) {
            for (Float item : bucket) {
                a[i] = item;
                i++;
            }
        }
    }

    private static void show(float[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(float[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 100;
        float[] a = new float[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = (float)random.nextDouble();
        }
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}