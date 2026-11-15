# CONTRIBUTIONS

Dokumen ini mencatat pembagian tugas, kontribusi teknis, aktivitas kolaborasi, serta riwayat kontribusi setiap anggota dalam proyek **UTS Struktur Data Kelompok 5** dengan topik **Analisis Putusan Mahkamah Agung (Narkotika)**.

Repository: [uts-struktur-data-narkotika-kelompok-5](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5)

## 1. Identitas Anggota

| Nama | NIM | GitHub |
|---|---|---|
| Nadin Mala Aprilia | 202510370110134 | [@nadinmalaa](https://github.com/nadinmalaa) |
| Bima Nalasakti Nugraha | 202510370110143 | [@stellanala](https://github.com/stellanala) |

## 2. Kontribusi Nadin Mala Aprilia

### Pembagian Tugas dan Implementasi

- Mengumpulkan dan mengekstraksi 25 data putusan narkotika (P001–P025).
- Membuat dokumentasi sumber data dan Data Provenance.
- Mengembangkan model data `Putusan.java`.
- Mengimplementasikan struktur data `DynamicArray` dan `Stack` generik.
- Mengembangkan `PutusanManager` dan `AuditLog`.
- Mengimplementasikan fitur import dan pembacaan dataset CSV.
- Mengembangkan fitur pencarian berdasarkan `caseId` dan nomor perkara.
- Mengembangkan fitur filter dan agregasi data putusan.
- Mengimplementasikan Insertion Sort untuk pengurutan data vonis.
- Mengimplementasikan Hash Table untuk pencarian data putusan.
- Mengembangkan dukungan pengelolaan beberapa terdakwa dalam satu perkara.
- Mengintegrasikan fitur agregasi dan ekspor laporan.
- Menyusun dokumentasi ADR, traceability matrix, panduan demo, dan README.
- Menyusun serta menjalankan pengujian struktur data dan pengolahan dataset.
- Melakukan integrasi branch, penyelesaian konflik, dan pengujian proyek.

### GitHub Issues

- [Issue #1 — NADIN: Pengumpulan 25 Data Putusan](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/issues/1)
- [Issue #7 — Menambahkan Unit Test untuk Struktur Data dan Pengolahan Dataset](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/issues/7)
- [Issue #10 — Implementasi Hash Table untuk Pencarian Data Putusan](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/issues/10)

### Pull Requests

- [PR #3 — Sync nadin branch with main](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/3): sinkronisasi branch dan penyiapan struktur repository.
- [PR #4 — Implementasi struktur data dan pengolahan dataset putusan](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/4): implementasi struktur data, pengolahan dataset, pengujian, dan dokumentasi proyek.

### Aktivitas Code Review

Melakukan review terhadap [PR #6 milik Bima](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/6), dengan aktivitas:

- Memberikan *Request Changes* terkait implementasi AuditStack, parsing CSV provenance, dan konfigurasi Maven.
- Memberikan masukan perbaikan pada file terkait.
- Memeriksa ulang perubahan yang dilakukan.
- Memberikan *Approve* setelah perbaikan selesai.

### Commit Utama

- `774b965` — Add dynamic array implementation.
- `866be27` — Improve dynamic array implementation.
- `669613b` — Add stack implementation.
- `123f3d8` — Add putusan model class.
- `6a1b8fd` — Add putusan manager.
- `0e7a973` — Add audit log implementation.
- `7b0d2ca` — Add search and aggregation features.
- `33bbff8` — Add insertion sort for verdict data.
- `0591fa2` — Perbaikan struktur data dan CSV reader.
- `7949958` — Add unit tests for putusan reader and data structures.
- `a9caa0b` — Add data provenance for 50 court decisions.
- `6d3829d` — Implement hash table for putusan lookup.
- `f41c14a` — Support multiple defendants per court case.
- `7c0d7ba` — Improve multi-defendant data handling.
- `2366e41` — Resolve conflicts and finalize aggregation export.
- `7c2bc2c` — Integrate aggregation and export report.

## 3. Kontribusi Bima Nalasakti Nugraha

### Pembagian Tugas dan Implementasi

- Mengumpulkan dan mengekstraksi 25 data putusan narkotika (P026–P050).
- Melakukan pencatatan sumber dan Data Provenance.
- Mengimplementasikan struktur data sekunder `AuditStack`.
- Mengembangkan mekanisme pencatatan riwayat operasi menggunakan struktur data Stack berbasis Node.
- Mengembangkan dan mengintegrasikan operasi tambah (*add*) dan hapus (*remove*).
- Memperbaiki parser CSV agar dapat menangani field berkutip dan koma.
- Merapikan konfigurasi Maven dan standardisasi Java 17.
- Mengembangkan fitur agregasi, pengurutan vonis, dan ekspor laporan.
- Melakukan pemeriksaan serta pembaruan data provenance.
- Menyusun dan menjalankan pengujian fitur yang dikerjakan.

### GitHub Issues

- [Issue #2 — BIMA: Pengumpulan 25 Data Putusan](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/issues/2)
- [Issue #5 — Implementasi Struktur Data Sekunder AuditStack dan Fitur Tambah-Hapus](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/issues/5)
- [Issue #8 — Implementasi Agregasi, Pengurutan Vonis, dan Ekspor Data](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/issues/8)
- [Issue #13 — Pembaruan Format File Provenance dan Pengujian Terkait](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/issues/13)

### Pull Requests

- [PR #6 — Implement AuditStack and Add-Remove Operations](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/6): implementasi AuditStack, operasi tambah-hapus, perbaikan parser CSV, dan konfigurasi Maven.

### Aktivitas Code Review

Melakukan review terhadap [PR #4 milik Nadin](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/4), dengan aktivitas:

- Memberikan *Request Changes* terkait validasi tanggal putusan.
- Memberikan masukan agar tanggal putusan tidak boleh bernilai null.
- Memeriksa kembali perbaikan yang dilakukan.
- Memberikan *Approve* setelah perbaikan selesai.

### Commit Utama

- `33889b0` — Replace java.util.Stack with custom Node-based stack implementation.
- `349990e` — Implement safe CSV line parser to handle quoted fields and commas.
- `1d6c25a` — Clean up duplicate compiler versions and standardize to Java 17.
- `84f45ed` — Finalize PR 2 data aggregation, sorting, TestNG test, and text report export.
- `fbd4c57` — Merge branch main into feature/issue-2-aggregation-export.

## 4. Kolaborasi dan Code Review

Kedua anggota bekerja sama dalam proses pengembangan melalui pembagian tugas, implementasi struktur data, pengolahan dataset, pengujian, integrasi program, dokumentasi, dan code review.

| Pull Request | Kontributor Utama | Reviewer | Hasil |
|---|---|---|---|
| [PR #3](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/3) | Nadin | Tidak ada | Merged |
| [PR #4](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/4) | Nadin | Bima | Request Changes → Approve → Merged |
| [PR #6](https://github.com/nadinmalaa/uts-struktur-data-narkotika-kelompok-5/pull/6) | Bima | Nadin | Request Changes → Approve → Merged |

## 5. Catatan Pembaruan Dokumentasi

Dokumen ini disusun berdasarkan pembagian tugas, GitHub Issues, Pull Requests yang telah selesai, aktivitas review, serta riwayat commit yang tersedia pada repository.

Dokumentasi dapat diperbarui apabila terdapat kontribusi, issue, pull request, commit, atau aktivitas review tambahan.

Pull Request yang masih terbuka dan belum di-merge belum dicantumkan sebagai Pull Request yang telah selesai.