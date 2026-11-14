# Log Jejak Pengujian (Test Trace)

Pengujian dilakukan menggunakan JUnit 5 pada kelas `MainTestBima.java`.

## Hasil Eksekusi Skenario Pengujian (9/9 Passed)
1. **testLoadCsvToSinglyLinkedList**: Berhasil memuat data dari file CSV utama dan menjaga validitas *invariant*[cite: 12].
2. **testHapusElemenPertamaTengahAkhir**: Validasi penghapusan *node* di awal, tengah, dan akhir dengan pembaruan pointer yang benar[cite: 12, 13].
3. **testTambahKembaliDanInvariant**: Validasi penambahan record berulang dan kestabilan *invariant*[cite: 12, 13].
4. **testAuditStackSekunder**: Menguji operasi *push*, *pop*, dan `searchPreserve` pada struktur stack sekunder[cite: 11, 12].
5. **testSkenario2Search**: Pengujian pencarian record awal, akhir, dan data tidak ada beserta pencacahan jumlah langkah[cite: 12, 13].
6. **testSkenario5FilterGabungan**: Pengujian filter berdasarkan kriteria narkotika dan vonis[cite: 12, 13].
7. **testSkenario7PenangananRecordInvalid**: Memastikan data korup/invalid ditangkap oleh *exception handling* tanpa *crash*[cite: 12, 13].
8. **testSkenarioBaruTerdakwaDanProvenance**: Validasi parser khusus untuk file Terdakwa dan Provenance[cite: 10].
9. **testSkenarioGabunganRelasiData**: Validasi relasi (*join*) antar tabel menggunakan `caseId`[cite: 10].