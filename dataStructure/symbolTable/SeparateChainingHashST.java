import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
    private int size;
    private int hashSize;
    private SequentialSearchST<Key, Value>[] sts;

    private static final int DEFAULT_HASH_SIZE = 17;
    private static final int MIN_HASH_SIZE = 4;

    /**
     * Initializes an empty symbol table.
     */
    public SeparateChainingHashST() {
        this(DEFAULT_HASH_SIZE);
    }

    /**
     * Initializes an empty symbol table with {@code hashSize} chains.
     * @param hashSize the initial number of chains
     */
    public SeparateChainingHashST(int hashSize) {
        this.hashSize = hashSize;
        sts = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[hashSize];
        for (int i = 0; i < hashSize; i++)
            sts[i] = new SequentialSearchST<>();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % this.hashSize;
    }

    // private int hash(Key key) {
    //     int h = key.hashCode();
    //     h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
    //     return h & (this.hashSize-1);
    // }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return sts[i].get(key);
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (this.size >= 10*this.hashSize)
            resize(2*this.hashSize);

        int i = hash(key);
        if (!sts[i].contains(key))
            this.size++;
        sts[i].put(key, val);
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (sts[i].contains(key))
            this.size--;
        sts[i].delete(key);

        // halve table size if average length of list <= 2
        if (this.hashSize > MIN_HASH_SIZE && this.size <= 2*this.hashSize)
            resize(this.hashSize/2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (SequentialSearchST<Key, Value> st : sts) {
            for (Key key : st.keys()) {
                queue.offer(key);
            }
        }
        return queue;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < hashSize; i++) {
            for (Key key : sts[i].keys()) {
                temp.put(key, sts[i].get(key));
            }
        }
        this.hashSize  = temp.hashSize;
        this.size  = temp.size;
        this.sts = temp.sts;
    }

    /* Test Code Start */
    private static void testKeys(SeparateChainingHashST<Integer, Integer> hst) {
        System.out.print("keys: ");
        Iterator<Integer> iterator = hst.keys().iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("size: " + hst.size());
        System.out.println();
    }

    private static void deleteTest() {
        System.out.println();
        System.out.println("Delete test start: ");
        SeparateChainingHashST<Integer, Integer> hst = new SeparateChainingHashST<>();
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
        // randomTest();
        deleteTest();
    }
}
