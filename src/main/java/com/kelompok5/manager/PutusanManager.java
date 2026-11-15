package com.kelompok5.manager;

import com.kelompok5.model.Putusan;
import com.kelompok5.model.TerdakwaData;
import com.kelompok5.structure.DynamicArray;
import com.kelompok5.structure.HashTable;

import java.util.Locale;

/**
 * Mengelola kumpulan data putusan perkara narkotika.
 *
 * DynamicArray digunakan sebagai penyimpanan utama.
 * HashTable digunakan sebagai indeks pencarian berdasarkan nomor perkara.
 *
 * Satu nomor perkara dapat memiliki lebih dari satu Putusan.
 * Oleh karena itu, nilai HashTable berupa DynamicArray<Putusan>.
 */
public class PutusanManager {

    private final DynamicArray<Putusan> daftarPutusan;

    // Indeks Hash Table berdasarkan nomor perkara.
    private final HashTable<String, DynamicArray<Putusan>>
            indeksNomorPerkara;

    private int langkahPencarianTerakhir;
    private int perbandinganPengurutanTerakhir;
    private int pergeseranPengurutanTerakhir;

    public PutusanManager() {
        daftarPutusan = new DynamicArray<>();
        indeksNomorPerkara = new HashTable<>();

        langkahPencarianTerakhir = 0;
        perbandinganPengurutanTerakhir = 0;
        pergeseranPengurutanTerakhir = 0;
    }

    // =====================================================
    // NORMALISASI DAN PENGELOLAAN INDEKS HASH TABLE
    // =====================================================

