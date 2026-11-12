
package com.kelompok5;

import com.kelompok5.manager.PutusanManager;
import com.kelompok5.manager.AuditLog;
import com.kelompok5.model.Putusan;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final PutusanManager manager = new PutusanManager();
    private static final AuditLog audit = new AuditLog();
    private static final PutusanCSVReader csvReader = new PutusanCSVReader();

    public static void main(String[] args) {

        // Impor data CSV saat program dijalankan
        System.out.println("=== IMPOR DATA PUTUSAN ===");

        csvReader.importData(
                "data/putusan-50 - Dataset Putusan.csv",
                "data/putusan-50 - Data Terdakwa.csv",
                "data/putusan-50 - Data Provenance.csv",
                manager
        );

        audit.catatAktivitas("Memuat data putusan dari CSV");

        int pilihan;

        do {
            tampilkanMenu();
            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tampilkanData();
                    break;

                case 2:
                    tambahData();
                    break;

                case 3:
                    hapusData();
                    break;

                case 4:
                    cariData();
                    break;

                case 5:
                    urutkanData();
                    break;

                case 6:
                    tampilkanStatistik();
                    break;

                case 7:
                    audit.tampilkanSemuaAktivitas();
                    break;

                case 8:
                    csvReader.tampilkanSemuaTerdakwa();
                    break;

                case 9:
                    filterData();
                    break;

                case 0:
                    System.out.println("Terima kasih. Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia!");
            }

            System.out.println();

        } while (pilihan != 0);
    }

    // Menampilkan menu
    private static void tampilkanMenu() {

        System.out.println("====================================");
        System.out.println("   APLIKASI PENGELOLAAN DATA PUTUSAN");
        System.out.println("====================================");
        System.out.println("1. Tampilkan seluruh data putusan");
        System.out.println("2. Tambah data putusan");
        System.out.println("3. Hapus data putusan");
        System.out.println("4. Cari data putusan");
        System.out.println("5. Urutkan berdasarkan lama vonis");
        System.out.println("6. Tampilkan statistik");
        System.out.println("7. Tampilkan riwayat aktivitas");
        System.out.println("8. Tampilkan seluruh data terdakwa");
        System.out.println("9. Filter data putusan");
        System.out.println("0. Keluar");
        System.out.println("====================================");
    }

    // Menampilkan seluruh putusan beserta semua terdakwa
    private static void tampilkanData() {

        System.out.println("\n=== DAFTAR DATA PUTUSAN ===");

        if (manager.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        csvReader.tampilkanPutusanDanSeluruhTerdakwa(manager);
    }

    // Menambahkan data putusan
    private static void tambahData() {

        System.out.println("\n=== TAMBAH DATA PUTUSAN ===");

        try {
            System.out.print("Case ID: ");
            String caseId = input.nextLine();

            System.out.print("Nomor perkara: ");
            String nomor = input.nextLine();

            System.out.print("Pengadilan: ");
            String pengadilan = input.nextLine();

            System.out.print("Tanggal putusan (YYYY-MM-DD): ");
            LocalDate tanggal = LocalDate.parse(input.nextLine());

            System.out.print("Kode terdakwa: ");
            String terdakwa = input.nextLine();

            System.out.print("Jenis narkotika: ");
            String jenis = input.nextLine();

            double berat = inputDouble("Berat barang bukti (gram): ");

            System.out.print("Pasal dakwaan: ");
            String pasalDakwaan = input.nextLine();

            System.out.print("Pasal terbukti: ");
            String pasalTerbukti = input.nextLine();

            int tuntutan = inputInt("Lama tuntutan (bulan): ");
            int vonis = inputInt("Lama vonis (bulan): ");
            long denda = inputLong("Denda (rupiah): ");

            System.out.print("Barang bukti: ");
            String barangBukti = input.nextLine();

            System.out.print("Keadaan memberatkan: ");
            String memberatkan = input.nextLine();

            System.out.print("Keadaan meringankan: ");
            String meringankan = input.nextLine();

            System.out.print("Halaman sumber: ");
            String halaman = input.nextLine();

            Putusan putusan = new Putusan(
                    caseId,
                    nomor,
                    pengadilan,
                    tanggal,
                    terdakwa,
                    jenis,
                    berat,
                    pasalDakwaan,
                    pasalTerbukti,
                    tuntutan,
                    vonis,
                    denda,
                    barangBukti,
                    memberatkan,
                    meringankan,
                    halaman
            );

            boolean berhasil = manager.tambahPutusan(putusan);

            if (berhasil) {
                audit.catatAktivitas("Menambahkan putusan " + caseId);
                System.out.println("Data putusan berhasil ditambahkan!");
            } else {
                System.out.println(
                        "Data gagal ditambahkan. Case ID mungkin sudah ada."
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());

        } catch (Exception e) {
            System.out.println(
                    "Input tidak valid. Periksa kembali data yang dimasukkan."
            );
        }
    }

    // Menghapus data putusan
    private static void hapusData() {

        System.out.println("\n=== HAPUS DATA PUTUSAN ===");

        System.out.print("Masukkan Case ID: ");
        String caseId = input.nextLine();

        boolean berhasil = manager.hapusPutusan(caseId);

        if (berhasil) {
            audit.catatAktivitas("Menghapus putusan " + caseId);
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Data dengan Case ID tersebut tidak ditemukan.");
        }
    }

    // Mencari data putusan dan menghitung langkah
    private static void cariData() {

        System.out.println("\n=== CARI DATA PUTUSAN ===");

        System.out.println("1. Cari berdasarkan Case ID");
        System.out.println("2. Cari berdasarkan nomor perkara");

        int pilihanCari = inputInt("Pilih jenis pencarian: ");

        Putusan hasil;
        String kataKunci;
        int langkah;

        if (pilihanCari == 1) {

            System.out.print("Masukkan Case ID: ");
            kataKunci = input.nextLine();

            hasil = manager.cariBerdasarkanCaseId(kataKunci);
            langkah = manager.langkahCariCaseId(kataKunci);

        } else if (pilihanCari == 2) {

            System.out.print("Masukkan nomor perkara: ");
            kataKunci = input.nextLine();

            hasil = manager.cariBerdasarkanNomorPerkara(kataKunci);
            langkah = manager.langkahCariNomorPerkara(kataKunci);

        } else {
            System.out.println("Pilihan pencarian tidak tersedia.");
            return;
        }

        System.out.println("Jumlah langkah pencarian: " + langkah);

        if (hasil != null) {
            System.out.println("Data ditemukan:");
            System.out.println(hasil);

            // Tampilkan semua terdakwa dalam perkara tersebut
            csvReader.tampilkanTerdakwaBerdasarkanCaseId(
                    hasil.getCaseId()
            );

            audit.catatAktivitas("Mencari putusan " + kataKunci);

        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }

    // Menu filter
    private static void filterData() {

        int pilihanFilter;

        do {
            System.out.println("\n=== FILTER DATA PUTUSAN ===");
            System.out.println("1. Filter jenis narkotika");
            System.out.println("2. Filter rentang berat barang bukti");
            System.out.println("3. Filter pasal terbukti");
            System.out.println("4. Filter rentang vonis");
            System.out.println("0. Kembali ke menu utama");

            pilihanFilter = inputInt("Pilih filter: ");

            switch (pilihanFilter) {

                case 1: {
                    System.out.print("Masukkan jenis narkotika: ");
                    String jenis = input.nextLine();

                    manager.filterJenisNarkotika(jenis);

                    audit.catatAktivitas(
                            "Filter jenis narkotika: " + jenis
                    );
                    break;
                }

                case 2: {
                    double minimumBerat =
                            inputDouble("Berat minimum (gram): ");

                    double maksimumBerat =
                            inputDouble("Berat maksimum (gram): ");

                    manager.filterRentangBerat(
                            minimumBerat,
                            maksimumBerat
                    );

                    audit.catatAktivitas(
                            "Filter rentang berat barang bukti"
                    );
                    break;
                }

                case 3: {
                    System.out.print("Masukkan pasal terbukti: ");
                    String pasal = input.nextLine();

                    manager.filterPasalTerbukti(pasal);

                    audit.catatAktivitas(
                            "Filter pasal terbukti: " + pasal
                    );
                    break;
                }

                case 4: {
                    int minimumVonis =
                            inputInt("Vonis minimum (bulan): ");

                    int maksimumVonis =
                            inputInt("Vonis maksimum (bulan): ");

                    manager.filterRentangVonis(
                            minimumVonis,
                            maksimumVonis
                    );

                    audit.catatAktivitas("Filter rentang vonis");
                    break;
                }

                case 0:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan filter tidak tersedia.");
            }

        } while (pilihanFilter != 0);
    }

    // Mengurutkan data
    private static void urutkanData() {

        System.out.println("\n=== SORTING DATA PUTUSAN ===");

        manager.urutkanBerdasarkanVonis();
        manager.tampilkanSemuaPutusan();

        audit.catatAktivitas(
                "Mengurutkan data berdasarkan lama vonis"
        );
    }

    // Menampilkan statistik
    private static void tampilkanStatistik() {

        System.out.println("\n=== STATISTIK PUTUSAN ===");

        System.out.println(
                "Jumlah putusan: " + manager.jumlahPutusan()
        );

        System.out.println(
                "Jumlah terdakwa: " + csvReader.jumlahDataTerdakwa()
        );

        System.out.println(
                "Rata-rata vonis putusan: "
                        + manager.rataRataVonis() + " bulan"
        );

        System.out.println(
                "Rata-rata vonis terdakwa: "
                        + csvReader.rataRataVonisTerdakwa() + " bulan"
        );

        System.out.println(
                "Total denda per putusan: Rp" + manager.totalDenda()
        );
    }

    // Input angka integer
    private static int inputInt(String pesan) {

        while (true) {
            try {
                System.out.print(pesan);
                return Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang benar!");
            }
        }
    }

    // Input angka desimal
    private static double inputDouble(String pesan) {

        while (true) {
            try {
                System.out.print(pesan);

                double nilai = Double.parseDouble(
                        input.nextLine().replace(",", ".")
                );

                if (!Double.isFinite(nilai)) {
                    System.out.println("Masukkan angka yang benar!");
                    continue;
                }

                return nilai;

            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang benar!");
            }
        }
    }

    // Input angka long
    private static long inputLong(String pesan) {

        while (true) {
            try {
                System.out.print(pesan);
                return Long.parseLong(input.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang benar!");
            }
        }
    }
}
