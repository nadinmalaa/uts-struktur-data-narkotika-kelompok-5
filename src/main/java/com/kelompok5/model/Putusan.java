package com.kelompok5.model;

import java.time.LocalDate;

public class Putusan {

    private String caseId;
    private String nomorPerkara;
    private String pengadilan;
    private LocalDate tanggalPutusan;
    private String terdakwaKode;
    private String jenisNarkotika;
    private double beratBarangBuktiGram;
    private String pasalDakwaan;
    private String pasalTerbukti;
    private int tuntutanBulan;
    private int vonisBulan;
    private long dendaRupiah;
    private String barangBukti;
    private String keadaanMemberatkan;
    private String keadaanMeringankan;
    private String sourcePages;

    public Putusan(
            String caseId,
            String nomorPerkara,
            String pengadilan,
            LocalDate tanggalPutusan,
            String terdakwaKode,
            String jenisNarkotika,
            double beratBarangBuktiGram,
            String pasalDakwaan,
            String pasalTerbukti,
            int tuntutanBulan,
            int vonisBulan,
            long dendaRupiah,
            String barangBukti,
            String keadaanMemberatkan,
            String keadaanMeringankan,
            String sourcePages) {

        this.caseId = requireText(caseId, "caseId");
        this.nomorPerkara = requireText(nomorPerkara, "nomorPerkara");
        this.pengadilan = requireText(pengadilan, "pengadilan");
        this.tanggalPutusan = tanggalPutusan;
        this.terdakwaKode = requireText(terdakwaKode, "terdakwaKode");
        this.jenisNarkotika = normalize(jenisNarkotika);

        if (beratBarangBuktiGram < 0 || tuntutanBulan < 0
                || vonisBulan < 0 || dendaRupiah < 0) {
            throw new IllegalArgumentException(
                    "Nilai numerik tidak boleh negatif");
        }

        this.beratBarangBuktiGram = beratBarangBuktiGram;
        this.pasalDakwaan = normalize(pasalDakwaan);
        this.pasalTerbukti = normalize(pasalTerbukti);
        this.tuntutanBulan = tuntutanBulan;
        this.vonisBulan = vonisBulan;
        this.dendaRupiah = dendaRupiah;
        this.barangBukti = normalize(barangBukti);
        this.keadaanMemberatkan = normalize(keadaanMemberatkan);
        this.keadaanMeringankan = normalize(keadaanMeringankan);
        this.sourcePages = normalize(sourcePages);
    }

    private String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " tidak boleh kosong");
        }
        return value.trim();
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
    public String getCaseId() {
        return caseId;
    }
    public String getNomorPerkara() {
        return nomorPerkara;
    }
    public String getPengadilan() {
        return pengadilan;
    }
    public LocalDate getTanggalPutusan() {
        return tanggalPutusan;
    }
    public String getTerdakwaKode() {
        return terdakwaKode;
    }
    public String getJenisNarkotika() {
        return jenisNarkotika;
    }
    public double getBeratBarangBuktiGram() {
        return beratBarangBuktiGram;
    }
    public String getPasalDakwaan() {
        return pasalDakwaan;
    }
    public String getPasalTerbukti() {
        return pasalTerbukti;
    }
    public int getTuntutanBulan() {
        return tuntutanBulan;
    }
    public int getVonisBulan() {
        return vonisBulan;
    }
    public long getDendaRupiah() {
        return dendaRupiah;
    }
    public String getBarangBukti() {
        return barangBukti;
    }
    public String getKeadaanMemberatkan() {
        return keadaanMemberatkan;
    }
    public String getKeadaanMeringankan() {
        return keadaanMeringankan;
    }
    public String getSourcePages() {
        return sourcePages;
    }

    public void setVonisBulan(int vonisBulan) {
        if (vonisBulan < 0) {
            throw new IllegalArgumentException(
                    "Vonis tidak boleh negatif");
        }
        this.vonisBulan = vonisBulan;
    }

    public void setDendaRupiah(long dendaRupiah) {
        if (dendaRupiah < 0) {
            throw new IllegalArgumentException(
                    "Denda tidak boleh negatif");
        }
        this.dendaRupiah = dendaRupiah;
    }

    @Override
    public String toString() {
        return "\n=== DATA PUTUSAN ===" +
                "\nCase ID              : " + caseId +
                "\nNomor Perkara        : " + nomorPerkara +
                "\nPengadilan           : " + pengadilan +
                "\nTanggal Putusan      : " + tanggalPutusan +
                "\nKode Terdakwa        : " + terdakwaKode +
                "\nJenis Narkotika      : " + jenisNarkotika +
                "\nBerat Barang Bukti   : " + beratBarangBuktiGram + " gram" +
                "\nPasal Dakwaan        : " + pasalDakwaan +
                "\nPasal Terbukti       : " + pasalTerbukti +
                "\nTuntutan             : " + tuntutanBulan + " bulan" +
                "\nVonis                : " + vonisBulan + " bulan" +
                "\nDenda                : Rp" + dendaRupiah +
                "\nBarang Bukti         : " + barangBukti +
                "\nKeadaan Memberatkan  : " + keadaanMemberatkan +
                "\nKeadaan Meringankan  : " + keadaanMeringankan +
                "\nSource Pages         : " + sourcePages;

    }
}

