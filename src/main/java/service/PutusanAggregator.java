package service;

import com.kelompok5.manager.PutusanManager;
import com.kelompok5.model.Putusan;
import com.kelompok5.model.TerdakwaData;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class PutusanAggregator {

    private final PutusanManager manager;

    public PutusanAggregator(PutusanManager manager) {
        if (manager == null) {
            throw new IllegalArgumentException(
                    "PutusanManager tidak boleh null");
        }
        this.manager = manager;
    }

    public int hitungTotalKasus() {
        return manager.jumlahPutusan();
    }

    public double hitungRataRataVonis() {
        return manager.rataRataVonis();
    }

    public int hitungBerdasarkanJenisNarkotika(String jenisTarget) {
        if (jenisTarget == null || jenisTarget.isBlank()) {
            throw new IllegalArgumentException(
                    "Jenis narkotika tidak boleh kosong");
        }

        int jumlah = 0;

        for (int i = 0; i < manager.jumlahPutusan(); i++) {
            Putusan putusan = manager.getPutusan(i);
            String jenis = putusan.getJenisNarkotika();

            if (jenis != null
                    && jenis.equalsIgnoreCase(jenisTarget.trim())) {
                jumlah++;
            }
        }

        return jumlah;
    }

    public String generateLaporanRingkasan() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== LAPORAN REKAPITULASI PUTUSAN KASUS ===\n");
        sb.append("Total Kasus       : ")
                .append(hitungTotalKasus()).append("\n");
        sb.append("Total Terdakwa    : ")
                .append(manager.jumlahSeluruhTerdakwa()).append("\n");
        sb.append("Rata-rata Vonis   : ")
                .append(String.format(
                        Locale.US, "%.2f", hitungRataRataVonis()))
                .append(" bulan\n");
        sb.append("Total Denda       : Rp ")
                .append(manager.totalDenda()).append("\n");
        sb.append("------------------------------------------\n");
        sb.append("DAFTAR PERKARA DAN TERDAKWA\n");

        for (int i = 0; i < manager.jumlahPutusan(); i++) {
            Putusan putusan = manager.getPutusan(i);

            sb.append("\n").append(i + 1).append(". ")
                    .append(putusan.getNomorPerkara()).append("\n");

            sb.append("   Jenis Narkotika: ")
                    .append(putusan.getJenisNarkotika()).append("\n");

            for (int j = 0; j < putusan.jumlahTerdakwa(); j++) {
                TerdakwaData terdakwa = putusan.getTerdakwa(j);

                sb.append("   - ").append(terdakwa.getKode())
                        .append(" | Tuntutan: ")
                        .append(terdakwa.getTuntutanBulan())
                        .append(" bulan | Vonis: ");

                if (terdakwa.getVonisBulan() == 0) {
                    sb.append("Tidak ditemukan\n");
                } else {
                    sb.append(terdakwa.getVonisBulan())
                            .append(" bulan\n");
                }
            }
        }

        return sb.toString();
    }

    public void eksporLaporanKeFile(String namaFile) throws IOException {
        if (namaFile == null || namaFile.isBlank()) {
            throw new IllegalArgumentException(
                    "Nama file tidak boleh kosong");
        }

        Path path = Path.of(namaFile);

        try (BufferedWriter writer = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8)) {
            writer.write(generateLaporanRingkasan());
        }
    }
}
