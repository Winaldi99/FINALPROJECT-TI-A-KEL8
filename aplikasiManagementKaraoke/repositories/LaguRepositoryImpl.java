package aplikasiManagementKaraoke.repositories;

import aplikasiManagementKaraoke.config.Database;
import aplikasiManagementKaraoke.entities.Lagu;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class LaguRepositoryImpl implements LaguRepository{

    private final Database database;

    public LaguRepositoryImpl(Database database) {
        this.database = database;
    }


    @Override
    public ArrayList<Lagu> fetchLagu() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM lagu";
        ArrayList<Lagu> listLagu = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lagu lagu = new Lagu();
                lagu.setJudul(resultSet.getString("judul"));
                lagu.setNama(resultSet.getString("nama"));
                lagu.setIdRuangan(resultSet.getInt("id_ruangan"));
                lagu.setIdLagu(resultSet.getInt("id"));

                listLagu.add(lagu);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listLagu;
    }

    @Override
    public void addLagu(Lagu lagu) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO lagu(judul, nama, id_ruangan) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, lagu.getJudul());
            preparedStatement.setString(2, lagu.getNama());
            preparedStatement.setInt(3, lagu.getIdRuangan());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Lagu added successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateLagu(Lagu lagu) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE lagu SET judul = ?, nama = ?, id_ruangan = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, lagu.getJudul());
            preparedStatement.setString(2, lagu.getNama());
            preparedStatement.setInt(3, lagu.getIdRuangan());
            preparedStatement.setInt(4, lagu.getIdLagu());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Lagu updated successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteLagu(Lagu lagu) {
        Connection connection = database.getConnection();
        String sqlStatement = "DELETE FROM lagu WHERE id = ?";
        System.out.println(lagu.getNama());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, lagu.getIdLagu());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Lagu deleted successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
