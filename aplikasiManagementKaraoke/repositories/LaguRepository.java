package aplikasiManagementKaraoke.repositories;

import aplikasiManagementKaraoke.entities.Lagu;

import java.util.ArrayList;

public interface LaguRepository {
    ArrayList<Lagu> fetchLagu();
    void addLagu(Lagu lagu);
    void updateLagu(Lagu lagu);
    void deleteLagu(Lagu lagu);
}
