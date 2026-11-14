# ADR-002: Penggunaan Stack (Tumpukan)

**Status:** Accepted  
**Tanggal:** 6 November 2026

## 1. Konteks

Aplikasi UTS Struktur Data Kelompok 5 membutuhkan struktur data tambahan untuk mencatat aktivitas pengelolaan data putusan pengadilan.
Aktivitas tersebut meliputi penambahan dan penghapusan data serta aktivitas penting lainnya. Riwayat aktivitas perlu dapat diakses berdasarkan urutan aktivitas terbaru.

## 2. Keputusan

Kelompok memutuskan menggunakan **Stack (Tumpukan)** sebagai struktur data sekunder.

Stack akan diimplementasikan secara manual menggunakan array dan konsep generic `<T>`.

Stack menerapkan prinsip LIFO (Last In, First Out), yaitu data yang terakhir dimasukkan akan menjadi data pertama yang dikeluarkan.

## 3. Alasan Pemilihan

- Cocok untuk menyimpan riwayat aktivitas aplikasi.
- Aktivitas terbaru dapat diakses terlebih dahulu.
- Memiliki operasi sederhana, yaitu `push()`, `pop()`, dan `peek()`.
- Dapat diimplementasikan tanpa menggunakan koleksi bawaan Java.

## 4. Konsekuensi

**Keuntungan:**
- Penambahan dan pengambilan elemen dari bagian atas Stack dapat dilakukan dengan cepat.
- Memudahkan pemeriksaan aktivitas terakhir.
- Implementasinya relatif sederhana.

**Kekurangan:**
- Riwayat aktivitas diakses berdasarkan urutan LIFO.
- Tidak dirancang untuk pencarian data berdasarkan indeks atau atribut.
- Kapasitas perlu dikelola agar tidak terjadi kesalahan ketika Stack penuh.

## 5. Implementasi

Kelas yang digunakan adalah `Stack<T>` dengan metode:
- `push()`
- `pop()`
- `peek()`
- `isEmpty()`
- `size()`

Riwayat aktivitas akan disimpan menggunakan `Stack<AuditEntry>`.
