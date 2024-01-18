package Rest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseCreator {

    private static final String JDBC_URL = "jdbc:sqlite:C:\\Users\\User\\OneDrive\\Desktop\\mydatabase.db";

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            System.out.println("Connected to the database");

            try (Statement statement = connection.createStatement()) {
                // Drop the 'train' table if it exists
                statement.executeUpdate("DROP TABLE IF EXISTS train");

                // Create the 'train' table
                String createTableSQL = "CREATE TABLE train (" +
                        "trainId TEXT PRIMARY KEY," +
                        "departureStation TEXT NOT NULL," +
                        "arrivalStation TEXT NOT NULL," +
                        "departure_date TEXT NOT NULL," +
                        "departure_time TEXT NOT NULL," +
                        "arrival_date TEXT NOT NULL," +
                        "arrival_time TEXT NOT NULL," +
                        "firstClassSeats INTEGER NOT NULL," +
                        "businessClassSeats INTEGER NOT NULL," +
                        "standardClassSeats INTEGER NOT NULL," +
                        "firstClassFares REAL NOT NULL," +
                        "businessClassFares REAL NOT NULL," +
                        "standardClassFares REAL NOT NULL)";

                statement.executeUpdate(createTableSQL);

                System.out.println("Table 'train' created");
            }

        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
}
