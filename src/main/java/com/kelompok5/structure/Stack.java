package com.kelompok5.structure;

public class Stack<T> {

    private Object[] elements;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Menambahkan data ke bagian atas Stack
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                "Data tidak boleh null"
            );
        }

        ensureCapacity();

        elements[size] = data;
        size++;
    }

    // Mengambil dan menghapus data paling atas
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException(
                "Stack kosong"
            );
        }

        T data = (T) elements[size - 1];

        elements[size - 1] = null;
        size--;

        return data;
    }

    // Melihat data paling atas tanpa menghapusnya
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException(
                "Stack kosong"
            );
        }

        return (T) elements[size - 1];
    }

    // Memeriksa apakah Stack kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Mengembalikan jumlah data
    public int size() {
        return size;
    }

    // Menghapus seluruh isi Stack
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    // Memeriksa kapasitas Stack
    public int capacity() {
        return elements.length;
    }

    // Memperbesar kapasitas jika Stack penuh
    private void ensureCapacity() {
        if (size == elements.length) {

            int newCapacity = elements.length * 2;

            Object[] newElements =
                new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }

            elements = newElements;
        }
    }

    // Memeriksa konsistensi Stack
    public boolean validateInvariant() {

        if (elements == null) {
            return false;
        }

        if (size < 0 || size > elements.length) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                return false;
            }
        }

        for (int i = size; i < elements.length; i++) {
            if (elements[i] != null) {
                return false;
            }
        }

        return true;
    }
}
