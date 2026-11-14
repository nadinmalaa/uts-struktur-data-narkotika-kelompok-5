# Laporan Kontribusi Kelompok (CONTRIBUTIONS)

Dokumen ini merangkum pembagian tugas, kontribusi teknis, serta riwayat kolaborasi pada proyek **UTS Struktur Data (Kelompok 5)** dengan topik **Analisis Putusan Mahkamah Agung (Narkotika)**.

# Team Contributions

Proyek UTS Struktur Data - Kelompok 5

## Anggota Kelompok
1. **Bima Nalasakti Nugraha** (`202510370110143`)
2. **Nadin Mala Aprilia** (`202510370110134`)

### 1. Nadin Mala Aprilia
GitHub: @nadinmalaa
---

## Rincian Pembagian Kerja & Kontribusi
Tugas:
- Mengumpulkan 25 data putusan pengadilan.
- Membuat dokumentasi sumber data.
- Mengembangkan fitur import dan tampilan data.
- Mengembangkan fitur pencarian dan filter.
- Membuat pengujian fitur yang dikerjakan.

### 1. Bima Nalasakti Nugraha
* **Pengumpulan & Ekstraksi Dataset**: Mengekstraksi 25 putusan narkotika dari Direktori Putusan Mahkamah Agung (`P026` – `P050`) beserta pencatatan *provenance*-nya.
* **Struktur Data Sekunder (`AuditStack.java`)**: Merancang dan mengimplementasikan struktur data tumpukan (*Stack*) generik `<T>` berbasis LIFO untuk pencatatan log audit operasi sistem secara non-destruktif (`searchPreserve`).
* **Manipulasi Struktur Data Utama**: Mengimplementasikan operasi penambahan dan penghapusan elemen (awal, tengah, akhir) pada `SinglyLinkedList` serta penjagaan *invariant* struktur data.
* **Pengujian Unit (`MainTestBima.java`)**: Menyusun dan menjalankan pengujian unit otomatis menggunakan JUnit 5 untuk memvalidasi manipulasi *linked list*, *audit stack*, dan pembacaan dataset CSV (*4 tests passed*).
* **Dokumentasi Arsitektur & Kontribusi**: Menyusun *Architecture Decision Records* (`001-pemilihan-struktur-data.md` & `002-strategi-pencarian-pengurutan.md`) serta dokumen `CONTRIBUTIONS.md`.
### 2. Bima Nalasakti Nugraha
GitHub: @stellanella

### 2. Nadin
* **Pengumpulan & Ekstraksi Dataset**: Mengekstraksi 25 putusan narkotika dari Direktori Putusan Mahkamah Agung (`P001` – `P025`) beserta pencatatan *provenance*-nya.
* **Model Data & Antarmuka**: Merancang *class* model `Putusan.java` dan kontrak *interface* struktur data generik.
* **Fitur Impor, Pencarian & Filter**: Mengimplementasikan pembacaan dan validasi 50 *record* CSV (`CsvParser.java`), pencarian berdasarkan `caseId` / nomor perkara beserta penghitungan langkah, serta penyaringan (*filtering*) atribut putusan.
* **Pengujian & Panduan Utama**: Melakukan pengujian fungsionalitas pencarian dan filter, serta menyusun panduan awal proyek (`README.md`).
Tugas:
- Mengumpulkan 25 data putusan pengadilan.
- Membuat dokumentasi sumber data.
- Mengembangkan fitur tambah dan hapus data.
- Mengembangkan fitur pengurutan dan agregasi.
- Mengembangkan fitur audit dan ekspor.
- Membuat pengujian fitur yang dikerjakan.

## Kolaborasi
Kedua anggota bekerja sama dalam integrasi program, pengujian keseluruhan, dan dokumentasi proyek.
---

## Ringkasan Aktivitas Repositori (GitHub)
* **Branching & Pull Requests**: Setiap fitur utama dan penambahan dokumentasi dikerjakan melalui *branch* terpisah dan digabungkan ke `main` melalui *Pull Request* yang saling di-*review* antar anggota.
* **Status Pengujian**: Seluruh *unit test* berjalan sukses tanpa error sebelum dilakukan *merge* dan rilis *tag* `uts-v1.0`.