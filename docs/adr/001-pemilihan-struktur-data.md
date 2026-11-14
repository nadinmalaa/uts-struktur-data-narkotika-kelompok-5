# ADR 001: Pemilihan Struktur Data Utama & Sekunder Berbasis Relasional

## Status
Diterima (Diperbarui sesuai arsitektur relasional 3 entitas)

## Konteks
Aplikasi perlu menyimpan dan mengelola dataset 50 putusan pengadilan narkotika yang telah dimodulasi menjadi tiga entitas relasional berbasis `caseId`:
1. **`Putusan`** (data inti perkara dan amar putusan)
2. **`Terdakwa`** (data identitas anonim dan tuntutan/vonis per terdakwa dengan relasi *One-to-Many*)
3. **`Provenance`** (metadata asal-usul, halaman sumber, dan status verifikasi per atribut)

Sesuai ketentuan penugasan, seluruh penyimpanan inti harus dibangun secara generik dari nol (`<T>`) tanpa menggunakan koleksi bawaan `java.util` (seperti `ArrayList` atau `LinkedList`), serta wajib menjaga konsistensi pointer melalui metode `validateInvariant()`.

## Keputusan
1. **Struktur Data Utama — `SinglyLinkedList<T>`**:
   Kami memilih **Singly Linked List** generik buatan sendiri sebagai struktur data utama untuk menampung ketiga dataset (`SinglyLinkedList<Putusan>`, `SinglyLinkedList<Terdakwa>`, dan `SinglyLinkedList<Provenance>`).
2. **Struktur Data Sekunder — `AuditStack<T>`**:
   Kami memilih **Stack** generik berbasis prinsip LIFO (*Last-In, First-Out*) sebagai struktur data sekunder untuk mencatat riwayat operasi pengguna (*audit trail*) dan mendukung pencarian non-destruktif (`searchPreserve`).

## Konsekuensi
* **Kelebihan**:
  * Alokasi memori dinamis tanpa batasan kapasitas tetap (*fixed capacity*) saat memuat jumlah baris yang bervariasi antar tabel relasional (`Putusan`, `Terdakwa`, `Provenance`).
  * Operasi penyisipan dan penghapusan node pada posisi awal, tengah, maupun akhir dapat dilakukan secara efisien melalui manipulasi pointer `head` dan `tail`.
  * Keutuhan struktur data dijamin secara deterministik lewat pengecekan `validateInvariant()` pada setiap operasi mutasi.
* **Kekurangan & Mitigasi**:
  * Akses elemen dan proses pencocokan relasi (`join` berdasarkan `caseId`) memiliki kompleksitas waktu $O(n)$ karena harus menelusuri pointer dari `head`. Hal ini dimitigasi dengan menyediakan metode pencarian terstruktur (`searchByCaseId`) yang melacak jumlah langkah (*steps*) secara akurat.