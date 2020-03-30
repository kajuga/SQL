package itvdn_jdbc.lesson_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String url = "jdbc:postgresql://localhost:5432/first_lesson";
        String name = "kajuga";
        String password = "sashok";

        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(url, name, password);
             BufferedReader sqFile = new BufferedReader(new FileReader("/home/kajuga/projects/SQL/src/main/java/itvdn_jdbc/books.sql"));
             Scanner scan = new Scanner(sqFile);
             Statement statement = connection.createStatement()){
            String line="";
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if(line.endsWith(";")) {
                    line = line.substring(0, line.length() - 1);

                }
                statement.executeUpdate(line);
            }

            ResultSet rs = null;
            try {
                rs = statement.executeQuery("SELECT * FROM Books");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String bookName = rs.getString(2);
                    double price = rs.getDouble(3);
                    System.out.println("Id= " + id + " name= " + bookName + " price= " + price);
                }
            } catch (SQLException ex) {
                System.err.println("SQLException message: " + ex.getMessage());
                System.err.println("SQLException SQL state: " + ex.getSQLState());
                System.err.println("SQLException error code: " + ex.getErrorCode());
            }finally {
                if(rs !=null) {
                    rs.close();
                } else {
                    System.out.println("Error reading from BD");
                }
            }
        }
    }
}
