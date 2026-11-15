# 002. Strategi Pencarian dan Pengurutan

## Status
Diterima / Disetujui

## Konteks
Dalam pengembangan sistem pengolahan data Putusan Mahkamah Agung (narkotika), fitur pencarian (*searching*) dan pengurutan (*sorting*) diperlukan untuk memudahkan analisis dokumen berdasarkan atribut tertentu, seperti nomor perkara, tanggal putusan, jenis narkotika, atau berat barang bukti. Karena sistem tidak menggunakan pustaka bawaan Java untuk struktur data utamanya, algoritma pencarian dan pengurutan perlu didefinisikan secara spesifik.

## Keputusan
1. **Strategi Pencarian (Searching)**:
    * Menggunakan algoritma **Sequential Search (Linear Search)** yang diimplementasikan langsung pada `SinglyLinkedList`.
    * Pencarian dilakukan dengan melakukan *traversal* dari simpul kepala (*head*) hingga simpul akhir untuk mencocokkan kriteria atribut (misalnya ID kasus atau jenis narkotika).
2. **Strategi Pengurutan (Sorting)**:
    * Menggunakan pendekatan berbasis iteratif/pembandingan elemen (seperti *Bubble Sort* atau *Insertion Sort* yang disesuaikan untuk struktur *Linked List*) guna mengurutkan data putusan berdasarkan besaran berat barang bukti (*beratBarangBuktiGram*) atau lama vonis.

## Alasan (Rationale)
* **Alasan Pemilihan Sequential Search**:
    * Struktur data `SinglyLinkedList` tidak mendukung akses acak (*random access*), sehingga algoritma *Binary Search* kurang efisien tanpa proses indeksing tambahan.
    * Mengingat ukuran dataset yang dikelola efisien (skala 50 data putusan dalam `putusan-50.csv`), *Sequential Search* memberikan performa yang memadai, mudah diimplementasikan, serta memastikan seluruh elemen terperiksa dengan akurat tanpa kompleksitas struktur yang berlebih.
* **Alasan Pemilihan Sorting Terstruktur**:
    * Pengurutan berbasis *Linked List node swapping* atau *data swapping* memungkinkan pengurutan dilakukan secara langsung pada memori dinamis tanpa memerlukan alokasi array tambahan.

## Konsekuensi
* **Kelebihan**:
    * Algoritma transparan, mudah dilacak (*traceable*), dan selaras dengan implementasi manual struktur data fundamental.
    * Memastikan integritas data tetap terjaga saat operasi pencarian dan pengurutan dijalankan.
* **Kekurangan / Batasan**:
    * Kompleksitas waktu pencarian adalah $O(n)$ pada kasus terburuk (*worst case*). Namun, batasan ini sangat dapat diterima untuk cakupan data tugas UTS ini.