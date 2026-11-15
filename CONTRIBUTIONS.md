# LAPORAN KONTRIBUSI KELOMPOK (CONTRIBUTIONS)

Dokumen ini merangkum pembagian tugas, kontribusi teknis, riwayat commit, Pull Request, serta aktivitas kolaborasi anggota dalam pengerjaan proyek **UTS Struktur Data (Kelompok 5)** dengan topik **Analisis Putusan Mahkamah Agung (Narkotika)**.

# Team Contributions

**Proyek UTS Struktur Data – Kelompok 5**

## Anggota Kelompok

1. **Nadin Mala Aprilia** (`202510370110134`)
    - GitHub: [@nadinmalaa](https://github.com/nadinmalaa)
2. **Bima Nalasakti Nugraha** (`202510370110143`)
    - GitHub: [@stellanala](https://github.com/stellanala)

---

## 1. Nadin Mala Aprilia

GitHub: @nadinmalaa

### Rincian Pembagian Kerja

Tugas:
- Mengumpulkan dan mengekstraksi 25 data putusan pengadilan.
- Membuat dokumentasi sumber data dan provenance.
- Mengembangkan struktur data utama dan model data.
- Mengembangkan fitur pencarian dan pengolahan data.
- Mengembangkan fitur pengurutan dan agregasi.
- Mengembangkan struktur data Hash Table.
- Membuat pengujian unit dan dokumentasi proyek.

### Kontribusi Teknis

* **Pengumpulan dan Ekstraksi Dataset**: Mengumpulkan dan mengekstraksi 25 putusan narkotika dari Direktori Putusan Mahkamah Agung (`P001`–`P025`) beserta pencatatan provenance untuk mendukung keterlacakan sumber data.

* **Struktur Data Utama**: Mengimplementasikan Dynamic Array dan Stack serta mengembangkan pengelolaan data putusan melalui `PutusanManager`.

* **Model Data (`Putusan.java`)**: Mengembangkan model data putusan untuk merepresentasikan informasi perkara, terdakwa, jenis narkotika, berat barang bukti, dan atribut putusan lainnya.

* **Fitur Pencarian dan Agregasi**: Mengimplementasikan fitur pencarian dan agregasi data putusan serta penghitungan langkah pencarian untuk mendukung analisis dataset.

* **Fitur Pengurutan (`Insertion Sort`)**: Mengimplementasikan algoritma Insertion Sort untuk mengurutkan data putusan berdasarkan atribut yang ditentukan.

* **Pengolahan Data Multi-Terdakwa**: Mengembangkan dukungan pengolahan perkara dengan lebih dari satu terdakwa serta memperbaiki penanganan data dan pelabelan hasil agregasi.

* **Data Provenance**: Menyusun dokumentasi provenance untuk 50 putusan pengadilan guna mencatat sumber dokumen, halaman, metode ekstraksi, pemeriksa, dan status verifikasi data.

* **Implementasi Hash Table**: Mengembangkan struktur data Hash Table untuk mendukung pencarian data putusan berdasarkan key tertentu, termasuk penanganan collision dan pengujian implementasinya.

* **Pengujian Unit**: Menyusun dan memperbaiki pengujian otomatis untuk pembaca CSV, model data, dan struktur data menggunakan framework pengujian Java.

* **Dokumentasi Arsitektur dan Penggunaan**: Menyusun dokumentasi ADR, traceability matrix, panduan demo, README, dan dokumentasi kontribusi proyek.

### Riwayat Commit

Beberapa commit yang menunjukkan kontribusi:

- `774b965` – Add dynamic array implementation
- `669613b` – Add stack implementation
- `6a1b8fd` – Add putusan manager
- `0e7a973` – Add audit log implementation
- `7b0d2ca` – Add search and aggregation features
- `33bbff8` – Add insertion sort for verdict data
- `da39f9a` – Implement DynamicArray, Stack, PutusanManager, and AuditLog with tests
- `a9caa0b` – docs: add data provenance for 50 court decisions
- `f41c14a` – feat: support multiple defendants per court case
- `7c0d7ba` – feat: improve multi-defendant data handling
- `7949958` – test: add unit tests for putusan reader and data structures
- `6d3829d` – feat: implement hash table for putusan lookup
- `25755c4` – docs: complete hash table JavaDoc and collision tests
- `4717045` – docs: update README

### Pull Request yang Dikerjakan

1. **PR #3 – Sync nadin branch with main**
    - Sinkronisasi branch pengembangan dengan branch utama.
    - Status: Merged.

2. **PR #4 – Implementasi struktur data dan pengolahan dataset putusan**
    - Implementasi struktur data utama.
    - Pengembangan pengelolaan dataset putusan.
    - Status: Merged.

3. **PR #9 – Menambahkan Pengujian Unit untuk Putusan Reader dan Struktur Data**
    - Pengembangan pengujian unit.
    - Validasi pembacaan data putusan dan struktur data.
    - Status: Merged.

4. **PR #11 – Implementasi Hash Table untuk Pencarian Data Putusan**
    - Implementasi Hash Table.
    - Pengembangan pencarian data putusan.
    - Dokumentasi JavaDoc dan pengujian collision.
    - Status: Merged.

5. **PR #18 – docs: update README**
    - Pembaruan dokumentasi README.
    - Status: Merged.

---

## 2. Bima Nalasakti Nugraha

GitHub: @stellanala

### Rincian Pembagian Kerja

Tugas:
- Mengumpulkan dan mengekstraksi 25 data putusan pengadilan.
- Membuat dokumentasi sumber data dan provenance.
- Mengembangkan struktur data Stack dan pengelolaan log audit.
- Mengembangkan operasi penambahan dan penghapusan data.
- Berkontribusi dalam fitur agregasi, pengurutan, dan ekspor laporan.
- Melakukan pengujian dan perbaikan implementasi program.
- Membantu proses integrasi dan penyelesaian konflik kode.

### Kontribusi Teknis

* **Pengumpulan dan Ekstraksi Dataset**: Mengumpulkan dan mengekstraksi 25 putusan narkotika dari Direktori Putusan Mahkamah Agung (`P026`–`P050`) beserta pencatatan sumber data dan provenance.

* **Struktur Data Stack (`AuditStack.java`)**: Mengembangkan struktur data Stack generik berbasis Node dengan prinsip LIFO (*Last In, First Out*) untuk mendukung pencatatan aktivitas dan operasi audit sistem tanpa menggunakan `java.util.Stack`.

* **CSV Parser**: Mengembangkan parser CSV yang mampu menangani nilai dengan tanda kutip dan koma di dalam kolom agar pembacaan dataset lebih aman dan konsisten.

* **Operasi Penambahan dan Penghapusan Data**: Mengembangkan operasi tambah dan hapus elemen pada struktur data, termasuk pengelolaan elemen pada awal, tengah, dan akhir sesuai implementasi.

* **Konfigurasi Proyek Maven**: Melakukan refactoring konfigurasi `pom.xml`, menghilangkan duplikasi versi compiler, dan menstandarkan penggunaan Java 17.

* **Agregasi dan Pengurutan Data**: Berkontribusi dalam pengembangan fitur agregasi data putusan dan algoritma pengurutan untuk mendukung analisis dataset.

* **Ekspor Laporan**: Berkontribusi dalam implementasi fitur ekspor hasil pengolahan dan agregasi putusan ke dalam laporan berbentuk teks.

* **Pengujian dan Perbaikan**: Melakukan pengujian menggunakan TestNG, membantu penyelesaian konflik merge, dan melakukan standardisasi model agar komponen program dapat terintegrasi dengan baik.

### Riwayat Commit

Beberapa commit yang menunjukkan kontribusi:

- `33889b0` – refactor: replace java.util.Stack with custom Node-based stack implementation
- `349990e` – refactor: implement safe CSV line parser to handle quoted fields and commas
- `1d6c25a` – refactor: clean up duplicate compiler versions in pom.xml and standardize to java 17
- `84f45ed` – feat: finalize PR 2 data aggregation, sorting, TestNG test, and text report export
- `55b31c0` – fix: finalize merge conflicts, update AI_USAGE, and ensure all tests pass successfully

### Pull Request yang Dikerjakan

1. **PR #6 – implement AuditStack and add-remove operations**
    - Implementasi AuditStack.
    - Pengembangan operasi tambah dan hapus data.
    - Status: Merged.

2. **PR #12 – Agregasi Putusan, Sorting, TestNG, dan Ekspor Laporan**
    - Pengembangan agregasi data putusan.
    - Implementasi pengurutan data.
    - Penambahan pengujian menggunakan TestNG.
    - Implementasi ekspor laporan.
    - Status: Merged.

3. **PR #17 – fix: resolve merge conflicts, standardize models, and pass 9/9 TestNG tests**
    - Penyelesaian konflik merge.
    - Standardisasi model data.
    - Perbaikan implementasi.
    - Validasi pengujian dengan hasil 9 dari 9 test berhasil.
    - Status: Merged.

---

## 3. Kolaborasi dan Integrasi

Kedua anggota bekerja sama dalam proses pengembangan dan integrasi program melalui GitHub dengan pembagian pekerjaan berdasarkan fitur dan tanggung jawab masing-masing.

Bentuk kolaborasi meliputi:

* **Branching dan Pull Request**: Pengembangan fitur dilakukan melalui branch terpisah untuk mempermudah pengelolaan perubahan, pemeriksaan kode, dan proses integrasi ke branch `main`.

* **Code Review**: Perubahan kode diajukan melalui Pull Request agar dapat ditinjau sebelum digabungkan. Beberapa Pull Request memperoleh persetujuan (*approved*) sebelum proses merge.

* **Integrasi Program**: Kedua anggota terlibat dalam proses integrasi kode, sinkronisasi branch, penyelesaian konflik merge, dan penyesuaian implementasi.

* **Pengujian Program**: Melakukan pengujian terhadap struktur data, pembacaan CSV, pencarian, pengurutan, agregasi, Hash Table, dan fitur lainnya.

* **Dokumentasi**: Berkolaborasi dalam penyusunan README, AI_USAGE, CONTRIBUTIONS, dokumentasi provenance, ADR, dan traceability matrix.

---

## 4. Ringkasan Aktivitas Repositori (GitHub)

Berdasarkan riwayat Git commit dan Pull Request pada repository GitHub, terdapat **8 Pull Request yang telah berhasil digabungkan (merged)** selama proses pengerjaan proyek.

### Rekapitulasi Pull Request

| No. | Pull Request | Kontributor | Keterangan |
|---|---|---|---|
| #3 | Sync nadin branch with main | Nadin | Sinkronisasi branch |
| #4 | Implementasi struktur data dan pengolahan dataset putusan | Nadin | Struktur data dan pengolahan dataset |
| #6 | implement AuditStack and add-remove operations | Bima | AuditStack dan operasi tambah-hapus |
| #9 | Menambahkan Pengujian Unit untuk Putusan Reader dan Struktur Data | Nadin | Pengujian unit |
| #11 | Implementasi Hash Table untuk Pencarian Data Putusan | Nadin | Hash Table dan pencarian |
| #12 | Agregasi Putusan, Sorting, TestNG, dan Ekspor Laporan | Bima | Agregasi, sorting, testing, dan ekspor |
| #17 | fix: resolve merge conflicts, standardize models, and pass 9/9 TestNG tests | Bima | Perbaikan integrasi dan pengujian |
| #18 | docs: update README | Nadin | Dokumentasi README |

### Ringkasan Jumlah Pull Request

| Anggota | Jumlah PR Merged |
|---|---:|
| Nadin Mala Aprilia | 5 |
| Bima Nalasakti Nugraha | 3 |
| **Total** | **8** |

### Ringkasan Aktivitas Pengembangan

* **Pengembangan Fitur**: Proyek mencakup implementasi Dynamic Array, Stack, Singly Linked List, AuditStack, Audit Log, pencarian, Insertion Sort, agregasi, pengolahan multi-terdakwa, Hash Table, dan ekspor laporan.

* **Dokumentasi**: Dokumentasi proyek mencakup ADR, traceability matrix, demo guide, data provenance, README, AI_USAGE, dan CONTRIBUTIONS.

* **Pengujian**: Pengujian dilakukan secara bertahap terhadap struktur data dan fungsionalitas program. Riwayat PR #17 mencatat hasil pengujian TestNG sebanyak 9 dari 9 test berhasil.

* **Integrasi**: Pengembangan dilakukan melalui beberapa branch dan Pull Request, dengan proses sinkronisasi, review, perbaikan, dan penyelesaian konflik sebelum perubahan digabungkan ke branch utama.

* **Riwayat Kolaborasi**: Git log menunjukkan aktivitas pengembangan kedua anggota sejak 5 November 2026 hingga 15 November 2026.

---

## 5. Penutup

Pembagian kontribusi dalam proyek UTS Struktur Data Kelompok 5 dilakukan berdasarkan tanggung jawab masing-masing anggota.

Nadin Mala Aprilia berkontribusi dalam pengembangan struktur data utama, pengelolaan dataset, pencarian, Hash Table, pengujian, serta dokumentasi.

Bima Nalasakti Nugraha berkontribusi dalam pengembangan AuditStack, parser CSV, operasi tambah-hapus, agregasi, pengurutan, ekspor laporan, serta pengujian dan integrasi program.

Kedua anggota juga bekerja sama dalam proses pengembangan, review, pengujian, dokumentasi, dan integrasi kode menuju branch utama.

Riwayat commit dan Pull Request pada GitHub digunakan sebagai bukti aktivitas pengembangan dan kolaborasi selama pengerjaan proyek ini.