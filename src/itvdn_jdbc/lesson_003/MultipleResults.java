package itvdn_jdbc.lesson_003;

import java.io.IOException;
import java.sql.*;

public class MultipleResults {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String userName = "root";
        String pass = "pass";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, userName, pass)) {
            CallableStatement callableStatement = null;
            try {
                callableStatement = conn.prepareCall("{CALL tablesCount}");
                boolean hasResults = callableStatement.execute();
                ResultSet resultSet = null;
                try {
                    while (hasResults) {
                        resultSet = callableStatement.getResultSet();
                        while (resultSet.next()) {
                            System.out.println("Количество записей в таблицу " + resultSet.getInt(1));

                        }
                        hasResults = callableStatement.getMoreResults();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    ex.printStackTrace();
                } finally {
                    if (resultSet != null) {
                        resultSet.close();
                    } else {
                        System.err.println("Ошибка чтения БД!!");
                    }
                }
            } catch (SQLException exx) {
                exx.printStackTrace();
            } finally {
                callableStatement.close();
            }
        }
    }
}