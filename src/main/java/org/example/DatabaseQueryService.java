package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private Database database;

    public DatabaseQueryService(Database database) {
        this.database = database;
    }


    public static void main(String[] args) {
        Database database = new Database();
        DatabaseQueryService queryService = new DatabaseQueryService(database);
        List<DatabaseQueryService.MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();
        System.out.println(maxProjectCountClients);
    }

    public class MaxProjectCountClient {
        private String name;
        private int projectCount;

        public MaxProjectCountClient(String name, int projectCount) {
            this.name = name;
            this.projectCount = projectCount;
        }

        public String getName() {
            return name;
        }

        public int getProjectCount() {
            return projectCount;
        }

        public String toString() {
            return " " + name + " " + projectCount + '\n';
        }
    }

    public class MaxSalaryWorker {
        private String name;
        private int projectCount;

        public MaxSalaryWorker(String name, int projectCount) {
            this.name = name;
            this.projectCount = projectCount;
        }

        public String getName() {
            return name;
        }

        public int getProjectCount() {
            return projectCount;
        }

        public String toString() {
            return " " + name + " " + projectCount + '\n';
        }
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = readSQLFromFile("sql/find_max_projects_client.sql");
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                Integer projectCount = resultSet.getInt("PROJECT_COUNT");
                if(projectCount != null) {
                    MaxProjectCountClient client = new MaxProjectCountClient(name, projectCount);
                    maxProjectCountClients.add(client);
                }
            }
            System.out.println(maxProjectCountClients.size());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxProjectCountClients;
    }
    public List<MaxProjectCountClient> findMaxSalaryWorker() {
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = readSQLFromFile("sql/find_max_salary_worker.sql");
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int projectCount = resultSet.getInt("PROJECT_COUNT");
                MaxProjectCountClient client = new MaxProjectCountClient(name, projectCount);
                maxProjectCountClients.add(client);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxProjectCountClients;
    }

    private String readSQLFromFile(String filename) throws IOException {
        Path filePath = Path.of(filename);
        return Files.readString(filePath);
    }
}