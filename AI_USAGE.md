# Catatan Penggunaan Kecerdasan Buatan (AI Usage Log)

Dokumen ini disusun sebagai bentuk transparansi akademik terkait pemanfaatan asisten berbasis Kecerdasan Buatan (AI) selama pengerjaan **UTS Struktur Data (Kelompok 5)**.

## Alat AI yang Digunakan
- **Gemini (Google AI)** – Berfungsi sebagai rekan diskusi teknis (*collaborative pair programmer*), alat bantu *debugging*, dan penyusunan draf dokumentasi.

---

## Ruang Lingkup Penggunaan & Kontribusi

Penggunaan AI pada proyek ini difokuskan pada beberapa aspek teknis dan administratif berikut:

1. **Resolusi Konflik & Refaktorisasi Kode:**
    - Membantu menganalisis dan membersihkan duplikasi kode (*duplicate methods*) serta konflik *merge* pada kelas-kelas model (`Putusan`, `Terdakwa`, `Provenance`) dan normalisasi dependensi pada `pom.xml`.

2. **Konsultasi Desain & Struktur Data:**
    - Mendiskusikan implementasi `SinglyLinkedList` sebagai struktur penyimpanan data utama serta `AuditStack` (LIFO) untuk kebutuhan riwayat audit log.
    - Memverifikasi konsep penjagaan *invariant* struktur data, operasi penelusuran (*traversal steps*), dan pencarian non-destruktif (`searchPreserve`).

3. **Debugging & Pengujian Unit (TestNG):**
    - Membantu mendiagnosis kendala teknis dan penyesuaian kerangka pengujian unit dari JUnit ke **TestNG** pada `MainTestBima.java`.
    - Membantu mencocokkan jumlah langkah traversal (*traversal steps*) pada dataset putusan pengadilan hingga seluruh skenario pengujian berhasil lolos 100% (*9/9 passes*).

4. **Penyusunan Kerangka Dokumentasi:**
    - Membantu menyusun kerangka penulisan dokumentasi teknis seperti `README.md`, `CONTRIBUTIONS.md`, serta catatan arsitektur sistem agar terstruktur rapi sesuai standar perangkat lunak.

---

## Verifikasi dan Integritas Akademik (Human-in-the-Loop)
Seluruh kode, draf logika, dan hasil yang didiskusikan bersama AI **tidak diadopsi secara mentah**. Seluruhnya melalui proses *review*, pengujian manual, dan penyesuaian ulang secara langsung oleh anggota kelompok (Bima & Nadin) di lingkungan IntelliJ IDEA hingga sistem terbukti stabil, akurat, dan lolos seluruh pengujian.