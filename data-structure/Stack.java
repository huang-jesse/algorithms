import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    private class Node {
        Item item;
        Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    public void push(Item item) {
        Node oldFirst = this.first;
        first = new Node(item);
        this.first.next = oldFirst;
        this.size++;
    }

    public Item pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = this.first.item;
        this.first = this.first.next;
        this.size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
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
        Stack<String> s = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }

}