    /**
     * Menormalisasi nomor perkara agar pencarian
     * tidak membedakan huruf kapital dan nonkapital.
     */
    private String normalisasiNomorPerkara(String nomorPerkara) {

        if (nomorPerkara == null || nomorPerkara.trim().isEmpty()) {
            return null;
        }

        return nomorPerkara.trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Menambahkan putusan ke indeks Hash Table.
     *
     * Jika nomor perkara sudah tersedia, putusan
     * ditambahkan ke DynamicArray yang sudah ada.
     */
    private void tambahKeIndeksNomorPerkara(Putusan putusan) {

        String key = normalisasiNomorPerkara(
                putusan.getNomorPerkara()
        );

        if (key == null) {
            return;
        }

        DynamicArray<Putusan> daftarDenganNomor =
                indeksNomorPerkara.get(key);

        if (daftarDenganNomor == null) {

            daftarDenganNomor = new DynamicArray<>();

            indeksNomorPerkara.put(
                    key,
                    daftarDenganNomor
            );
        }

        daftarDenganNomor.add(putusan);
    }

    /**
     * Menghapus putusan dari indeks Hash Table.
     *
     * Jika tidak ada lagi putusan dengan nomor perkara
     * tersebut, key dihapus dari Hash Table.
     */
    private void hapusDariIndeksNomorPerkara(Putusan putusan) {

        String key = normalisasiNomorPerkara(
                putusan.getNomorPerkara()
        );

        if (key == null) {
            return;
        }

        DynamicArray<Putusan> daftarDenganNomor =
                indeksNomorPerkara.get(key);

        if (daftarDenganNomor == null) {
            return;
        }

        for (int i = 0; i < daftarDenganNomor.size(); i++) {

            if (daftarDenganNomor.get(i) == putusan) {
                daftarDenganNomor.remove(i);
                break;
            }
        }

        if (daftarDenganNomor.isEmpty()) {
            indeksNomorPerkara.remove(key);
        }
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

        tambahKeIndeksNomorPerkara(putusan);

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

    /**
     * Menghapus putusan berdasarkan caseId.
     *
     * Data dihapus dari penyimpanan utama dan indeks Hash Table.
     */
    public boolean hapusPutusan(String caseId) {

        if (caseId == null || caseId.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            if (putusan.getCaseId().equalsIgnoreCase(caseId.trim())) {

                hapusDariIndeksNomorPerkara(putusan);

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

    /**
     * Menghapus seluruh data dari penyimpanan utama
     * dan indeks Hash Table.
     */
    public void clear() {

        daftarPutusan.clear();
        indeksNomorPerkara.clear();

        langkahPencarianTerakhir = 0;
        perbandinganPengurutanTerakhir = 0;
        pergeseranPengurutanTerakhir = 0;
    }

    // =====================================================
    // PENCARIAN
    // =====================================================

    /**
     * Linear Search berdasarkan caseId.
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
     * Pencarian berdasarkan nomor perkara menggunakan Hash Table.
     *
     * Mengembalikan putusan pertama jika terdapat lebih dari
     * satu putusan dengan nomor perkara yang sama.
     */
    public Putusan cariBerdasarkanNomorPerkara(String nomorPerkara) {

        langkahPencarianTerakhir = 0;

        String key = normalisasiNomorPerkara(nomorPerkara);

        if (key == null) {
            return null;
        }

        DynamicArray<Putusan> hasil =
                indeksNomorPerkara.get(key);

        if (hasil == null || hasil.isEmpty()) {
            return null;
        }

        // Satu akses indeks Hash Table.
        // Bukan jumlah seluruh perbandingan node saat collision.
        langkahPencarianTerakhir = 1;

        return hasil.get(0);
    }

    /**
     * Mengambil seluruh putusan berdasarkan nomor perkara.
     *
     * Hasil dikembalikan sebagai salinan DynamicArray
     * agar perubahan pada hasil tidak mengubah indeks internal.
     */
    public DynamicArray<Putusan> cariSemuaBerdasarkanNomorPerkara(
            String nomorPerkara) {

        langkahPencarianTerakhir = 0;

        DynamicArray<Putusan> hasil = new DynamicArray<>();

        String key = normalisasiNomorPerkara(nomorPerkara);

        if (key == null) {
            return hasil;
        }

        DynamicArray<Putusan> daftarDenganNomor =
                indeksNomorPerkara.get(key);

        if (daftarDenganNomor == null) {
            return hasil;
        }

        langkahPencarianTerakhir = 1;

        for (int i = 0; i < daftarDenganNomor.size(); i++) {
            hasil.add(daftarDenganNomor.get(i));
        }

        return hasil;
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
     * Menghitung rata-rata vonis seluruh terdakwa.
     * Nilai 0 berarti data vonis tidak ditemukan,
     * sehingga tidak dimasukkan ke perhitungan.
     */
    public double rataRataVonis() {

        long totalVonis = 0;
        int jumlahDataValid = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            for (int j = 0; j < putusan.jumlahTerdakwa(); j++) {

                TerdakwaData terdakwa = putusan.getTerdakwa(j);
                int vonis = terdakwa.getVonisBulan();

                if (vonis > 0) {
                    totalVonis += vonis;
                    jumlahDataValid++;
                }
            }
        }

        if (jumlahDataValid == 0) {
            return 0.0;
        }

        return (double) totalVonis / jumlahDataValid;
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

    /**
     * Menghitung rata-rata vonis dalam satu perkara.
     *
     * Nilai 0 tidak dimasukkan ke perhitungan.
     * Perkara tanpa data vonis valid diberi nilai
     * positif tak hingga agar ditempatkan paling akhir
     * pada pengurutan ascending.
     */
    private double rataRataVonisPerkara(Putusan putusan) {

        if (putusan == null || putusan.jumlahTerdakwa() == 0) {
            return Double.POSITIVE_INFINITY;
        }

        long total = 0;
        int jumlahDataValid = 0;

        for (int i = 0; i < putusan.jumlahTerdakwa(); i++) {

            int vonis = putusan.getTerdakwa(i).getVonisBulan();

            if (vonis > 0) {
                total += vonis;
                jumlahDataValid++;
            }
        }

        if (jumlahDataValid == 0) {
            return Double.POSITIVE_INFINITY;
        }

        return (double) total / jumlahDataValid;
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

        if (!Double.isFinite(minimum)
                || !Double.isFinite(maksimum)
                || minimum < 0
                || maksimum < minimum) {

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
     * memiliki vonis valid dalam rentang yang dipilih.
     * Nilai 0 tidak dianggap sebagai vonis aktual.
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

                if (vonis > 0
                        && vonis >= minimum
                        && vonis <= maksimum) {

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
     *
     * Perkara tanpa vonis valid ditempatkan paling akhir.
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

    /**
     * Memvalidasi konsistensi penyimpanan utama,
     * indeks Hash Table, data putusan, dan data terdakwa.
     */
    public boolean validateInvariant() {

        if (!daftarPutusan.validateInvariant()) {
            return false;
        }

        if (!indeksNomorPerkara.validateInvariant()) {
            return false;
        }

        int totalDataDalamIndeks = 0;
        int jumlahKeyUnik = 0;

        for (int i = 0; i < daftarPutusan.size(); i++) {

            Putusan putusan = daftarPutusan.get(i);

            if (putusan == null) {
                return false;
            }

            if (putusan.getCaseId() == null
                    || putusan.getCaseId().trim().isEmpty()) {
                return false;
            }

            if (putusan.getNomorPerkara() == null
                    || putusan.getNomorPerkara().trim().isEmpty()) {
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

            // =============================================
            // VALIDASI INDEKS HASH TABLE
            // =============================================

            String key = normalisasiNomorPerkara(
                    putusan.getNomorPerkara()
            );

            DynamicArray<Putusan> daftarDenganNomor =
                    indeksNomorPerkara.get(key);

            if (daftarDenganNomor == null) {
                return false;
            }

            if (!daftarDenganNomor.validateInvariant()) {
                return false;
            }

            // Setiap putusan harus muncul tepat satu kali
            // dalam bucket nomor perkaranya.
            int jumlahKemunculan = 0;

            for (int j = 0; j < daftarDenganNomor.size(); j++) {

                if (daftarDenganNomor.get(j) == putusan) {
                    jumlahKemunculan++;
                }
            }

            if (jumlahKemunculan != 1) {
                return false;
            }

            // Hitung key unik berdasarkan penyimpanan utama.
            boolean keySudahDihitung = false;

            for (int j = 0; j < i; j++) {

                String keySebelumnya = normalisasiNomorPerkara(
                        daftarPutusan.get(j).getNomorPerkara()
                );

                if (key.equals(keySebelumnya)) {
                    keySudahDihitung = true;
                    break;
                }
            }

            if (!keySudahDihitung) {

                jumlahKeyUnik++;

                totalDataDalamIndeks += daftarDenganNomor.size();

                // Pastikan seluruh data dalam bucket
                // benar-benar berasal dari penyimpanan utama.
                for (int j = 0; j < daftarDenganNomor.size(); j++) {

                    Putusan dataIndeks = daftarDenganNomor.get(j);

                    if (dataIndeks == null) {
                        return false;
                    }

                    String keyDataIndeks =
                            normalisasiNomorPerkara(
                                    dataIndeks.getNomorPerkara()
                            );

                    if (!key.equals(keyDataIndeks)) {
                        return false;
                    }

                    boolean ditemukanDalamPenyimpananUtama = false;

                    for (int k = 0; k < daftarPutusan.size(); k++) {

                        if (daftarPutusan.get(k) == dataIndeks) {
                            ditemukanDalamPenyimpananUtama = true;
                            break;
                        }
                    }

                    if (!ditemukanDalamPenyimpananUtama) {
                        return false;
                    }
                }
            }

            // =============================================
            // VALIDASI DATA TERDAKWA
            // =============================================

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

                    TerdakwaData terdakwaLain =
                            putusan.getTerdakwa(k);

                    if (terdakwaLain == null) {
                        return false;
                    }

                    if (terdakwa.getKode().equalsIgnoreCase(
                            terdakwaLain.getKode())) {
                        return false;
                    }
                }
            }

            // =============================================
            // VALIDASI DUPLIKASI CASE ID
            // =============================================

            for (int j = i + 1; j < daftarPutusan.size(); j++) {

                Putusan putusanLain = daftarPutusan.get(j);

                if (putusanLain == null) {
                    return false;
                }

                if (putusan.getCaseId().equalsIgnoreCase(
                        putusanLain.getCaseId())) {
                    return false;
                }
            }
        }

        // Jumlah key pada Hash Table harus sama dengan
        // jumlah nomor perkara unik di penyimpanan utama.
        if (indeksNomorPerkara.size() != jumlahKeyUnik) {
            return false;
        }

        // Jumlah seluruh elemen bucket harus sama
        // dengan jumlah putusan pada penyimpanan utama.
        if (totalDataDalamIndeks != daftarPutusan.size()) {
            return false;
        }

        return true;
    }
}
