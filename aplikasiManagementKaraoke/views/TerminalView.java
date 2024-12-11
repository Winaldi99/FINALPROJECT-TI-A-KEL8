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
                    showDaftarRuangan();
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
                    showDaftarRuangan();
                    transactionServices.checkoutRuangan();
                    break;
                case 9:
                    showPendapatan();
                    break;
                case 10:
                    showTotalPenggunaanRuangan();
                    break;
                case 11:
                    showDaftarRuangan();
                    adminServices.deleteRuangan();
                    break;
                case 12:
                    showDaftarRuangan();
                    adminServices.editRuangan();
                    break;
                case 13:
                    showDaftarLagu();
                    adminServices.editLagu();
                    break;
                case 14:
                    showDaftarLagu();
                    adminServices.deleteLagu();
                    break;
                case 15:
                    showDaftarLagu();
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

    private void showPendapatan(){
        int nums = 1;
        int totalPendapatan = 0;
        ArrayList<Keuntungan> keuntunganList = transactionServices.getAllKeuntungan();
        System.out.printf("%-5s %-10s %-15s%n", "No.", "Tanggal", "Pendapatan");
        for (Keuntungan i : keuntunganList) {
            System.out.printf("%-5d %-10s %-15d%n", nums, i.getDate(), i.getPenghasilan());
            totalPendapatan += i.getPenghasilan();
            nums++;
        }

        if (nums == 1) {
            System.out.println("Masih Belum Ada Pendapatan Terdaftar !");
        } else {
            System.out.printf("Total Penghasilan : %d%n", totalPendapatan);
        }
    }

    private void showTotalPenggunaanRuangan(){
        Map<String,Integer> totalRuangan = new HashMap<>();
        Map<String,Integer> totalRuanganTerpakai = new HashMap<>();

        ArrayList<Ruangan>ruanganList = adminServices.getAllRuangan();

        for (Ruangan i : ruanganList){
            if (totalRuangan.containsKey(i.getTipeRuangan())){
                int tmp = totalRuangan.get(i.getTipeRuangan());
                tmp++;
                totalRuangan.put(i.getTipeRuangan(),tmp);
            }else {
                totalRuangan.put(i.getTipeRuangan(),1);
            }

            if (totalRuanganTerpakai.containsKey(i.getTipeRuangan())){
                if (i.isStatusRuangan()){
                    int tmp = totalRuanganTerpakai.get(i.getTipeRuangan());
                    tmp++;
                    totalRuanganTerpakai.put(i.getTipeRuangan(),tmp);
                }
            }else {
                totalRuanganTerpakai.put(i.getTipeRuangan(),0);
            }
        }

        System.out.println("Daftar Penggunaan Ruangan");
        for (Map.Entry<String,Integer> i : totalRuanganTerpakai.entrySet()){
            System.out.println("Ruangan Bertipe : " + i.getKey() + ", Sebanyak " + i.getValue() + " Terpakai Dari " + totalRuangan.get(i.getKey()));
        }

    }

    private void showDaftarRuangan(){
        int nums = 1;
        ArrayList<Ruangan> ruanganList = adminServices.getAllRuangan();
        System.out.printf("%-5s %-15s %-10s %-10s %-10s%n", "No.", "Tipe", "Harga", "Id", "Status");
        for (Ruangan i : ruanganList) {
            System.out.printf("%-5d %-15s %-10d %-10s %-10s%n", nums, i.getTipeRuangan(), i.getHarga(), i.getIdRuangan(), getStatus(i.isStatusRuangan()));
            nums++;
        }
        if (nums == 1) {
            System.out.println("Daftar Ruangan Masih Belum Tersedia");
        }

    }

    private void showDaftarLagu(){
        int nums = 1;
        ArrayList<Lagu> laguList = adminServices.getAllLagu();
        System.out.printf("%-5s %-30s %-20s%n", "No.", "Judul", "Penyanyi");
        System.out.println("----------------------------------------------------------");
        for (Lagu i : laguList) {
            System.out.printf("%-5d %-30s %-20s%n", nums, i.getJudul(), i.getNama());
            nums++;
        }
        if (nums == 1) {
            System.out.println("Daftar Lagu Masih belum Tersedia");
        }
    }

    private void showDaftarLaguRuangan(int idRuangan){
        int nums = 1;
        ArrayList<Lagu> laguList = adminServices.getAllLagu();
        System.out.printf("%-5s %-30s %-20s%n", "No.", "Judul", "Penyanyi");
        System.out.println("----------------------------------------------------------");
        for (Lagu i : laguList) {
            if (idRuangan == i.getIdRuangan()){
                System.out.printf("%-5d %-30s %-20s%n", nums, i.getJudul(), i.getNama());
                nums++;
            }
        }
        if (nums == 1) {
            System.out.println("Daftar Lagu Masih belum Tersedia");
        }
    }

    private String getStatus(boolean statusRuangan){
        if(statusRuangan)return "Di Pesan";
        return "Tersedia";
    }

}
