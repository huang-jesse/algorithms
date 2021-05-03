import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

public class LinearProbingHashST<Key, Value> {
    private int size;
    // hashSize is the size of linear table that store all of keys(or values)
    private int hashSize;
    private Key[] keys;
    private Value[] values;

    private static final int DEFAULT_HASH_SIZE = 16;

    public LinearProbingHashST() {
        this(DEFAULT_HASH_SIZE);
    }

    public LinearProbingHashST(int hashSize) {
        this.hashSize = hashSize;
        keys = (Key[]) new Object[this.hashSize];
        values = (Value[]) new Object[this.hashSize];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % this.hashSize;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % this.hashSize) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new UnsupportedOperationException();
        if (this.size >= this.hashSize / 2)
            resize(2 * this.hashSize);

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % this.hashSize) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        this.size++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key))
            return;
        // remove target key&value
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i+1) % this.hashSize;
        keys[i] = null;
        values[i] = null;
        // redo all of the key&value that after target key
        i = (i+1) % this.hashSize;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            this.size--;
            put(keyToRedo, valueToRedo);
            i = (i+1) % this.hashSize;
        }
        this.size--;
        if (!isEmpty() && this.size <= this.hashSize / 8)
            resize(this.hashSize / 2);
    }

    public boolean contains(Key key) {
        if (isEmpty() || key == null) return false;
        for (int i = hash(key); keys[i] != null; i = (i+1) % this.hashSize) {
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    private void resize(int capability) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<>(capability);
        for (int i = 0; i < this.hashSize; i++) {
            if (keys[i] != null)
                st.put(keys[i], values[i]);
        }
        this.hashSize = capability;
        this.keys = st.keys;
        this.values = st.values;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (Key key : keys) {
            if (key != null)
                queue.offer(key);
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
    private static void testKeys(LinearProbingHashST<Integer, Integer> hst) {
        System.out.print("keys: ");
        Iterator<Integer> iterator = hst.keys().iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("size: " + hst.size());
    }

    private static void randomTest() {
        System.out.println("Random test start: ");
        LinearProbingHashST<Integer, Integer> hst = new LinearProbingHashST<>();
        List<Integer> ints = new Random().ints(1, 100).limit(10).boxed().collect(Collectors.toList());
        System.out.println("ints: " + ints);
        ints.stream().forEach(i -> {
            hst.put(i, i);
        });
        testKeys(hst);
        System.out.println("get: ");
        ints.stream().forEach(i -> {
            System.out.println("key: " + i + " value: " + hst.get(i) + " ");
        });
    }

    private static void deleteTest() {
        System.out.println();
        System.out.println("delete test start: ");
        LinearProbingHashST<Integer, Integer> hst = new LinearProbingHashST<>();
        List<Integer> ints = new Random().ints(1, 100).limit(10).boxed().collect(Collectors.toList());
        System.out.println("ints: " + ints);
        ints.stream().forEach(i -> {
            hst.put(i, i);
        });
        testKeys(hst);
        Collections.shuffle(ints);
        System.out.println("show delete: ");
        ints.stream().forEach(i -> {
            System.out.println("delete target: " + i);
            hst.delete(i);
            testKeys(hst);
        });
    }
    /* Test Code End */

    public static void main(String[] args) {
        randomTest();
        deleteTest();
    }
}
