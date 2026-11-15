package com.kelompok5.manager;

import com.kelompok5.model.Putusan;
import com.kelompok5.model.TerdakwaData;
import com.kelompok5.structure.DynamicArray;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PutusanManagerHashTableTest {

    private PutusanManager manager;

    @BeforeEach
    void setUp() {
        manager = new PutusanManager();
    }

    /**
     * Membuat data putusan untuk kebutuhan pengujian.
     */
    private Putusan buatPutusan(
            String caseId,
            String nomorPerkara
    ) {

        Putusan putusan = new Putusan(
                caseId,
                nomorPerkara,
                "Pengadilan Negeri Jakarta Pusat",
                LocalDate.of(2025, 1, 15),
                "Sabu",
                1.5,
                "Pasal 112 ayat (1)",
                "Pasal 112 ayat (1)",
                10000000L,
                "1 paket barang bukti",
                "Perbuatan dilakukan secara sadar",
                "Terdakwa bersikap sopan",
                "1-3"
        );

        putusan.tambahTerdakwa(
                new TerdakwaData(caseId, "T1", 12, 10)
        );

        return putusan;
    }

    // =============================================
    // TEST PENAMBAHAN DATA
    // =============================================

    @Test
    void testTambahPutusanMemperbaruiHashTable() {

        Putusan putusan = buatPutusan(
                "P001",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        assertTrue(manager.tambahPutusan(putusan));

        assertEquals(1, manager.jumlahPutusan());

        assertSame(
                putusan,
                manager.cariBerdasarkanNomorPerkara(
                        "123/Pid.Sus/2025/PN.Jkt.Pst"
                )
        );
    }

    @Test
    void testNomorPerkaraTidakSensitifHurufBesarKecil() {

        Putusan putusan = buatPutusan(
                "P001",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        manager.tambahPutusan(putusan);

        Putusan hasil = manager.cariBerdasarkanNomorPerkara(
                " 123/PID.SUS/2025/PN.JKT.PST "
        );

        assertSame(putusan, hasil);
    }

    @Test
    void testNomorPerkaraDuplikatTetapTersimpan() {

        Putusan putusan1 = buatPutusan(
                "P001",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        Putusan putusan2 = buatPutusan(
                "P002",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        assertTrue(manager.tambahPutusan(putusan1));
        assertTrue(manager.tambahPutusan(putusan2));

        assertEquals(2, manager.jumlahPutusan());

        DynamicArray<Putusan> hasil =
                manager.cariSemuaBerdasarkanNomorPerkara(
                        "123/Pid.Sus/2025/PN.Jkt.Pst"
                );

        assertEquals(2, hasil.size());
        assertSame(putusan1, hasil.get(0));
        assertSame(putusan2, hasil.get(1));
    }

    @Test
    void testPencarianNomorPerkaraTidakDitemukan() {

        Putusan hasil = manager.cariBerdasarkanNomorPerkara(
                "999/Pid.Sus/2025/PN.Jkt.Pst"
        );

        assertNull(hasil);
    }

    @Test
    void testPencarianNomorPerkaraKosong() {

        assertNull(manager.cariBerdasarkanNomorPerkara(null));

        assertNull(manager.cariBerdasarkanNomorPerkara(""));

        assertNull(manager.cariBerdasarkanNomorPerkara("   "));
    }

    // =============================================
    // TEST PENGHAPUSAN DATA
    // =============================================

    @Test
    void testHapusPutusanMemperbaruiHashTable() {

        Putusan putusan = buatPutusan(
                "P001",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        manager.tambahPutusan(putusan);

        assertTrue(manager.hapusPutusan("P001"));

        assertEquals(0, manager.jumlahPutusan());

        assertNull(
                manager.cariBerdasarkanNomorPerkara(
                        "123/Pid.Sus/2025/PN.Jkt.Pst"
                )
        );
    }

    @Test
    void testHapusSatuDariDuaNomorPerkaraYangSama() {

        Putusan putusan1 = buatPutusan(
                "P001",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        Putusan putusan2 = buatPutusan(
                "P002",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        manager.tambahPutusan(putusan1);
        manager.tambahPutusan(putusan2);

        assertTrue(manager.hapusPutusan("P001"));

        assertEquals(1, manager.jumlahPutusan());

        assertSame(
                putusan2,
                manager.cariBerdasarkanNomorPerkara(
                        "123/Pid.Sus/2025/PN.Jkt.Pst"
                )
        );
    }

    @Test
    void testHapusPutusanTidakDitemukan() {

        Putusan putusan = buatPutusan(
                "P001",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        manager.tambahPutusan(putusan);

        assertFalse(manager.hapusPutusan("P999"));

        assertEquals(1, manager.jumlahPutusan());
    }

    // =============================================
    // TEST CLEAR
    // =============================================

    @Test
    void testClearMengosongkanPenyimpananDanHashTable() {

        manager.tambahPutusan(
                buatPutusan("P001", "123/Pid.Sus/2025/PN.Jkt.Pst")
        );

        manager.tambahPutusan(
                buatPutusan("P002", "456/Pid.Sus/2025/PN.Jkt.Pst")
        );

        manager.clear();

        assertTrue(manager.isEmpty());
        assertEquals(0, manager.jumlahPutusan());

        assertNull(
                manager.cariBerdasarkanNomorPerkara(
                        "123/Pid.Sus/2025/PN.Jkt.Pst"
                )
        );

        assertNull(
                manager.cariBerdasarkanNomorPerkara(
                        "456/Pid.Sus/2025/PN.Jkt.Pst"
                )
        );
    }

    // =============================================
    // TEST DUPLIKASI CASE ID
    // =============================================

    @Test
    void testCaseIdDuplikatDitolak() {

        Putusan putusan1 = buatPutusan(
                "P001",
                "123/Pid.Sus/2025/PN.Jkt.Pst"
        );

        Putusan putusan2 = buatPutusan(
                "P001",
                "456/Pid.Sus/2025/PN.Jkt.Pst"
        );

        assertTrue(manager.tambahPutusan(putusan1));

        assertFalse(manager.tambahPutusan(putusan2));

        assertEquals(1, manager.jumlahPutusan());

        assertSame(
                putusan1,
                manager.cariBerdasarkanNomorPerkara(
                        "123/Pid.Sus/2025/PN.Jkt.Pst"
                )
        );

        assertNull(
                manager.cariBerdasarkanNomorPerkara(
                        "456/Pid.Sus/2025/PN.Jkt.Pst"
                )
        );
    }

    // =============================================
    // TEST VALIDASI INVARIANT
    // =============================================

    @Test
    void testValidateInvariantSetelahTambahData() {

        manager.tambahPutusan(
                buatPutusan("P001", "123/Pid.Sus/2025/PN.Jkt.Pst")
        );

        manager.tambahPutusan(
                buatPutusan("P002", "456/Pid.Sus/2025/PN.Jkt.Pst")
        );

        assertTrue(manager.validateInvariant());
    }

    @Test
    void testValidateInvariantSetelahHapusData() {

        manager.tambahPutusan(
                buatPutusan("P001", "123/Pid.Sus/2025/PN.Jkt.Pst")
        );

        manager.tambahPutusan(
                buatPutusan("P002", "123/Pid.Sus/2025/PN.Jkt.Pst")
        );

        manager.hapusPutusan("P001");

        assertTrue(manager.validateInvariant());
    }

    @Test
    void testValidateInvariantSetelahClear() {

        manager.tambahPutusan(
                buatPutusan("P001", "123/Pid.Sus/2025/PN.Jkt.Pst")
        );

        manager.clear();

        assertTrue(manager.validateInvariant());
    }
}
