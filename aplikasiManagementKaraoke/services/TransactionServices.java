package aplikasiManagementKaraoke.services;

import aplikasiManagementKaraoke.entities.Keuntungan;
import aplikasiManagementKaraoke.entities.Keuntungan;

import java.util.ArrayList;

public interface TransactionServices {
    void pesanRuangan();
    void checkoutRuangan();
    ArrayList<Keuntungan> getAllKeuntungan();
}