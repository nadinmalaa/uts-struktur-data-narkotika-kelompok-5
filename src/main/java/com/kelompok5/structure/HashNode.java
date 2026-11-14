package com.kelompok5.structure;

public class HashNode<K, V> {

    private final K key;
    private V value;
    private HashNode<K, V> next;

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

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "Value tidak boleh null"
            );
        }

        this.value = value;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
}