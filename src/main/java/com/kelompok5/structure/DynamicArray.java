
package com.kelompok5.structure;

public class DynamicArray<T> {

    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public DynamicArray() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T data) {
        ensureCapacity();
        elements[size] = data;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    public void set(int index, T data) {
        checkIndex(index);
        elements[index] = data;
    }

    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements =
                new Object[elements.length * 2];

            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }

            elements = newElements;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index tidak valid: " + index
            );
        }
    }

    public boolean validateInvariant() {
        if (elements == null) return false;
        if (size < 0 || size > elements.length) return false;

        for (int i = 0; i < size; i++) {
            if (elements[i] == null) return false;
        }

        for (int i = size; i < elements.length; i++) {
            if (elements[i] != null) return false;
        }

        return true;
    }
}
