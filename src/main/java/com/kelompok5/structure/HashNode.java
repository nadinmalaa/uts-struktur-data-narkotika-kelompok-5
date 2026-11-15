package com.kelompok5.structure;

/**
 * Merepresentasikan satu node dalam Hash Table.
 * Setiap node menyimpan key, value, dan referensi
 * ke node berikutnya untuk Separate Chaining.
 *
 * @param <K> tipe key
 * @param <V> tipe value
 */
public class HashNode<K, V> {

    private final K key;
    private V value;
    private HashNode<K, V> next;

    /**
     * Membuat node baru dengan key dan value tertentu.
     *
     * @param key key yang disimpan
     * @param value value yang disimpan
     * @throws IllegalArgumentException jika key atau value null
     */
    public HashNode(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                    "Key dan value tidak boleh null"
            );
        }

        this.key = key;
        this.value = value;
        this.next = null;
    }

    /**
     * Mengambil key dari node.
     *
     * @return key node
     */
    public K getKey() {
        return key;
    }

    /**
     * Mengambil value dari node.
     *
     * @return value node
     */
    public V getValue() {
        return value;
    }

    /**
     * Mengubah value dari node.
     *
     * @param value nilai baru
     * @throws IllegalArgumentException jika value null
     */
    public void setValue(V value) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "Value tidak boleh null"
            );
        }

        this.value = value;
    }

    /**
     * Mengambil node berikutnya dalam rantai.
     *
     * @return node berikutnya atau null jika tidak ada
     */
    public HashNode<K, V> getNext() {
        return next;
    }

    /**
     * Mengatur node berikutnya dalam rantai.
     *
     * @param next node berikutnya, boleh null
     */
    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
}
