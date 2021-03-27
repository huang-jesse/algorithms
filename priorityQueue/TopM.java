import java.util.ArrayDeque;
import java.util.Deque;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TopM {
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