# UTS Struktur Data Kelompok 5

Aplikasi Pengelolaan Data Putusan Pengadilan untuk Proyek UTS Struktur Data Kelompok 5.
# UTS Struktur Data – Kelompok 5

Aplikasi Pengelolaan Data Putusan Pengadilan untuk memenuhi tugas Ujian Tengah Semester mata kuliah Struktur Data.

## 1. Deskripsi Project

Project ini merupakan aplikasi berbasis Java untuk mengelola dan mengolah data putusan pengadilan. Aplikasi dikembangkan menggunakan konsep Object-Oriented Programming (OOP) dan implementasi struktur data secara manual.

Dataset kelompok terdiri dari 50 dokumen putusan pengadilan yang dikumpulkan dan disusun oleh anggota kelompok.

## 2. Informasi Project

- **Nama Project:** Aplikasi Pengelolaan Data Putusan Pengadilan
- **Kelompok:** 5
- **Bahasa Pemrograman:** Java
- **Java SDK:** 25
- **Struktur Data Utama:** Singly Linked List
- **Struktur Data Sekunder:** Stack
- **IDE:** IntelliJ IDEA

## 3. Struktur Data

### Singly Linked List

Digunakan sebagai struktur penyimpanan utama untuk mengelola data putusan.

Operasi yang direncanakan:
- Add First
- Add Last
- Insert
- Get
- Remove
- Search

### Stack

Digunakan untuk menyimpan riwayat aktivitas atau audit operasi pengguna dengan prinsip LIFO (Last In, First Out).

## 4. Fitur Aplikasi

Fitur yang dikembangkan meliputi:

- Pengelolaan data putusan.
- Pencarian berdasarkan Case ID atau nomor perkara.
- Penghapusan data.
- Pengurutan berdasarkan vonis.
- Perhitungan agregasi data.
- Pencatatan riwayat operasi.
- Import dan export data.

*Status implementasi setiap fitur akan diperbarui selama pengembangan.*

## 5. Struktur Folder

```text
uts-struktur-data-kelompok-5/
│
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
│
├── data/
│   └── putusan-50.csv
│
├── docs/
│   ├── adr/
│   ├── trace/
│   ├── demo/
│   └── data-provenance.csv
│
├── AI_USAGE.md
├── CONTRIBUTIONS.md
├── README.md
└── pom.xml
```

## 6. Anggota Kelompok

- Bima
- Nadin

## 7. Teknologi

- Java 25
- IntelliJ IDEA
- Git dan GitHub
- Maven (akan dikonfigurasi)

## 8. Status Pengembangan

Project masih dalam tahap pengembangan. Penyusunan dataset, implementasi fitur, pengujian, dan dokumentasi dilakukan secara bertahap.

## 9. Cara Menjalankan

Setelah konfigurasi project selesai, buka project melalui IntelliJ IDEA, pastikan Java SDK sudah menggunakan versi 25, kemudian jalankan kelas utama aplikasi.


## Deskripsi Proyek

Proyek ini merupakan aplikasi berbasis Java untuk mengelola 50 data putusan pengadilan terkait kasus narkotika. Aplikasi dirancang untuk mendukung penyimpanan, pencarian, pengurutan, pengolahan, serta pengelolaan data putusan.

## Anggota Kelompok

1. Nadin Mala Aprilia
2. Bima Nalasakti Nugraha

## Teknologi yang Digunakan

- Java JDK 17
- Maven
- Git dan GitHub
- IntelliJ IDEA

## Fitur Aplikasi

- Import data putusan dari file CSV.
- Menampilkan seluruh data putusan.
- Menambah dan menghapus data.
- Mencari data berdasarkan nomor perkara.
- Memfilter data putusan.
- Mengurutkan data menggunakan algoritma Insertion Sort.
- Menghitung statistik data.
- Mencatat aktivitas menggunakan Audit Log.
- Mengekspor data ke format TXT atau CSV.

## Struktur Repository

```text
uts-struktur-data-kelompok-5/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
├── data/
├── docs/
├── pom.xml
└── README.md
```

## Cara Menjalankan

1. Pastikan Java JDK 17 sudah terinstal.
2. Clone repository GitHub.
3. Buka proyek menggunakan IntelliJ IDEA.
4. Tunggu proses sinkronisasi Maven selesai.
5. Jalankan file `Main.java`.

## Status Pengembangan

Pengembangan struktur data dan pengujian awal menggunakan data dummy telah dilakukan. Pengolahan dataset putusan asli, integrasi fitur, dan pengujian akhir masih dalam proses.
