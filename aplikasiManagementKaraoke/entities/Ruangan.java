package aplikasiManagementKaraoke.entities;

public class Ruangan {
    boolean statusRuangan;
    int harga;
    int idRuangan;
    String tipeRuangan;

    public boolean isStatusRuangan() {
        return statusRuangan;
    }

    public void setStatusRuangan(boolean statusRuangan) {
        this.statusRuangan = statusRuangan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getTipeRuangan() {
        return tipeRuangan;
    }

    public void setTipeRuangan(String tipeRuangan) {
        this.tipeRuangan = tipeRuangan;
    }
}
