package dynamicArray;

import java.util.Iterator;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T[] array;
    private int size = 0, maxSize = 0;

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        if (size + 1 >= maxSize) {
            maxSize *= 2; // Double Max Size
            T[] _new = (T[]) new Object[maxSize];
            if (size >= 0) System.arraycopy(array, 0, _new, 0, size);
            array = _new;
        }
        array[size++] = element;
    }

    public Optional<T> get(int index) {
        if (index >= 0 && index < size) {
            return Optional.empty();
        }
        return Optional.of(array[index]);
    }

    public Optional<T> remove(int index) {
        if (index >= 0 && index < size) {
            return Optional.empty();
        }
        T value = array[index];
        T[] _new = (T[]) new Object[size - 1];
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (i == index) {
                j--;
            } else {
                _new[j] = array[i];
            }
        }
        array = _new;
        maxSize = --size;
        return Optional.of(value);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }
}
