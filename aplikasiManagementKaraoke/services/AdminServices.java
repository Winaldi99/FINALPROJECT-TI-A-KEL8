package aplikasiManagementKaraoke.services;

import aplikasiManagementKaraoke.entities.Lagu;
import aplikasiManagementKaraoke.entities.Ruangan;

import java.util.ArrayList;

public interface AdminServices{
    ArrayList<Ruangan> getAllRuangan();
    void deleteRuangan();
    void editRuangan();
    void deleteLagu();
    void editLagu();
    void addRuangan();
    ArrayList<Lagu> getAllLagu();
    void addDaftarLaguRuangan(int idRuangan);
    void addLagu();
    Lagu cariLagu(String namaLagu);
}
