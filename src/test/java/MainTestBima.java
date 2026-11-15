import datastructure.AuditStack;
import datastructure.SinglyLinkedList;
import model.Putusan;
import model.Terdakwa;
import model.Provenance;
import org.testng.annotations.Test;
import service.CsvParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.testng.Assert.*;

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
        assertEquals(list.size(), 3, "Ukuran awal harus 3");

        Putusan removedMiddle = list.remove(1);
        assertEquals(removedMiddle.getCaseId(), "P002");
        assertEquals(list.size(), 2);
        assertTrue(list.validateInvariant(), "Invariant harus valid setelah hapus tengah");

        Putusan removedFirst = list.remove(0);
        assertEquals(removedFirst.getCaseId(), "P001");
        assertEquals(list.size(), 1);
        assertTrue(list.validateInvariant(), "Invariant harus valid setelah hapus pertama");

        Putusan removedLast = list.remove(0);
        assertEquals(removedLast.getCaseId(), "P003");
        assertTrue(list.isEmpty(), "List harus kosong setelah semua elemen dihapus");
        assertTrue(list.validateInvariant(), "Invariant harus valid saat list kosong");
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

        assertEquals(list.size(), 3);
        assertTrue(list.validateInvariant(), "Invariant harus tetap terjaga setelah penambahan record berulang");
    }

    @Test
    public void testAuditStackSekunder() {
        AuditStack<String> auditStack = new AuditStack<>();

        assertTrue(auditStack.isEmpty());

        auditStack.push("TAMBAH: P026");
        auditStack.push("HAPUS: P027");
        auditStack.push("CARI: P028");

        assertEquals(auditStack.size(), 3);
        assertTrue(auditStack.validateInvariant(), "Invariant AuditStack harus valid");

        boolean found = auditStack.searchPreserve("HAPUS: P027");
        assertTrue(found, "Aksi harus ditemukan dalam stack");
        assertEquals(auditStack.size(), 3, "Ukuran stack tidak boleh berubah setelah pencarian non-destruktif");

        String lastAction = auditStack.pop();
        assertEquals(lastAction, "CARI: P028");
        assertEquals(auditStack.size(), 2);
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

        SinglyLinkedList.SearchResult<Putusan> resAwal = dataset.searchByCaseId("P001");
        assertTrue(resAwal.isFound(), "Perkara P001 harus ditemukan");
        assertEquals(resAwal.getSteps(), 1, "Pencarian P001 harus membutuhkan tepat 1 langkah");
        assertEquals(resAwal.getData().getCaseId(), "P001");

        SinglyLinkedList.SearchResult<Putusan> resAkhir = dataset.searchByCaseId("P050");
        assertTrue(resAkhir.isFound(), "Perkara P050 harus ditemukan");
        assertEquals(resAkhir.getSteps(), 107, "Pencarian P050 harus membutuhkan 107 langkah");
        assertEquals(resAkhir.getData().getCaseId(), "P050");

        SinglyLinkedList.SearchResult<Putusan> resTidakAda = dataset.searchByCaseId("X999");
        assertFalse(resTidakAda.isFound(), "Perkara X999 seharusnya tidak ditemukan");
        assertEquals(resTidakAda.getSteps(), 108, "Pencarian data yang tidak ada harus menelusuri seluruh list (108 langkah)");
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

        assertTrue(!datasetPutusan.isEmpty(), "Dataset Putusan harus berisi data");
        assertTrue(!datasetTerdakwa.isEmpty(), "Dataset Terdakwa harus berisi data");
        assertTrue(!datasetProv.isEmpty(), "Dataset Provenance harus berisi data");

        var searchResult = datasetPutusan.searchByCaseId("P001");
        assertTrue(searchResult.isFound(), "Perkara P001 harus ditemukan dalam dataset utama");

        assertTrue(totalTerdakwa > 0);
    }
}
