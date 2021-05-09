package priorityQueue;

import java.util.*;

@SuppressWarnings("unchecked")
public class BinaryHeap<T extends Comparable<T>> implements Iterable<T> {

    private final List<T> heapList;
    private int size;
    private int maxSize;

    public BinaryHeap(T[] array) {
        this.size = this.maxSize = array.length;
        this.heapList = new ArrayList<T>(this.maxSize);

        // copy array into heapList
        heapList.addAll(Arrays.asList(array).subList(0, size));

        // O(n) - bubble down each array element
        for (int i = Math.max(0, this.size / 2 - 1); i >= 0; --i) {
            bubbleDown(i);
        }
    }

    // update the position of an element given by its index
    private void bubbleUp(int index) {
        int parent = getParentOf(index);

        while (true) {
            if (index <= 0) break;
            if (heapList.get(index).compareTo(heapList.get(parent)) >= 0) break;

            swap(index, getParentOf(index));
            index = parent;
            parent = getParentOf(index);
        }
    }

    private void bubbleDown(int index) {
        while (true) {
            int firstChildIndex = getFirstChildOf(index);
            int secondChildIndex = getSecondChildOf(index);

            int smallestChildIndex = firstChildIndex;

            if (smallestChildIndex >= size && secondChildIndex >= size) break;

            T smallestChild = heapList.get(firstChildIndex);

            if (secondChildIndex < size && smallestChild.compareTo(heapList.get(secondChildIndex)) >= 0) {
                smallestChildIndex = secondChildIndex;
                smallestChild = heapList.get(secondChildIndex);
            }

            if (smallestChildIndex >= size || smallestChild.compareTo(heapList.get(index)) >= 0) {
                break;
            }

            swap(index, smallestChildIndex);
            index = smallestChildIndex;
        }
    }

    private void swap(int i, int j) {
        T elementI = heapList.get(i);
        T elementJ = heapList.get(j);
        heapList.set(i, elementJ);
        heapList.set(j, elementI);
    }

    private int getParentOf(int index) {
        if (index <= 0) {
            // root
            return 0;
        }
        if (index % 2 == 0) {
            // right child
            return (index - 2) / 2;
        }
        // left child
        return (index - 1) / 2;
    }

    private int getFirstChildOf(int index) {
        return index * 2 + 1;
    }

    private int getSecondChildOf(int index) {
        return index * 2 + 2;
    }

    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        if (size < maxSize) {
            // Set element to the last position of the heapList to insure consistency
            // [data, data, data, data, null, data] would not be allowed
            heapList.set(size, element);
        } else {
            // Add to the end of the List
            heapList.add(element);
            // Update maxSize
            maxSize++;
        }

        // bubbleUp to its proper position
        bubbleUp(size);
        size++;
    }

    // O(1) - lookup the top priority element in constant speed
    public Optional<T> getTopPriority() {
        if (isEmpty()) return Optional.empty();

        return Optional.of(heapList.get(0));
    }

    public Optional<T> pop() {
        if (isEmpty()) return Optional.empty();

        Optional<T> returnElement = getTopPriority();
        size--;
        swap(0, size);
        heapList.set(size, null);
        bubbleDown(0);

        return returnElement;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return heapList.iterator();
    }
}
