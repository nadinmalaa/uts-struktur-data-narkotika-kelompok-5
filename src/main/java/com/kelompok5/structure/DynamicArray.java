package com.kelompok5.structure;

public class DynamicArray<T> implements StrukturData<T> {

    private Object[] elements;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public DynamicArray() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Menambahkan data ke akhir array
    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "Data tidak boleh null"
            );
        }

        ensureCapacity();

        elements[size] = data;
        size++;
    }

    // Mengambil data berdasarkan index
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);

        return (T) elements[index];
    }

    // Mengubah data berdasarkan index
    @Override
    public void set(int index, T data) {
        checkIndex(index);

        if (data == null) {
            throw new IllegalArgumentException(
                    "Data tidak boleh null"
            );
        }

        elements[index] = data;
    }

    // Menghapus data berdasarkan index
    @Override
    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;
    }

    // Memeriksa apakah data tersedia
    @Override
    public boolean contains(T data) {
        for (int i = 0; i < size; i++) {
            if (java.util.Objects.equals(data, elements[i])) {
                return true;
            }
        }

        return false;
    }

    // Mengembalikan jumlah elemen
    @Override
    public int size() {
        return size;
    }

    // Memeriksa apakah array kosong
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Menghapus seluruh data
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    // Mengembalikan kapasitas array
    public int capacity() {
        return elements.length;
    }

    // Menambah kapasitas jika array penuh
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

    // Memeriksa validitas index
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index tidak valid: " + index
            );
        }
    }

    // Memeriksa konsistensi struktur data
    @Override
    public boolean validateInvariant() {

        if (elements == null) {
            return false;
        }

        if (size < 0 || size > elements.length) {
            return false;
        }

        // Memastikan elemen yang terisi tidak null
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                return false;
            }
        }

        // Memastikan bagian kosong bernilai null
        for (int i = size; i < elements.length; i++) {
            if (elements[i] != null) {
                return false;
            }
        }

        return true;
    }
}
