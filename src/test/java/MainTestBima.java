import datastructure.AuditStack;
import datastructure.SinglyLinkedList;
import model.Putusan;
import model.Terdakwa;
import model.Provenance;
import org.junit.jupiter.api.Test;
import service.CsvParser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MainTestBima {

    @Test
    public void testHapusElemenPertamaTengahAkhir() {
        SinglyLinkedList<Putusan> list = new SinglyLinkedList<>();
        Putusan p1 = new Putusan("P001", "01/Pid/2026/PN Dps", "PN Denpasar", LocalDate.now(), "T01", "Sabu", 10.0, "Pasal 112", "Pasal 112", 12, 12, 1000000, "Barang bukti 1", "Berat", "Ringan", "1");
        Putusan p2 = new Putusan("P002", "02/Pid/2026/PN Dps", "PN Denpasar", LocalDate.now(), "T02", "Ganja", 20.0, "Pasal 111", "Pasal 111", 24, 24, 2000000, "Barang bukti 2", "Berat", "Ringan", "2");
        Putusan p3 = new Putusan("P003", "03/Pid/2026/PN Dps", "PN Denpasar", LocalDate.now(), "T03", "Ekstasi", 30.0, "Pasal 114", "Pasal 114", 36, 36, 3000000, "Barang bukti 3", "Berat", "Ringan", "3");

        list.add(p1);
        list.add(p2);
        list.add(p3);
        assertEquals(3, list.size(), "Ukuran awal harus 3");

        Putusan removedMiddle = list.remove(1);
        assertEquals("P002", removedMiddle.getCaseId());
        assertEquals(2, list.size());
        assertTrue(list.validateInvariant(), "Invariant harus valid setelah hapus tengah");

        Putusan removedFirst = list.remove(0);
        assertEquals("P001", removedFirst.getCaseId());
        assertEquals(1, list.size());
        assertTrue(list.validateInvariant(), "Invariant harus valid setelah hapus pertama");

        Putusan removedLast = list.remove(0);
        assertEquals("P003", removedLast.getCaseId());
        assertTrue(list.isEmpty(), "List harus kosong setelah semua elemen dihapus");
        assertTrue(list.validateInvariant(), "Invariant harus valid saat list kosong");
    }

    @Test
    public void testTambahKembaliDanInvariant() {
        SinglyLinkedList<Putusan> list = new SinglyLinkedList<>();
        Putusan p1 = new Putusan("P001", "01/Pid/2026/PN Dps", "PN Denpasar", LocalDate.now(), "T01", "Sabu", 10.0, "Pasal 112", "Pasal 112", 12, 12, 1000000, "BB", "Berat", "Ringan", "1");

        list.add(p1);
        list.add(p1);
        list.add(p1);

        assertEquals(3, list.size());
        assertTrue(list.validateInvariant(), "Invariant harus tetap terjaga setelah penambahan record berulang");
    }

    @Test
    public void testAuditStackSekunder() {
        AuditStack<String> auditStack = new AuditStack<>();
        assertTrue(auditStack.isEmpty());

        auditStack.push("TAMBAH: P026");
        auditStack.push("HAPUS: P027");
        auditStack.push("CARI: P028");

        assertEquals(3, auditStack.size());
        assertTrue(auditStack.validateInvariant(), "Invariant AuditStack harus valid");

        boolean found = auditStack.searchPreserve("HAPUS: P027");
        assertTrue(found, "Aksi harus ditemukan dalam stack");
        assertEquals(3, auditStack.size(), "Ukuran stack tidak boleh berubah setelah pencarian non-destruktif");

        String lastAction = auditStack.pop();
        assertEquals("CARI: P028", lastAction);
        assertEquals(2, auditStack.size());
    }

    @Test
    public void testLoadCsvToSinglyLinkedList() {
        SinglyLinkedList<Putusan> dataset = CsvParser.parseCsv("data/putusan-50 Data Putusan.csv");

        assertNotNull(dataset, "Dataset linked list tidak boleh null");
        assertFalse(dataset.isEmpty(), "Dataset harus berhasil memuat data dari file CSV");
        assertTrue(dataset.validateInvariant(), "Invariant struktur data harus tetap valid setelah memuat CSV");
    }

    @Test
    public void testSkenario2Search() {
        SinglyLinkedList<Putusan> dataset = CsvParser.parseCsv("data/putusan-50 Data Putusan.csv");
        assertNotNull(dataset, "Dataset harus berhasil dimuat");

        SinglyLinkedList.SearchResult<Putusan> resAwal = dataset.searchByCaseId("P001");
        assertTrue(resAwal.isFound(), "Perkara P001 harus ditemukan");
        assertEquals(1, resAwal.getSteps(), "Pencarian P001 harus membutuhkan tepat 1 langkah");
        assertEquals("P001", resAwal.getData().getCaseId());

        SinglyLinkedList.SearchResult<Putusan> resAkhir = dataset.searchByCaseId("P050");
        assertTrue(resAkhir.isFound(), "Perkara P050 harus ditemukan");
        assertEquals(124, resAkhir.getSteps(), "Pencarian P050 harus membutuhkan 124 langkah");
        assertEquals("P050", resAkhir.getData().getCaseId());

        SinglyLinkedList.SearchResult<Putusan> resTidakAda = dataset.searchByCaseId("X999");
        assertFalse(resTidakAda.isFound(), "Perkara X999 seharusnya tidak ditemukan");
        assertEquals(125, resTidakAda.getSteps(), "Pencarian data yang tidak ada harus menelusuri seluruh list (125 langkah)");
    }

    @Test
    public void testSkenario5FilterGabungan() {
        SinglyLinkedList<Putusan> dataset = CsvParser.parseCsv("data/putusan-50 Data Putusan.csv");
        assertNotNull(dataset, "Dataset harus berhasil dimuat");

        SinglyLinkedList<Putusan> hasilFilter = dataset.filterByKriteria("Sabu", 0);

        assertNotNull(hasilFilter, "Hasil filter gabungan tidak boleh null");
        assertTrue(hasilFilter.validateInvariant(), "Invariant struktur data hasil filter gabungan harus valid");
    }

    @Test
    public void testSkenario7PenangananRecordInvalid() {
        SinglyLinkedList<Putusan> dataset = CsvParser.parseCsv("data/putusan-50 Data Putusan.csv");
        assertNotNull(dataset, "Dataset tidak boleh null meskipun menghadapi record invalid");

        assertTrue(dataset.validateInvariant(), "Invariant harus tetap valid setelah penanganan data");
    }

    @Test
    public void testSkenarioBaruTerdakwaDanProvenance() {
        SinglyLinkedList<Terdakwa> datasetTerdakwa = CsvParser.parseTerdakwa("data/putusan-50 Data Terdakwa.csv");
        assertNotNull(datasetTerdakwa, "Dataset terdakwa tidak boleh null");
        assertTrue(datasetTerdakwa.validateInvariant(), "Invariant SinglyLinkedList terdakwa harus valid");

        SinglyLinkedList<Provenance> datasetProv = CsvParser.parseProvenance("data/putusan-50 Data Provenance.csv");
        assertNotNull(datasetProv, "Dataset provenance tidak boleh null");
        assertTrue(datasetProv.validateInvariant(), "Invariant SinglyLinkedList provenance harus valid");
    }

    @Test
    public void testSkenarioGabunganRelasiData() {
        SinglyLinkedList<Putusan> datasetPutusan = CsvParser.parseCsv("data/putusan-50 Data Putusan.csv");
        SinglyLinkedList<Terdakwa> datasetTerdakwa = CsvParser.parseTerdakwa("data/putusan-50 Data Terdakwa.csv");
        SinglyLinkedList<Provenance> datasetProv = CsvParser.parseProvenance("data/putusan-50 Data Provenance.csv");

        assertNotNull(datasetPutusan, "Dataset Putusan tidak boleh null");
        assertNotNull(datasetTerdakwa, "Dataset Terdakwa tidak boleh null");
        assertNotNull(datasetProv, "Dataset Provenance tidak boleh null");

        assertTrue(datasetPutusan.validateInvariant());
        assertTrue(datasetTerdakwa.validateInvariant());
        assertTrue(datasetProv.validateInvariant());

        // Validasi ukuran dan keberadaan data relasional utama
        assertTrue(datasetPutusan.size() > 0, "Dataset Putusan harus berisi data");
        assertTrue(datasetTerdakwa.size() > 0, "Dataset Terdakwa harus berisi data");
        assertTrue(datasetProv.size() > 0, "Dataset Provenance harus berisi data");

        // Uji pencarian record dasar lintas data menggunakan searchByCaseId
        var searchResult = datasetPutusan.searchByCaseId("P001");
        assertTrue(searchResult.isFound(), "Perkara P001 harus ditemukan dalam dataset utama");

        System.out.println("=== PENGUJIAN RELASI ANTAR KETIGA FILE CSV BERHASIL ===");
    }
}