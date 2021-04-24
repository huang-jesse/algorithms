import java.util.Random;

/**
 * 基于二叉堆的下沉堆排序
 */
public class Heap {
    public static void sort(Comparable[] a) {
        // 二叉堆，堆尾
        int last = a.length;
        // 堆构造（最大值为根节点）
        for (int k = last/2; k >= 1; k--) {
            sink(a, k, last);
        }
        // 下沉排序
        while (last > 1) {
            // 交换堆头和堆尾，不断将堆中最大的值放到堆尾（这部分已排序）
            exch(a, 1, last);
            // 不断向前缩小堆的大小，重新修复交换后的堆
            sink(a, 1, --last);
        }
    }

    /**
     * 如果父节点k的值小于子节点(2*k, 2*k+1)，则下沉父节点。接着令k等于子节点，继续下沉操作。直到2*k>最后的元素last时结束循环
     * @param a 排序数组
     * @param k 父节点
     * @param last 最后的元素last
     */
    private static void sink(Comparable[] a, int k, int last) {
        while (2*k <= last) {
            int j = 2*k;
            if (j < last && less(a, j, j+1)) j++;
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        // 堆排序从1开始计数，因此i,j通过减1获取真实值
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // 堆排序从1开始计数，因此i,j通过减1获取真实值
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
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
        Integer[] a = new Random().ints(1, 100).limit(100).boxed().toArray(Integer[]::new);
        sort(a);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}
