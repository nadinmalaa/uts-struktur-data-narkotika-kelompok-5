package com.kelompok5;

import com.kelompok5.manager.PutusanManager;
import com.kelompok5.model.Putusan;
import com.kelompok5.model.TerdakwaData;
import com.kelompok5.structure.DynamicArray;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PutusanCSVReader {

    private final DynamicArray<TerdakwaData> semuaTerdakwa =
            new DynamicArray<>();

    private final DynamicArray<ProvenanceData> semuaProvenance =
            new DynamicArray<>();

    // =====================================================
    // IMPORT DATA
    // =====================================================

    public void importData(
            String filePutusan,
            String fileTerdakwa,
            String fileProvenance,
            PutusanManager manager) {

        if (manager == null) {
            System.out.println("Manager tidak boleh null.");
            return;
        }

        semuaTerdakwa.clear();
        semuaProvenance.clear();

        int berhasil = 0;
        int ditolak = 0;
        int jumlahBaris = 0;

        // 1. Membaca data terdakwa
        System.out.println("\nMembaca data terdakwa...");

        try (Reader reader = Files.newBufferedReader(
                Path.of(fileTerdakwa), StandardCharsets.UTF_8);
             CSVParser parser = parser(reader)) {

            for (CSVRecord row : parser) {
                try {
                    TerdakwaData data = new TerdakwaData(
                            wajib(row.get("caseId")),
                            wajib(row.get("kodeTerdakwa")),
                            angkaWajib(row.get("tuntutanBulan")),
                            angkaWajib(row.get("vonisBulan"))
                    );

                    semuaTerdakwa.add(data);

                } catch (Exception e) {
                    System.out.println(
                            "Data terdakwa baris "
                                    + row.getRecordNumber()
                                    + " ditolak: " + e.getMessage()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println(
                    "Gagal membaca CSV terdakwa: " + e.getMessage()
            );
            return;
        }

        // 2. Membaca data provenance
        System.out.println("Membaca data provenance...");

        try (Reader reader = Files.newBufferedReader(
                Path.of(fileProvenance), StandardCharsets.UTF_8);
             CSVParser parser = parser(reader)) {

            for (CSVRecord row : parser) {
                try {
                    semuaProvenance.add(
                            new ProvenanceData(
                                    wajib(row.get("caseId")),
                                    nilai(row.get("sourcePages"))
                            )
                    );

                } catch (Exception e) {
                    System.out.println(
                            "Provenance baris "
                                    + row.getRecordNumber()
                                    + " dilewati: " + e.getMessage()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println(
                    "Gagal membaca CSV provenance: " + e.getMessage()
            );
            return;
        }

        // 3. Membaca data putusan
        System.out.println("Membaca data putusan...");

        try (Reader reader = Files.newBufferedReader(
                Path.of(filePutusan), StandardCharsets.UTF_8);
             CSVParser parser = parser(reader)) {

            for (CSVRecord row : parser) {
                jumlahBaris++;

                try {
                    String caseId = wajib(row.get("caseId"));

                    // Buat satu objek Putusan untuk satu perkara.
                    Putusan putusan = new Putusan(
                            caseId,
                            wajib(row.get("nomorPerkara")),
                            wajib(row.get("pengadilanNegeri")),
                            tanggal(row.get("tanggalPutusan")),
                            wajib(row.get("jenisNarkotika")),
                            desimalWajib(row.get("beratBarangBuktiGram")),
                            nilai(row.get("pasalDakwaan")),
                            nilai(row.get("pasalTerbukti")),
                            bilanganPanjang(row.get("dendaRupiah")),
                            nilai(row.get("barangBukti")),
                            nilai(row.get("keadaanMemberatkan")),
                            nilai(row.get("keadaanMeringankan")),
                            cariSourcePages(caseId)
                    );

                    // Masukkan SEMUA terdakwa yang memiliki caseId sama.
                    int jumlahTerdakwa = 0;

                    for (int i = 0; i < semuaTerdakwa.size(); i++) {
                        TerdakwaData terdakwa = semuaTerdakwa.get(i);

                        if (terdakwa.getCaseId().equalsIgnoreCase(caseId)) {
                            putusan.tambahTerdakwa(terdakwa);
                            jumlahTerdakwa++;
                        }
                    }

                    if (jumlahTerdakwa == 0) {
                        throw new IllegalArgumentException(
                                "Tidak ditemukan data terdakwa untuk perkara "
                                        + caseId
                        );
                    }

                    // Masukkan perkara ke manager.
                    if (manager.tambahPutusan(putusan)) {
                        berhasil++;
                    } else {
                        ditolak++;

                        System.out.println(
                                "Putusan gagal ditambahkan atau duplikat: "
                                        + caseId
                        );
                    }

                } catch (Exception e) {
                    ditolak++;

                    System.out.println(
                            "Putusan baris " + row.getRecordNumber()
                                    + " ditolak: " + e.getMessage()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println(
                    "Gagal membaca CSV putusan: " + e.getMessage()
            );
            return;
        }

        // 4. Menampilkan hasil impor
        System.out.println("\n========== HASIL IMPOR ==========");
        System.out.println("Jumlah baris putusan : " + jumlahBaris);
        System.out.println("Putusan berhasil     : " + berhasil);
        System.out.println("Putusan ditolak      : " + ditolak);
        System.out.println("Jumlah di aplikasi   : " + manager.jumlahPutusan());
        System.out.println("Jumlah data terdakwa : " + semuaTerdakwa.size());
        System.out.println("Invariant valid      : " + manager.validateInvariant());

        if (jumlahBaris != 50) {
            System.out.println(
                    "PERINGATAN: Dataset seharusnya berisi 50 perkara."
            );
        }

        if (berhasil != 50) {
            System.out.println(
                    "PERINGATAN: Belum seluruh 50 perkara berhasil diimpor."
            );
        }

        if (semuaTerdakwa.size() != 55) {
            System.out.println(
                    "PERINGATAN: Jumlah data terdakwa seharusnya 55."
            );
        }
    }

    // =====================================================
    // MENAMPILKAN PUTUSAN DAN SELURUH TERDAKWA
    // =====================================================

    public void tampilkanPutusanDanSeluruhTerdakwa(
            PutusanManager manager) {

        System.out.println(
                "\n======= DATA PUTUSAN DAN SELURUH TERDAKWA ======="
        );

        if (manager == null || manager.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        for (int i = 0; i < manager.jumlahPutusan(); i++) {
            Putusan putusan = manager.getPutusan(i);

            System.out.println("\n========================================");
            System.out.println("DATA PUTUSAN KE-" + (i + 1));
            System.out.println("========================================");

            System.out.println(putusan);
        }

        System.out.println("\n========================================");
        System.out.println("TOTAL PUTUSAN  : " + manager.jumlahPutusan());
        System.out.println("TOTAL TERDAKWA : " + semuaTerdakwa.size());
        System.out.println("========================================");
    }

    // =====================================================
    // MENAMPILKAN TERDAKWA BERDASARKAN CASE ID
    // =====================================================

    public void tampilkanTerdakwaBerdasarkanCaseId(String caseId) {

        if (caseId == null || caseId.isBlank()) {
            System.out.println("Case ID tidak valid.");
            return;
        }

        boolean ditemukan = false;
        int nomor = 1;

        for (int i = 0; i < semuaTerdakwa.size(); i++) {
            TerdakwaData data = semuaTerdakwa.get(i);

            if (data.getCaseId().equalsIgnoreCase(caseId.trim())) {
                System.out.println("\nTerdakwa ke-" + nomor);
                System.out.println(data);

                nomor++;
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Data terdakwa tidak ditemukan.");
        } else {
            System.out.println(
                    "Jumlah terdakwa dalam perkara ini: " + (nomor - 1)
            );
        }
    }

    // =====================================================
    // MENAMPILKAN SELURUH DATA TERDAKWA
    // =====================================================

    public void tampilkanSemuaTerdakwa() {

        System.out.println("\n======= DAFTAR SELURUH TERDAKWA =======");

        if (semuaTerdakwa.isEmpty()) {
            System.out.println("Belum ada data terdakwa.");
            return;
        }

        for (int i = 0; i < semuaTerdakwa.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + semuaTerdakwa.get(i)
            );
        }

        System.out.println(
                "\nTotal seluruh terdakwa: " + semuaTerdakwa.size()
        );
    }

    // =====================================================
    // MENGHITUNG RATA-RATA VONIS TERDAKWA
    // =====================================================

    public double rataRataVonisTerdakwa() {

        long total = 0;
        int jumlah = 0;

        for (int i = 0; i < semuaTerdakwa.size(); i++) {
            TerdakwaData data = semuaTerdakwa.get(i);

            total += data.getVonisBulan();
            jumlah++;
        }

        return jumlah == 0 ? 0 : (double) total / jumlah;
    }

    // =====================================================
    // MENGHITUNG JUMLAH TERDAKWA
    // =====================================================

    public int jumlahDataTerdakwa() {
        return semuaTerdakwa.size();
    }

    // =====================================================
    // MENCARI HALAMAN SUMBER PROVENANCE
    // =====================================================

    private String cariSourcePages(String caseId) {

        StringBuilder hasil = new StringBuilder();

        for (int i = 0; i < semuaProvenance.size(); i++) {
            ProvenanceData item = semuaProvenance.get(i);

            if (item.caseId.equalsIgnoreCase(caseId)
                    && !item.pages.isBlank()) {

                if (hasil.length() > 0) {
                    hasil.append(", ");
                }

                hasil.append(item.pages);
            }
        }

        return hasil.toString();
    }

    // =====================================================
    // FORMAT CSV
    // =====================================================

    private CSVParser parser(Reader reader) throws Exception {

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        return format.parse(reader);
    }

    // =====================================================
    // VALIDASI STRING WAJIB
    // =====================================================

    private String wajib(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Kolom wajib tidak boleh kosong."
            );
        }

        return value.trim();
    }

    // =====================================================
    // MENGAMBIL STRING OPSIONAL
    // =====================================================

    private String nilai(String value) {
        return value == null ? "" : value.trim();
    }

    // =====================================================
    // KONVERSI INTEGER WAJIB
    // =====================================================

    private int angkaWajib(String value) {

        if (value == null || value.isBlank()
                || value.trim().equals("-")) {
            throw new IllegalArgumentException(
                    "Nilai tuntutan atau vonis kosong."
            );
        }

        int hasil = Integer.parseInt(value.trim());

        if (hasil < 0) {
            throw new IllegalArgumentException(
                    "Nilai angka tidak boleh negatif."
            );
        }

        return hasil;
    }

    // =====================================================
    // KONVERSI DESIMAL WAJIB
    // =====================================================

    private double desimalWajib(String value) {

        if (value == null || value.isBlank()
                || value.trim().equals("-")) {
            throw new IllegalArgumentException(
                    "Berat barang bukti kosong."
            );
        }

        double hasil = Double.parseDouble(
                value.trim().replace(",", ".")
        );

        if (!Double.isFinite(hasil) || hasil < 0) {
            throw new IllegalArgumentException(
                    "Berat barang bukti tidak valid."
            );
        }

        return hasil;
    }

    // =====================================================
    // KONVERSI LONG
    // =====================================================

    private long bilanganPanjang(String value) {

        if (value == null || value.isBlank()
                || value.trim().equals("-")) {
            return 0L;
        }

        String angka = value.trim()
                .replaceAll("[^0-9]", "");

        if (angka.isBlank()) {
            return 0L;
        }

        return Long.parseLong(angka);
    }

    // =====================================================
    // KONVERSI TANGGAL
    // =====================================================

    private LocalDate tanggal(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Tanggal putusan kosong."
            );
        }

        String tanggal = value.trim();

        try {
            return LocalDate.parse(tanggal);

        } catch (Exception ignored) {
            return LocalDate.parse(
                    tanggal,
                    DateTimeFormatter.ofPattern("d/M/yyyy")
            );
        }
    }

    // =====================================================
    // MODEL DATA PROVENANCE
    // =====================================================

    private static class ProvenanceData {

        private final String caseId;
        private final String pages;

        ProvenanceData(String caseId, String pages) {

            if (caseId == null || caseId.isBlank()) {
                throw new IllegalArgumentException(
                        "Case ID provenance kosong."
                );
            }

            this.caseId = caseId.trim();
            this.pages = pages == null ? "" : pages.trim();
        }
    }
}
