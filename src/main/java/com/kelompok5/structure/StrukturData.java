package com.kelompok5.structure;

public interface StrukturData<T> {

    // Menambahkan data
    void add(T data);

    // Mengambil data berdasarkan index
    T get(int index);

    // Mengubah data berdasarkan index
    void set(int index, T data);

    // Menghapus data berdasarkan index
    void remove(int index);

    // Memeriksa apakah data tersedia
    boolean contains(T data);

    // Mengembalikan jumlah data
    int size();

    // Memeriksa apakah struktur data kosong
    boolean isEmpty();

    // Menghapus seluruh data
    void clear();

    // Memeriksa konsistensi struktur data
    boolean validateInvariant();
}
