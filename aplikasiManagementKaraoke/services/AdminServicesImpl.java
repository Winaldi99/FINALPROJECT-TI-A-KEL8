package aplikasiManagementKaraoke.services;

import aplikasiManagementKaraoke.entities.Lagu;
import aplikasiManagementKaraoke.entities.Ruangan;
import aplikasiManagementKaraoke.repositories.LaguRepository;
import aplikasiManagementKaraoke.repositories.RuanganRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class AdminServicesImpl implements AdminServices{
    private LaguRepository laguRepository;
    private RuanganRepository ruanganRepository;
    private Scanner input = new Scanner(System.in);


    public AdminServicesImpl(LaguRepository laguRepository, RuanganRepository ruanganRepository) {
        this.laguRepository = laguRepository;
        this.ruanganRepository = ruanganRepository;
    }

    @Override
    public void addLagu() {
        Lagu lagu = new Lagu();
        System.out.print("Masukan Judul Lagu : ");
        lagu.setJudul(input.nextLine());
        System.out.print("Masukan Nama Penyanyi : ");
        lagu.setNama(input.nextLine());

        laguRepository.addLagu(lagu);
    }

    @Override
    public void addDaftarLaguRuangan(int idRuangan) {
        System.out.print("Masukan Judul Lagu : ");
        Lagu lagu = cariLagu(input.nextLine());
        lagu.setIdRuangan(idRuangan);
        laguRepository.updateLagu(lagu);
    }

    @Override
    public Lagu cariLagu(String namaLagu){
        ArrayList<Lagu> listLagu = getAllLagu();
        for (Lagu i : listLagu){
            if(namaLagu.equals(i.getJudul())){
                return i;
            }
        }
        return new Lagu();
    }

    @Override
    public void editLagu() {

        System.out.print("Masukan Judul Lagu : ");
        Lagu lagu = cariLagu(input.nextLine());
        System.out.print("Masukan Judul Baru Lagu : ");
        lagu.setJudul(input.nextLine());
        System.out.print("Masukan Nama Penyanyi Baru : ");
        lagu.setNama(input.nextLine());

        laguRepository.updateLagu(lagu);
    }

    @Override
    public void deleteLagu() {
        System.out.print("Masukan Judul Lagu : ");
        Lagu lagu = cariLagu(input.nextLine());
        laguRepository.deleteLagu(lagu);
    }

    @Override
    public ArrayList<Lagu> getAllLagu() {
        return laguRepository.fetchLagu();
    }

    @Override
    public void addRuangan() {
        Ruangan ruangan = new Ruangan();
        System.out.print("Masukan Tipe Ruangan : ");
        ruangan.setTipeRuangan(input.next());
        System.out.print("Masukan Harga : ");
        ruangan.setHarga(input.nextInt());
        input.nextLine();

        ruangan.setStatusRuangan(false);

        ruanganRepository.addRuangan(ruangan);
    }



    private Ruangan cariRuangan(int idRuangan){
        ArrayList<Ruangan> listRuangan = getAllRuangan();
        for (Ruangan i : listRuangan){
            if(idRuangan == i.getIdRuangan()){
                return i;
            }
        }
        return new Ruangan();
    }

    @Override
    public void editRuangan() {

        System.out.print("Masukan Id Ruangan : ");
        Ruangan ruangan = cariRuangan(input.nextInt());
        input.nextLine();
        System.out.print("Masukan Tipe Ruangan : ");
        ruangan.setTipeRuangan(input.nextLine());
        System.out.print("Masukan Harga : ");
        ruangan.setHarga(input.nextInt());
        input.nextLine();


        ruanganRepository.updateRuangan(ruangan);
    }

    @Override
    public void deleteRuangan() {
        System.out.print("Masukan Id Ruangan : ");
        Ruangan ruangan = cariRuangan(input.nextInt());

        ruanganRepository.deleteRuangan(ruangan);
    }

    @Override
    public ArrayList<Ruangan> getAllRuangan() {
        return ruanganRepository.fetchRuangan();
    }
}
