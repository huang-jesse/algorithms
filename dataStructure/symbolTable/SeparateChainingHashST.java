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

    public SeparateChainingHashST() {
        this(997);
    }
    public SeparateChainingHashST(int hashSize) {
        this.hashSize = hashSize;
        sts = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[hashSize];
        for (int i = 0; i < hashSize; i++) {
            sts[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % this.hashSize;
    }

    public Value get(Key key) {
        return sts[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        SequentialSearchST<Key, Value> st = sts[hash(key)];
        int oldSize = st.size();
        st.put(key, value);

        this.size = this.size + (st.size() - oldSize);
    }

    public void delete(Key key) {
        sts[hash(key)].delete(key);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (SequentialSearchST<Key, Value> st : sts) {
            Iterator<Key> iterator = st.keys().iterator();
            while (iterator.hasNext()) {
                queue.offer(iterator.next());
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

    /* Test Code Start */
    private static void testKeys(SeparateChainingHashST<Integer, Integer> hst) {
        System.out.print("keys: ");
        Iterator<Integer> iterator = hst.keys().iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    private static void randomTest() {
        System.out.println("Random test start: ");
        SeparateChainingHashST<Integer, Integer> hst = new SeparateChainingHashST<>();
        List<Integer> ints = new Random().ints(1, 100).limit(10).boxed().collect(Collectors.toList());
        System.out.println("ints: " + ints);
        ints.stream().forEach(i -> {
            hst.put(i, i);
        });
        testKeys(hst);
        System.out.println();
        System.out.println("get: ");
        ints.stream().forEach(i -> {
            System.out.println("key: " + i + " value: " + hst.get(i) + " ");
        });

        System.out.println("delete: ");
        ints.stream().forEach(i -> {
            hst.delete(i);
        });
        testKeys(hst);
    }
    /* Test Code End */

    public static void main(String[] args) {
        randomTest();
        randomTest();
    }
}
