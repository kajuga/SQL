package itvdn_jdbc.lesson_005;

import java.io.IOException;
import java.sql.*;

public class Lesson51 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String pass = "pass";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, userName, pass);
             Statement stat = connection.createStatement()) {
            String createTable = "CREATE TABLE Fruit (name VARCHAR (15) NOT NULL, amount INTEGER, price DOUBLE NOT NULL, PRIMARY KEY(name))";
            String command1 = "INSERT INTO Fruit (name, amount, price) VALUES('apple', 200, 3.50)";
            String command2 = "INSERT INTO Fruit (name, amount, price) VALUES('orange', 50, 5.50)";
            String command3 = "INSERT INTO Fruit (name, amount, price) VALUES('lemon', 30, 3.50)";
            String command4 = "INSERT INTO Fruit (name, amount, price) VALUES('pineapple', 20, 7.50)";

            connection.setAutoCommit(true);
            stat.addBatch(createTable);
            stat.addBatch(command1);
            stat.addBatch(command2);
            stat.addBatch(command3);
            stat.addBatch(command4);
            stat.executeBatch();
        }
    }
}