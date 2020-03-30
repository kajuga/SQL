package itvdn_jdbc.lesson_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:postgresql://localhost:5432/first_lesson";
        String name = "kajuga";
        String password = "sashok";

        Class.forName("org.postgresql.Driver");

        try (Connection conn = DriverManager.getConnection(url, name, password)) {
            System.out.println("Connection Successfull!");
        }

    }
}
