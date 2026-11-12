
package com.kelompok5;

import com.kelompok5.manager.PutusanManager;
import com.kelompok5.model.Putusan;
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

    // IMPORT SELURUH DATA
    public void importData(
            String filePutusan,
            String fileTerdakwa,
            String fileProvenance,
            PutusanManager manager) {

        int berhasil = 0;
        int ditolak = 0;
        int jumlahBaris = 0;

        semuaTerdakwa.clear();

        // 1. Membaca seluruh data terdakwa
        System.out.println("\nMembaca data terdakwa...");

        try (Reader reader = Files.newBufferedReader(
                Path.of(fileTerdakwa), StandardCharsets.UTF_8);
             CSVParser parser = parser(reader)) {

            for (CSVRecord row : parser) {
                try {
                    TerdakwaData data = new TerdakwaData(
                            row.get("caseId"),
                            row.get("kodeTerdakwa"),
                            angka(row.get("tuntutanBulan")),
                            angka(row.get("vonisBulan"))
                    );

                    semuaTerdakwa.add(data);

                } catch (Exception e) {
                    System.out.println(
                            "Terdakwa baris " + row.getRecordNumber()
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
        DynamicArray<ProvenanceData> provenance =
                new DynamicArray<>();

        try (Reader reader = Files.newBufferedReader(
                Path.of(fileProvenance), StandardCharsets.UTF_8);
             CSVParser parser = parser(reader)) {

            for (CSVRecord row : parser) {
                try {
                    provenance.add(new ProvenanceData(
                            row.get("caseId"),
                            row.get("sourcePages")
                    ));

                } catch (Exception e) {
                    System.out.println(
                            "Provenance dilewati: " + e.getMessage()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println(
                    "Gagal membaca provenance: " + e.getMessage()
            );
            return;
        }

        // 3. Membaca maksimal 50 putusan
        try (Reader reader = Files.newBufferedReader(
                Path.of(filePutusan), StandardCharsets.UTF_8);
             CSVParser parser = parser(reader)) {

            for (CSVRecord row : parser) {
                jumlahBaris++;

                try {
                    if (jumlahBaris > 50) {
                        ditolak++;
                        continue;
                    }

                    String caseId = wajib(row.get("caseId"));

                    // Mengambil terdakwa pertama untuk ringkasan Putusan.
                    // Semua terdakwa tetap disimpan terpisah.
                    TerdakwaData terdakwa = cariTerdakwa(caseId);

                    if (terdakwa == null) {
                        throw new IllegalArgumentException(
                                "Data terdakwa tidak ditemukan."
                        );
                    }

                    String sourcePages =
                            cariSourcePages(caseId, provenance);

                    Putusan putusan = new Putusan(
                            caseId,
                            wajib(row.get("nomorPerkara")),
                            wajib(row.get("pengadilanNegeri")),
                            tanggal(row.get("tanggalPutusan")),
                            terdakwa.kode,
                            wajib(row.get("jenisNarkotika")),
                            desimal(row.get("beratBarangBuktiGram")),
                            row.get("pasalDakwaan"),
                            row.get("pasalTerbukti"),
                            terdakwa.tuntutan,
                            terdakwa.vonis,
                            bilanganPanjang(row.get("dendaRupiah")),
                            row.get("barangBukti"),
                            row.get("keadaanMemberatkan"),
                            row.get("keadaanMeringankan"),
                            sourcePages
                    );

                    if (manager.tambahPutusan(putusan)) {
                        berhasil++;
                    } else {
                        ditolak++;

                        System.out.println(
                                "Putusan duplikat ditolak: " + caseId
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

            // Hasil impor
            System.out.println("\n========== HASIL IMPOR ==========");
            System.out.println("Jumlah baris putusan : " + jumlahBaris);
            System.out.println("Putusan berhasil     : " + berhasil);
            System.out.println("Putusan ditolak      : " + ditolak);
            System.out.println(
                    "Jumlah di aplikasi   : " + manager.jumlahPutusan()
            );
            System.out.println(
                    "Jumlah data terdakwa : " + semuaTerdakwa.size()
            );
            System.out.println(
                    "Invariant valid      : " + manager.validateInvariant()
            );

            if (jumlahBaris != 50) {
                System.out.println(
                        "PERINGATAN: CSV seharusnya berisi 50 record."
                );
            }

        } catch (Exception e) {
            System.out.println(
                    "Gagal membaca CSV putusan: " + e.getMessage()
            );
        }
    }

    // MENAMPILKAN SEMUA PUTUSAN BESERTA SELURUH TERDAKWA
    public void tampilkanPutusanDanSeluruhTerdakwa(
            PutusanManager manager) {

        System.out.println(
                "\n======= DATA PUTUSAN DAN SELURUH TERDAKWA ======="
        );

        if (manager.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        for (int i = 0; i < manager.jumlahPutusan(); i++) {

            Putusan putusan = manager.getPutusan(i);

            System.out.println("\n========================================");
            System.out.println("DATA PUTUSAN KE-" + (i + 1));
            System.out.println("========================================");

            System.out.println("Case ID       : " + putusan.getCaseId());
            System.out.println(
                    "Nomor Perkara : " + putusan.getNomorPerkara()
            );
            System.out.println(
                    "Pengadilan    : " + putusan.getPengadilan()
            );
            System.out.println(
                    "Tanggal       : " + putusan.getTanggalPutusan()
            );
            System.out.println(
                    "Jenis Narkotika: " + putusan.getJenisNarkotika()
            );
            System.out.println(
                    "Berat Barang Bukti: "
                            + putusan.getBeratBarangBuktiGram() + " gram"
            );
            System.out.println(
                    "Pasal Terbukti : " + putusan.getPasalTerbukti()
            );
            System.out.println(
                    "Denda         : Rp" + putusan.getDendaRupiah()
            );

            System.out.println("\n--- SELURUH TERDAKWA ---");

            tampilkanTerdakwaBerdasarkanCaseId(
                    putusan.getCaseId()
            );
        }

        System.out.println("\n========================================");
        System.out.println(
                "TOTAL PUTUSAN   : " + manager.jumlahPutusan()
        );
        System.out.println(
                "TOTAL TERDAKWA  : " + semuaTerdakwa.size()
        );
        System.out.println("========================================");
    }

    // MENAMPILKAN TERDAKWA BERDASARKAN CASE ID
    public void tampilkanTerdakwaBerdasarkanCaseId(String caseId) {

        boolean ditemukan = false;
        int nomor = 1;

        for (int i = 0; i < semuaTerdakwa.size(); i++) {

            TerdakwaData data = semuaTerdakwa.get(i);

            if (data.caseId.equalsIgnoreCase(caseId.trim())) {

                System.out.println("\nTerdakwa ke-" + nomor);
                System.out.println("Case ID       : " + data.caseId);
                System.out.println("Kode Terdakwa : " + data.kode);
                System.out.println(
                        "Tuntutan      : " + data.tuntutan + " bulan"
                );
                System.out.println(
                        "Vonis         : " + data.vonis + " bulan"
                );

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

    // MENAMPILKAN SELURUH DATA TERDAKWA
    public void tampilkanSemuaTerdakwa() {

        System.out.println("\n======= DAFTAR SELURUH TERDAKWA =======");

        if (semuaTerdakwa.isEmpty()) {
            System.out.println("Belum ada data terdakwa.");
            return;
        }

        for (int i = 0; i < semuaTerdakwa.size(); i++) {

            TerdakwaData data = semuaTerdakwa.get(i);

            System.out.println(
                    (i + 1) + ". Case ID: " + data.caseId
                            + " | Kode: " + data.kode
                            + " | Tuntutan: " + data.tuntutan + " bulan"
                            + " | Vonis: " + data.vonis + " bulan"
            );
        }

        System.out.println(
                "\nTotal seluruh terdakwa: " + semuaTerdakwa.size()
        );
    }

    // MENGHITUNG RATA-RATA VONIS TERDAKWA
    public double rataRataVonisTerdakwa() {

        long total = 0;
        int jumlah = 0;

        for (int i = 0; i < semuaTerdakwa.size(); i++) {

            int vonis = semuaTerdakwa.get(i).vonis;

            if (vonis >= 0) {
                total += vonis;
                jumlah++;
            }
        }

        return jumlah == 0 ? 0 : (double) total / jumlah;
    }

    // MENGHITUNG JUMLAH TERDAKWA
    public int jumlahDataTerdakwa() {
        return semuaTerdakwa.size();
    }

    // MENCARI TERDAKWA PERTAMA BERDASARKAN CASE ID
    private TerdakwaData cariTerdakwa(String caseId) {

        for (int i = 0; i < semuaTerdakwa.size(); i++) {

            TerdakwaData data = semuaTerdakwa.get(i);

            if (data.caseId.equalsIgnoreCase(caseId)) {
                return data;
            }
        }

        return null;
    }

    // MENCARI HALAMAN SUMBER
    private String cariSourcePages(
            String caseId,
            DynamicArray<ProvenanceData> data) {

        StringBuilder hasil = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {

            ProvenanceData item = data.get(i);

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

    // FORMAT CSV
    private CSVParser parser(Reader reader) throws Exception {

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        return format.parse(reader);
    }

    // VALIDASI KOLOM WAJIB
    private String wajib(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Kolom wajib tidak boleh kosong."
            );
        }

        return value.trim();
    }

    // KONVERSI INTEGER
    private int angka(String value) {

        if (value == null || value.isBlank()
                || value.trim().equals("-")) {
            return 0;
        }

        return Integer.parseInt(value.trim());
    }

    // KONVERSI DESIMAL
    private double desimal(String value) {

        if (value == null || value.isBlank()) {
            return 0;
        }

        return Double.parseDouble(
                value.trim().replace(",", ".")
        );
    }

    // KONVERSI LONG
    private long bilanganPanjang(String value) {

        if (value == null || value.isBlank()) {
            return 0;
        }

        String angka = value.trim().replaceAll("[^0-9-]", "");

        return angka.isBlank() ? 0 : Long.parseLong(angka);
    }

    // KONVERSI TANGGAL
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

    // MODEL DATA TERDAKWA
    private static class TerdakwaData {

        String caseId;
        String kode;
        int tuntutan;
        int vonis;

        TerdakwaData(
                String caseId,
                String kode,
                int tuntutan,
                int vonis) {

            if (caseId == null || caseId.isBlank()
                    || kode == null || kode.isBlank()) {

                throw new IllegalArgumentException(
                        "Case ID atau kode terdakwa kosong."
                );
            }

            this.caseId = caseId.trim();
            this.kode = kode.trim();
            this.tuntutan = tuntutan;
            this.vonis = vonis;
        }
    }

    // MODEL DATA PROVENANCE
    private static class ProvenanceData {

        String caseId;
        String pages;

        ProvenanceData(String caseId, String pages) {

            this.caseId = caseId == null ? "" : caseId.trim();
            this.pages = pages == null ? "" : pages.trim();
        }
    }
}
