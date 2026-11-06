package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Karyawan> daftarKaryawan = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Menambahkan (dummy data) untuk pengujian
        daftarKaryawan.add(new KaryawanTetap("EMP-001", "Budi Santoso", 5000000, 1500000, 500000));
        daftarKaryawan.add(new KaryawanKontrak("EMP-002", "Siti Rahma", 4000000, 12, 200000));

        boolean running = true;

        while (running) {
            tampilkanMenu();
            System.out.print("Pilih menu (1-5): ");
            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1" -> tambahKaryawanTetap();
                case "2" -> tambahKaryawanKontrak();
                case "3" -> tampilkanSemuaKaryawan();
                case "4" -> cariKaryawan();
                case "5" -> {
                    System.out.println("\nTerima kasih telah menggunakan Sistem Manajemen Karyawan!");
                    running = false;
                }
                default -> System.out.println("\n[Error] Pilihan menu tidak valid. Silakan pilih 1-5.");
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println("\n========================================");
        System.out.println("     SISTEM MANAJEMEN KARYAWAN (CLI)    ");
        System.out.println("========================================");
        System.out.println("1. Tambah Karyawan Tetap");
        System.out.println("2. Tambah Karyawan Kontrak");
        System.out.println("3. Tampilkan Semua Karyawan & Total Gaji");
        System.out.println("4. Cari Karyawan Berdasarkan ID");
        System.out.println("5. Keluar");
        System.out.println("----------------------------------------");
    }

    private static void tambahKaryawanTetap() {
        System.out.println("\n--- Tambah Karyawan Tetap ---");
        try {
            System.out.print("ID Karyawan        : ");
            String id = scanner.nextLine().trim();
            cekIdDuplikat(id);

            System.out.print("Nama Karyawan      : ");
            String nama = scanner.nextLine().trim();

            System.out.print("Gaji Pokok (Rp)    : ");
            double gajiPokok = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Bonus Performa (Rp): ");
            double bonus = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Tunjangan Kes (Rp) : ");
            double tunjangan = Double.parseDouble(scanner.nextLine().trim());

            KaryawanTetap kt = new KaryawanTetap(id, nama, gajiPokok, bonus, tunjangan);
            daftarKaryawan.add(kt);
            System.out.println("\n[Sukses] Karyawan Tetap berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            System.out.println("\n[Error Input] Nominal angka harus berupa bilangan yang valid!");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[Error Validasi] " + e.getMessage());
        }
    }

    private static void tambahKaryawanKontrak() {
        System.out.println("\n--- Tambah Karyawan Kontrak ---");
        try {
            System.out.print("ID Karyawan        : ");
            String id = scanner.nextLine().trim();
            cekIdDuplikat(id);

            System.out.print("Nama Karyawan      : ");
            String nama = scanner.nextLine().trim();

            System.out.print("Gaji Pokok (Rp)    : ");
            double gajiPokok = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Lama Kontrak (Bulan): ");
            int lamaKontrak = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Potongan PPh (Rp)  : ");
            double pph = Double.parseDouble(scanner.nextLine().trim());

            KaryawanKontrak kk = new KaryawanKontrak(id, nama, gajiPokok, lamaKontrak, pph);
            daftarKaryawan.add(kk);
            System.out.println("\n[Sukses] Karyawan Kontrak berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            System.out.println("\n[Error Input] Input angka/bulan harus berupa angka yang valid!");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[Error Validasi] " + e.getMessage());
        }
    }

    private static void tampilkanSemuaKaryawan() {
        System.out.println("\n========================================");
        System.out.println("         DAFTAR SELURUH KARYAWAN        ");
        System.out.println("========================================");

        if (daftarKaryawan.isEmpty()) {
            System.out.println("Belum ada data karyawan.");
            return;
        }

        double totalPengeluaranGaji = 0;
        for (Karyawan k : daftarKaryawan) {
            k.tampilkanInformasi();
            totalPengeluaranGaji += k.hitungTotalGaji();
        }

        System.out.printf("TOTAL PENGELUARAN GAJI: Rp %,.2f%n", totalPengeluaranGaji);
    }

    private static void cariKaryawan() {
        System.out.println("\n--- Pencarian Karyawan ---");
        System.out.print("Masukkan ID Karyawan yang dicari: ");
        String idCari = scanner.nextLine().trim();

        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(idCari)) {
                System.out.println("\n[Data Ditemukan]");
                System.out.println("----------------------------------------");
                k.tampilkanInformasi();
                return;
            }
        }

        System.out.println("\n[Informasi] Karyawan dengan ID '" + idCari + "' tidak ditemukan.");
    }

    private static void cekIdDuplikat(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) {
                throw new IllegalArgumentException("ID Karyawan '" + id + "' sudah digunakan oleh karyawan lain!");
            }
        }
    }
}