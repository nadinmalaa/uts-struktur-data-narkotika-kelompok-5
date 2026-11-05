
package com.kelompok5;

import com.kelompok5.manager.PutusanManager;
import com.kelompok5.manager.AuditLog;
import com.kelompok5.model.Putusan;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PutusanManager manager = new PutusanManager();
        AuditLog audit = new AuditLog();

        // 1. MEMBUAT DATA PUTUSAN
        Putusan p1 = new Putusan(
                "P001", "12/Pid.Sus/2025/PN Dps", "PN Denpasar",
                LocalDate.of(2025, 1, 10), "Terdakwa-001", "Sabu",
                5.5, "Pasal 112", "Pasal 112", 120, 96,
                800000000, "5,5 gram sabu", "Jumlah barang bukti",
                "Bersikap sopan", "Halaman 1-10"
        );

        Putusan p2 = new Putusan(
                "P002", "15/Pid.Sus/2025/PN Dps", "PN Denpasar",
                LocalDate.of(2025, 2, 10), "Terdakwa-002", "Ganja",
                10.0, "Pasal 111", "Pasal 111", 60, 48,
                500000000, "10 gram ganja", "Barang bukti",
                "Bersikap sopan", "Halaman 1-8"
        );

        Putusan p3 = new Putusan(
                "P003", "20/Pid.Sus/2025/PN Dps", "PN Denpasar",
                LocalDate.of(2025, 3, 10), "Terdakwa-003", "Sabu",
                8.0, "Pasal 112", "Pasal 112", 90, 72,
                700000000, "8 gram sabu", "Barang bukti",
                "Mengakui perbuatan", "Halaman 1-9"
        );

        // 2. TEST TAMBAH DATA
        manager.tambahPutusan(p1);
        manager.tambahPutusan(p2);
        manager.tambahPutusan(p3);

        audit.catatAktivitas("Menambahkan putusan P001");
        audit.catatAktivitas("Menambahkan putusan P002");
        audit.catatAktivitas("Menambahkan putusan P003");

        System.out.println("=== DATA PUTUSAN ===");
        manager.tampilkanSemuaPutusan();

        // 3. TEST PENCARIAN
        System.out.println("\n=== TEST PENCARIAN ===");
        Putusan hasil = manager.cariBerdasarkanCaseId("P001");

        if (hasil != null) {
            System.out.println("Data ditemukan: " + hasil);
        } else {
            System.out.println("Data tidak ditemukan");
        }

        // 4. TEST STATISTIK
        System.out.println("\n=== STATISTIK ===");
        System.out.println("Jumlah putusan: " + manager.jumlahPutusan());
        System.out.println("Rata-rata vonis: " + manager.rataRataVonis());
        System.out.println("Total denda: " + manager.totalDenda());

        // 5. TEST SORTING
        System.out.println("\n=== TEST SORTING ===");
        manager.urutkanBerdasarkanVonis();
        manager.tampilkanSemuaPutusan();

        // 6. TEST AUDIT LOG
        System.out.println("\n=== AUDIT LOG ===");
        audit.tampilkanSemuaAktivitas();

        // 7. TEST PENGHAPUSAN
        System.out.println("\n=== TEST PENGHAPUSAN ===");
        boolean berhasil = manager.hapusPutusan("P001");

        if (berhasil) {
            System.out.println("Putusan P001 berhasil dihapus.");
        } else {
            System.out.println("Putusan P001 gagal dihapus.");
        }

        System.out.println("Jumlah putusan setelah dihapus: "
                + manager.jumlahPutusan());

        // 8. VALIDASI
        System.out.println("\n=== VALIDASI ===");
        System.out.println("DynamicArray valid: "
                + manager.validateInvariant());
        System.out.println("Stack valid: "
                + audit.validateInvariant());
    }
}
