import datastructure.AuditStack;
import datastructure.SinglyLinkedList;
import model.Putusan;
import model.Terdakwa;
import model.Provenance;
import org.junit.jupiter.api.Test;
import service.CsvParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MainTestBima {

    private static final String PUTUSAN_CSV =
            "data/putusan-50 - Data Putusan.csv";

    private static final String TERDAKWA_CSV =
            "data/putusan-50 - Data Terdakwa.csv";

    private static final String PROVENANCE_CSV =
            "data/putusan-50 - Data Provenance.csv";

    @Test
    public void testHapusElemenPertamaTengahAkhir() {
        SinglyLinkedList<Putusan> list = new SinglyLinkedList<>();

        Putusan p1 = new Putusan("P001", "01/Pid/2026/PN Dps",
                "PN Denpasar", LocalDate.now(), "T01", "Sabu",
                10.0, "Pasal 112", "Pasal 112", 12, 12,
                1000000, "Barang bukti 1", "Berat", "Ringan", "1");

        Putusan p2 = new Putusan("P002", "02/Pid/2026/PN Dps",
                "PN Denpasar", LocalDate.now(), "T02", "Ganja",
                20.0, "Pasal 111", "Pasal 111", 24, 24,
                2000000, "Barang bukti 2", "Berat", "Ringan", "2");

        Putusan p3 = new Putusan("P003", "03/Pid/2026/PN Dps",
                "PN Denpasar", LocalDate.now(), "T03", "Ekstasi",
                30.0, "Pasal 114", "Pasal 114", 36, 36,
                3000000, "Barang bukti 3", "Berat", "Ringan", "3");

        list.add(p1);
        list.add(p2);
        list.add(p3);

        assertEquals(3, list.size());

        Putusan removedMiddle = list.remove(1);
        assertEquals("P002", removedMiddle.getCaseId());
        assertEquals(2, list.size());
        assertTrue(list.validateInvariant());

        Putusan removedFirst = list.remove(0);
        assertEquals("P001", removedFirst.getCaseId());
        assertEquals(1, list.size());
        assertTrue(list.validateInvariant());

        Putusan removedLast = list.remove(0);
        assertEquals("P003", removedLast.getCaseId());
        assertTrue(list.isEmpty());
        assertTrue(list.validateInvariant());
    }

    @Test
    public void testTambahKembaliDanInvariant() {
        SinglyLinkedList<Putusan> list = new SinglyLinkedList<>();

        Putusan p1 = new Putusan("P001", "01/Pid/2026/PN Dps",
                "PN Denpasar", LocalDate.now(), "T01", "Sabu",
                10.0, "Pasal 112", "Pasal 112", 12, 12,
                1000000, "BB", "Berat", "Ringan", "1");

        list.add(p1);
        list.add(p1);
        list.add(p1);

        assertEquals(3, list.size());
        assertTrue(list.validateInvariant());
    }

    @Test
    public void testAuditStackSekunder() {
        AuditStack<String> auditStack = new AuditStack<>();

        assertTrue(auditStack.isEmpty());

        auditStack.push("TAMBAH: P026");
        auditStack.push("HAPUS: P027");
        auditStack.push("CARI: P028");

        assertEquals(3, auditStack.size());
        assertTrue(auditStack.validateInvariant());

        boolean found = auditStack.searchPreserve("HAPUS: P027");

        assertTrue(found);
        assertEquals(3, auditStack.size());

        String lastAction = auditStack.pop();

        assertEquals("CARI: P028", lastAction);
        assertEquals(2, auditStack.size());
    }

    @Test
    public void testLoadCsvToSinglyLinkedList() {
        SinglyLinkedList<Putusan> dataset =
                CsvParser.parseCsv(PUTUSAN_CSV);

        assertNotNull(dataset);
        assertFalse(dataset.isEmpty());
        assertTrue(dataset.validateInvariant());
    }

    @Test
    public void testSkenario2Search() {
        SinglyLinkedList<Putusan> dataset =
                CsvParser.parseCsv(PUTUSAN_CSV);

        assertNotNull(dataset);
        assertFalse(dataset.isEmpty());

        SinglyLinkedList.SearchResult<Putusan> resAwal =
                dataset.searchByCaseId("P001");

        assertTrue(resAwal.isFound());
        assertEquals(1, resAwal.getSteps());
        assertEquals("P001", resAwal.getData().getCaseId());

        SinglyLinkedList.SearchResult<Putusan> resAkhir =
                dataset.searchByCaseId("P050");

        assertTrue(resAkhir.isFound());
        assertTrue(resAkhir.getSteps() > 1);
        assertEquals("P050", resAkhir.getData().getCaseId());

        SinglyLinkedList.SearchResult<Putusan> resTidakAda =
                dataset.searchByCaseId("X999");

        assertFalse(resTidakAda.isFound());
        assertEquals(dataset.size(), resTidakAda.getSteps());
    }

    @Test
    public void testSkenario5FilterGabungan() {
        SinglyLinkedList<Putusan> dataset =
                CsvParser.parseCsv(PUTUSAN_CSV);

        assertNotNull(dataset);

        SinglyLinkedList<Putusan> hasilFilter =
                dataset.filterByKriteria("Sabu", 0);

        assertNotNull(hasilFilter);
        assertTrue(hasilFilter.validateInvariant());
    }

    @Test
    public void testSkenario7PenangananRecordInvalid() {
        SinglyLinkedList<Putusan> dataset =
                CsvParser.parseCsv(PUTUSAN_CSV);

        assertNotNull(dataset);
        assertTrue(dataset.validateInvariant());
    }

    @Test
    public void testSkenarioBaruTerdakwaDanProvenance() {
        SinglyLinkedList<Terdakwa> datasetTerdakwa =
                CsvParser.parseTerdakwa(TERDAKWA_CSV);

        assertNotNull(datasetTerdakwa);
        assertTrue(datasetTerdakwa.validateInvariant());

        SinglyLinkedList<Provenance> datasetProv =
                CsvParser.parseProvenance(PROVENANCE_CSV);

        assertNotNull(datasetProv);
        assertTrue(datasetProv.validateInvariant());
    }

    @Test
    public void testSkenarioGabunganRelasiData() {
        SinglyLinkedList<Putusan> datasetPutusan =
                CsvParser.parseCsv(PUTUSAN_CSV);

        SinglyLinkedList<Terdakwa> datasetTerdakwa =
                CsvParser.parseTerdakwa(TERDAKWA_CSV);

        SinglyLinkedList<Provenance> datasetProv =
                CsvParser.parseProvenance(PROVENANCE_CSV);

        assertNotNull(datasetPutusan);
        assertNotNull(datasetTerdakwa);
        assertNotNull(datasetProv);

        assertTrue(datasetPutusan.validateInvariant());
        assertTrue(datasetTerdakwa.validateInvariant());
        assertTrue(datasetProv.validateInvariant());

        assertTrue(datasetPutusan.size() > 0);
        assertTrue(datasetTerdakwa.size() > 0);
        assertTrue(datasetProv.size() > 0);

        var searchResult =
                datasetPutusan.searchByCaseId("P001");

        assertTrue(searchResult.isFound());

        System.out.println(
                "=== PENGUJIAN RELASI ANTAR KETIGA FILE CSV BERHASIL ==="
        );
    }

    @Test
    public void testPR2AgregasiSortingDanEkspor() {
        File fileCek = new File(TERDAKWA_CSV);

        assertTrue(fileCek.exists(),
                "File CSV terdakwa tidak ditemukan: "
                        + fileCek.getAbsolutePath());

        SinglyLinkedList<Terdakwa> dataset =
                CsvParser.parseTerdakwa(TERDAKWA_CSV);

        assertNotNull(dataset);
        assertFalse(dataset.isEmpty());

        int totalKasus = dataset.size();
        long totalVonis = 0;

        for (int i = 0; i < dataset.size(); i++) {
            totalVonis += dataset.get(i).getVonisBulan();
        }

        double rataRataVonis =
                (double) totalVonis / totalKasus;

        System.out.println("=== HASIL AGREGASI PR 2 ===");
        System.out.println("Total Kasus: " + totalKasus);
        System.out.println("Rata-rata Vonis: "
                + String.format("%.2f", rataRataVonis)
                + " bulan");

        String namaFileExport = "laporan_pr2_rekapitulasi.txt";

        StringBuilder sb = new StringBuilder();
        sb.append("=== LAPORAN REKAPITULASI PUTUSAN (PR 2) ===\n");
        sb.append("Total Kasus Terdaftar: ")
                .append(totalKasus).append("\n");
        sb.append("Rata-rata Lama Vonis: ")
                .append(String.format("%.2f", rataRataVonis))
                .append(" bulan\n");
        sb.append("------------------------------------------\n");

        try (FileWriter writer = new FileWriter(namaFileExport)) {
            writer.write(sb.toString());

            System.out.println(
                    "Berhasil mengekspor laporan PR 2 ke file: "
                            + namaFileExport
            );

        } catch (IOException e) {
            fail("Gagal menulis file ekspor: " + e.getMessage());
        }

        assertTrue(totalKasus > 0);
    }
}
