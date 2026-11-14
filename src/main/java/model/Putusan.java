package model;

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
    private double dendaRupiah;
    private String barangBukti;
    private String keadaanMemberatkan;
    private String keadaanMeringankan;
    private String sourcePages;

    public Putusan(String caseId, String nomorPerkara, String pengadilan, LocalDate tanggalPutusan, String terdakwaKode, String jenisNarkotika, double beratBarangBuktiGram, String pasalDakwaan, String pasalTerbukti, int tuntutanBulan, int vonisBulan, double dendaRupiah, String barangBukti, String keadaanMemberatkan, String keadaanMeringankan, String sourcePages) {
        this.caseId = caseId;
        this.nomorPerkara = nomorPerkara;
        this.pengadilan = pengadilan;
        this.tanggalPutusan = tanggalPutusan;
        this.terdakwaKode = terdakwaKode;
        this.jenisNarkotika = jenisNarkotika;
        this.beratBarangBuktiGram = beratBarangBuktiGram;
        this.pasalDakwaan = pasalDakwaan;
        this.pasalTerbukti = pasalTerbukti;
        this.tuntutanBulan = tuntutanBulan;
        this.vonisBulan = vonisBulan;
        this.dendaRupiah = dendaRupiah;
        this.barangBukti = barangBukti;
        this.keadaanMemberatkan = keadaanMemberatkan;
        this.keadaanMeringankan = keadaanMeringankan;
        this.sourcePages = sourcePages;
    }

    public Putusan() {
    }

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getNomorPerkara() {
        return this.nomorPerkara;
    }

    public void setNomorPerkara(String nomorPerkara) {
        this.nomorPerkara = nomorPerkara;
    }

    public String getPengadilan() {
        return this.pengadilan;
    }

    public void setPengadilan(String pengadilan) {
        this.pengadilan = pengadilan;
    }

    public LocalDate getTanggalPutusan() {
        return this.tanggalPutusan;
    }

    public void setTanggalPutusan(LocalDate tanggalPutusan) {
        this.tanggalPutusan = tanggalPutusan;
    }

    public String getTerdakwaKode() {
        return this.terdakwaKode;
    }

    public void setTerdakwaKode(String terdakwaKode) {
        this.terdakwaKode = terdakwaKode;
    }

    public String getJenisNarkotika() {
        return this.jenisNarkotika;
    }

    public void setJenisNarkotika(String jenisNarkotika) {
        this.jenisNarkotika = jenisNarkotika;
    }

    public double getBeratBarangBuktiGram() {
        return this.beratBarangBuktiGram;
    }

    public void setBeratBarangBuktiGram(double beratBarangBuktiGram) {
        this.beratBarangBuktiGram = beratBarangBuktiGram;
    }

    public String getPasalDakwaan() {
        return this.pasalDakwaan;
    }

    public void setPasalDakwaan(String pasalDakwaan) {
        this.pasalDakwaan = pasalDakwaan;
    }

    public String getPasalTerbukti() {
        return this.pasalTerbukti;
    }

    public void setPasalTerbukti(String pasalTerbukti) {
        this.pasalTerbukti = pasalTerbukti;
    }

    public int getTuntutanBulan() {
        return this.tuntutanBulan;
    }

    public void setTuntutanBulan(int tuntutanBulan) {
        this.tuntutanBulan = tuntutanBulan;
    }

    public int getVonisBulan() {
        return this.vonisBulan;
    }

    public void setVonisBulan(int vonisBulan) {
        this.vonisBulan = vonisBulan;
    }

    public double getDendaRupiah() {
        return this.dendaRupiah;
    }

    public void setDendaRupiah(double dendaRupiah) {
        this.dendaRupiah = dendaRupiah;
    }

    public String getBarangBukti() {
        return this.barangBukti;
    }

    public void setBarangBukti(String barangBukti) {
        this.barangBukti = barangBukti;
    }

    public String getKeadaanMemberatkan() {
        return this.keadaanMemberatkan;
    }

    public void setKeadaanMemberatkan(String keadaanMemberatkan) {
        this.keadaanMemberatkan = keadaanMemberatkan;
    }

    public String getKeadaanMeringankan() {
        return this.keadaanMeringankan;
    }

    public void setKeadaanMeringankan(String keadaanMeringankan) {
        this.keadaanMeringankan = keadaanMeringankan;
    }

    public String getSourcePages() {
        return this.sourcePages;
    }

    public void setSourcePages(String sourcePages) {
        this.sourcePages = sourcePages;
    }
}