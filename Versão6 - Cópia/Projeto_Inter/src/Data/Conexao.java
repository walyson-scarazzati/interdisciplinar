package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/venda_de_titulos?useSSL=false&createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // Method to establish and return a new connection
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); // Load the JDBC driver
        return DriverManager.getConnection(URL, USER, PASSWORD); // Create a new connection
    }

    // Method to close the connection
    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
