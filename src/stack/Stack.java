package stack;

import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {

    private LinkedList<T> linkedList = new LinkedList<>();

    public Stack() {
    }

    public int getSize() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void push(T element) {
        linkedList.addLast(element);
    }

    public T pop() {
        return linkedList.removeLast();
    }

    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
