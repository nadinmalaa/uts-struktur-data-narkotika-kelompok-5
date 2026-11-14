package com.kelompok5.manager;

import com.kelompok5.model.Putusan;
import com.kelompok5.model.TerdakwaData;
import com.kelompok5.structure.DynamicArray;

/**
 * Mengelola kumpulan data putusan perkara narkotika.
 * Satu Putusan mewakili satu perkara dan dapat memiliki
 * lebih dari satu data terdakwa.
 */
public class PutusanManager {

    private final DynamicArray<Putusan> daftarPutusan;

    private int langkahPencarianTerakhir;
    private int perbandinganPengurutanTerakhir;
    private int pergeseranPengurutanTerakhir;

    public PutusanManager() {
        daftarPutusan = new DynamicArray<>();
        langkahPencarianTerakhir = 0;
        perbandinganPengurutanTerakhir = 0;
        pergeseranPengurutanTerakhir = 0;
    }

    // =====================================================
    // TAMBAH DATA
    // =====================================================

    /**
     * Menambahkan putusan jika caseId belum tersedia.
     */
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

    /**
     * Alias untuk kompatibilitas dengan PutusanCSVReader.
     */
    public boolean addPutusan(Putusan putusan) {
        return tambahPutusan(putusan);
    }

    /**
     * Alias tambahan untuk pemanggilan add().
     */
    public boolean add(Putusan putusan) {
        return tambahPutusan(putusan);
    }

    // =====================================================
    // HAPUS DAN AKSES DATA
    // =====================================================

    public boolean hapusPutusan(String caseId) {

        if (caseId == null || caseId.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            if (putusan.getCaseId().equalsIgnoreCase(caseId.trim())) {
                daftarPutusan.remove(i);
                return true;
            }
        }

        return false;
    }

    public Putusan getPutusan(int index) {
        return daftarPutusan.get(index);
    }

    public int jumlahPutusan() {
        return daftarPutusan.size();
    }

    public boolean isEmpty() {
        return daftarPutusan.isEmpty();
    }

    public void clear() {
        daftarPutusan.clear();
        langkahPencarianTerakhir = 0;
        perbandinganPengurutanTerakhir = 0;
        pergeseranPengurutanTerakhir = 0;
    }

    // =====================================================
    // PENCARIAN
    // =====================================================

    /**
     * Linear search berdasarkan caseId.
     */
    public Putusan cariBerdasarkanCaseId(String caseId) {

        langkahPencarianTerakhir = 0;

        if (caseId == null || caseId.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {

            langkahPencarianTerakhir++;

            Putusan putusan = daftarPutusan.get(i);

            if (putusan.getCaseId().equalsIgnoreCase(caseId.trim())) {
                return putusan;
            }
        }

        return null;
    }

    /**
     * Linear search berdasarkan nomor perkara.
     */
    public Putusan cariBerdasarkanNomorPerkara(String nomorPerkara) {

        langkahPencarianTerakhir = 0;

        if (nomorPerkara == null || nomorPerkara.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {

            langkahPencarianTerakhir++;

            Putusan putusan = daftarPutusan.get(i);

            if (putusan.getNomorPerkara()
                    .equalsIgnoreCase(nomorPerkara.trim())) {
                return putusan;
            }
        }

        return null;
    }

    public int getLangkahPencarianTerakhir() {
        return langkahPencarianTerakhir;
    }

    public int langkahCariCaseId(String caseId) {
        cariBerdasarkanCaseId(caseId);
        return langkahPencarianTerakhir;
    }

    public int langkahCariNomorPerkara(String nomorPerkara) {
        cariBerdasarkanNomorPerkara(nomorPerkara);
        return langkahPencarianTerakhir;
    }

    // =====================================================
    // AGREGASI
    // =====================================================

    /**
     * Rata-rata vonis seluruh terdakwa.
     * Setiap terdakwa dihitung satu kali.
     */
    public double rataRataVonis() {

        long totalVonis = 0;
        int jumlahTerdakwa = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            for (int j = 0; j < putusan.jumlahTerdakwa(); j++) {

                TerdakwaData terdakwa = putusan.getTerdakwa(j);

                totalVonis += terdakwa.getVonisBulan();
                jumlahTerdakwa++;
            }
        }

        if (jumlahTerdakwa == 0) {
            return 0.0;
        }

        return (double) totalVonis / jumlahTerdakwa;
    }

    /**
     * Total denda dihitung satu kali per perkara.
     */
    public long totalDenda() {

        long total = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            total += daftarPutusan.get(i).getDendaRupiah();
        }

        return total;
    }

    /**
     * Jumlah seluruh terdakwa dari semua perkara.
     */
    public int jumlahSeluruhTerdakwa() {

        int total = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {
            total += daftarPutusan.get(i).jumlahTerdakwa();
        }

        return total;
    }

    private double rataRataVonisPerkara(Putusan putusan) {

        if (putusan == null || putusan.jumlahTerdakwa() == 0) {
            return 0.0;
        }

        long total = 0;

        for (int i = 0; i < putusan.jumlahTerdakwa(); i++) {
            total += putusan.getTerdakwa(i).getVonisBulan();
        }

        return (double) total / putusan.jumlahTerdakwa();
    }

    // =====================================================
    // MENAMPILKAN DATA
    // =====================================================

