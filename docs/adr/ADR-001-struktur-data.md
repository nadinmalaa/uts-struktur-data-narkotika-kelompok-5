# ADR-001: Pemilihan Struktur Data

## Status
Diterima

## Konteks
Aplikasi Pengelolaan Data Putusan Pengadilan membutuhkan struktur data untuk menyimpan dan mengelola kumpulan data putusan serta mencatat aktivitas pengguna.

## Keputusan
Proyek menggunakan struktur data yang diimplementasikan secara mandiri, yaitu:

1. **Dynamic Array** untuk menyimpan kumpulan data putusan dengan kapasitas yang dapat bertambah secara dinamis.
2. **Stack** untuk menyimpan riwayat aktivitas menggunakan prinsip Last In, First Out (LIFO).
3. **Insertion Sort** untuk mengurutkan data putusan berdasarkan lama vonis.

## Alasan
Pemilihan struktur data tersebut bertujuan untuk menerapkan konsep Struktur Data yang dipelajari dalam perkuliahan serta memahami cara kerja setiap struktur dan algoritma.

## Konsekuensi
- Program dapat mengelola data putusan secara dinamis.
- Riwayat aktivitas dapat diakses dari aktivitas terbaru.
- Data putusan dapat diurutkan berdasarkan lama vonis.
- Implementasi struktur data perlu diuji untuk memastikan ketepatan operasi.
