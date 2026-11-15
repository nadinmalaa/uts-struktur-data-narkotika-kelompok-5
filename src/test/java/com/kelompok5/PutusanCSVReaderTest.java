package com.kelompok5;

import com.kelompok5.manager.PutusanManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PutusanCSVReaderTest {

    @TempDir
    Path tempDir;

    private Path buatFile(String nama, String isi) throws IOException {
        Path file = tempDir.resolve(nama);
        Files.writeString(file, isi, StandardCharsets.UTF_8);
        return file;
    }

    private Path buatCSVPutusan() throws IOException {
        return buatFile(
                "putusan.csv",
                "caseId,nomorPerkara,pengadilanNegeri,tanggalPutusan,"
                        + "jenisNarkotika,beratBarangBuktiGram,pasalDakwaan,"
                        + "pasalTerbukti,dendaRupiah,barangBukti,"
                        + "keadaanMemberatkan,keadaanMeringankan\n"
                        + "C001,01/Pid.Sus/2025,PN Malang,2025-01-10,"
                        + "Sabu,2.5,Pasal 112,Pasal 112,1000000,"
                        + "Sabu,Memberatkan,Meringankan\n"
        );
    }

    private Path buatCSVTerdakwa() throws IOException {
        return buatFile(
                "terdakwa.csv",
                "caseId,kodeTerdakwa,tuntutanBulan,vonisBulan\n"
                        + "C001,T001,36,24\n"
                        + "C001,T002,48,36\n"
        );
    }

    private Path buatCSVProvenance() throws IOException {
        return buatFile(
                "provenance.csv",
                "caseId,sourcePages\n"
                        + "C001,Halaman 1-3\n"
        );
    }

    @Test
    void testImportDataBerhasil() throws IOException {
        Path putusan = buatCSVPutusan();
        Path terdakwa = buatCSVTerdakwa();
        Path provenance = buatCSVProvenance();

        PutusanManager manager = new PutusanManager();
        PutusanCSVReader reader = new PutusanCSVReader();

        reader.importData(
                putusan.toString(),
                terdakwa.toString(),
                provenance.toString(),
                manager
        );

        assertEquals(1, manager.jumlahPutusan());
        assertEquals(2, reader.jumlahDataTerdakwa());
        assertEquals(2, manager.jumlahSeluruhTerdakwa());
        assertTrue(manager.validateInvariant());
    }

    @Test
    void testImportBeberapaTerdakwaDalamSatuPerkara()
            throws IOException {

        PutusanManager manager = new PutusanManager();
        PutusanCSVReader reader = new PutusanCSVReader();

        reader.importData(
                buatCSVPutusan().toString(),
                buatCSVTerdakwa().toString(),
                buatCSVProvenance().toString(),
                manager
        );

        assertEquals(1, manager.jumlahPutusan());
        assertEquals(2, manager.getPutusan(0).jumlahTerdakwa());
    }

    @Test
    void testRataRataVonisTerdakwa() throws IOException {
        PutusanManager manager = new PutusanManager();
        PutusanCSVReader reader = new PutusanCSVReader();

        reader.importData(
                buatCSVPutusan().toString(),
                buatCSVTerdakwa().toString(),
                buatCSVProvenance().toString(),
                manager
        );

        // (24 + 36) / 2 = 30 bulan
        assertEquals(30.0, reader.rataRataVonisTerdakwa(), 0.001);
    }

    @Test
    void testImportDitolakJikaTidakAdaTerdakwaCocok()
            throws IOException {

        Path terdakwa = buatFile(
                "terdakwa.csv",
                "caseId,kodeTerdakwa,tuntutanBulan,vonisBulan\n"
                        + "C999,T001,36,24\n"
        );

        PutusanManager manager = new PutusanManager();
        PutusanCSVReader reader = new PutusanCSVReader();

        reader.importData(
                buatCSVPutusan().toString(),
                terdakwa.toString(),
                buatCSVProvenance().toString(),
                manager
        );

        assertEquals(0, manager.jumlahPutusan());
        assertTrue(manager.isEmpty());
    }

    @Test
    void testBarisPutusanTidakValidDitolak() throws IOException {
        Path putusan = buatFile(
                "putusan-invalid.csv",
                "caseId,nomorPerkara,pengadilanNegeri,tanggalPutusan,"
                        + "jenisNarkotika,beratBarangBuktiGram,pasalDakwaan,"
                        + "pasalTerbukti,dendaRupiah,barangBukti,"
                        + "keadaanMemberatkan,keadaanMeringankan\n"
                        + "C001,01/2025,PN Malang,2025-01-10,"
                        + "Sabu,-2.5,Pasal 112,Pasal 112,1000000,"
                        + "Sabu,Memberatkan,Meringankan\n"
        );

        PutusanManager manager = new PutusanManager();
        PutusanCSVReader reader = new PutusanCSVReader();

        reader.importData(
                putusan.toString(),
                buatCSVTerdakwa().toString(),
                buatCSVProvenance().toString(),
                manager
        );

        assertEquals(0, manager.jumlahPutusan());
        assertEquals(2, reader.jumlahDataTerdakwa());
    }

    @Test
    void testImportDenganManagerNull() throws IOException {
        PutusanCSVReader reader = new PutusanCSVReader();

        assertDoesNotThrow(() ->
                reader.importData(
                        buatCSVPutusan().toString(),
                        buatCSVTerdakwa().toString(),
                        buatCSVProvenance().toString(),
                        null
                )
        );

        assertEquals(0, reader.jumlahDataTerdakwa());
    }

    @Test
    void testImportGagalJikaFileTerdakwaTidakAda()
            throws IOException {

        PutusanManager manager = new PutusanManager();
        PutusanCSVReader reader = new PutusanCSVReader();

        Path putusan = buatCSVPutusan();
        Path provenance = buatCSVProvenance();

        reader.importData(
                putusan.toString(),
                tempDir.resolve("tidak-ada.csv").toString(),
                provenance.toString(),
                manager
        );

        assertEquals(0, manager.jumlahPutusan());
        assertEquals(0, reader.jumlahDataTerdakwa());
    }

    @Test
    void testImportGagalJikaFileProvenanceTidakAda()
            throws IOException {

        PutusanManager manager = new PutusanManager();
        PutusanCSVReader reader = new PutusanCSVReader();

        Path putusan = buatCSVPutusan();
        Path terdakwa = buatCSVTerdakwa();

        reader.importData(
                putusan.toString(),
                terdakwa.toString(),
                tempDir.resolve("provenance-tidak-ada.csv").toString(),
                manager
        );

        assertEquals(0, manager.jumlahPutusan());
        assertEquals(2, reader.jumlahDataTerdakwa());
    }

    @Test
    void testJumlahDataTerdakwaAwalnyaNol() {
        PutusanCSVReader reader = new PutusanCSVReader();

        assertEquals(0, reader.jumlahDataTerdakwa());
    }

    @Test
    void testRataRataVonisAwalnyaNol() {
        PutusanCSVReader reader = new PutusanCSVReader();

        assertEquals(0.0, reader.rataRataVonisTerdakwa(), 0.001);
    }
}
