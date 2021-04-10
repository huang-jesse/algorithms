import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * SequentialSearchST
 */
public class SequentialSearchST<Key, Value> {

    private int size;
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public void put(Key key, Value val) {
        if (val == null) {
            this.delete(key);
            return;
        }
        for (Node item = first; item != null; item = item.next) {
            if (item.key.equals(key)) {
                item.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        size++;
    }

    public Value get(Key key) {
        for (Node item = first; item != null; item = item.next) {
            if (item.key.equals(key)) {
                return item.val;
            }
        }
        return null;
    }

    public void delete(Key key) {
        Node item = first;
        if (item == null || item.key.equals(key)) {
            first = null;
            return;
        }
        while (item.next != null) {
            if (item.next.key.equals(key)) {
                item.next = item.next.next;
                return;
            }
            item = item.next;
        }
    }

    public boolean contains(Key key) {
        for (Node item = first; item != null; item = item.next) {
            if (item.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public Iterable<Key> keys() {
        return new STIterable();
    }

    private class STIterable implements Iterable<Key> {
        @Override
        public Iterator<Key> iterator() {
            return new STIterator();
        }
    }

    private class STIterator implements Iterator<Key> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public Key next() {
            if (this.current == null) {
                throw new NoSuchElementException();
            }
            Key key = this.current.key;
            this.current = this.current.next;
            return key;
        }
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st;
        st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty() && i < 5; i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}