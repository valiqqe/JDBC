package service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        try {
            Connection con = Database.getInstance().getConnection();
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/init_db.sql")));
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }
}