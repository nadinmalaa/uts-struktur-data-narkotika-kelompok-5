package com.kelompok5.model;

public class TerdakwaData {

    private final String caseId;
    private final String kode;
    private final int tuntutanBulan;
    private final int vonisBulan;

    public TerdakwaData(
            String caseId,
            String kode,
            int tuntutanBulan,
            int vonisBulan) {

        if (caseId == null || caseId.isBlank()) {
            throw new IllegalArgumentException("Case ID tidak boleh kosong");
        }

        if (kode == null || kode.isBlank()) {
            throw new IllegalArgumentException("Kode terdakwa tidak boleh kosong");
        }

        if (tuntutanBulan < 0 || vonisBulan < 0) {
            throw new IllegalArgumentException(
                    "Tuntutan dan vonis tidak boleh negatif");
        }

        this.caseId = caseId.trim();
        this.kode = kode.trim();
        this.tuntutanBulan = tuntutanBulan;
        this.vonisBulan = vonisBulan;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getKode() {
        return kode;
    }

    public int getTuntutanBulan() {
        return tuntutanBulan;
    }

    public int getVonisBulan() {
        return vonisBulan;
    }

    @Override
    public String toString() {
        return "Kode Terdakwa: " + kode
                + ", Tuntutan: " + tuntutanBulan + " bulan"
                + ", Vonis: " + vonisBulan + " bulan";
    }
}