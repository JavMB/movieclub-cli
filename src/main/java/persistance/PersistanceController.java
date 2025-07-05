package persistance;

import persistance.entity.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersistanceController {
    private int movieCount = 0;
    private String dbUrl = "jdbc:postgresql://localhost:5432/movies";
    private String dbUser = "root";
    private String dbPassword = "1234";

    public int getMovieCount() {
        return movieCount;
    }


    public void saveMovie(Movie movie) {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
