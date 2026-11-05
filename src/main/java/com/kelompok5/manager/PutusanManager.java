package com.kelompok5.manager;

import com.kelompok5.model.Putusan;
import com.kelompok5.structure.DynamicArray;

public class PutusanManager {

    private DynamicArray<Putusan> daftarPutusan;

    public PutusanManager() {
        daftarPutusan = new DynamicArray<>();
    }

    public void tambahPutusan(Putusan putusan) {
        if (putusan == null) {
            throw new IllegalArgumentException("Putusan tidak boleh null");
        }

        if (cariBerdasarkanCaseId(putusan.getCaseId()) != null) {
            throw new IllegalArgumentException("Case ID sudah terdaftar");
        }

        daftarPutusan.add(putusan);
    }

    public Putusan cariBerdasarkanCaseId(String caseId) {
        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan putusan = daftarPutusan.get(i);

            if (putusan.getCaseId().equalsIgnoreCase(caseId)) {
                return putusan;
            }
        }
        return null;
    }

    public boolean hapusPutusan(String caseId) {
        for (int i = 0; i < daftarPutusan.size(); i++) {
            if (daftarPutusan.get(i).getCaseId().equalsIgnoreCase(caseId)) {
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

    public boolean validateInvariant() {
        return daftarPutusan.validateInvariant();
    }

    public void tampilkanSemuaPutusan() {
        for (int i = 0; i < daftarPutusan.size(); i++) {
            System.out.println(daftarPutusan.get(i));
        }
    }
}
