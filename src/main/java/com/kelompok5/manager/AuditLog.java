package com.kelompok5.manager;

import com.kelompok5.structure.Stack;

public class AuditLog {

    private Stack<String> aktivitas;

    public AuditLog() {
        aktivitas = new Stack<>();
    }

    public void catatAktivitas(String deskripsi) {
        if (deskripsi == null || deskripsi.isBlank()) {
            throw new IllegalArgumentException(
                    "Deskripsi aktivitas tidak boleh kosong");
        }

        aktivitas.push(deskripsi);
    }

    public String ambilAktivitasTerbaru() {
        if (aktivitas.isEmpty()) {
            return "Belum ada aktivitas";
        }

        return aktivitas.peek();
    }

    public String hapusAktivitasTerbaru() {
        if (aktivitas.isEmpty()) {
            return "Tidak ada aktivitas untuk dihapus";
        }

        return aktivitas.pop();
    }

    public void tampilkanSemuaAktivitas() {
        Stack<String> sementara = new Stack<>();

        System.out.println("=== RIWAYAT AKTIVITAS ===");

        while (!aktivitas.isEmpty()) {
            String data = aktivitas.pop();

            System.out.println(data);
            sementara.push(data);
        }

        while (!sementara.isEmpty()) {
            aktivitas.push(sementara.pop());
        }
    }

    public int jumlahAktivitas() {
        return aktivitas.size();
    }

    public boolean isEmpty() {
        return aktivitas.isEmpty();
    }

    public boolean validateInvariant() {
        return aktivitas.validateInvariant();
    }
}
