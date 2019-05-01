package itvdn_jdbc.lesson_005;

import java.io.IOException;
import java.sql.*;

public class Lesson5 {

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

            connection.setAutoCommit(false);

            stat.executeUpdate(createTable);
            stat.executeUpdate(command1);
            Savepoint spt = connection.setSavepoint();
            stat.executeUpdate(command2);
            stat.executeUpdate(command3);
            stat.executeUpdate(command4);

//            connection.commit();
            connection.rollback(spt);
            connection.commit();
            connection.releaseSavepoint(spt);






        }


    }
}

