import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Queue<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node(Item item) {
            this.item = item;
        }
    }

    public void enqueue(Item item) {
        Node node = new Node(item);
        if (this.isEmpty()) {
            first = node;
            last = first;
        } else {
            last.next = node;
            last = last.next;
        }
        size++;
    }

    public Item dequeue() {
        if (this.isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size--;
        return item;
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
        Queue<String> q = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty())
                StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}