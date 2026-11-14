package com.kelompok5.structure;

/**
 * Implementasi Hash Table menggunakan Separate Chaining.
 * Collision ditangani menggunakan linked list.
 */
public class HashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double MAX_LOAD_FACTOR = 0.75;

    private HashNode<K, V>[] buckets;
    private int size;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Kapasitas harus lebih dari 0"
            );
        }

        buckets = (HashNode<K, V>[]) new HashNode[capacity];
        size = 0;
    }

    /**
     * Menambahkan data ke Hash Table.
     * Jika key sudah ada, value akan diperbarui.
     */
    public V put(K key, V value) {
        validateKeyValue(key, value);

        int index = hash(key);
        HashNode<K, V> current = buckets[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                V oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            }

            current = current.getNext();
        }

        HashNode<K, V> newNode =
                new HashNode<>(key, value);

        newNode.setNext(buckets[index]);
        buckets[index] = newNode;

        size++;

        if (loadFactor() > MAX_LOAD_FACTOR) {
            resize();
        }

        return null;
    }

    /**
     * Mengambil value berdasarkan key.
     */
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);
        HashNode<K, V> current = buckets[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }

            current = current.getNext();
        }

        return null;
    }

    /**
     * Memeriksa keberadaan key.
     */
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }

        int index = hash(key);
        HashNode<K, V> current = buckets[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    /**
     * Menghapus data berdasarkan key.
     */
    public V remove(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);

        HashNode<K, V> current = buckets[index];
        HashNode<K, V> previous = null;

        while (current != null) {
            if (current.getKey().equals(key)) {

                if (previous == null) {
                    buckets[index] = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }

                size--;

                return current.getValue();
            }

            previous = current;
            current = current.getNext();
        }

        return null;
    }

    /**
     * Menghapus seluruh isi Hash Table.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = (HashNode<K, V>[]) new HashNode[
                DEFAULT_CAPACITY
                ];

        size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return buckets.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public double loadFactor() {
        return (double) size / buckets.length;
    }

    /**
     * Menghitung indeks bucket.
     */
    private int hash(K key) {
        return Math.floorMod(
                key.hashCode(),
                buckets.length
        );
    }

    /**
     * Memperbesar kapasitas dan memindahkan node.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        HashNode<K, V>[] oldBuckets = buckets;

        int newCapacity = oldBuckets.length * 2;

        buckets = (HashNode<K, V>[]) new HashNode[
                newCapacity
                ];

        for (HashNode<K, V> head : oldBuckets) {
            HashNode<K, V> current = head;

            while (current != null) {
                HashNode<K, V> next = current.getNext();

                int newIndex = hash(current.getKey());

                current.setNext(buckets[newIndex]);
                buckets[newIndex] = current;

                current = next;
            }
        }
    }

    private void validateKeyValue(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException(
                    "Key tidak boleh null"
            );
        }

        if (value == null) {
            throw new IllegalArgumentException(
                    "Value tidak boleh null"
            );
        }
    }

    /**
     * Memeriksa konsistensi Hash Table.
     */
    public boolean validateInvariant() {
        if (buckets == null || buckets.length == 0) {
            return false;
        }

        int countedNodes = 0;

        for (int i = 0; i < buckets.length; i++) {
            HashNode<K, V> current = buckets[i];

            while (current != null) {

                if (current.getKey() == null
                        || current.getValue() == null) {
                    return false;
                }

                if (hash(current.getKey()) != i) {
                    return false;
                }

                HashNode<K, V> other = current.getNext();

                while (other != null) {
                    if (current.getKey().equals(
                            other.getKey())) {
                        return false;
                    }

                    other = other.getNext();
                }

                countedNodes++;
                current = current.getNext();
            }
        }

        return countedNodes == size;
    }
}
