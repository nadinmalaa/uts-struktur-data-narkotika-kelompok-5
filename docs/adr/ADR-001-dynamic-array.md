# ADR-001: Penggunaan Dynamic Array (Array Dinamis)

**Status:** Accepted  
**Tanggal:** 6 November 2026

## 1. Konteks

Aplikasi UTS Struktur Data Kelompok 5 membutuhkan struktur data untuk menyimpan dan mengelola 50 data putusan pengadilan terkait kasus narkotika.

Data perlu mendukung operasi penambahan, penghapusan, pencarian, filter, pengurutan, dan penampilan seluruh data.

## 2. Keputusan

Kelompok memutuskan menggunakan **Dynamic Array (Array Dinamis)** sebagai struktur data utama.

Struktur ini diimplementasikan sendiri menggunakan array biasa dengan konsep generic `<T>`. Kapasitas array bertambah secara otomatis ketika jumlah data melebihi kapasitas yang tersedia.

## 3. Alasan Pemilihan

- Akses data berdasarkan indeks dapat dilakukan dengan cepat.
- Kapasitas dapat bertambah secara otomatis.
- Cocok untuk menyimpan dan mengelola 50 data putusan.
- Mendukung operasi pencarian, pengurutan, dan filter.
- Tidak menggunakan koleksi bawaan Java sebagai penyimpanan utama.

## 4. Konsekuensi

**Keuntungan:**
- Akses elemen berdasarkan indeks memiliki kompleksitas O(1).
- Penambahan data memiliki kompleksitas rata-rata O(1).
- Implementasi dapat digunakan kembali untuk berbagai tipe data.

**Kekurangan:**
- Penghapusan elemen di tengah array memerlukan pergeseran data.
- Penambahan kapasitas membutuhkan proses penyalinan array.
- Perlu memastikan indeks dan jumlah elemen selalu valid.

## 5. Implementasi

Kelas yang digunakan adalah `DynamicArray<T>` dengan metode:
- `add()`
- `get()`
- `set()`
- `remove()`
- `size()`
- `ensureCapacity()`
- `validateInvariant()`

Data putusan disimpan menggunakan `DynamicArray<Putusan>`.
