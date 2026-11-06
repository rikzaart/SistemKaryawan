package src;

public class Karyawan {
    private String id;
    private String nama;
    private double gajiPokok;

    public Karyawan(String id, String nama, double gajiPokok) {
        this.id = id;
        this.nama = nama;
        setGajiPokok(gajiPokok);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getGajiPokok() { return gajiPokok; }
    public void setGajiPokok(double gajiPokok) {
        if (gajiPokok < 0) throw new IllegalArgumentException("Gaji pokok tidak boleh negatif");
        this.gajiPokok = gajiPokok;
    }

    public double hitungTotalGaji() { return gajiPokok; }

    public void tampilkanInformasi() {
        System.out.printf("ID Karyawan   : %s%nNama Karyawan : %s%nGaji Pokok    : Rp %,.2f%n", id, nama, gajiPokok);
    }
}