package aplikasiManagementKaraoke.services;

import aplikasiManagementKaraoke.entities.Keuntungan;
import aplikasiManagementKaraoke.entities.Ruangan;
import aplikasiManagementKaraoke.repositories.KeuntunganRepository;
import aplikasiManagementKaraoke.repositories.RuanganRepository;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Component
public class TransactionServicesImpl implements TransactionServices{
    private KeuntunganRepository keuntunganRepository;
    private RuanganRepository ruanganRepository;
    private Scanner input = new Scanner(System.in);


    public TransactionServicesImpl(KeuntunganRepository keuntunganRepository, RuanganRepository ruanganRepository) {
        this.keuntunganRepository = keuntunganRepository;
        this.ruanganRepository = ruanganRepository;
    }

    @Override
    public void pesanRuangan() {
        System.out.print("Masukan Id Ruangan Yang Ingin Di Pesan : ");
        Ruangan ruangan = cariRuangan(input.nextInt());
        if (!ruangan.isStatusRuangan()  && ruangan.getTipeRuangan() != null){
            ruangan.setStatusRuangan(true);
            ruanganRepository.updateRuangan(ruangan);
        }else {
            System.out.println("Ruangan Tidak Tersedia");
        }
    }


    private Ruangan cariRuangan(int idRuangan){
        ArrayList<Ruangan> listRuangan = ruanganRepository.fetchRuangan();
        for (Ruangan i : listRuangan){
            if(idRuangan == i.getIdRuangan()){
                return i;
            }
        }
        return new Ruangan();
    }

    @Override
    public ArrayList<Keuntungan> getAllKeuntungan() {
        return keuntunganRepository.fetchKeuntungan();
    }



}
