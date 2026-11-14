import datastructure.SinglyLinkedList;
import model.Putusan;
import org.testng.annotations.Test;
import service.CsvParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.testng.Assert.*;

public class MainTestBima {

    @Test
    public void testPR2AgregasiSortingDanEkspor() {
        // Menggunakan nama file yang sesuai persis dengan panel project: "putusan-50 - Data Putusan.csv"
        String pathCsv = "data/putusan-50 - Data Putusan.csv";

        // Validasi fisik file untuk memastikan terbaca dengan benar
        File fileCek = new File(pathCsv);
        assertTrue(fileCek.exists(), "File CSV fisik tidak ditemukan pada path: " + fileCek.getAbsolutePath());

        SinglyLinkedList<Putusan> dataset = CsvParser.parseCsv(pathCsv);
        assertNotNull(dataset, "Dataset tidak boleh null");
        assertFalse(dataset.isEmpty(), "Dataset harus memuat data CSV");

        int totalKasus = dataset.size();
        assertTrue(totalKasus > 0, "Total kasus harus lebih dari 0");

        long totalVonis = 0;
        for (int i = 0; i < dataset.size(); i++) {
            totalVonis += dataset.get(i).getVonisBulan();
        }
        double rataRataVonis = (double) totalVonis / totalKasus;

        System.out.println("=== HASIL AGREGASI PR 2 ===");
        System.out.println("Total Kasus : " + totalKasus);
        System.out.println("Rata-rata Konversi Vonis: " + String.format("%.2f", rataRataVonis) + " bulan");

        String namaFileExport = "laporan_pr2_rekapitulasi.txt";
        StringBuilder sb = new StringBuilder();
        sb.append("=== LAPORAN REKAPITULASI PUTUSAN (PR 2) ===\n");
        sb.append("Total Kasus Terdaftar : ").append(totalKasus).append("\n");
        sb.append("Rata-rata Lama Vonis   : ").append(String.format("%.2f", rataRataVonis)).append(" bulan\n");
        sb.append("------------------------------------------\n");

        try (FileWriter writer = new FileWriter(namaFileExport)) {
            writer.write(sb.toString());
            System.out.println("Berhasil mengekspor laporan PR 2 ke file: " + namaFileExport);
        } catch (IOException e) {
            fail("Gagal menulis file ekspor: " + e.getMessage());
        }

        assertTrue(totalKasus > 0, "Pengujian PR 2 selesai dengan sukses");
    }
}