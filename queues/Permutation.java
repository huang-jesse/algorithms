/* *****************************************************************************
 *  Name: Luoo Chen
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // String fileName = args[1];
        // In in = new In(fileName);      // input file
        RandomizedQueue<String> deque = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String n = StdIn.readString();         // string
            deque.enqueue(n);
        }
        for (int i = 0; i < k; i++) {
            StdOut.print(deque.dequeue());
            StdOut.println();
        }
    }
}
