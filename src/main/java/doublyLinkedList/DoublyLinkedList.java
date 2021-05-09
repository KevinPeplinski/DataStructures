package doublyLinkedList;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node head = null, tail = null;

    private class Node {
        private final T value;
        private Node predecessor, successor;

        public Node(T value, Node predecessor, Node successor) {
            this.value = value;
            this.predecessor = predecessor;
            this.successor = successor;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void add(T element) {
        addTail(element);
    }

    public void addHead(T element) {
        if (isEmpty()) {
            head = tail = new Node(element, null, null);
        } else {
            head.predecessor = new Node(element, null, head);
            head = head.predecessor;
        }
        size++;
    }

    public void addTail(T element) {
        if (isEmpty()) {
            head = tail = new Node(element, null, null);
        } else {
            tail.successor = new Node(element, tail, null);
            tail = tail.successor;
        }
        size++;
    }

    public T getHead() {
        return head.value;
    }

    public T getTail() {
        return tail.value;
    }

    public T removeHead() {
        T value = head.value;
        head = head.successor;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.predecessor = null;
        }

        return value;
    }

    public T removeTail() {
        T value = tail.value;
        tail = tail.predecessor;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.successor = null;
        }

        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node travers = head;

            @Override
            public boolean hasNext() {
                return travers != null;
            }

            @Override
            public T next() {
                T value = travers.value;
                travers = travers.successor;
                return value;
            }
        };
    }
}
