package com.kelompok5.manager;

import com.kelompok5.model.Putusan;
import com.kelompok5.structure.DynamicArray;

public class PutusanManager {

    private final DynamicArray<Putusan> daftarPutusan;

    // Constructor
    public PutusanManager() {
        daftarPutusan = new DynamicArray<>();
    }

    // Menambahkan putusan
    public boolean tambahPutusan(Putusan putusan) {
        if (putusan == null) {
            return false;
        }

        if (cariBerdasarkanCaseId(putusan.getCaseId()) != null) {
            return false;
        }

        daftarPutusan.add(putusan);
        return true;
    }

    // Mencari berdasarkan Case ID (Linear Search)
    public Putusan cariBerdasarkanCaseId(String caseId) {
        if (caseId == null || caseId.isBlank()) {
            return null;
        }

        String keyword = caseId.trim();

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getCaseId().equalsIgnoreCase(keyword)) {
                return p;
            }
        }

        return null;
    }

    // Mencari berdasarkan nomor perkara (Linear Search)
    public Putusan cariBerdasarkanNomorPerkara(String nomorPerkara) {
        if (nomorPerkara == null || nomorPerkara.isBlank()) {
            return null;
        }

        String keyword = nomorPerkara.trim();

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getNomorPerkara().equalsIgnoreCase(keyword)) {
                return p;
            }
        }

        return null;
    }

    // Mencari Case ID sekaligus menghitung langkah
    public int langkahCariCaseId(String caseId) {
        if (caseId == null || caseId.isBlank()) {
            return 0;
        }

        int langkah = 0;
        String keyword = caseId.trim();

        for (int i = 0; i < daftarPutusan.size(); i++) {
            langkah++;

            Putusan p = daftarPutusan.get(i);

            if (p.getCaseId().equalsIgnoreCase(keyword)) {
                return langkah;
            }
        }

        return langkah;
    }

    // Mencari nomor perkara sekaligus menghitung langkah
    public int langkahCariNomorPerkara(String nomorPerkara) {
        if (nomorPerkara == null || nomorPerkara.isBlank()) {
            return 0;
        }

        int langkah = 0;
        String keyword = nomorPerkara.trim();

        for (int i = 0; i < daftarPutusan.size(); i++) {
            langkah++;

            Putusan p = daftarPutusan.get(i);

            if (p.getNomorPerkara().equalsIgnoreCase(keyword)) {
                return langkah;
            }
        }

        return langkah;
    }

    // Menghapus putusan berdasarkan Case ID
    public boolean hapusPutusan(String caseId) {
        if (caseId == null || caseId.isBlank()) {
            return false;
        }

        String keyword = caseId.trim();

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getCaseId().equalsIgnoreCase(keyword)) {
                daftarPutusan.remove(i);
                return true;
            }
        }

        return false;
    }

    // Mengambil putusan berdasarkan indeks
    public Putusan getPutusan(int index) {
        return daftarPutusan.get(index);
    }

    // Menghitung jumlah putusan
    public int jumlahPutusan() {
        return daftarPutusan.size();
    }

    // Menghitung rata-rata vonis
    public double rataRataVonis() {
        long totalVonis = 0;
        int jumlah = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getVonisBulan() >= 0) {
                totalVonis += p.getVonisBulan();
                jumlah++;
            }
        }

        return jumlah == 0 ? 0 : (double) totalVonis / jumlah;
    }

    // Menghitung total denda
    public long totalDenda() {
        long total = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            total += daftarPutusan.get(i).getDendaRupiah();
        }

        return total;
    }

    // Menampilkan seluruh putusan
    public void tampilkanSemuaPutusan() {
        if (daftarPutusan.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {
            System.out.println(daftarPutusan.get(i));
        }
    }

    // FILTER 1: Berdasarkan jenis narkotika
    public void filterJenisNarkotika(String jenis) {
        if (jenis == null || jenis.isBlank()) {
            System.out.println("Jenis narkotika tidak boleh kosong.");
            return;
        }

        String keyword = jenis.trim();
        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getJenisNarkotika() != null &&
                    p.getJenisNarkotika().equalsIgnoreCase(keyword)) {

                System.out.println(p);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ada putusan dengan jenis narkotika tersebut."
            );
        }
    }

    // FILTER 2: Berdasarkan rentang berat barang bukti
    public void filterRentangBerat(double minimum, double maksimum) {
        if (Double.isNaN(minimum) || Double.isNaN(maksimum)
                || Double.isInfinite(minimum)
                || Double.isInfinite(maksimum)
                || minimum < 0 || maksimum < minimum) {

            System.out.println("Rentang berat tidak valid.");
            return;
        }

        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            double berat = p.getBeratBarangBuktiGram();

            if (berat >= minimum && berat <= maksimum) {
                System.out.println(p);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ada putusan dalam rentang berat tersebut."
            );
        }
    }

    // FILTER 3: Berdasarkan pasal terbukti
    public void filterPasalTerbukti(String pasal) {
        if (pasal == null || pasal.isBlank()) {
            System.out.println("Pasal tidak boleh kosong.");
            return;
        }

        String keyword = pasal.trim();
        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            if (p.getPasalTerbukti() != null &&
                    p.getPasalTerbukti().toLowerCase()
                            .contains(keyword.toLowerCase())) {

                System.out.println(p);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ada putusan dengan pasal tersebut."
            );
        }
    }

    // FILTER 4: Berdasarkan rentang vonis
    public void filterRentangVonis(int minimum, int maksimum) {
        if (minimum < 0 || maksimum < minimum) {
            System.out.println("Rentang vonis tidak valid.");
            return;
        }

        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);

            int vonis = p.getVonisBulan();

            if (vonis >= minimum && vonis <= maksimum) {
                System.out.println(p);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ada putusan dalam rentang vonis tersebut."
            );
        }
    }

    // Insertion Sort berdasarkan lama vonis (ascending)
    public void urutkanBerdasarkanVonis() {
        for (int i = 1; i < daftarPutusan.size(); i++) {

            Putusan key = daftarPutusan.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    daftarPutusan.get(j).getVonisBulan()
                            > key.getVonisBulan()) {

                daftarPutusan.set(
                        j + 1,
                        daftarPutusan.get(j)
                );

                j--;
            }

            daftarPutusan.set(j + 1, key);
        }
    }

    // Memeriksa invariant struktur data
    public boolean validateInvariant() {
        return daftarPutusan.validateInvariant();
    }

    // Memeriksa apakah data kosong
    public boolean isEmpty() {
        return daftarPutusan.isEmpty();
    }
}
