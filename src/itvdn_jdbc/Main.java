package itvdn_jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/itvdn_jdbc";
        String userName = "root";
        String pass = "xxx";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, userName, pass);
             BufferedReader sqFile = new BufferedReader(new FileReader("/home/kajuga/projects/SQL/src/itvdn_jdbc/approach.sql"));
             Scanner scanner = new Scanner(sqFile);
             Statement statement = connection.createStatement()){
            String line = "";
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
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
                    Double price = rs.getDouble(3);
                    System.out.println("Id= "  + id + " name= " + name + " price= " + price);
                }
            } catch (SQLException ex) {
                System.err.println("SQLEception message: " + ex.getMessage());
                System.err.println("SQLException SQL state: " + ex.getSQLState());
                System.err.println("SQLExcepton error code" + ex.getErrorCode());
            } finally {
                if (rs !=null) {
                    rs.close();
                } else {
                    System.out.println("Ошибка чтения данных с БД");
                }
            }

        }
        }

}
