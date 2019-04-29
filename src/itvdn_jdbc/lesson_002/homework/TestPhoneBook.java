package itvdn_jdbc.lesson_002.homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestPhoneBook {
    public static void main(String[] args) throws SQLException, IOException {
        List<Abonent> abonents = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String pass = "pass";
//        Class.forName("com.mysql.jdbc.Driver");

        String line = "";
        try (Connection connection = DriverManager.getConnection(url, userName, pass);
        Statement stat = connection.createStatement();
        BufferedReader fileSQL = new BufferedReader(new FileReader("/home/kajuga/projects/SQL/src/itvdn_jdbc/lesson_002/homework/phonebook.sql"));
        Scanner scanner = new Scanner(fileSQL)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.endsWith(";")) {
                    line = line.substring(0, line.length() -1);
                }
                stat.executeUpdate(line);
            }

            ResultSet resultSet = null;
            try {
                resultSet = stat.executeQuery("SELECT * FROM Phonebook");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    int num = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    abonents.add(new Abonent(id, num, name));
                }
                for (Abonent a : abonents) {
                    if (a.getId() > 3) {
                        System.out.println(a);
                    }
                }

            } catch (SQLException ex){
                System.err.println("SQLException message: " + ex.getMessage());
                System.err.println("SQLException SQL state: " + ex.getSQLState());
                System.err.println("SQLException SQL error code: " + ex.getErrorCode());

                // Освобождаем ресурсы затраченные на ResultSet
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                } else {
                    System.err.println("Ошибка чтения данных с БД");
                }
            }
        }
    }
}
