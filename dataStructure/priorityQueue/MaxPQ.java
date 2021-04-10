import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * MaxPQ 基于数组实现的二叉堆优先队列
 */
public class MaxPQ<Key extends Comparable<Key>> {

    // 优先队列数组
    private Key[] pq;
    // 优先队列长度（位置从1开始，0不使用）
    private int N = 0;
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 创建一个优先队列
     */
    public MaxPQ() {
        pq = (Key[]) new Comparable[DEFAULT_CAPACITY];
    };

    /**
     * 创建一个初始容量为max的优先队列
     */
    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max];
    };

    /**
     * 用a[]中的元素创建一个优先队列
     */
    public MaxPQ(Key[] a) {
        int len = a.length;
        pq = (Key[]) new Comparable[len + 1];
        for (int i = 1; i < len + 1; i++) {
            pq[i] = a[i - 1];
        }
    };

    public void insert(Key v) {
        if (N == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++N] = v;
        // 上浮到合适位置
        swim(N);
    };

    public Key max() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        return pq[1];
    }

    public Key delMax() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        Key t = pq[1];
        // 将根节点置换到最后一个节点
        exch(1, N);
        // 删除最后一个节点（避免对象游离）
        pq[N--] = null;
        // 下沉节点到合适的位置
        sink(1);

        if (N > 0 && N < pq.length / 4) {
            resize(pq.length / 2);
        }
        return t;
    };

    public boolean isEmpty() {
        return N == 0;
    };

    public int size() {
        return N;
    }

    /**
     * 上浮
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j)) {
                // 已下沉到合适位置，当前节点大于等于两个子节点
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public static void main(String[] args) {
        int M = 5;
        MaxPQ<Integer> pq = new MaxPQ<Integer>(M + 1);
        for (int i = 0; i < 10; i++) {
            pq.insert(StdRandom.uniform(0, 100));
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        while (!pq.isEmpty())
            stack.push(pq.delMax());
        for (Integer t : stack)
            StdOut.println(t);
    }
}