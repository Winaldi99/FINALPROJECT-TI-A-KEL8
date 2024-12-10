package aplikasiManagementKaraoke.repositories;

import aplikasiManagementKaraoke.entities.Ruangan;

import java.util.ArrayList;

public interface RuanganRepository {
    ArrayList<Ruangan> fetchRuangan();
    void addRuangan(Ruangan ruangan);
    void updateRuangan(Ruangan ruangan);
    void deleteRuangan(Ruangan ruangan);

}