    public void tampilkanSemuaPutusan() {

        if (daftarPutusan.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        System.out.println("\n========== DAFTAR PUTUSAN ==========");

        for (int i = 0; i < daftarPutusan.size(); i++) {
            System.out.println("\nData ke-" + (i + 1));
            System.out.println(daftarPutusan.get(i));
        }

        System.out.println("\nJumlah perkara: " + jumlahPutusan());
        System.out.println("Jumlah terdakwa: " + jumlahSeluruhTerdakwa());
    }

    // =====================================================
    // FILTER DATA
    // =====================================================

    public void filterJenisNarkotika(String jenis) {

        if (jenis == null || jenis.trim().isEmpty()) {
            System.out.println("Jenis narkotika tidak boleh kosong.");
            return;
        }

        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            if (putusan.getJenisNarkotika()
                    .toLowerCase()
                    .contains(jenis.trim().toLowerCase())) {

                System.out.println(putusan);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ditemukan putusan dengan jenis narkotika tersebut."
            );
        }
    }

    public void filterRentangBerat(double minimum, double maksimum) {

        if (minimum < 0 || maksimum < minimum) {
            System.out.println("Rentang berat tidak valid.");
            return;
        }

        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);
            double berat = putusan.getBeratBarangBuktiGram();

            if (berat >= minimum && berat <= maksimum) {
                System.out.println(putusan);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ditemukan putusan dalam rentang berat tersebut."
            );
        }
    }

    public void filterPasalTerbukti(String pasal) {

        if (pasal == null || pasal.trim().isEmpty()) {
            System.out.println("Pasal tidak boleh kosong.");
            return;
        }

        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            if (putusan.getPasalTerbukti()
                    .toLowerCase()
                    .contains(pasal.trim().toLowerCase())) {

                System.out.println(putusan);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ditemukan putusan dengan pasal tersebut."
            );
        }
    }

    /**
     * Perkara ditampilkan jika minimal satu terdakwa
     * memiliki vonis dalam rentang yang dipilih.
     */
    public void filterRentangVonis(int minimum, int maksimum) {

        if (minimum < 0 || maksimum < minimum) {
            System.out.println("Rentang vonis tidak valid.");
            return;
        }

        boolean ditemukan = false;

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);
            boolean cocok = false;

            for (int j = 0; j < putusan.jumlahTerdakwa(); j++) {

                int vonis = putusan.getTerdakwa(j).getVonisBulan();

                if (vonis >= minimum && vonis <= maksimum) {
                    cocok = true;
                    break;
                }
            }

            if (cocok) {
                System.out.println(putusan);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ditemukan terdakwa dengan rentang vonis tersebut."
            );
        }
    }

    // =====================================================
    // PENGURUTAN
    // =====================================================

    /**
     * Insertion Sort berdasarkan rata-rata vonis terdakwa
     * pada setiap perkara, secara ascending.
     */
    public void urutkanBerdasarkanVonis() {

        perbandinganPengurutanTerakhir = 0;
        pergeseranPengurutanTerakhir = 0;

        for (int i = 1; i < daftarPutusan.size(); i++) {

            Putusan key = daftarPutusan.get(i);
            double nilaiKey = rataRataVonisPerkara(key);

            int j = i - 1;

            while (j >= 0) {

                perbandinganPengurutanTerakhir++;

                Putusan pembanding = daftarPutusan.get(j);

                double nilaiPembanding =
                        rataRataVonisPerkara(pembanding);

                if (nilaiPembanding > nilaiKey) {

                    daftarPutusan.set(j + 1, pembanding);

                    pergeseranPengurutanTerakhir++;
                    j--;

                } else {
                    break;
                }
            }

            daftarPutusan.set(j + 1, key);
        }
    }

    /**
     * Alias untuk nama method sorting alternatif.
     */
    public void sortBerdasarkanRataRataVonis() {
        urutkanBerdasarkanVonis();
    }

    public int getPerbandinganPengurutanTerakhir() {
        return perbandinganPengurutanTerakhir;
    }

    public int getPergeseranPengurutanTerakhir() {
        return pergeseranPengurutanTerakhir;
    }

    // Alias untuk kompatibilitas dengan Main versi sebelumnya.
    public int getJumlahPerbandinganSorting() {
        return perbandinganPengurutanTerakhir;
    }

    public int getJumlahPergeseranSorting() {
        return pergeseranPengurutanTerakhir;
    }

    // =====================================================
    // VALIDASI INVARIANT
    // =====================================================

    public boolean validateInvariant() {

        if (!daftarPutusan.validateInvariant()) {
            return false;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            if (putusan == null) {
                return false;
            }

            if (putusan.getCaseId() == null
                    || putusan.getCaseId().trim().isEmpty()) {
                return false;
            }

            if (putusan.getDaftarTerdakwa() == null) {
                return false;
            }

            if (!putusan.getDaftarTerdakwa().validateInvariant()) {
                return false;
            }

            if (putusan.jumlahTerdakwa() == 0) {
                return false;
            }

            // Validasi caseId dan kode terdakwa.
            for (int j = 0; j < putusan.jumlahTerdakwa(); j++) {

                TerdakwaData terdakwa = putusan.getTerdakwa(j);

                if (terdakwa == null) {
                    return false;
                }

                if (!putusan.getCaseId().equals(terdakwa.getCaseId())) {
                    return false;
                }

                for (int k = j + 1;
                     k < putusan.jumlahTerdakwa();
                     k++) {

                    if (terdakwa.getKode().equalsIgnoreCase(
                            putusan.getTerdakwa(k).getKode())) {
                        return false;
                    }
                }
            }

            // Validasi caseId putusan tidak duplikat.
            for (int j = i + 1; j < daftarPutusan.size(); j++) {

                if (putusan.getCaseId().equalsIgnoreCase(
                        daftarPutusan.get(j).getCaseId())) {
                    return false;
                }
            }
        }

        return true;
    }
}
