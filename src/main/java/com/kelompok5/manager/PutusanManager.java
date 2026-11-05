package com.kelompok5.manager;

import com.kelompok5.model.Putusan;
import com.kelompok5.structure.DynamicArray;

public class PutusanManager {

    private DynamicArray<Putusan> daftarPutusan;

    public PutusanManager() {
        daftarPutusan = new DynamicArray<>();
    }

    // Menambahkan data putusan
    public void tambahPutusan(Putusan putusan) {
        if (putusan == null) {
            throw new IllegalArgumentException(
                    "Putusan tidak boleh null");
        }

        if (cariBerdasarkanCaseId(putusan.getCaseId()) != null) {
            throw new IllegalArgumentException(
                    "Case ID sudah terdaftar");
        }

        daftarPutusan.add(putusan);
    }

    // Mencari berdasarkan Case ID
    public Putusan cariBerdasarkanCaseId(String caseId) {
        if (caseId == null) return null;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getCaseId().equalsIgnoreCase(caseId)) {
                return p;
            }
        }

        return null;
    }

    // Mencari berdasarkan nomor perkara
    public Putusan cariBerdasarkanNomorPerkara(String nomorPerkara) {
        if (nomorPerkara == null) return null;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getNomorPerkara().equalsIgnoreCase(nomorPerkara)) {
                return p;
            }
        }

        return null;
    }

    // Menghapus data putusan
    public boolean hapusPutusan(String caseId) {
        for (int i = 0; i < daftarPutusan.size(); i++) {
            if (daftarPutusan.get(i).getCaseId()
                    .equalsIgnoreCase(caseId)) {
                daftarPutusan.remove(i);
                return true;
            }
        }

        return false;
    }

    // Mengambil data berdasarkan indeks
    public Putusan getPutusan(int index) {
        return daftarPutusan.get(index);
    }

    // Menghitung jumlah data
    public int jumlahPutusan() {
        return daftarPutusan.size();
    }

    // Menghitung rata-rata lama vonis
    public double rataRataVonis() {
        if (daftarPutusan.isEmpty()) return 0;

        long total = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            total += daftarPutusan.get(i).getVonisBulan();
        }

        return (double) total / daftarPutusan.size();
    }

    // Menghitung total denda
    public long totalDenda() {
        long total = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            total += daftarPutusan.get(i).getDendaRupiah();
        }

        return total;
    }

    // Menampilkan seluruh data
    public void tampilkanSemuaPutusan() {
        for (int i = 0; i < daftarPutusan.size(); i++) {
            System.out.println(daftarPutusan.get(i));
        }
    }

    // Memeriksa invariant
    public boolean validateInvariant() {
        return daftarPutusan.validateInvariant();
    }

    public boolean isEmpty() {
        return daftarPutusan.isEmpty();
    }
}
