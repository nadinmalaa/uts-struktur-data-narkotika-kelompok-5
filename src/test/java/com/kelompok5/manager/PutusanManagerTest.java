package com.kelompok5.manager;

import com.kelompok5.model.Putusan;
import com.kelompok5.model.TerdakwaData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PutusanManagerTest {

    private Putusan buatPutusan(
            String caseId,
            String nomor,
            long denda,
            int... vonis) {

        Putusan putusan = new Putusan(
                caseId,
                nomor,
                "Pengadilan Negeri Malang",
                LocalDate.of(2025, 1, 10),
                "Sabu",
                2.5,
                "Pasal 112",
                "Pasal 112",
                denda,
                "Barang bukti",
                "Keadaan memberatkan",
                "Keadaan meringankan",
                "Halaman 1"
        );

        for (int i = 0; i < vonis.length; i++) {
            putusan.tambahTerdakwa(
                    new TerdakwaData(
                            caseId,
                            "T" + (i + 1),
                            vonis[i],
                            vonis[i]
                    )
            );
        }

        return putusan;
    }

    @Test
    void testInitialState() {
        PutusanManager manager = new PutusanManager();

        assertTrue(manager.isEmpty());
        assertEquals(0, manager.jumlahPutusan());
        assertTrue(manager.validateInvariant());
    }

    @Test
    void testTambahPutusan() {
        PutusanManager manager = new PutusanManager();

        assertTrue(manager.tambahPutusan(
                buatPutusan("C001", "01/Pid.Sus/2025", 1000000, 24)
        ));

        assertEquals(1, manager.jumlahPutusan());
        assertFalse(manager.isEmpty());
    }

    @Test
    void testTolakPutusanNull() {
        PutusanManager manager = new PutusanManager();

        assertFalse(manager.tambahPutusan(null));
        assertEquals(0, manager.jumlahPutusan());
    }

    @Test
    void testTolakCaseIdDuplikat() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/Pid.Sus/2025", 1000000, 24)
        );

        assertFalse(manager.tambahPutusan(
                buatPutusan("c001", "02/Pid.Sus/2025", 2000000, 36)
        ));

        assertEquals(1, manager.jumlahPutusan());
    }

    @Test
    void testAliasAddPutusan() {
        PutusanManager manager = new PutusanManager();

        assertTrue(manager.addPutusan(
                buatPutusan("C001", "01/Pid.Sus/2025", 1000000, 24)
        ));
    }

    @Test
    void testAliasAdd() {
        PutusanManager manager = new PutusanManager();

        assertTrue(manager.add(
                buatPutusan("C001", "01/Pid.Sus/2025", 1000000, 24)
        ));
    }

    @Test
    void testHapusPutusan() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/Pid.Sus/2025", 1000000, 24)
        );

        assertTrue(manager.hapusPutusan("C001"));
        assertEquals(0, manager.jumlahPutusan());
        assertTrue(manager.isEmpty());
    }

    @Test
    void testHapusPutusanDenganCaseIdTidakValid() {
        PutusanManager manager = new PutusanManager();

        assertFalse(manager.hapusPutusan(null));
        assertFalse(manager.hapusPutusan(""));
        assertFalse(manager.hapusPutusan("   "));
        assertFalse(manager.hapusPutusan("TIDAK ADA"));
    }

    @Test
    void testGetPutusan() {
        PutusanManager manager = new PutusanManager();
        Putusan putusan = buatPutusan(
                "C001", "01/Pid.Sus/2025", 1000000, 24
        );

        manager.tambahPutusan(putusan);

        assertSame(putusan, manager.getPutusan(0));
    }

    @Test
    void testCariBerdasarkanCaseId() {
        PutusanManager manager = new PutusanManager();
        Putusan putusan = buatPutusan(
                "C001", "01/Pid.Sus/2025", 1000000, 24
        );

        manager.tambahPutusan(putusan);

        assertSame(putusan, manager.cariBerdasarkanCaseId("c001"));
        assertNull(manager.cariBerdasarkanCaseId("C999"));
    }

    @Test
    void testCariCaseIdTidakValid() {
        PutusanManager manager = new PutusanManager();

        assertNull(manager.cariBerdasarkanCaseId(null));
        assertNull(manager.cariBerdasarkanCaseId(""));
        assertNull(manager.cariBerdasarkanCaseId("   "));
    }

    @Test
    void testCariBerdasarkanNomorPerkara() {
        PutusanManager manager = new PutusanManager();
        Putusan putusan = buatPutusan(
                "C001", "01/Pid.Sus/2025", 1000000, 24
        );

        manager.tambahPutusan(putusan);

        assertSame(
                putusan,
                manager.cariBerdasarkanNomorPerkara("01/Pid.Sus/2025")
        );
    }

    @Test
    void testLangkahPencarian() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 24)
        );
        manager.tambahPutusan(
                buatPutusan("C002", "02/2025", 2000000, 36)
        );

        assertEquals(2, manager.langkahCariCaseId("C002"));
        assertEquals(1, manager.langkahCariNomorPerkara("01/2025"));
    }

    @Test
    void testRataRataVonis() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 12, 24)
        );
        manager.tambahPutusan(
                buatPutusan("C002", "02/2025", 2000000, 36)
        );

        assertEquals(24.0, manager.rataRataVonis(), 0.001);
    }

    @Test
    void testRataRataVonisMengabaikanNilaiNol() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 0, 24)
        );

        assertEquals(24.0, manager.rataRataVonis(), 0.001);
    }

    @Test
    void testRataRataVonisTanpaDataValid() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 0)
        );

        assertEquals(0.0, manager.rataRataVonis(), 0.001);
    }

    @Test
    void testTotalDenda() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 24)
        );
        manager.tambahPutusan(
                buatPutusan("C002", "02/2025", 2000000, 36)
        );

        assertEquals(3000000L, manager.totalDenda());
    }

    @Test
    void testJumlahSeluruhTerdakwa() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 12, 24)
        );
        manager.tambahPutusan(
                buatPutusan("C002", "02/2025", 2000000, 36)
        );

        assertEquals(3, manager.jumlahSeluruhTerdakwa());
    }

    @Test
    void testUrutkanBerdasarkanVonis() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 36)
        );
        manager.tambahPutusan(
                buatPutusan("C002", "02/2025", 2000000, 12)
        );
        manager.tambahPutusan(
                buatPutusan("C003", "03/2025", 3000000, 24)
        );

        manager.urutkanBerdasarkanVonis();

        assertEquals("C002", manager.getPutusan(0).getCaseId());
        assertEquals("C003", manager.getPutusan(1).getCaseId());
        assertEquals("C001", manager.getPutusan(2).getCaseId());

        assertTrue(manager.getPerbandinganPengurutanTerakhir() > 0);
    }

    @Test
    void testPutusanTanpaVonisValidDiletakkanTerakhir() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 0)
        );
        manager.tambahPutusan(
                buatPutusan("C002", "02/2025", 2000000, 24)
        );

        manager.urutkanBerdasarkanVonis();

        assertEquals("C002", manager.getPutusan(0).getCaseId());
        assertEquals("C001", manager.getPutusan(1).getCaseId());
    }

    @Test
    void testValidateInvariant() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 24, 36)
        );

        assertTrue(manager.validateInvariant());
    }

    @Test
    void testInvariantMenolakPutusanTanpaTerdakwa() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000)
        );

        assertFalse(manager.validateInvariant());
    }

    @Test
    void testClear() {
        PutusanManager manager = new PutusanManager();

        manager.tambahPutusan(
                buatPutusan("C001", "01/2025", 1000000, 24)
        );

        manager.clear();

        manager.clear();

        assertTrue(
                manager.isEmpty(),
                "Manager harus kosong setelah clear() dipanggil"
        );
        assertEquals(
                0,
                manager.jumlahPutusan(),
                "Jumlah putusan harus 0 setelah clear()"
        );
        assertEquals(
                0,
                manager.totalDenda(),
                "Total denda harus 0 setelah clear()"
        );
        assertTrue(
                manager.validateInvariant(),
                "Invariant harus tetap valid setelah clear()"
        );
    }
}
