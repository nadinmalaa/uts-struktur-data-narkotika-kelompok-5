package com.kelompok5.model;
import com.kelompok5.structure.DynamicArray;
import java.time.LocalDate;

public class Putusan {

    private final String caseId;
    private final String nomorPerkara;
    private final String pengadilan;
    private final LocalDate tanggalPutusan;

    private final DynamicArray<TerdakwaData> daftarTerdakwa;

    private final String jenisNarkotika;
    private final double beratBarangBuktiGram;
    private final String pasalDakwaan;
    private final String pasalTerbukti;
    private final long dendaRupiah;
    private final String barangBukti;
    private final String keadaanMemberatkan;
    private final String keadaanMeringankan;
    private final String sourcePages;

    public Putusan(
            String caseId,
            String nomorPerkara,
            String pengadilan,
            LocalDate tanggalPutusan,
            String jenisNarkotika,
            double beratBarangBuktiGram,
            String pasalDakwaan,
            String pasalTerbukti,
            long dendaRupiah,
            String barangBukti,
            String keadaanMemberatkan,
            String keadaanMeringankan,
            String sourcePages) {

        this.caseId = requireText(caseId, "caseId");
        this.nomorPerkara = requireText(nomorPerkara, "nomorPerkara");
        this.pengadilan = requireText(pengadilan, "pengadilan");

        if (tanggalPutusan == null) {
            throw new IllegalArgumentException(
                    "Tanggal putusan tidak boleh null");
        }

        if (!Double.isFinite(beratBarangBuktiGram)
                || beratBarangBuktiGram < 0) {
            throw new IllegalArgumentException(
                    "Berat barang bukti tidak valid");
        }

        if (dendaRupiah < 0) {
            throw new IllegalArgumentException(
                    "Denda tidak boleh negatif");
        }

        this.tanggalPutusan = tanggalPutusan;
        this.jenisNarkotika = normalize(jenisNarkotika);
        this.beratBarangBuktiGram = beratBarangBuktiGram;
        this.pasalDakwaan = normalize(pasalDakwaan);
        this.pasalTerbukti = normalize(pasalTerbukti);
        this.dendaRupiah = dendaRupiah;
        this.barangBukti = normalize(barangBukti);
        this.keadaanMemberatkan = normalize(keadaanMemberatkan);
        this.keadaanMeringankan = normalize(keadaanMeringankan);
        this.sourcePages = normalize(sourcePages);

        this.daftarTerdakwa = new DynamicArray<>();
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " tidak boleh kosong");
        }

        return value.trim();
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    // Menambahkan terdakwa ke perkara ini
    public void tambahTerdakwa(TerdakwaData terdakwa) {
        if (terdakwa == null) {
            throw new IllegalArgumentException(
                    "Data terdakwa tidak boleh null");
        }

        if (!caseId.equalsIgnoreCase(terdakwa.getCaseId())) {
            throw new IllegalArgumentException(
                    "Case ID terdakwa tidak sesuai dengan putusan");
        }

        for (int i = 0; i < daftarTerdakwa.size(); i++) {
            if (daftarTerdakwa.get(i).getKode()
                    .equalsIgnoreCase(terdakwa.getKode())) {
                throw new IllegalArgumentException(
                        "Kode terdakwa sudah terdaftar pada perkara ini");
            }
        }

        daftarTerdakwa.add(terdakwa);
    }

    public TerdakwaData getTerdakwa(int index) {
        return daftarTerdakwa.get(index);
    }

    public int jumlahTerdakwa() {
        return daftarTerdakwa.size();
    }

    public DynamicArray<TerdakwaData> getDaftarTerdakwa() {
        return daftarTerdakwa;
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

    // Menghitung total tuntutan seluruh terdakwa dalam perkara
    public long totalTuntutanBulan() {
        long total = 0;

        for (int i = 0; i < daftarTerdakwa.size(); i++) {
            total += daftarTerdakwa.get(i).getTuntutanBulan();
        }

        return total;
    }

    // Menghitung total vonis seluruh terdakwa dalam perkara
    public long totalVonisBulan() {
        long total = 0;

        for (int i = 0; i < daftarTerdakwa.size(); i++) {
            total += daftarTerdakwa.get(i).getVonisBulan();
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder hasil = new StringBuilder();

        hasil.append("\n=== DATA PUTUSAN ===")
                .append("\nCase ID             : ").append(caseId)
                .append("\nNomor Perkara       : ").append(nomorPerkara)
                .append("\nPengadilan          : ").append(pengadilan)
                .append("\nTanggal Putusan     : ").append(tanggalPutusan)
                .append("\nJenis Narkotika     : ").append(jenisNarkotika)
                .append("\nBerat Barang Bukti  : ")
                .append(beratBarangBuktiGram).append(" gram")
                .append("\nPasal Dakwaan       : ").append(pasalDakwaan)
                .append("\nPasal Terbukti      : ").append(pasalTerbukti)
                .append("\nDenda               : Rp").append(dendaRupiah)
                .append("\nBarang Bukti        : ").append(barangBukti)
                .append("\nKeadaan Memberatkan : ").append(keadaanMemberatkan)
                .append("\nKeadaan Meringankan : ").append(keadaanMeringankan)
                .append("\nSource Pages        : ").append(sourcePages)
                .append("\nJumlah Terdakwa     : ").append(jumlahTerdakwa());

        for (int i = 0; i < daftarTerdakwa.size(); i++) {
            hasil.append("\n").append(i + 1).append(". ")
                    .append(daftarTerdakwa.get(i));
        }

        return hasil.toString();
    }
}