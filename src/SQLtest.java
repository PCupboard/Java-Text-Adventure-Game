import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLtest {

    private static final String URL = "jdbc:mysql://localhost:3306/java_game";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static final String QUERY = "SELECT * FROM items";

    public static void main(String[] args) throws SQLException {
        try (final var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            final var statement = connection.createStatement()) {
                final var resultSet = statement.executeQuery(QUERY);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }
        }
    }
}
