package itvdn_jdbc.lesson_001;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Создание подключения
public class TestConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/first_lesson?serverTimezone=EST5EDT";
        String userName = "kajuga";
        String pass = "ancd@1234";
//        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(url, userName, pass)) {
            System.out.println("Connection Successful");

        }
    }
}