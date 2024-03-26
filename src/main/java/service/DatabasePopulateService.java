package service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try {
            Connection con = Database.getInstance().getConnection();
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/populate_db.sql")));

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error populating database", e);
        }
    }
}

