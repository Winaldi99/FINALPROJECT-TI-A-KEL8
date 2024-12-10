package aplikasiManagementKaraoke.repositories;

import aplikasiManagementKaraoke.config.Database;
import aplikasiManagementKaraoke.entities.Keuntungan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class KeuntunganRepositoryImpl implements KeuntunganRepository{
    private final Database database;

    public KeuntunganRepositoryImpl(Database database) {
        this.database = database;
    }


    @Override
    public ArrayList<Keuntungan> fetchKeuntungan() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM keuntungan";
        ArrayList<Keuntungan> listKeuntungan = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Keuntungan barisKeuntungan = new Keuntungan();

                barisKeuntungan.setPenghasilan(resultSet.getInt("penghasilan"));
                barisKeuntungan.setDate(resultSet.getString("tanggal"));
                listKeuntungan.add(barisKeuntungan);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listKeuntungan;
    }

    @Override
    public void saveKeuntungan(Keuntungan barisKeuntungan) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO keuntungan(penghasilan, tanggal) VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, barisKeuntungan.getPenghasilan());
            preparedStatement.setString(2, barisKeuntungan.getDate());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ketungan Recorded successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
