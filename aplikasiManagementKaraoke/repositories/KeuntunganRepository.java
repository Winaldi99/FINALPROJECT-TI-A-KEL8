package aplikasiManagementKaraoke.repositories;

import aplikasiManagementKaraoke.entities.Keuntungan;

import java.util.ArrayList;

public interface KeuntunganRepository {
    ArrayList<Keuntungan> fetchKeuntungan();
    void saveKeuntungan(Keuntungan barisKeuntungan);
}
