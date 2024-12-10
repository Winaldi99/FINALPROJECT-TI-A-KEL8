package aplikasiManagementKaraoke.repositories;

import aplikasiManagementKaraoke.config.Database;
import aplikasiManagementKaraoke.entities.Ruangan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class RuanganRepositoryImpl implements RuanganRepository{

    private final Database database;

    public RuanganRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Ruangan> fetchRuangan() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM ruangan";
        ArrayList<Ruangan> listRuangan = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ruangan barisRuangan = new Ruangan();
                barisRuangan.setIdRuangan(resultSet.getInt("id"));
                barisRuangan.setTipeRuangan(resultSet.getString("tipe_ruangan"));
                barisRuangan.setHarga(resultSet.getInt("harga"));
                barisRuangan.setStatusRuangan(resultSet.getBoolean("status"));


                listRuangan.add(barisRuangan);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listRuangan;
    }

    @Override
    public void addRuangan(Ruangan ruangan) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO ruangan(tipe_ruangan, harga, status) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, ruangan.getTipeRuangan());
            preparedStatement.setInt(2, ruangan.getHarga());
            preparedStatement.setBoolean(3, ruangan.isStatusRuangan());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ruangan added successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateRuangan(Ruangan ruangan) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE ruangan SET tipe_ruangan = ?, harga = ?, status = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, ruangan.getTipeRuangan());
            preparedStatement.setInt(2, ruangan.getHarga());
            preparedStatement.setBoolean(3, ruangan.isStatusRuangan());
            preparedStatement.setInt(4, ruangan.getIdRuangan());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ruangan updated successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteRuangan(Ruangan ruangan) {
        Connection connection = database.getConnection();
        String sqlStatement = "DELETE FROM ruangan WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, ruangan.getIdRuangan());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ruangan deleted successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
