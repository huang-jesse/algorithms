import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PeekingIterator<E> implements Iterator<E> {
    private Iterator<E> iterator;
    private E nextElement;

    public PeekingIterator(Iterator<E> iterator) {
        this.iterator = iterator;
        nextElement = iterator.next();
    }
    
    public E peek() {
        return nextElement;
    }

    @Override
    public E next() {
        E ret = nextElement;
        nextElement = iterator.hasNext() ? iterator.next() : null;
        return ret;
    }
    
    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    public static void main(String[] args) {
		List<Integer> numList = new ArrayList<>();
		numList.add(1);
		numList.add(2);
        PeekingIterator sol = new PeekingIterator(numList.iterator());
        System.out.println("peek: " + sol.peek());
        System.out.println("next: " + sol.next());
        System.out.println("next: " + sol.next());
    }
}