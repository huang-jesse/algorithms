import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        public Node(Item item) {
            this.item = item;
        }
    }

    public void add(Item item) {
        Node oldFirst = this.first;
        this.first = new Node(item);
        this.first.next = oldFirst;
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;
        @Override
        public boolean hasNext() {
            return this.current != null;
        }
        @Override
        public Item next() {
            if (this.current == null) {
                throw new NoSuchElementException();
            }
            Item item = this.current.item;
            this.current = this.current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<Double>();
        while (!StdIn.isEmpty() && numbers.size < 10) {
            Double read = StdIn.readDouble();
            numbers.add(read);
        }
        int N = numbers.size();
        double sum = 0.0;
        for (double x : numbers)
            sum += x;
        double mean = sum / N;
        sum = 0.0;
        for (double x : numbers)
            sum += (x - mean) * (x - mean);
        double std = Math.sqrt(sum / (N - 1));
        StdOut.printf("Mean: %.2f\n", mean);
        StdOut.printf("Std dev: %.2f\n", std);
    }

}