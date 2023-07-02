package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    private static final String DB_URL = "jdbc:h2:file:C:/data/INIT_DB";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";
    private static final String SQL_FILE_PATH = "D:\\untitled1\\all.sql\\init_db.sql";

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            executeSqlFile(connection, SQL_FILE_PATH);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    private static void executeSqlFile(Connection connection, String filePath) {
        try {
            Path path = Paths.get(filePath);
            String sqlScript = new String(Files.readAllBytes(path));

            Statement statement = connection.createStatement();
            statement.execute(sqlScript);
            statement.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}