package aplikasiManagementKaraoke.views;

import aplikasiManagementKaraoke.entities.Keuntungan;
import aplikasiManagementKaraoke.entities.Lagu;
import aplikasiManagementKaraoke.entities.Ruangan;
import aplikasiManagementKaraoke.services.AdminServicesImpl;
import aplikasiManagementKaraoke.services.TransactionServicesImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class TerminalView implements Menus{
    private Scanner input = new Scanner(System.in);
    private AdminServicesImpl adminServices;
    private TransactionServicesImpl transactionServices;

    public TerminalView(AdminServicesImpl adminServices, TransactionServicesImpl transactionServices) {
        this.adminServices = adminServices;
        this.transactionServices = transactionServices;
    }

    @Override
    public void run(){
        mainMenus();
    }

    private void mainMenus(){
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("-- SISTEM MANAJEMEN KARAOKE --");
            System.out.println("1. Tambah Ruangan");
            System.out.println("2. Lihat Daftar Ruangan");
            System.out.println("3. Pesan Ruangan");
            System.out.println("4. Tambah Lagu");
            System.out.println("5. Cari Lagu");
            System.out.println("6. Tambah Lagu ke Daftar Putar Ruangan");
            System.out.println("7. Lihat Daftar Putar Ruangan");
            System.out.println("8. Selesai Menggunakan Ruangan");
            System.out.println("9. Lihat Total Pendapatan");
            System.out.println("10. Laporan Penggunaan Ruangan");
            System.out.println("11. Delete Ruangan");
            System.out.println("12. Edit Ruangan");
            System.out.println("13. Edit Lagu");
            System.out.println("14. Delete Lagu");
            System.out.println("15. Lihat Daftar Lagu");
            System.out.println("0. E X I T");
            System.out.print("Masukan Pilihan Anda : ");
            int selectedMenu = input.nextInt();
            input.nextLine();
            switch (selectedMenu) {
                case 1:
                    adminServices.addRuangan();
                    break;
                case 2:
                    break;
                case 3:
                    transactionServices.pesanRuangan();
                    break;
                case 4:
                    adminServices.addLagu();
                    break;
                case 5:
                    cariLagu();
                    break;
                case 6:
                    tambahDaftarPutar();
                    break;
                case 7:
                    lihatDaftarPutarRuangan();
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Menu Yang Anda Masukan Tidak Tersedia !");
            }
        }
    }

    private void lihatDaftarPutarRuangan() {
        showDaftarRuangan();
        System.out.print("Masukan Id Ruangan : ");
        showDaftarLaguRuangan(input.nextInt());
        input.nextLine();

    }

    private void tambahDaftarPutar() {
        showDaftarRuangan();
        System.out.print("Masukan Id Ruangan : ");
        adminServices.addDaftarLaguRuangan(input.nextInt());
        input.nextLine();
    }

    private void cariLagu() {
        System.out.print("Masukan Nama Lagu Yang Ingin Di Cari : ");
        Lagu lagu = adminServices.cariLagu(input.nextLine());
        if (lagu.getJudul() == null){
            System.out.println("Maaf Lagu Tidak Tersedia");
        }else {
            System.out.println("Berikut Adalah Data Dari Lagu Tersebut : ");
            System.out.println("Judul : " + lagu.getJudul() + "\nPenyanyi : " + lagu.getNama() + "\nId Lagu : " + lagu.getIdLagu() + "\nId Ruangan : " + lagu.getIdRuangan());
        }
    }



}
