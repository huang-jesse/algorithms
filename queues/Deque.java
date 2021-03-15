/* *****************************************************************************
 *  Name: Luoo Chen
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;

    private Node last;

    private int n;

    // helper doubly linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node pre;

        private Node(Item item, Node next, Node pre) {
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node(item, oldfirst, null);
        if (oldfirst != null) oldfirst.pre = first;
        if (isEmpty()) last = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node(item, null, oldlast);
        if (oldlast != null) oldlast.next = last;
        if (isEmpty()) first = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        if (first != null) first.pre = null;
        n--;
        if (isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        last = last.pre;
        if (last != null) last.next = null;
        n--;
        if (isEmpty()) first = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit tests the {@code Deque} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                deque.addFirst(item);
            }
            else if (!deque.isEmpty()) {
                for (String s : deque) {
                    StdOut.print(s);
                }
                StdOut.print(deque.removeLast() + " ");
                StdOut.print("isEmpty: " + deque.isEmpty());
            }
        }
        StdOut.println("(" + deque.size() + " left on deque)");
    }
}
