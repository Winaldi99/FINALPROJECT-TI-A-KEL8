package aplikasiManagementKaraoke.entities;

public class Lagu {
    private String nama;
    private String judul;
    int idLagu;
    int idRuangan;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getIdLagu() {
        return idLagu;
    }

    public void setIdLagu(int idLagu) {
        this.idLagu = idLagu;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }
}
