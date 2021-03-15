/* *****************************************************************************
 *  Name: Luoo Chen
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        n = 0;
    }

    private void resize(int capacity) {
        Item[] oldQueue = queue;
        queue = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            queue[i] = oldQueue[i];
        }
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == queue.length) {
            resize(n * 2);
        }
        queue[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        randomItem(n);
        Item removeItem = queue[--n];
        queue[n] = null;
        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        return removeItem;
    }

    private void randomItem(int index) {
        int randomNum = StdRandom.uniform(0, index);
        Item randomItem = queue[randomNum];
        queue[randomNum] = queue[index - 1];
        queue[index - 1] = randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        randomItem(n);
        return queue[n - 1];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {

        private int i;
        private Item[] iteratorQueue;

        private LinkedIterator() {
            if (isEmpty()) {
                i = n - 1;
                iteratorQueue = (Item[]) new Object[n];
                for (int j = 0; j < n; j++) {
                    iteratorQueue[j] = queue[j];
                }
                StdRandom.shuffle(iteratorQueue);
            }
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Stack underflow");
            return iteratorQueue[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }

}
