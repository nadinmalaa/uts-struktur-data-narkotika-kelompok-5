package com.kelompok5.structure;

/**
 * Implementasi struktur data Hash Table menggunakan Separate Chaining.
 * Collision ditangani menggunakan linked list pada setiap bucket.
 *
 * @param <K> tipe data key
 * @param <V> tipe data value
 */
public class HashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double MAX_LOAD_FACTOR = 0.75;

    private HashNode<K, V>[] buckets;
    private int size;

    /**
     * Membuat Hash Table dengan kapasitas default.
     */
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Membuat Hash Table dengan kapasitas awal tertentu.
     *
     * @param capacity kapasitas awal Hash Table
     * @throws IllegalArgumentException jika kapasitas kurang dari atau sama dengan 0
     */
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
     * Menambahkan pasangan key dan value ke Hash Table.
     * Jika key sudah ada, value lama akan diperbarui.
     * Jika key belum ada, node baru ditambahkan ke bucket.
     *
     * @param key key yang akan disimpan
     * @param value value yang akan disimpan
     * @return value lama jika key sudah ada, atau null jika key baru
     * @throws IllegalArgumentException jika key atau value null
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
     * Mencari value berdasarkan key.
     *
     * @param key key yang dicari
     * @return value jika ditemukan, atau null jika tidak ditemukan
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
     * Memeriksa apakah suatu key terdapat dalam Hash Table.
     *
     * @param key key yang diperiksa
     * @return true jika key ditemukan, false jika tidak ditemukan
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
     * Menghapus pasangan key dan value berdasarkan key.
     *
     * @param key key yang akan dihapus
     * @return value yang dihapus, atau null jika key tidak ditemukan
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
     * Kapasitas tabel dikembalikan ke kapasitas default.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = (HashNode<K, V>[]) new HashNode[
                DEFAULT_CAPACITY
                ];

        size = 0;
    }

    /**
     * Mengambil jumlah pasangan key dan value yang tersimpan.
     *
     * @return jumlah elemen dalam Hash Table
     */
    public int size() {
        return size;
    }

    /**
     * Mengambil kapasitas bucket Hash Table saat ini.
     *
     * @return jumlah bucket yang tersedia
     */
    public int capacity() {
        return buckets.length;
    }

    /**
     * Memeriksa apakah Hash Table tidak memiliki elemen.
     *
     * @return true jika kosong, false jika memiliki elemen
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Menghitung rasio jumlah elemen terhadap kapasitas bucket.
     *
     * @return nilai load factor Hash Table
     */
    public double loadFactor() {
        return (double) size / buckets.length;
    }

    /**
     * Menghitung indeks bucket berdasarkan hash code key.
     *
     * @param key key yang digunakan untuk menghitung indeks
     * @return indeks bucket yang sesuai
     */
    private int hash(K key) {
        return Math.floorMod(
                key.hashCode(),
                buckets.length
        );
    }

    /**
     * Memperbesar kapasitas Hash Table menjadi dua kali lipat.
     * Seluruh node dipindahkan ke bucket baru berdasarkan
     * hasil perhitungan hash yang baru.
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

    /**
     * Memvalidasi key dan value sebelum disimpan.
     *
     * @param key key yang akan divalidasi
     * @param value value yang akan divalidasi
     * @throws IllegalArgumentException jika key atau value null
     */
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
     * Memeriksa konsistensi struktur internal Hash Table.
     * Pemeriksaan meliputi posisi bucket, keunikan key,
     * validitas key dan value, serta kesesuaian jumlah node.
     *
     * @return true jika seluruh invariant terpenuhi,
     *         false jika ditemukan ketidaksesuaian
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
