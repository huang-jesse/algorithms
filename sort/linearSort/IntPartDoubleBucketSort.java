// package linearSort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * IntPartFloatBucketSort
 */
public class IntPartDoubleBucketSort {

    public static void sort(double[] a, int bucketNum) {
        double min = a[0];
        double max = a[0];
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] < min) {
                min = a[i];
            }
            if (a[i] > max) {
                max = a[i];
            }
        }
        // create empty buckets
        LinkedList<Double>[] buckets = (LinkedList<Double>[]) new LinkedList[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new LinkedList();
        }
        // the range is [a, b) [c, d) ... [m] and m = max
        double range = (max - min) / (bucketNum - 1);

        // put array elements to different buckets
        for (int i = 0; i < n; i++) {
            int index = (int) ((a[i] - min) / range);
            buckets[index].add(a[i]);
        }

        // sort individual buckets
        for (LinkedList<Double> bucket : buckets) {
            Collections.sort(bucket);
        }

        // concatenate all buckets to arr[]
        int i = 0;
        for (LinkedList<Double> bucket : buckets) {
            for (Double item : bucket) {
                a[i] = item;
                i++;
            }
        }
    }
    private static void show(double[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(double[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 200;
        double[] a = new Random().doubles(0, 100).limit(n).toArray();
        sort(a, 10);
        System.out.println("Is sorted: " + isSorted(a));
        // show(a);
    }
}