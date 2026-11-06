package src;

public class KaryawanTetap extends Karyawan {
    private double bonusPerforma;
    private double tunjanganKesehatan;

    public KaryawanTetap(String id, String nama, double gajiPokok, double bonusPerforma, double tunjanganKesehatan) {
        super(id, nama, gajiPokok);
        setBonusPerforma(bonusPerforma);
        setTunjanganKesehatan(tunjanganKesehatan);
    }

    public double getBonusPerforma() { return bonusPerforma; }
    public void setBonusPerforma(double bonusPerforma) {
        if (bonusPerforma < 0) throw new IllegalArgumentException("Bonus tidak boleh negatif");
        this.bonusPerforma = bonusPerforma;
    }

    public double getTunjanganKesehatan() { return tunjanganKesehatan; }
    public void setTunjanganKesehatan(double tunjanganKesehatan) {
        if (tunjanganKesehatan < 0) throw new IllegalArgumentException("Tunjangan tidak boleh negatif");
        this.tunjanganKesehatan = tunjanganKesehatan;
    }

    @Override
    public double hitungTotalGaji() {
        return getGajiPokok() + bonusPerforma + tunjanganKesehatan;
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.printf("Status        : Karyawan Tetap%nBonus Performa: Rp %,.2f%nTunjangan Kes : Rp %,.2f%nTotal Gaji    : Rp %,.2f%n----------------------------------------%n", 
            bonusPerforma, tunjanganKesehatan, hitungTotalGaji());
    }
}