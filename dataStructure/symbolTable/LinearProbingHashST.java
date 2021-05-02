import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

public class LinearProbingHashST<Key, Value> {
    private int size;
    // hashSize is the size of linear table that store all of keys(or values)
    private int hashSize = 16;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[this.hashSize];
        values = (Value[]) new Object[this.hashSize];
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
        for (int i = hash(key); keys[i] != null; i = (i+1) % hashSize) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public boolean contains(Key key) {
        if (isEmpty() || key == null) return false;
        for (int i = hash(key); keys[i] != null; i = (i+1) % hashSize) {
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new UnsupportedOperationException();
        if (size >= hashSize/2)
            resize(2*hashSize);

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % hashSize) {
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
        if (!contains(key))
            throw new NoSuchElementException();
        // remove target key&value
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i+1) % hashSize;
        keys[i] = null;
        values[i] = null;
        // redo all of the key&value that after target key
        i = (i+1) % hashSize;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value ValueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            this.size--;
            put(keyToRedo, ValueToRedo);
            i = (i+1) % hashSize;
        }
        this.size--;
        if (!isEmpty() && this.size <= this.hashSize/8)
            resize(hashSize/2);
    }

    public void resize(int capability) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<>(capability);
        for (int i = 0; i < this.size; i++) {
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
