package config;
import java.sql.*;
import java.util.Properties;


public class DBConnection {
    final String url = "jdbc:postgresql://localhost:5432/coffeeshop";
    final Properties props = new Properties();

    public Connection getConnection() throws SQLException{
        props.setProperty("user", "postgres");
        props.setProperty("password", "123");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println(conn.getMetaData().getDatabaseProductVersion());
        return conn;
    }
}
