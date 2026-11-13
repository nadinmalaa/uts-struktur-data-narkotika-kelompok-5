# Catatan Penggunaan Kecerdasan Buatan (AI Usage Log)

Dokumen ini disusun sebagai bentuk transparansi akademik terkait pemanfaatan asisten berbasis Kecerdasan Buatan (AI) selama pengerjaan **UTS Struktur Data (Kelompok 5)**.

## Alat AI yang Digunakan
* **Gemini (Google AI)**

---

## Ruang Lingkup Penggunaan

Penggunaan AI pada proyek ini difokuskan sebagai rekan diskusi teknis (*thought partner*), alat bantu *debugging*, dan penyusunan draf dokumentasi, dengan rincian sebagai berikut:

1. **Konsultasi Desain & Struktur Data**:
    * Mendiskusikan perbandingan efisiensi dan kesesuaian antara penggunaan `SinglyLinkedList` sebagai struktur utama serta `AuditStack` (LIFO) untuk kebutuhan audit log.
    * Memverifikasi konsep penjagaan *invariant* struktur data dan operasi pencarian non-destruktif (`searchPreserve`).
2. **Debugging & Penyesuaian Path / Sintaks**:
    * Membantu mendiagnosis kendala teknis seperti ketidaksesuaian *relative path* direktori file CSV (`data/putusan-50.csv`) saat menjalankan pengujian unit JUnit 5 di IntelliJ IDEA.
    * Membantu meninjau struktur pengujian unit pada `MainTestBima.java` agar seluruh skenario manipulasi *node* dan pemuatan dataset berjalan sesuai kriteria.
3. **Penyusunan Kerangka Dokumentasi**:
    * Membantu menyusun kerangka penulisan *Architecture Decision Records* (`001-pemilihan-struktur-data.md` dan `002-strategi-pencarian-pengurutan.md`), `README.md`, serta `CONTRIBUTIONS.md` agar terstruktur rapi sesuai standar dokumentasi perangkat lunak.

---

## Batasan & Validasi Mandiri

Seluruh keluaran (*output*) dari AI telah melalui proses peninjauan, penyesuaian, dan pengujian mandiri oleh anggota kelompok:
* **Ekstraksi Data Hukum**: Seluruh 50 data putusan narkotika dikumpulkan, dibaca, dan diverifikasi secara manual dari dokumen asli Direktori Putusan Mahkamah Agung RI (`P001`–`P050`).
* **Verifikasi Kode**: Seluruh implementasi kode Java telah dikompilasi dan diuji secara lokal untuk memastikan logika berjalan akurat dan bebas dari *bug*.