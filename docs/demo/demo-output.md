# Dokumentasi Demonstrasi Aplikasi & Hasil Eksekusi (Demo Output)
Ujian Tengah Semester (UTS) - Struktur Data 2026/2027

Dokumen ini melampirkan ringkasan hasil demonstrasi interaktif dari sistem pengolahan data putusan pengadilan narkotika berbasis Java (menggunakan struktur data utama *Singly Linked List* dan struktur sekunder *AuditStack*).

---

## 1. Skenario Demonstrasi Fitur Utama

### A. Impor Data & Validasi Record
* **Aksi**: Program membaca file `putusan-50 Data Putusan.csv`, `putusan-50 Data Terdakwa.csv`, dan `putusan-50 Data Provenance.csv`.
* **Output Konsol**:
    - Data berhasil dimuat sepenuhnya tanpa menghentikan program meskipun ditemukan baris data korup/invalid (misalnya tanda `-` pada kolom angka).
    - Status *invariant* struktur data terverifikasi valid.

### B. Pencarian Data (Search) Berbasis Langkah (*Steps*)
* **Aksi**: Mencari perkara dengan `caseId` tertentu (contoh: `P001` dan `P050`).
* **Output Konsol**:
    - Perkara `P001` ditemukan dalam `1` langkah.
    - Perkara `P050` ditemukan dalam `124` langkah.
    - Data yang tidak ada (`X999`) terdeteksi tidak ditemukan setelah menelusuri seluruh list.

### C. Operasi Tambah, Hapus, & Invariant
* **Aksi**: Menghapus *node* di awal, tengah, dan akhir, lalu menambahkan kembali record baru.
* **Output Konsol**: Perubahan pointer berhasil dilakukan dan *invariant* Linked List tetap konsisten (*passed*).

### D. Fitur Filter, Agregasi, & Pengurutan (*Sorting*)
* **Aksi**: Memfilter data berdasarkan jenis narkotika (Sabu) serta mengurutkan daftar putusan berdasarkan masa vonis.
* **Output Konsol**: Hasil saringan dan urutan data ditampilkan secara terurut tanpa merusak struktur utama.

### E. Audit & Riwayat Operasi (Stack Sekunder)
* **Aksi**: Menampilkan riwayat aksi pengguna menggunakan struktur sekunder `AuditStack` dengan metode pencarian non-destruktif (`searchPreserve`).
* **Output Konsol**: Riwayat operasi terekam rapi dengan urutan LIFO yang terjaga.

### F. Ekspor Laporan
* **Aksi**: Menyimpan hasil pencarian atau rekapitulasi ke dalam file eksternal (TXT/CSV).
* **Output Konsol**: File hasil ekspor berhasil digenerate di direktori keluaran.

---

## 2. Bukti Pengujian Otomatis (Unit Testing)
Seluruh skenario di atas telah diuji secara otomatis melalui JUnit 5 (`MainTestBima.java`) dengan hasil **9/9 Tests Passed (100% Sukses)**.