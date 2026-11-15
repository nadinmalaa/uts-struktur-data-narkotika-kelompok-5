# Aplikasi Pengelolaan Data Putusan Pengadilan Kasus Narkotika

## Identitas Kelompok

| Nama | NIM | GitHub |
|---|---|---|
| Nadin Mala Aprilia | 202510370110134 | [@nadinmalaa](https://github.com/nadinmalaa) |
| Bima Nalasakti Nugraha | 202510370110143 | [@stellanala](https://github.com/stellanala) |

## Deskripsi Proyek

Proyek ini merupakan aplikasi berbasis Java untuk mengelola data putusan pengadilan terkait kasus narkotika. Aplikasi dikembangkan sebagai bagian dari tugas Ujian Tengah Semester (UTS) mata kuliah Struktur Data.

Aplikasi memproses dataset putusan pengadilan yang terdiri dari 50 data putusan dan 55 data terdakwa. Data dilengkapi dengan informasi provenance untuk mendukung penelusuran sumber dan proses pengolahan data.

Sistem menyediakan fitur pengelolaan data, pencarian, pengurutan, penyaringan, statistik, pencatatan aktivitas, serta ekspor laporan rekapitulasi.

## Tujuan Proyek

1. Menerapkan konsep struktur data dalam pengelolaan data putusan pengadilan.
2. Mengimplementasikan operasi penambahan, penghapusan, pencarian, dan pengurutan data.
3. Mengolah dan memvalidasi dataset putusan, terdakwa, serta provenance.
4. Menyediakan informasi statistik dan laporan rekapitulasi.
5. Menerapkan pengujian untuk memastikan fungsi dan konsistensi struktur data.

## Struktur Data yang Digunakan

| Struktur Data | Kegunaan |
|---|---|
| Singly Linked List | Menyimpan dan mengelola kumpulan data putusan secara dinamis. |
| Dynamic Array | Menyediakan penyimpanan data berbasis array yang dapat berkembang secara dinamis. |
| Stack / AuditStack | Menyimpan riwayat aktivitas dan mendukung pencatatan operasi aplikasi. |
| Hash Table | Mendukung pencarian data putusan menggunakan mekanisme hashing dengan Separate Chaining. |
| Insertion Sort | Mengurutkan data berdasarkan rata-rata vonis. |

## Teknologi yang Digunakan

- **Bahasa Pemrograman:** Java
- **Build Tool:** Apache Maven
- **Struktur Proyek:** Maven Standard Directory Layout
- **Library CSV:** Apache Commons CSV
- **Framework Pengujian:** JUnit 5
- **IDE:** IntelliJ IDEA
- **Version Control:** Git dan GitHub

## Dataset

Dataset yang digunakan dalam aplikasi terdiri dari:

| Dataset | Jumlah |
|---|---:|
| Data putusan pengadilan | 50 |
| Data terdakwa | 55 |
| Data provenance | Menyesuaikan jumlah dan keterkaitan sumber data |

Data putusan memuat informasi perkara dan hasil putusan pengadilan. Data terdakwa menyimpan informasi pihak terdakwa yang berkaitan dengan perkara. Sementara itu, data provenance digunakan untuk mencatat asal data, sumber dokumen, proses ekstraksi, reviewer, dan status verifikasi.

### Hasil Import Dataset

Berdasarkan hasil eksekusi aplikasi, proses import dataset menghasilkan keluaran berikut:

```text
========== HASIL IMPOR ==========
Jumlah baris putusan : 50
Putusan berhasil     : 50
Putusan ditolak      : 0
Jumlah di aplikasi   : 50
Jumlah data terdakwa : 55
Invariant valid      : true
=================================
```

Hasil tersebut menunjukkan bahwa seluruh 50 baris putusan berhasil diproses tanpa penolakan dan invariant struktur data dinyatakan valid.

## Fitur Aplikasi

Aplikasi menyediakan fitur utama berikut:

1. **Tampilkan seluruh data putusan**  
   Menampilkan seluruh data putusan yang tersimpan dalam aplikasi.

2. **Tambah data putusan**  
   Menambahkan data putusan baru ke dalam sistem.

3. **Hapus data putusan**  
   Menghapus data putusan berdasarkan identitas yang ditentukan.

4. **Cari data putusan**  
   Melakukan pencarian data putusan berdasarkan kriteria yang tersedia.

5. **Urutkan berdasarkan rata-rata vonis**  
   Mengurutkan data putusan berdasarkan rata-rata vonis menggunakan Insertion Sort.

6. **Tampilkan statistik**  
   Menampilkan informasi statistik dari dataset putusan.

7. **Tampilkan riwayat aktivitas**  
   Menampilkan catatan aktivitas yang dilakukan selama aplikasi digunakan.

8. **Tampilkan seluruh data terdakwa**  
   Menampilkan data terdakwa yang berkaitan dengan putusan.

9. **Filter data putusan**  
   Menyaring data putusan berdasarkan kriteria tertentu.

10. **Ekspor laporan rekapitulasi ke TXT**  
    Menghasilkan laporan rekapitulasi dalam format teks.

## Cara Menjalankan Aplikasi

### 1. Persyaratan

Pastikan perangkat telah memiliki:

- Java Development Kit (JDK).
- Apache Maven.
- IntelliJ IDEA atau IDE Java lainnya.
- Git.

### 2. Clone Repository

```bash
git clone https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5.git
```

Masuk ke direktori proyek:

```bash
cd uts-struktur-data-narkotika-kelompok-5
```

### 3. Build Project

Jalankan perintah berikut:

```bash
mvn clean compile
```

Untuk membuat paket aplikasi:

```bash
mvn clean package
```

### 4. Menjalankan Aplikasi melalui IntelliJ IDEA

1. Buka repository proyek melalui IntelliJ IDEA.
2. Tunggu hingga proses sinkronisasi Maven selesai.
3. Buka file `src/main/java/com/kelompok5/Main.java`.
4. Jalankan method `main()` dengan memilih **Run 'Main.main()'**.
5. Tunggu hingga proses import dataset selesai.
6. Aplikasi akan menampilkan menu utama pada terminal.

## Menu Utama Aplikasi

Berikut menu utama yang ditampilkan ketika aplikasi berhasil dijalankan:

```text
====================================
   APLIKASI PENGELOLAAN DATA PUTUSAN
====================================
1. Tampilkan seluruh data putusan
2. Tambah data putusan
3. Hapus data putusan
4. Cari data putusan
5. Urutkan berdasarkan rata-rata vonis
6. Tampilkan statistik
7. Tampilkan riwayat aktivitas
8. Tampilkan seluruh data terdakwa
9. Filter data putusan
10. Ekspor laporan rekapitulasi ke TXT
0. Keluar
====================================
Pilih menu:
```

| Pilihan | Operasi |
|---|---|
| 1 | Tampilkan seluruh data putusan |
| 2 | Tambah data putusan |
| 3 | Hapus data putusan |
| 4 | Cari data putusan |
| 5 | Urutkan berdasarkan rata-rata vonis |
| 6 | Tampilkan statistik |
| 7 | Tampilkan riwayat aktivitas |
| 8 | Tampilkan seluruh data terdakwa |
| 9 | Filter data putusan |
| 10 | Ekspor laporan rekapitulasi ke TXT |
| 0 | Keluar dari aplikasi |

## Pengujian

Pengujian dilakukan menggunakan JUnit 5 untuk memeriksa fungsi struktur data dan pengolahan dataset.

Pengujian mencakup:

- Pengujian operasi Dynamic Array.
- Pengujian operasi Stack.
- Pengujian Hash Table.
- Pengujian pencarian dan pengelolaan data putusan.
- Pengujian pembacaan dataset CSV.
- Pengujian pengolahan data putusan.

Untuk menjalankan seluruh pengujian, gunakan:

```bash
mvn test
```

Untuk menampilkan laporan pengujian, periksa direktori:

```text
target/surefire-reports/
```

## Struktur Repository

```text
uts-struktur-data-narkotika-kelompok-5/
│
├── data/
│   ├── putusan-50.csv
│   └── ...
│
├── docs/
│   ├── adr/
│   ├── trace/
│   ├── demo/
│   └── data-provenance.csv
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── com/kelompok5/
│   │       │   └── Main.java
│   │       ├── datastructure/
│   │       ├── model/
│   │       ├── manager/
│   │       └── service/
│   │
│   └── test/
│       └── java/
│
├── AI_USAGE.md
├── CONTRIBUTIONS.md
├── README.md
└── pom.xml
```

## Dokumentasi Tambahan

Dokumentasi proyek tersedia pada direktori `docs/` dan file berikut:

- **AI_USAGE.md** — Dokumentasi penggunaan AI selama pengembangan proyek.
- **CONTRIBUTIONS.md** — Dokumentasi kontribusi masing-masing anggota kelompok.
- **docs/adr/** — Architecture Decision Records yang mencatat keputusan desain sistem.
- **docs/trace/** — Dokumentasi penelusuran dan keterkaitan kebutuhan dengan implementasi.
- **docs/demo/** — Dokumentasi demonstrasi aplikasi.
- **docs/data-provenance.csv** — Dokumentasi asal-usul dan proses pengolahan data.

## Version Control dan Kolaborasi

Pengembangan proyek menggunakan Git dan GitHub sebagai sarana version control dan kolaborasi kelompok.

Alur kolaborasi dilakukan melalui:

1. Pembuatan GitHub Issue untuk mencatat pekerjaan.
2. Pembuatan branch untuk setiap fitur atau perbaikan.
3. Implementasi dan pengujian pada branch masing-masing.
4. Pengajuan Pull Request (PR) ke branch `main`.
5. Code review oleh anggota kelompok.
6. Penggabungan perubahan setelah proses review selesai.

## Penutup

Proyek ini menerapkan berbagai konsep struktur data untuk mendukung pengelolaan dataset putusan pengadilan kasus narkotika. Melalui implementasi struktur data, pengolahan CSV, pengujian, dan dokumentasi, diharapkan aplikasi dapat menjadi contoh penerapan konsep Struktur Data dalam menyelesaikan permasalahan pengelolaan data yang terstruktur.