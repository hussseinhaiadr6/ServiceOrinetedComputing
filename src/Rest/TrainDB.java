package Rest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainDB {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL)) {
            System.out.println("Connected to the database");

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO train VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                // Train 1
                addTrain(preparedStatement, "T001", "StationA", "StationB", "2023-01-01", "08:00", "10:00",
                        "2023-01-01", "30,15,60","140,150,180");

                // Train 2
                addTrain(preparedStatement, "T002", "StationB", "StationC", "2023-01-01", "10:00", "12:00",
                        "2023-01-01", "40,20,80","140,150,180");

                // Train 3
                addTrain(preparedStatement, "T003", "StationC", "StationD", "2023-01-02", "12:00", "14:00",
                        "2023-01-02", "60,40,120","140,150,180");

                // Train 3
                addTrain(preparedStatement, "T004", "StationB", "StationA", "2023-02-02", "12:00", "14:00",
                        "2023-01-02", "60,40,120","140,150,180");

                System.out.println("Three trains added to the database");

                // Display the trains
                displayTrains(connection);
            }

        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    private static void addTrain(PreparedStatement preparedStatement, String trainId, String departureStation,
                                 String arrivalStation, String departure_date, String departure_time,String arrival_time,
                                 String arrival_date, String seats,String fares)
            throws SQLException {
    	System.out.println(trainId+ departureStation+arrivalStation+departure_date+departure_time+arrival_time+ arrival_date+ seats+fares);
        preparedStatement.setString(1, trainId);
        preparedStatement.setString(2, departureStation);
        preparedStatement.setString(3, arrivalStation);
        preparedStatement.setString(4, departure_date);
        preparedStatement.setString(5, departure_time);
        preparedStatement.setString(6, arrival_date);
        preparedStatement.setString(7, ""); // Placeholder for arrival_time, as it's not provided in the input
        String[] seatArray = seats.split(",");
        for(String e : seatArray) {
        	System.out.println(e);
        }
        String[] fareArray = fares.split(",");
        preparedStatement.setInt(8, Integer.parseInt(seatArray[0]));
        preparedStatement.setInt(9, Integer.parseInt(seatArray[1]));
        preparedStatement.setInt(10, Integer.parseInt(seatArray[2]));
        preparedStatement.setDouble(11, Double.parseDouble(fareArray[0]));
        preparedStatement.setDouble(12, Double.parseDouble(fareArray[1]));
        preparedStatement.setDouble(13, Double.parseDouble(fareArray[2]));

        preparedStatement.executeUpdate();
    }

    private static void displayTrains(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM train";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("\nTrain Data:");

            while (resultSet.next()) {
                System.out.println("Train ID: " + resultSet.getString("trainId"));
                System.out.println("Departure Station: " + resultSet.getString("departureStation"));
                System.out.println("Arrival Station: " + resultSet.getString("arrivalStation"));
                System.out.println("Departure Date: " + resultSet.getString("departure_date"));
                System.out.println("Departure Time: " + resultSet.getString("departure_time"));
                System.out.println("Arrival Date: " + resultSet.getString("arrival_date"));
                System.out.println("Arrival Time: " + resultSet.getString("arrival_time"));
                System.out.println("First Class Seats: " + resultSet.getInt("firstClassSeats"));
                System.out.println("Business Class Seats: " + resultSet.getInt("businessClassSeats"));
                System.out.println("Standard Class Seats: " + resultSet.getInt("standardClassSeats"));
                System.out.println("First Class Fares: " + resultSet.getDouble("firstClassFares"));
                System.out.println("Business Class Fares: " + resultSet.getDouble("businessClassFares"));
                System.out.println("Standard Class Fares: " + resultSet.getDouble("standardClassFares"));
                System.out.println("-------------------------------------------");
            }
        }
    }
}
