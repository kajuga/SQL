package itvdn_jdbc.lesson_002;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String password = "pass";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, userName, password);
        BufferedReader sqlFile = new BufferedReader(new FileReader("/home/kajuga/projects/SQL/src/itvdn_jdbc/lesson_002/approach.sql"));
        Scanner scan = new Scanner(sqlFile);
        Statement statement = connection.createStatement()) {

            String line = "";
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (line.endsWith(";")) {
                    line = line.substring(0, line.length() - 1);
                }
                statement.executeUpdate(line);
            }

            ResultSet rs = null;
            try {
                rs = statement.executeQuery("SELECT * FROM Books");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    double price = rs.getDouble(3);
                    System.out.println("Id = " + id + " name = " + name + " price = " + price);
                }

            } catch (SQLException ex) {
                System.err.println("SQLException message " + ex.getMessage());
                System.err.println("SQLExceprion SQL state " + ex.getSQLState());
                System.err.println("SQLException error code " + ex.getErrorCode());
            } finally {
                if(rs != null) {
                    rs.close();
                } else {
                    System.err.println("Ошибка чтения данных с БД!");
                }
            }
        }
    }
}



