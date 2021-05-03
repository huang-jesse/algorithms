import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

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
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
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
        this.size++;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        for (Node item = first; item != null; item = item.next) {
            if (item.key.equals(key)) {
                return item.val;
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
        if (first == null)
            return;
        if (first.key.equals(key)) {
            first = first.next;
            this.size--;
            return;
        }
        Node current = first;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next;
                this.size--;
                return;
            }
            current = current.next;
        }
    }

    // public void delete(Key key) {
    //     if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    //     first = delete(first, key);
    // }

    // // delete key in linked list beginning at Node x
    // // warning: function call stack too large if table is large
    // private Node delete(Node x, Key key) {
    //     if (x == null) return null;
    //     if (key.equals(x.key)) {
    //         this.size--;
    //         return x.next;
    //     }
    //     x.next = delete(x.next, key);
    //     return x;
    // }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
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

    /* Test Code Start */
    private static void testKeys(SequentialSearchST<Integer, Integer> hst) {
        System.out.print("keys: ");
        for (Integer key : hst.keys()) {
            System.out.print(key + " ");
        }
        System.out.println("size: " + hst.size());
    }

    private static void randomTest() {
        System.out.println("Random test start: ");
        SequentialSearchST<Integer, Integer> hst = new SequentialSearchST<>();
        List<Integer> ints = new Random().ints(1, 100).limit(10).boxed().collect(Collectors.toList());
        System.out.println("ints: " + ints);
        ints.stream().forEach(i -> {
            hst.put(i, i);
        });
        testKeys(hst);
    }

    private static void deleteTest() {
        System.out.println();
        System.out.println("Delete test start: ");
        SequentialSearchST<Integer, Integer> hst = new SequentialSearchST<>();
        List<Integer> ints = new Random().ints(1, 100).limit(10).boxed().collect(Collectors.toList());
        System.out.println("ints: " + ints);
        ints.stream().forEach(i -> {
            hst.put(i, i);
        });
        testKeys(hst);
        Collections.shuffle(ints);
        System.out.println("show delete: ");
        for (int i : ints) {
            System.out.println("delete target: " + i);
            hst.delete(i);
            testKeys(hst);
        }
    }
    /* Test Code End */

    public static void main(String[] args) {
        randomTest();
        deleteTest();
    }
}