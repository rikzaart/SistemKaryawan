package src;

public class KaryawanKontrak extends Karyawan {
    private int lamaKontrakBulan;
    private double potonganPph;

    public KaryawanKontrak(String id, String nama, double gajiPokok, int lamaKontrakBulan, double potonganPph) {
        super(id, nama, gajiPokok);
        setLamaKontrakBulan(lamaKontrakBulan);
        setPotonganPph(potonganPph);
    }

    public int getLamaKontrakBulan() { return lamaKontrakBulan; }
    public void setLamaKontrakBulan(int lamaKontrakBulan) {
        // Perbaikan Poin 4: Durasi kontrak harus > 0
        if (lamaKontrakBulan <= 0) throw new IllegalArgumentException("Lama kontrak harus lebih dari 0 bulan");
        this.lamaKontrakBulan = lamaKontrakBulan;
    }

    public double getPotonganPph() { return potonganPph; }
    public void setPotonganPph(double potonganPph) {
        if (potonganPph < 0) throw new IllegalArgumentException("Potongan PPh tidak boleh negatif");
        if (potonganPph > getGajiPokok()) throw new IllegalArgumentException("Potongan PPh tidak boleh melebihi gaji pokok");
        this.potonganPph = potonganPph;
    }

    @Override
public void setGajiPokok(double gajiPokok) {
    if (gajiPokok < this.potonganPph) {
        throw new IllegalArgumentException("Gaji pokok baru tidak boleh lebih kecil dari potongan PPh yang ada");
    }
    super.setGajiPokok(gajiPokok);
}

    @Override
    public double hitungTotalGaji() {
        return getGajiPokok() - potonganPph;
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.printf("Status        : Karyawan Kontrak%nLama Kontrak  : %d Bulan%nPotongan PPh  : Rp %,.2f%nTotal Gaji    : Rp %,.2f%n----------------------------------------%n", 
            lamaKontrakBulan, potonganPph, hitungTotalGaji());
    }
}