package service;

import datastructure.DynamicArray;
import model.Putusan;

import java.io.FileWriter;
import java.io.IOException;

public class PutusanAggregator {
    private DynamicArray<Putusan> daftarPutusan;

    public PutusanAggregator(DynamicArray<Putusan> daftarPutusan) {
        this.daftarPutusan = daftarPutusan;
    }

    // 1. Menghitung total kasus
    public int hitungTotalKasus() {
        return daftarPutusan.size();
    }

    // 2. Menghitung rata-rata lama vonis (dalam bulan)
    public double hitungRataRataVonis() {
        int totalKasus = daftarPutusan.size();
        if (totalKasus == 0) return 0.0;

        long totalBulan = 0;
        for (int i = 0; i < totalKasus; i++) {
            totalBulan += daftarPutusan.get(i).getVonisBulan();
        }
        return (double) totalBulan / totalKasus;
    }

    // 3. Menghitung jumlah kasus berdasarkan jenis narkotika
    public int hitungBerdasarkanJenisNarkotika(String jenisTarget) {
        int count = 0;
        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            if (p.getJenisNarkotika() != null && p.getJenisNarkotika().equalsIgnoreCase(jenisTarget)) {
                count++;
            }
        }
        return count;
    }

    // 4. Fitur Pengurutan Vonis (Sorting) Menggunakan Bubble Sort (Ascending)
    public void sortByVonisAscending() {
        int n = daftarPutusan.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Putusan p1 = daftarPutusan.get(j);
                Putusan p2 = daftarPutusan.get(j + 1);

                if (p1.getVonisBulan() > p2.getVonisBulan()) {
                    // Tukar posisi (Swap)
                    daftarPutusan.set(j, p2);
                    daftarPutusan.set(j + 1, p1);
                }
            }
        }
    }

    // 5. Fitur Ekspor Data: Menghasilkan string laporan ringkasan
    public String generateLaporanRingkasan() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== LAPORAN REKAPITULASI PUTUSAN KASUS ===\n");
        sb.append("Total Kasus Terdaftar : ").append(hitungTotalKasus()).append("\n");
        sb.append("Rata-rata Lama Vonis   : ").append(String.format("%.2f", hitungRataRataVonis())).append(" bulan\n");
        sb.append("------------------------------------------\n");
        sb.append("Daftar Urutan Putusan (Berdasarkan Vonis):\n");

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            sb.append(i + 1).append(". Nomor: ").append(p.getNomorPerkara())
                    .append(" | Vonis: ").append(p.getVonisBulan()).append(" bulan")
                    .append(" | Jenis: ").append(p.getJenisNarkotika()).append("\n");
        }

        return sb.toString();
    }

    // 6. Fitur Ekspor Data ke File Fisik (txt)
    public void eksporLaporanKeFile(String namaFile) {
        String isiLaporan = generateLaporanRingkasan();
        try (FileWriter writer = new FileWriter(namaFile)) {
            writer.write(isiLaporan);
            System.out.println("Berhasil mengekspor laporan ke file: " + namaFile);
        } catch (IOException e) {
            System.err.println("Gagal menulis file ekspor: " + e.getMessage());
        }
    }
}