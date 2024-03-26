package service;

import tasks.LongestProject;
import tasks.MaxProjectCountClient;
import tasks.Worker;
import tasks.ProjectPrice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.sql.PreparedStatement;

public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String filePath = "/Users/valentindidok/IdeaProjects/JDBC/src/main/resources/find_max_projects_client.sql";

        try {
            String sql = new String(Files.readAllBytes(Paths.get(filePath)));
            Database database = Database.getInstance();
            Connection connection = database.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                List<MaxProjectCountClient> clients = new ArrayList<>();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int projectCount = resultSet.getInt("project_count");
                    MaxProjectCountClient client = new MaxProjectCountClient(name, projectCount);
                    clients.add(client);
                }

                for (MaxProjectCountClient client : clients) {
                    System.out.println("Name: " + client.getName());
                    System.out.println("Project Count: " + client.getProjectCount());
                    System.out.println();
                }

                return clients;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return Collections.emptyList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


   public List<LongestProject> findLongestProject() {
        String filePath = "/Users/valentindidok/IdeaProjects/JDBC/src/main/resources/find_longest_project.sql";

        try {
            String sql = readSqlFile(filePath);
            Database database = Database.getInstance();
            Connection connection = database.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                List<LongestProject> projects = new ArrayList<>();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int monthCount = resultSet.getInt("MONTH_COUNT");
                    LongestProject project = new LongestProject(id, monthCount);
                    projects.add(project);
                }

                for (LongestProject project : projects) {
                    System.out.println("ID: " + project.getId());
                    System.out.println("Month Count: " + project.getMonthCount());
                    System.out.println();
                }

                return projects;
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }




    public List<Worker> findYoungestAndEldestWorkers() {
        String filePath = "/Users/valentindidok/IdeaProjects/JDBC/src/main/resources/find_youngest_eldest_workers.sql";

        try {
            String sql = readSqlFile(filePath);
            Database database = Database.getInstance();
            Connection connection = database.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                List<Worker> workers = new ArrayList<>();

                while (resultSet.next()) {
                    String type = resultSet.getString("TYPE");
                    String name = resultSet.getString("NAME");
                    String birthday = resultSet.getString("BIRTHDAY");
                    Worker worker = new Worker(type, name, birthday);
                    workers.add(worker);
                }

                for (Worker worker : workers) {
                    System.out.println("Type: " + worker.getType());
                    System.out.println("Name: " + worker.getName());
                    System.out.println("Birthday: " + worker.getBirthday());
                    System.out.println();
                }

                return workers;

            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    public List<Worker> findMaxSalaryWorker() {
        String filePath = "/Users/valentindidok/IdeaProjects/JDBC/src/main/resources/find_max_salary_worker.sql";

        try {
            String sql = readSqlFile(filePath);
            Database database = Database.getInstance();
            Connection connection = database.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                List<Worker> workers = new ArrayList<>();

                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    double salary = resultSet.getDouble("SALARY");

                    Worker worker = new Worker(name, salary);
                    workers.add(worker);
                }

                for (Worker worker : workers) {
                    System.out.println("Name: " + worker.getName());
                    System.out.println("Salary: " + worker.getSalary());
                    System.out.println();
                }

                return workers;

            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }



    public List<ProjectPrice> printProjectPrices() {
        try {
            String sql = readSqlFile("/Users/valentindidok/IdeaProjects/JDBC/src/main/resources/print_project_prices.sql");
            Database database = Database.getInstance();
            Connection connection = database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<ProjectPrice> projectPrices = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double price = resultSet.getDouble("PRICE");

                ProjectPrice projectPrice = new ProjectPrice(id, price);
                projectPrices.add(projectPrice);
            }

            for (ProjectPrice projectPrice : projectPrices) {
                System.out.println("ID: " + projectPrice.getId());
                System.out.println("Price: " + projectPrice.getPrice());
                System.out.println();
            }

            return projectPrices;

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private String readSqlFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }
}