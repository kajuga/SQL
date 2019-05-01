package itvdn_jdbc.lesson_006;

import java.io.IOException;
import java.sql.*;

public class Lesson6 {
    static String url = "jdbc:mysql://localhost:3306/first_lesson";
    static String userName = "root";
    static String pass = "pass";

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        try (Connection connection = DriverManager.getConnection(url, userName, pass);
             Statement stat = connection.createStatement()) {
            //отключаю автоматической фиксации изменений
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            stat.executeUpdate("UPDATE Books SET price = 100 WHERE book_id = 1");
            new OtherTransaction().start();
            Thread.sleep(2000);
            connection.rollback();
        }
    }

    static class OtherTransaction extends Thread {
        @Override
        public void run() {
            try (Connection connection = DriverManager.getConnection(url, userName, pass);
                 Statement stat = connection.createStatement()) {
                //отключаю автоматической фиксации изменений
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                ResultSet rs = stat.executeQuery("SELECT * FROM Books");
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " " + rs.getDouble(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}